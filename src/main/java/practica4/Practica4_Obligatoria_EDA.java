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

public class Practica4_Obligatoria_EDA {

    private static final double PORCENTAJE_APROBADOS = 0.8;

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
        System.out.print("\n");

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
                System.out.println("Error de lectura.");
            } catch (NumberFormatException ex) {
                System.out.println("Escriba un numero, por favor.");
            }

            switch (opcion) {

                case 1:                   
                    //<editor-fold defaultstate="collapsed" desc=" Probar criterios. ">
                    
                    String opcionSalida = "";

                    do {

                        String criterioATratar = "";
                        boolean esCriterioValido = false;
                        while (!esCriterioValido) {
                            System.out.println("Los criterios a evaluar este año son: ");
                            for (String criterio : listaCriterios) {
                                System.out.println(criterio);
                            }
                            System.out.println("Escriba con que criterio desea trabajar: ");
                            try {
                                criterioATratar = lectura.readLine();
                            } catch (IOException ex) {
                                System.out.println("El criterio esta mal escrito.");
                            }
                            esCriterioValido = esCriterioValido(criterioATratar, listaCriterios);
                            if (!esCriterioValido) {
                                System.out.println("El criterio escrito no esta en la lista.");
                                lectura.readLine();
                            }
                        }
                        System.out.println("El criterio elegido es: " + criterioATratar);

                        boolean parseado;
                        Double rangoInferior = 0.0;
                        
                        do {
                        
                            parseado=true;
                            System.out.println("Establezca el requisito minimo para el criterio: ");
                            try {
                                rangoInferior = Double.parseDouble(lectura.readLine());
                            } catch (IOException ex) {
                                System.out.println("Error de lectura.");
                            } catch (NumberFormatException ex) {
                                System.out.println("Escriba un numero, por favor.");
                                parseado=false;
                            }
                            
                            if(parseado){
                                System.out.println("El requisito minimo elegido es: " + rangoInferior);
                            }    
                            
                        } while(!parseado);
                        
                        Double rangoSuperior = 0.0;
                        
                        do {
                        
                            parseado=true;
                            System.out.println("Establezca el requisito maximo para el criterio: ");
                            try {
                                rangoSuperior = Double.parseDouble(lectura.readLine());
                            } catch (IOException ex) {
                                System.out.println("Error de lectura.");
                            } catch (NumberFormatException ex) {
                                System.out.println("Escriba un numero, por favor.");
                                parseado=false;
                            }
                            
                            if(parseado){
                                System.out.println("El requisito maximo elegido es: " + rangoSuperior);
                            }    
                            
                        } while(!parseado);

                        System.out.println("Procesando ciudades aprobadas...");
                        Requisito req = new Requisito(criterioATratar, rangoInferior, rangoSuperior);
                        List<Ciudad> listaElegidas = generarCiudadesAprobadas(listaCiudades, req);                      
                        if(listaElegidas.isEmpty()){
                            System.out.println("Ninguna ciudad ha aprobado.");
                        } else {
                            System.out.println("Las ciudades aprobadas son...");
                            for (Ciudad c : listaElegidas) {
                                System.out.print(c.getNombre() + ", " + c.getMapaCategorias().get(req.getCriterio()) + "; ");
                            }
                            System.out.print("\n");
                        }

                        do {

                            System.out.println("Desea realizar otra prueba? (s/n)");
                            try {
                                opcionSalida = lectura.readLine();
                            } catch (IOException ex) {
                                System.out.println("La opción esta mal escrita.");
                            }

                        } while (!((opcionSalida.equals("n")) || (opcionSalida.equals("s"))));

                    } while (opcionSalida.equals("s"));

                    //</editor-fold>
                    break;
                case 2:
                    //<editor-fold defaultstate="collapsed" desc=" Enviar criterios ">
                    
                    String opcionEnviarOtra = "";

                    do {

                        List<Requisito> listaRequisitos = new ArrayList<>();

                        System.out.println("Bien, vamos a rellenar la ficha que debera enviar al "
                                + "comite.");

                        for (String c : listaCriterios) {

                            System.out.println("Para el criterio \"" + c + "\"...");

                            boolean parseado;
                            Double rangoInferior = 0.0;

                            do {

                                parseado=true;
                                System.out.println("Establezca el requisito minimo: ");
                                try {
                                    rangoInferior = Double.parseDouble(lectura.readLine());
                                } catch (IOException ex) {
                                    System.out.println("Error de lectura.");
                                } catch (NumberFormatException ex) {
                                    System.out.println("Escriba un numero, por favor.");
                                    parseado=false;
                                }

                                if(parseado){
                                    System.out.println("El requisito minimo elegido es: " + rangoInferior);
                                }    

                            } while(!parseado);

                            Double rangoSuperior = 0.0;

                            do {

                                parseado=true;
                                System.out.println("Establezca el requisito maximo: ");
                                try {
                                    rangoSuperior = Double.parseDouble(lectura.readLine());
                                } catch (IOException ex) {
                                    System.out.println("Error de lectura.");
                                } catch (NumberFormatException ex) {
                                    System.out.println("Escriba un numero, por favor.");
                                    parseado=false;
                                }

                                if(parseado){
                                    System.out.println("El requisito maximo elegido es: " + rangoSuperior);
                                }    

                            } while(!parseado);

                            listaRequisitos.add(new Requisito(c, rangoInferior, rangoSuperior));

                        }

                        String opcionEnvio = "";

                        do {

                            System.out.println("Ha finalizado su tarea, desea enviar la ficha"
                                    + " al comite? (s/n)");
                            try {
                                opcionEnvio = lectura.readLine();
                            } catch (IOException ex) {
                                System.out.println("La opción esta mal escrita.");
                            }

                        } while (!((opcionEnvio.equals("n")) || (opcionEnvio.equals("s"))));

                        if (opcionEnvio.equals("s")) {
                            Evaluacion toSend = new Evaluacion(listaRequisitos);
                            listaEvaluaciones.add(toSend);
                            System.out.println("El comite recibio la ficha.");
                        } else {
                            break;
                        }

                        do {

                            System.out.println("Desea rellenar y enviar otra ficha? (s/n)");
                            try {
                                opcionEnviarOtra = lectura.readLine();
                            } catch (IOException ex) {
                                System.out.println("La opción esta mal escrita.");
                            }

                        } while (!((opcionEnviarOtra.equals("n")) || (opcionEnviarOtra.equals("s"))));

                    } while (opcionEnviarOtra.equals("s"));

                    System.out.println("Volviendo al menu...");

                    //</editor-fold>
                    break;
                case 3:
                    //<editor-fold defaultstate="collapsed" desc=" Mostrar ciudades candidatas ">
                    
                    if (listaEvaluaciones.isEmpty()) {
                        System.out.println("El comite no recibio ninguna ficha.");
                    } else {
                        List<Ciudad> listaCandidatas = generarListaCiudadesCandidatas(listaEvaluaciones, listaCiudades);
                        System.out.println("Procesando ciudades candidatas...");
                        if(listaCandidatas.isEmpty()){
                            System.out.println("Ninguna ciudad puede organizar los JJ00.");
                        } else {
                            System.out.println("Las ciudades candidatas a los JJOO son...");
                            for (Ciudad c : listaCandidatas) {
                                System.out.print(c.getNombre() + "; ");
                            }
                        }
                        System.out.print("\n");
                        opcion = 4; //FIN del programa.
                    }
                   
                    //</editor-fold>
                    break;
                case 4: System.out.println("Saliendo de la aplicacion..."); break;
                default: System.out.println("La opcion escrita no es valida.");   
            
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
            System.out.println("Error: " + e.getMessage());
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
