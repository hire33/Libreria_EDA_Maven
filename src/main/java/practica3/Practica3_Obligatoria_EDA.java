package practica3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import material.maps.Entry;
import material.maps.HashTableMapLP;
import material.maps.Map;

public class Practica3_Obligatoria_EDA {
    
    /**
     * Con las tablas hash evitamos complejidades del tipo O(n^2).
     */
            
    public static void main(String[] args) {
        
        List <Noticia> listaNoticias = generarListaNoticias(getNews()); 
        
        //Ejercicio solicitado para la práctica.
        
        imprimirAnalisisEMN(analisisEMN(listaNoticias));
        
        //Muestra por pantalla de la lista de noticias.
        
//        for(Noticia n : listaNoticias){
//            System.out.println(n + "\n");
//        }
            
    }
    
    public static String leerTitulo(String nombre) {
        File f;
        FileReader lectorArchivo;
        try {
            f = new File(nombre);
            lectorArchivo = new FileReader(f);
            String l = "";
            try ( BufferedReader br = new BufferedReader(lectorArchivo) ) {
                String aux;
                aux = br.readLine();
                if (aux != null) {
                    l = l + aux;
                } 
            }
            lectorArchivo.close();
            return l;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
 
    public static String leer(String nombre) {
        File f;
        FileReader lectorArchivo;
        try {
            f = new File(nombre);
            lectorArchivo = new FileReader(f);
            String l = "";
            try ( BufferedReader br = new BufferedReader(lectorArchivo) ) {
                String aux;
                while (true) {
                    aux = br.readLine();
                    if (aux != null) {
                        l = l + aux + "\n";
                    } else {
                        break;
                    }
                }
            }
            lectorArchivo.close();
            return l;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    private static Noticia tratarNoticia(String path, Map <String,String> stopListMap) {

        /**
         * Creamos una tabla hash donde guardaremos el nombre de cada una de las
         * entidades (clave) y las veces que aparece en la noticia (valor).
         */
        
        Map<String, Integer> crawlingMap;
        crawlingMap = new HashTableMapLP(479);
         
        String[] listaPalabras = leer(path).split(" ");
        Entidad eMN = new Entidad ();
        int maxFrecuencia = 0;
        
        for(int i=0; i<listaPalabras.length; i++){
            
            String palabraActual = listaPalabras[i];
            
            //Comprobamos si la palabra empieza por mayuscula.
            
            if(Character.isUpperCase(palabraActual.charAt(0))){ 
                
                //Comprobamos si la palabra esta en la tabla stopList.
                         
                String toCompare = palabraActual.toLowerCase();
                String stopListElement = stopListMap.get(toCompare);
                
                //Si no esta, seguimos avanzando.
                
                if(stopListElement==null){ 
                    
                    //Buscamos la palabra en la tabla hash.
                    
                    Integer frec = crawlingMap.get(palabraActual);
                    
                    if(frec!=null){ //Si esta, aumentamos su frecuencia.
                        frec++;
                    } else { //Si no, la inicializamos.
                        frec = 1;
                    }
                    
                    /**
                     * Comprobamos si la palabra estudiada podría ser nuestra
                     * entidad mas nombrada.
                     */
                    
                    if(frec>=maxFrecuencia){
                        eMN.setNombre(palabraActual);
                        eMN.setFrecuencia(frec);
                        maxFrecuencia=frec;
                    }
                    
                    //Actualizamos la tabla hash.
                    
                    crawlingMap.put(palabraActual, frec);
  
                }                
            }           
        }
              
        //Devolvemos la noticia ya tratada.
        
        Noticia toReturn = new Noticia(leerTitulo(path), generarListaEntidades(crawlingMap), eMN);
        
        return toReturn;
    
    }

    private static String getStopList() {
        return "C:" + File.separator + "Users" + File.separator + "Juan"
            + File.separator + "Desktop" + File.separator + "data" + File.separator +
            "ES_stopList.txt";    
    }
    
    private static String getNews() {
        return "C:" + File.separator + "Users" + File.separator + "Juan"
            + File.separator + "Desktop" + File.separator + "data" + File.separator +
            "news";    
    }

    private static Map <String,String> generarTablaStopList(String path) {
        String tiraStopList = leer(path);
        String[] stopList = tiraStopList.split(" ");
        Map <String, String> stopListMap = new HashTableMapLP (709); //Tamaño para evitar rehashing.
        for(int i=0; i<stopList.length; i++){
            stopListMap.put(stopList[i], stopList[i]);
        }
        return stopListMap;
    }

    private static List<Noticia> generarListaNoticias(String path) {
        
        List <Noticia> toReturn = new ArrayList<> ();
        Map<String, String> stopListMap = generarTablaStopList(getStopList());
        
        File f = new File (path);
        File[] children = f.listFiles();
        
        String news = (path + File.separator); 
        
        for(int j=0; j<children.length; j++){
            Noticia actual = tratarNoticia(news + children[j].getName(), stopListMap);
            toReturn.add(actual);
        }
        
        return toReturn;
        
        
    }

    private static List<Entidad> generarListaEntidades(Map<String, Integer> crawlingMap) {
        
        List <Entidad> listaEntidades = new ArrayList <> ();
        
        Iterable<Entry<String,Integer>> listaEntradas = crawlingMap.entrySet();
        
        for(Entry<String, Integer> v : listaEntradas){
            listaEntidades.add(new Entidad(v.getKey(), v.getValue()));
        }
        
        return listaEntidades;
        
    }

    private static Map<String, List<String>> analisisEMN(List<Noticia> listaNoticias) {
       
        /**
         * Creamos una tabla hash donde guardaremos el nombre de la entidad mas nombrada
         * (clave) y el titulo de las noticias donde esta entidad tiene dicha categoria
         * (valor).
         */
        
        Map<String, List<String>> outputMap = new HashTableMapLP (101);
        
        for (Noticia n : listaNoticias){
            
            String eMNActual = n.getEntidadMasNombrada().getNombre();
            String tituloActual = n.getTitulo();
            
            /**
             * Buscamos la entidad, si esta en la tabla, añadimos a su lista de titulos
             * el titulo actual, si no, añadimos la entidad a la tabla y hacemos lo
             * propio con el titulo actual.
             */
         
            List<String> listaTitulos = outputMap.get(eMNActual);            
            if(listaTitulos==null){
                listaTitulos = new ArrayList <> ();               
            } 
            listaTitulos.add(tituloActual);
            outputMap.put(eMNActual, listaTitulos);          
        }
        
        return outputMap;
             
    }

    private static void imprimirAnalisisEMN(Map<String, List<String>> analisisEMN) {
        
        Iterable<Entry<String,List<String>>> listaEntradas = analisisEMN.entrySet();
        
        for(Entry<String, List<String>> v : listaEntradas){
            System.out.println(v.getKey());
            for(String s : v.getValue()){
                System.out.println("*[" + s + "]");
            }
        }
        
    }
    
}
