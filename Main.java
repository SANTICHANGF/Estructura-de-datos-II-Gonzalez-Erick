/**
 * Laboratorio "Mi primer árbol binario" (Guía 3).
 * Árbol de 7 nodos con enteros, construido a mano enlazando referencias.
 */
public class Main {

    public static void main(String[] args) {

        // 1. Crear los siete nodos (genérico con Integer).
        NodoArbol<Integer> n50 = new NodoArbol<>(50);
        NodoArbol<Integer> n30 = new NodoArbol<>(30);
        NodoArbol<Integer> n70 = new NodoArbol<>(70);
        NodoArbol<Integer> n60 = new NodoArbol<>(60);
        NodoArbol<Integer> n90 = new NodoArbol<>(90);
        NodoArbol<Integer> n80 = new NodoArbol<>(80);
        NodoArbol<Integer> n95 = new NodoArbol<>(95);

        /*
         * 2. Enlazar referencias:
         *
         *            50
         *          /    \
         *        30      70
         *               /  \
         *             60    90
         *                  /  \
         *                80    95
         *
         * Lleno (todo nodo tiene 0 o 2 hijos), pero NO completo ni perfecto.
         */
        n50.setIzquierdo(n30);
        n50.setDerecho(n70);

        n70.setIzquierdo(n60);
        n70.setDerecho(n90);

        n90.setIzquierdo(n80);
        n90.setDerecho(n95);

        // 3. Crear el árbol.
        ArbolBinario<Integer> arbol = new ArbolBinario<>(n50);

        System.out.println("=== RAÍZ E HIJOS ===");
        arbol.imprimirRaizYHijos();

        System.out.println("\n=== ÁRBOL COMPLETO ===");
        arbol.imprimirArbol();

        System.out.println("\n=== PROPIEDADES ===");
        System.out.println("Nodos: " + arbol.contarNodos());
        System.out.println("Hojas: " + arbol.contarHojas());
        System.out.println("Altura: " + arbol.altura());
        System.out.println("Grado de 70: " + n70.grado());
        System.out.println("¿30 es hoja?: " + n30.esHoja());
        System.out.println("¿70 es hoja?: " + n70.esHoja());

        System.out.println("\n=== CONTROL DE NULL ===");
        System.out.println("Hijo izquierdo de 30: " + n30.getIzquierdo());
        if (n30.getIzquierdo() != null) {
            System.out.println(n30.getIzquierdo().getDato());
        } else {
            System.out.println("30 no tiene hijo izquierdo; la referencia es null.");
        }

        System.out.println("\n=== ÁRBOL VACÍO ===");
        new ArbolBinario<String>().imprimirRaizYHijos();
    }
}
