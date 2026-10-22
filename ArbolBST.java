import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Árbol binario de búsqueda (BST) con los cuatro recorridos.
 * Cada recorrido devuelve una lista con la secuencia visitada.
 */
public class ArbolBST {
    private NodoArbol<Integer> raiz;

    public NodoArbol<Integer> getRaiz() { return raiz; }

    /** Inserta una clave; ignora duplicados. */
    public void insertar(int clave) { raiz = insertar(raiz, clave); }

    private NodoArbol<Integer> insertar(NodoArbol<Integer> n, int clave) {
        if (n == null) return new NodoArbol<>(clave);
        if (clave < n.getDato()) n.setIzquierdo(insertar(n.getIzquierdo(), clave));
        else if (clave > n.getDato()) n.setDerecho(insertar(n.getDerecho(), clave));
        return n;
    }

    // ---------- DFS (recursivos) ----------

    /** Raíz - Izquierda - Derecha */
    public List<Integer> preorden() {
        List<Integer> out = new ArrayList<>();
        preorden(raiz, out);
        return out;
    }
    private void preorden(NodoArbol<Integer> n, List<Integer> out) {
        if (n == null) return;
        out.add(n.getDato());
        preorden(n.getIzquierdo(), out);
        preorden(n.getDerecho(), out);
    }

    /** Izquierda - Raíz - Derecha (en un BST produce claves ordenadas) */
    public List<Integer> inorden() {
        List<Integer> out = new ArrayList<>();
        inorden(raiz, out);
        return out;
    }
    private void inorden(NodoArbol<Integer> n, List<Integer> out) {
        if (n == null) return;
        inorden(n.getIzquierdo(), out);
        out.add(n.getDato());
        inorden(n.getDerecho(), out);
    }

    /** Izquierda - Derecha - Raíz */
    public List<Integer> postorden() {
        List<Integer> out = new ArrayList<>();
        postorden(raiz, out);
        return out;
    }
    private void postorden(NodoArbol<Integer> n, List<Integer> out) {
        if (n == null) return;
        postorden(n.getIzquierdo(), out);
        postorden(n.getDerecho(), out);
        out.add(n.getDato());
    }

    // ---------- BFS ----------

    /** Recorrido por niveles usando una cola. */
    public List<Integer> porNiveles() {
        List<Integer> out = new ArrayList<>();
        if (raiz == null) return out;
        Queue<NodoArbol<Integer>> cola = new LinkedList<>();
        cola.add(raiz);
        while (!cola.isEmpty()) {
            NodoArbol<Integer> actual = cola.poll();
            out.add(actual.getDato());
            if (actual.getIzquierdo() != null) cola.add(actual.getIzquierdo());
            if (actual.getDerecho() != null) cola.add(actual.getDerecho());
        }
        return out;
    }

    // ---------- Búsqueda y utilidades ----------

    /** Indica si la clave existe en el árbol. */
    public boolean buscar(int clave) {
        NodoArbol<Integer> n = raiz;
        while (n != null) {
            if (clave == n.getDato()) return true;
            n = (clave < n.getDato()) ? n.getIzquierdo() : n.getDerecho();
        }
        return false;
    }

    /** Menor clave del subárbol que empieza en n (el nodo más a la izquierda). */
    private NodoArbol<Integer> minimo(NodoArbol<Integer> n) {
        while (n.getIzquierdo() != null) n = n.getIzquierdo();
        return n;
    }

    public int tamano() { return tamano(raiz); }
    private int tamano(NodoArbol<Integer> n) {
        return n == null ? 0 : 1 + tamano(n.getIzquierdo()) + tamano(n.getDerecho());
    }

    /** Número de niveles del árbol (0 si está vacío). */
    public int altura() { return altura(raiz); }
    private int altura(NodoArbol<Integer> n) {
        return n == null ? 0 : 1 + Math.max(altura(n.getIzquierdo()), altura(n.getDerecho()));
    }

    /** Copia profunda, útil para guardar el estado "antes" de una eliminación. */
    public ArbolBST copiar() {
        ArbolBST c = new ArbolBST();
        c.raiz = copiar(raiz);
        return c;
    }
    private NodoArbol<Integer> copiar(NodoArbol<Integer> n) {
        if (n == null) return null;
        NodoArbol<Integer> nuevo = new NodoArbol<>(n.getDato());
        nuevo.setIzquierdo(copiar(n.getIzquierdo()));
        nuevo.setDerecho(copiar(n.getDerecho()));
        return nuevo;
    }

    /** Dibuja el árbol en consola, girado 90°: la raíz queda a la izquierda. */
    public void dibujar() {
        if (raiz == null) { System.out.println("(árbol vacío)"); return; }
        dibujar(raiz, 0);
    }
    private void dibujar(NodoArbol<Integer> n, int nivel) {
        if (n == null) return;
        dibujar(n.getDerecho(), nivel + 1);
        System.out.println("      ".repeat(nivel) + n.getDato());
        dibujar(n.getIzquierdo(), nivel + 1);
    }

    // ---------- Eliminación ----------

    /**
     * Elimina una clave del BST.
     * @return true si la clave existía y fue eliminada; false si no estaba.
     */
    public boolean eliminar(int clave) {
        if (!buscar(clave)) return false;
        raiz = eliminar(raiz, clave);
        return true;
    }

    private NodoArbol<Integer> eliminar(NodoArbol<Integer> n, int clave) {
        if (n == null) return null;

        if (clave < n.getDato()) {
            n.setIzquierdo(eliminar(n.getIzquierdo(), clave));
        } else if (clave > n.getDato()) {
            n.setDerecho(eliminar(n.getDerecho(), clave));
        } else {
            // Caso 1: nodo hoja -> simplemente se quita
            if (n.getIzquierdo() == null && n.getDerecho() == null) {
                return null;
            }
            // Caso 2: un solo hijo -> el hijo ocupa el lugar del nodo
            if (n.getIzquierdo() == null) return n.getDerecho();
            if (n.getDerecho() == null) return n.getIzquierdo();

            // Caso 3: dos hijos -> se copia el sucesor inorden (menor del
            // subárbol derecho) y luego se elimina ese sucesor, que tiene
            // como máximo un hijo (cae en el caso 1 o 2).
            NodoArbol<Integer> sucesor = minimo(n.getDerecho());
            n.setDato(sucesor.getDato());
            n.setDerecho(eliminar(n.getDerecho(), sucesor.getDato()));
        }
        return n;
    }
}
