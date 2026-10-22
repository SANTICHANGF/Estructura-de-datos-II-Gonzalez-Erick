/**
 * Nodo de un árbol binario.
 */
public class NodoArbol<T> {
    private T dato;
    private NodoArbol<T> izquierdo;
    private NodoArbol<T> derecho;

    public NodoArbol(T dato) { this.dato = dato; }

    public T getDato() { return dato; }
    public void setDato(T dato) { this.dato = dato; }
    public NodoArbol<T> getIzquierdo() { return izquierdo; }
    public NodoArbol<T> getDerecho() { return derecho; }
    public void setIzquierdo(NodoArbol<T> n) { this.izquierdo = n; }
    public void setDerecho(NodoArbol<T> n) { this.derecho = n; }
}
