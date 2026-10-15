/**
 * Árbol de expresión para: (7 * 2) + (20 / 4)
 *
 *            +
 *          /   \
 *        *       /
 *       / \     / \
 *      7   2  20   4
 */
public class EjemploArbolExpresion {

    public static void main(String[] args) {

        NodoArbol<String> suma = new NodoArbol<>("+");
        NodoArbol<String> mult = new NodoArbol<>("*");
        NodoArbol<String> div  = new NodoArbol<>("/");
        NodoArbol<String> n7   = new NodoArbol<>("7");
        NodoArbol<String> n2   = new NodoArbol<>("2");
        NodoArbol<String> n20  = new NodoArbol<>("20");
        NodoArbol<String> n4   = new NodoArbol<>("4");

        suma.setIzquierdo(mult);
        suma.setDerecho(div);
        mult.setIzquierdo(n7);
        mult.setDerecho(n2);
        div.setIzquierdo(n20);
        div.setDerecho(n4);

        ArbolBinario<String> arbol = new ArbolBinario<>(suma);

        System.out.println("=== ÁRBOL DE EXPRESIÓN ===");
        arbol.imprimirArbol();
        System.out.println("\nResultado de (7 * 2) + (20 / 4): " + evaluar(suma));
    }

    public static int evaluar(NodoArbol<String> nodo) {
        if (nodo == null) {
            throw new IllegalArgumentException("No se puede evaluar una referencia null.");
        }
        if (nodo.esHoja()) {
            return Integer.parseInt(nodo.getDato());
        }
        int izq = evaluar(nodo.getIzquierdo());
        int der = evaluar(nodo.getDerecho());
        return switch (nodo.getDato()) {
            case "+" -> izq + der;
            case "-" -> izq - der;
            case "*" -> izq * der;
            case "/" -> izq / der;
            default -> throw new IllegalArgumentException("Operador no válido: " + nodo.getDato());
        };
    }
}
