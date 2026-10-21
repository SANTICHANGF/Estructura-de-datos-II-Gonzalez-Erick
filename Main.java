/**
 * Guía 5. BST II: eliminación de nodos.
 *
 * Se prueban de forma independiente los tres casos:
 * 1. eliminación de una hoja;
 * 2. eliminación de un nodo con un hijo;
 * 3. eliminación de un nodo con dos hijos.
 *
 * El tercer caso elimina la raíz, cumpliendo además
 * el ejercicio adicional de la guía.
 */
public class Main {

    public static void main(String[] args) {

        probarCasoHoja();
        probarCasoUnHijo();
        probarCasoDosHijosYRaiz();
        probarValorInexistente();
    }

    // ==========================================================
    // CASO 1: ELIMINAR UNA HOJA
    // ==========================================================

    private static void probarCasoHoja() {

        System.out.println(
                "========================================"
        );
        System.out.println(
                "CASO 1: ELIMINAR NODO HOJA"
        );
        System.out.println(
                "========================================"
        );

        ArbolBST arbol = crearArbolBase();

        System.out.println(
                "\nANTES de eliminar 15:"
        );
        arbol.imprimirArbol();

        System.out.println(
                "\nInorden antes:"
        );
        arbol.mostrarInorden();

        arbol.eliminar(15);

        System.out.println(
                "\nDESPUÉS de eliminar 15:"
        );
        arbol.imprimirArbol();

        System.out.println(
                "\nInorden después:"
        );
        arbol.mostrarInorden();

        System.out.println(
                "¿BST válido?: "
                        + arbol.esBSTValido()
        );

        System.out.println(
                "¿15 existe?: "
                        + arbol.buscar(15)
        );

        System.out.println();
    }

    // ==========================================================
    // CASO 2: ELIMINAR UN NODO CON UN HIJO
    // ==========================================================

    private static void probarCasoUnHijo() {

        System.out.println(
                "========================================"
        );
        System.out.println(
                "CASO 2: ELIMINAR NODO CON UN HIJO"
        );
        System.out.println(
                "========================================"
        );

        /*
         * Se agrega 60 como hijo derecho de 55.
         *
         * Antes:
         *
         *        65
         *       /
         *      55
         *        \
         *         60
         *
         * Al eliminar 55, la referencia de 65
         * debe pasar directamente a 60.
         */
        ArbolBST arbol = crearArbolConNodoDeUnHijo();

        System.out.println(
                "\nANTES de eliminar 55:"
        );
        arbol.imprimirArbol();

        System.out.println(
                "\nInorden antes:"
        );
        arbol.mostrarInorden();

        arbol.eliminar(55);

        System.out.println(
                "\nDESPUÉS de eliminar 55:"
        );
        arbol.imprimirArbol();

        System.out.println(
                "\nInorden después:"
        );
        arbol.mostrarInorden();

        System.out.println(
                "¿BST válido?: "
                        + arbol.esBSTValido()
        );

        System.out.println(
                "¿55 existe?: "
                        + arbol.buscar(55)
        );

        System.out.println(
                "¿60 sigue existiendo?: "
                        + arbol.buscar(60)
        );

        System.out.println();
    }

    // ==========================================================
    // CASO 3: ELIMINAR UN NODO CON DOS HIJOS
    // Y, AL MISMO TIEMPO, ELIMINAR LA RAÍZ
    // ==========================================================

    private static void probarCasoDosHijosYRaiz() {

        System.out.println(
                "========================================"
        );
        System.out.println(
                "CASO 3: DOS HIJOS / ELIMINAR RAÍZ"
        );
        System.out.println(
                "========================================"
        );

        ArbolBST arbol = crearArbolBase();

        System.out.println(
                "\nANTES de eliminar la raíz 45:"
        );
        arbol.imprimirArbol();

        System.out.println(
                "\nInorden antes:"
        );
        arbol.mostrarInorden();

        /*
         * 45 tiene dos hijos.
         * Su sucesor inorden es el mínimo
         * del subárbol derecho: 55.
         */
        int sucesor =
                arbol.minValor(
                        arbol.getRaiz().getDerecho()
                );

        System.out.println(
                "\nSucesor inorden de 45: "
                        + sucesor
        );

        arbol.eliminar(45);

        System.out.println(
                "\nDESPUÉS de eliminar la raíz 45:"
        );
        arbol.imprimirArbol();

        System.out.println(
                "\nInorden después:"
        );
        arbol.mostrarInorden();

        System.out.println(
                "Nueva raíz: "
                        + arbol.getRaiz().getDato()
        );

        System.out.println(
                "¿BST válido?: "
                        + arbol.esBSTValido()
        );

        System.out.println(
                "¿45 existe?: "
                        + arbol.buscar(45)
        );

        System.out.println();
    }

    // ==========================================================
    // PRUEBA EXTRA: VALOR INEXISTENTE
    // ==========================================================

    private static void probarValorInexistente() {

        System.out.println(
                "========================================"
        );
        System.out.println(
                "PRUEBA EXTRA: ELIMINAR VALOR INEXISTENTE"
        );
        System.out.println(
                "========================================"
        );

        ArbolBST arbol = crearArbolBase();

        int antes = arbol.contarNodos();

        arbol.eliminar(999);

        int despues = arbol.contarNodos();

        System.out.println(
                "Nodos antes: " + antes
        );

        System.out.println(
                "Nodos después: " + despues
        );

        System.out.println(
                "¿BST válido?: "
                        + arbol.esBSTValido()
        );
    }

    // ==========================================================
    // ÁRBOLES DE PRUEBA
    // ==========================================================

    /**
     * BST base:
     *
     *          45
     *        /    \
     *      25      65
     *     /  \    /  \
     *   15   35  55   85
     */
    private static ArbolBST crearArbolBase() {

        int[] valores = {
                45, 25, 65, 15, 35, 55, 85
        };

        ArbolBST arbol = new ArbolBST();

        for (int valor : valores) {
            arbol.insertar(valor);
        }

        return arbol;
    }

    /**
     * BST con un nodo que tiene exactamente un hijo:
     *
     *          45
     *        /    \
     *      25      65
     *     /  \    /  \
     *   15   35  55   85
     *               \
     *                60
     *
     * El nodo 55 tiene un único hijo: 60.
     */
    private static ArbolBST crearArbolConNodoDeUnHijo() {

        int[] valores = {
                45, 25, 65, 15,
                35, 55, 85, 60
        };

        ArbolBST arbol = new ArbolBST();

        for (int valor : valores) {
            arbol.insertar(valor);
        }

        return arbol;
    }
}
