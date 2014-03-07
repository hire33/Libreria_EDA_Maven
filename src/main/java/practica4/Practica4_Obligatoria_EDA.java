package practica4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import material.maps.Entry;
import material.maps.HashTableMapLP;
import material.maps.Map;
import material.ordereddictionary.OrderedDictionary;
import material.ordereddictionary.RBOrderedDictionary;

/**
 *
 * @author Asus
 */
public class Practica4_Obligatoria_EDA {

    private static final double PORCENTAJE_APROBADOS = 0.8;

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        List<Ciudad> listaCiudades = generarListaCiudades(getPathCiudades());
        List<String> listaCriterios = generarListaCriterios();
        List<Evaluacion> listaEvaluaciones = new ArrayList<>();

        BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;

        System.out.println("Recuerde...");
        System.out.println("\"area\": cualquier numero");
        System.out.println("\"aeropuertos\": cualquier numero entero");
        System.out.println("\"vias\": cualquier numero entero");
        System.out.println("\"transporte\": valoracion del 1 al 10");
        System.out.println("\"telecomunicaciones\": valoracion del 1 al 10");
        System.out.println("\"criminalidad\": un numero del 1 al 1000");
        System.out.println("\"desempleo\": porcentaje (0-100)");
        System.out.println("\"mortalidad\": porcentaje (0-100)");
        System.out.println("\"crecimiento\": porcentaje (0-100)");
        System.out.println("\"deuda\": cualquier numero");
        System.out.println("\"infraestructuras\": cualquier numero entero");
        System.out.println("\"competiciones\": cualquier numero entero");
        System.out.println("\"disciplinas\": cualquier numero entero");

        while (opcion != 4) {

            System.out.println("Bienvenido, miembro del comite, elija una opción:  ");
            System.out.println("1. Probar criterios.");
            System.out.println("2. Enviar criterios.");
            System.out.println("3. Mostrar ciudades candidatas.");
            System.out.println("4. Salir de la aplicación.");
            System.out.println("Numero de opción:  ");

            try {
                opcion = Integer.parseInt(lectura.readLine());
            } catch (IOException ex) {
            } catch (NumberFormatException ex) {
            }

        }

    }

    /**
     * Devuelve la carpeta donde estan almacenadas las ciudades.
     */
    private static String getPathCiudades() {



        return "C:" + File.separator + "Users" + File.separator + "Juan"
                + File.separator + "Desktop" + File.separator + "CiudadesEditadas";

    }

    /**
     * Covierte la carpeta que se le pasa como parametro, en una lista de
     * objetos Ciudad.
     */
    private static List<Ciudad> generarListaCiudades(String pathCiudades) {



        List<Ciudad> toReturn = new ArrayList<>();

        File f = new File(pathCiudades);
        File[] children = f.listFiles();

        String ciudades = (pathCiudades + File.separator);

        for (int j = 0; j < children.length; j++) {
            Ciudad actual = tratarCiudad(ciudades + children[j].getName());
            toReturn.add(actual);
        }

        return toReturn;

    }

    /**
     * Dada la ruta del fichero donde se almacenan los datos de una ciudad
     * genera el objeto Ciudad correspondiente.
     */
    private static Ciudad tratarCiudad(String path) {

        Map<String, Double> mapaCategorias = generarMapaCategorias(path);

        //Devolvemos la ciudad ya tratada.

        Ciudad toReturn = new Ciudad(leerTitulo(path), mapaCategorias);

        return toReturn;

    }

    /**
     * Extrae la primera linea de un fichero, en este caso, el nombre de la
     * ciudad.
     */
    public static String leerTitulo(String nombre) {

        File f;
        FileReader lectorArchivo;
        try {
            f = new File(nombre);
            lectorArchivo = new FileReader(f);
            String l = "";
            try (BufferedReader br = new BufferedReader(lectorArchivo)) {
                String aux;
                aux = br.readLine();
                if (aux != null) {
                    l += aux;
                }
            }
            lectorArchivo.close();
            return l;
        } catch (IOException e) {
        }
        return null;

    }

    /**
     * En este método se genera la tabla hash que tendra todo objeto Ciudad,
     * en esta guardamos el nombre de las categorias a evaluar y el valor
     * dado a cada una de estas.
     */
    public static Map<String, Double> generarMapaCategorias(String path) {

        Map<String, Double> mapaCategorias;
        mapaCategorias = new HashTableMapLP(29);

        File f;
        FileReader lectorArchivo;
        try {
            f = new File(path);
            lectorArchivo = new FileReader(f);
            try (BufferedReader br = new BufferedReader(lectorArchivo)) {
                String aux;
                int i = 0;
                while (i < 15) {
                    aux = br.readLine();
                    if (aux != null) {
                        if (i > 1) {
                            String[] prueba = aux.split(" ");
                            switch (i) {
                                case 2:
                                    mapaCategorias.put("area", Double.parseDouble(prueba[2]));
                                    break;
                                case 3:
                                    mapaCategorias.put("aeropuertos", Double.parseDouble(prueba[3]));
                                    break;
                                case 4:
                                    mapaCategorias.put("vias", Double.parseDouble(prueba[4]));
                                    break;
                                case 5:
                                    mapaCategorias.put("transporte", Double.parseDouble(prueba[7]));
                                    break;
                                case 6:
                                    mapaCategorias.put("telecomunicaciones", Double.parseDouble(prueba[6]));
                                    break;
                                case 7:
                                    mapaCategorias.put("criminalidad", Double.parseDouble(prueba[3]));
                                    break;
                                case 8:
                                    mapaCategorias.put("desempleo", tratarPorcentaje(prueba[3]));
                                    break;
                                case 9:
                                    mapaCategorias.put("mortalidad", tratarPorcentaje(prueba[3]));
                                    break;
                                case 10:
                                    mapaCategorias.put("crecimiento", tratarPorcentaje(prueba[6]));
                                    break;
                                case 11:
                                    mapaCategorias.put("deuda", Double.parseDouble(prueba[2]));
                                    break;
                                case 12:
                                    mapaCategorias.put("infraestructuras", Double.parseDouble(prueba[4]));
                                    break;
                                case 13:
                                    mapaCategorias.put("competiciones", Double.parseDouble(prueba[3]));
                                    break;
                                default:
                                    mapaCategorias.put("disciplinas", Double.parseDouble(prueba[2]));
                            }
                        }
                    } else {
                        break;
                    }
                    i++;
                }
            }
            lectorArchivo.close();
            return mapaCategorias;
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * Elimina el simbolo % de un String.
     */
    private static Double tratarPorcentaje(String sinTratar) {
        String[] division = sinTratar.split("%");
        Double toReturn = Double.parseDouble(division[0]);
        return toReturn;
    }

    /**
     * Devuelve una lista con los criterios elegidos para este año.
     */
    private static List<String> generarListaCriterios() {

        List<String> toReturn = new ArrayList<>();

        toReturn.add("telecomunicaciones");
        toReturn.add("transporte");
        toReturn.add("infraestructuras");

        return toReturn;

    }

    /**
     * Devuelve cierto si el criterio introducido está en la lista de
     * criterios elegidos para este año.
     */   
    private static boolean esCriterioValido(String criterioATratar, List<String> listaCriterios) {

        for (String v : listaCriterios) {
            if (v.equals(criterioATratar)) {
                return true;
            }
        }
        return false;

    }

    /**
     * Dada una lista de ciudades y un requisito, nos devuelve la lista de
     * las ciudades que cumplen con el requisito.
     */
    private static List<Ciudad> generarCiudadesAprobadas(List<Ciudad> listaCiudades, Requisito req) {

        OrderedDictionary<Double, Ciudad> diccionario = generarDiccionario(listaCiudades, req.getCriterio());

        List<Entry<Double, Ciudad>> listaEntradas =
                (List<Entry<Double, Ciudad>>) diccionario.findRange(req.getRangoInf(), req.getRangoSup());

        List<Ciudad> toReturn = new ArrayList<>();

        for (Entry<Double, Ciudad> e : listaEntradas) {
            toReturn.add(e.getValue());
        }

        return toReturn;

    }

    /**
     * Dada una lista de ciudades y un criterio, nos devuelve un diccionario
     * de ciudades ordenadas en función de dicho criterio.
     */
    private static OrderedDictionary<Double, Ciudad> generarDiccionario(List<Ciudad> listaCiudades, String criterio) {
    
        /**
         * He decidido implementar el diccionario con un árbol rojo-negro, 
         * debido al alto numero de inserciones.
         */
        
        OrderedDictionary<Double, Ciudad> toReturn = new RBOrderedDictionary<>();

        for (Ciudad c : listaCiudades) {
            Double valorCriterio = c.getMapaCategorias().get(criterio);
            toReturn.insert(valorCriterio, c);
        }

        return toReturn;

    }

    /**
     * Dada una lista con todas las evaluaciones (listas de requisitos
     * pertenecientes a cada uno de los miembros), se haya que ciudades
     * cumplen con al menos el 80% de los requisitos y, por tanto, salen
     * elegidas.
     */
    private static List<Ciudad> generarListaCiudadesCandidatas(List<Evaluacion> listaEvaluaciones, List<Ciudad> listaCiudades) {

        /**
         * Utilizo una tabla hash para ir guardando cuantos requisitos van
         * cumpliendo cada una de las ciudades.
         */
        
        Map<Ciudad, Integer> mapaDecision = new HashTableMapLP<>(79);

        int numRequisitos = 0;
        for (Evaluacion e : listaEvaluaciones) {
            numRequisitos += e.getListaRequisitos().size();
            for (Requisito r : e.getListaRequisitos()) {
                List<Ciudad> aprobaronR = generarCiudadesAprobadas(listaCiudades, r);
                for (Ciudad c : aprobaronR) {
                    Integer reqAprobados = mapaDecision.get(c);
                    if (reqAprobados != null) { //Si esta, aumentamos su frecuencia.
                        reqAprobados++;
                    } else { //Si no, la inicializamos.
                        reqAprobados = 1;
                    }
                    mapaDecision.put(c, reqAprobados);
                }
            }
        }
        List<Ciudad> toReturn = new ArrayList<>();
        for (Entry<Ciudad, Integer> e : mapaDecision.entrySet()) {
            if (e.getValue() >= Math.ceil(PORCENTAJE_APROBADOS * numRequisitos)) {
                toReturn.add(e.getKey());
            }
        }
        return toReturn;
    }

}
