/**
 * Guía 4. BST I: inserción y búsqueda.
 * Compara un BST construido con datos distribuidos
 * frente a otro construido con datos ordenados.
 */
public class Main {

    public static void main(String[] args) {

        // ----------------------------------------------------------
        // EJERCICIO DE LA GUÍA: 45,25,65,15,35,55,85
        // ----------------------------------------------------------
        System.out.println("=== BST DE 7 NODOS ===");

        int[] valores = {45, 25, 65, 15, 35, 55, 85};
        ArbolBST bst = new ArbolBST();

        for (int v : valores) {
            bst.insertar(v);
        }

        bst.imprimirArbol();

        System.out.println("\nInorden:");
        bst.mostrarInorden();

        System.out.println("\nBuscar 55:");
        System.out.println(bst.buscarConTraza(55));

        System.out.println("\nBuscar 60:");
        System.out.println(bst.buscarConTraza(60));

        // ----------------------------------------------------------
        // 15 CÓDIGOS EN ORDEN RAZONABLEMENTE DISTRIBUIDO
        // ----------------------------------------------------------
        int[] distribuidos = {
            40, 20, 60, 10, 30,
            50, 70, 5, 15, 25,
            35, 45, 55, 65, 75
        };

        ArbolBST bstDistribuido = new ArbolBST();
        for (int v : distribuidos) {
            bstDistribuido.insertar(v);
        }

        // ----------------------------------------------------------
        // 15 CÓDIGOS YA ORDENADOS
        // ----------------------------------------------------------
        int[] ordenados = {
            5, 10, 15, 20, 25,
            30, 35, 40, 45, 50,
            55, 60, 65, 70, 75
        };

        ArbolBST bstOrdenado = new ArbolBST();
        for (int v : ordenados) {
            bstOrdenado.insertar(v);
        }

        int[] consultas = {5, 35, 65, 75, 80};

        System.out.println("\n=== DATOS DISTRIBUIDOS ===");
        int compDistribuido = probarBusquedas(bstDistribuido, consultas);

        System.out.println("\n=== DATOS ORDENADOS ===");
        int compOrdenado = probarBusquedas(bstOrdenado, consultas);

        // ----------------------------------------------------------
        // COMPARACIÓN FINAL
        // ----------------------------------------------------------
        System.out.println("\n=== COMPARACIÓN ===");
        System.out.println(
            "Altura distribuido: " + bstDistribuido.altura()
        );
        System.out.println(
            "Altura ordenado: " + bstOrdenado.altura()
        );
        System.out.println(
            "Comparaciones distribuidas: " + compDistribuido
        );
        System.out.println(
            "Comparaciones ordenadas: " + compOrdenado
        );

        System.out.printf(
            "Promedio distribuido: %.2f%n",
            compDistribuido / (double) consultas.length
        );

        System.out.printf(
            "Promedio ordenado: %.2f%n",
            compOrdenado / (double) consultas.length
        );

        System.out.println(
            "\nConclusión: insertar datos ya ordenados puede "
            + "degenerar el BST y acercar búsqueda/inserción a O(n)."
        );
    }

    private static int probarBusquedas(
            ArbolBST arbol,
            int[] consultas) {

        int total = 0;

        for (int valor : consultas) {
            ResultadoBusqueda r = arbol.buscarConTraza(valor);
            System.out.println("Buscar " + valor + " -> " + r);
            total += r.getComparaciones();
        }

        return total;
    }
}
