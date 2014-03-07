package practica1;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import material.tree.LCRSTree.LCRSTree;
import material.tree.Position;
import material.tree.exceptions.InvalidPositionException;

public class Practica1_Obligatoria_EDA {

    private static LCRSTree<File> almacenador = new LCRSTree<>();
    
    private static void crearArbol(Position<File> v, LCRSTree<File> almacenador)
            throws InvalidPositionException {

        almacenador.checkPosition(v);
        File[] hijos = v.element().listFiles();

        if (hijos != null) {

            for (File f : hijos) {
                almacenador.add(v, f);
            }

            for (Position<File> w : almacenador.children(v)) {
                crearArbol(w, almacenador);
            }

        }

    }

    private static long tamañoParcial(Position<File> v, LCRSTree<File> almacenador)
            throws InvalidPositionException {
        
        //Basado en la idea del nodesInTree.

        almacenador.checkPosition(v);
        List<Position<File>> rec = new ArrayList<>();
        almacenador.preorderPositions(v, rec);
        
        Iterator<Position<File>> it = rec.iterator();
        long tamañoParcial = 0;
        
        for (Position<File> w : rec) {
            tamañoParcial += w.element().length();
        }
        
        return tamañoParcial;

    }
                      
    public static void main(String[] args) {
        
        long tamañoTotal = 0;
        String escritorio = "C:" + File.separator + "Users" + File.separator + "Juan"
            + File.separator + "Desktop" + File.separator;
        char df;
        
        /* Creación de un árbol que representará la estructura que hay por debajo
         * del directorio que le pasemos. */
        
        //"G:" + File.separator + "[Entretenimiento]"

        File prueba = new File(escritorio + "Carpeta1");
        Position<File> raiz = almacenador.addRoot(prueba);
        crearArbol(raiz, almacenador);

        /* Muestra por pantalla todos los elementos del árbol (directorios, d
         * o ficheros, f), el tamaño de cada uno de ellos y el tamaño total.
         */

        List<Position<File>> pos = (List<Position<File>>) almacenador.positions();
        Iterator<Position<File>> it = pos.iterator();

        while (it.hasNext()) {
            Position<File> aux = it.next();
            tamañoTotal += aux.element().length();
            df = aux.element().isDirectory() ? 'd' : 'f';
            System.out.println(aux.element().getName() + " (" + df + ") "
                    + tamañoParcial(aux, almacenador)/1024 + "KB.");
        }

        System.out.println("Tamaño total de " + prueba.getName() + " : " + tamañoTotal/1024 + "KB.");

    }

}

    

