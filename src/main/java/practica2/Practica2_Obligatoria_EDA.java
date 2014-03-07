package practica2;

import java.util.ArrayList;
import java.util.List;
import material.tree.Position;
import material.tree.binarytree.arraybinarytree.ArrayBinaryTree;

public class Practica2_Obligatoria_EDA {

    private static ArrayBinaryTree<Double> arbolDecision;
    private static Informe alumno;

    private static void generarArbolDecision(ArrayBinaryTree<Double> arbolDecision) {

        //Nivel 0 (Asistencia).

        Position<Double> a = arbolDecision.addRoot(0.8);

        //Nivel 1 (Hoja de problemas).

        Position<Double> b = arbolDecision.insertRight(a, 5.0);
        Position<Double> c = arbolDecision.insertLeft(a, 7.0);

        //Nivel 2 (Examen).

        Position<Double> d = arbolDecision.insertRight(b, 5.0);
        Position<Double> e = arbolDecision.insertLeft(b, 7.0);
        Position<Double> f = arbolDecision.insertRight(c, 6.0);
        Position<Double> g = arbolDecision.insertLeft(c, -1.0);

        //Nivel 3 (Ejercicios voluntarios).

        Position<Double> h = arbolDecision.insertRight(d, 10.0);
        Position<Double> i = arbolDecision.insertLeft(d, -1.0);
        Position<Double> j = arbolDecision.insertRight(e, 10.0);
        Position<Double> k = arbolDecision.insertLeft(e, -1.0);
        Position<Double> l = arbolDecision.insertRight(f, 10.0);
        Position<Double> m = arbolDecision.insertLeft(f, -1.0);

        //Nivel 4 (Nota?).

        Position<Double> n = arbolDecision.insertRight(h, 0.0);
        Position<Double> o = arbolDecision.insertLeft(h, 0.0);
        Position<Double> p = arbolDecision.insertRight(j, 0.0);
        Position<Double> q = arbolDecision.insertLeft(j, 0.0);
        Position<Double> r = arbolDecision.insertRight(l, 0.0);
        Position<Double> s = arbolDecision.insertLeft(l, 0.0);

    }

    private static void evaluarInforme(ArrayBinaryTree<Double> arbolDecision, Informe alumno) {

        List<Double> listaNotas = (List<Double>) Practica2_Obligatoria_EDA.generarListaNotas(alumno);

        List<Double> condiciones = new ArrayList<>();

        boolean[] superadas = new boolean[4];

        boolean suspenso = false;

        evaluarInformeAux(arbolDecision, arbolDecision.root(), listaNotas, 0, 0.0,
                suspenso, condiciones, superadas);
    }

    private static void evaluarInformeAux(ArrayBinaryTree<Double> arbolDecision,
            Position<Double> posicionActual, List<Double> listaNotas, int nivel, double notaFinal,
            boolean suspenso, List<Double> condiciones, boolean[] superadas) {

        double condicion = posicionActual.element();
        if (condicion == -1.0) {
            suspenso = true;
            mostrarNota(notaFinal, suspenso, condiciones, superadas);
        } else if (condicion == 0) {
            mostrarNota(notaFinal, suspenso, condiciones, superadas);
        } else {
            condiciones.add(condicion);
            if (listaNotas.get(nivel) >= condicion) {
                superadas[nivel] = true;
                notaFinal += incrementoNota(nivel, listaNotas.get(nivel));
                evaluarInformeAux(arbolDecision, arbolDecision.right(posicionActual), listaNotas, nivel + 1,
                        notaFinal, suspenso, condiciones, superadas);
            } else {
                notaFinal += incrementoNota(nivel, listaNotas.get(nivel));
                evaluarInformeAux(arbolDecision, arbolDecision.left(posicionActual), listaNotas, nivel + 1,
                        notaFinal, suspenso, condiciones, superadas);
            }

        }

    }

    private static Iterable<Double> generarListaNotas(Informe alumno) {

        List<Double> lista = new ArrayList<>();

        lista.add(alumno.getAsistencia());
        lista.add(alumno.getProblemas());
        lista.add(alumno.getExamen());
        lista.add(alumno.getVoluntarios());

        return lista;

    }

    private static void mostrarNota(double notaFinal, boolean suspenso,
            List<Double> condiciones, boolean[] superadas) {

        String calificacion = (suspenso) ? "SUSPENSO" : "APROBADO";

        System.out.println("\nEl alumno con expediente " + alumno.getExpediente()
                + " ha obtenido la calificación (" + notaFinal + ") y esta " + calificacion + ".\n");
        System.out.println("Informe:");

        double minimo;

        try {

            minimo = condiciones.get(0);

            String superada = (superadas[0]) ? "Prueba superada" : "Prueba NO superada";

            System.out.println("Asistencia. " + superada + ": " + alumno.getAsistencia() + " ("
                    + minimo + ")");

            minimo = condiciones.get(1);

            superada = (superadas[1]) ? "Prueba superada" : "Prueba NO superada";

            System.out.println("Hoja de problemas. " + superada + ": " + alumno.getProblemas() + " ("
                    + minimo + ")");

            minimo = condiciones.get(2);

            superada = (superadas[2]) ? "Prueba superada" : "Prueba NO superada";

            System.out.println("Examen. " + superada + ": " + alumno.getExamen() + " ("
                    + minimo + ")");

            minimo = condiciones.get(3);

            superada = (superadas[3]) ? "Prueba superada" : "Prueba NO superada";

            System.out.println("Ejercicios voluntarios. " + superada + ": " + alumno.getVoluntarios() + " ("
                    + minimo + ")\n");

        } catch (IndexOutOfBoundsException ex) {

            System.out.println("\nEl alumno no pudo realizar algunas pruebas por no superar "
                    + "las anteriores.\n");

        }

    }

    private static double incrementoNota(int categoria, double nota) {

        double incremento;

        switch (categoria) {

            case 0:
                incremento = (nota >= 0.8) ? 1.0 : 0.0;
                break; //Asistencia.
            case 1:
                incremento = nota * 0.3;
                break; //Hojas.
            case 2:
                incremento = nota * 0.5;
                break; //Examen.
            case 3:
                incremento = nota * 0.1;
                break; //Voluntarios.
            default:
                incremento = 0;

        }

        return incremento;

    }

    public static void main(String[] args) {
        arbolDecision = new ArrayBinaryTree<>();
        generarArbolDecision(arbolDecision);
        alumno = new Informe(343, 0.9, 7.0, 4.0, 10.0);
        evaluarInforme(arbolDecision, alumno);
    }

}
