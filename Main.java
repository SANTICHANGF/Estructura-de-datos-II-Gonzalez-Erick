import java.io.IOException;
import java.util.*;

/**
 * Eliminación en un BST: los tres casos (hoja, un hijo, dos hijos).
 * Muestra el árbol antes y después de cada eliminación, genera los
 * diagramas SVG en la carpeta diagramas/ y ejecuta pruebas.
 */
public class Main {

    static final int[] ORDEN_INSERCION = {50, 30, 70, 20, 40, 60, 80, 10, 35, 65};

    static ArbolBST construirArbol() {
        ArbolBST a = new ArbolBST();
        for (int c : ORDEN_INSERCION) a.insertar(c);
        return a;
    }

    public static void main(String[] args) throws IOException {
        // Cada caso parte de un árbol nuevo, para que los diagramas sean independientes.
        caso("CASO 1: nodo hoja", 10,
             Map.of(10, Diagramas.ROJO), Map.of(20, Diagramas.AMARILLO),
             "diagramas/caso1_hoja.svg",
             "Eliminar 10 (hoja): se quita el nodo y el padre (20) queda sin hijo izquierdo.");

        caso("CASO 2: nodo con un hijo", 20,
             Map.of(20, Diagramas.ROJO, 10, Diagramas.VERDE), Map.of(10, Diagramas.VERDE, 30, Diagramas.AMARILLO),
             "diagramas/caso2_un_hijo.svg",
             "Eliminar 20 (un hijo): su hijo (10) sube y el padre (30) pasa a apuntar a él.");

        caso("CASO 3: nodo con dos hijos", 30,
             Map.of(30, Diagramas.ROJO, 35, Diagramas.VERDE), Map.of(35, Diagramas.VERDE, 40, Diagramas.AMARILLO),
             "diagramas/caso3_dos_hijos.svg",
             "Eliminar 30 (dos hijos): lo reemplaza su sucesor inorden (35), el menor del subárbol derecho.");

        probar();
    }

    private static void caso(String titulo, int clave, Map<Integer, String> colAntes,
                             Map<Integer, String> colDespues, String archivo, String nota) throws IOException {
        System.out.println("==================== " + titulo + " (eliminar " + clave + ") ====================");
        ArbolBST arbol = construirArbol();
        ArbolBST antes = arbol.copiar();

        System.out.println("--- ANTES ---");
        arbol.dibujar();
        System.out.println("Inorden: " + arbol.inorden());

        boolean ok = arbol.eliminar(clave);

        System.out.println("--- DESPUÉS (eliminado: " + ok + ") ---");
        arbol.dibujar();
        System.out.println("Inorden: " + arbol.inorden());
        System.out.println();

        Diagramas.guardar(archivo, titulo + " - eliminar " + clave, nota, antes, colAntes, arbol, colDespues);
    }

    // ---------- Pruebas ----------
    private static int fallos = 0;

    private static void probar() {
        System.out.println("==================== PRUEBAS ====================");

        // Los tres casos sobre el árbol base
        ArbolBST a = construirArbol();
        a.eliminar(10);
        verificar("Caso 1: hoja (10)", a, Arrays.asList(20, 30, 35, 40, 50, 60, 65, 70, 80),
                  Arrays.asList(50, 30, 20, 40, 35, 70, 60, 65, 80));

        a = construirArbol();
        a.eliminar(20);
        verificar("Caso 2: un hijo (20)", a, Arrays.asList(10, 30, 35, 40, 50, 60, 65, 70, 80),
                  Arrays.asList(50, 30, 10, 40, 35, 70, 60, 65, 80));

        a = construirArbol();
        a.eliminar(30);
        verificar("Caso 3: dos hijos (30, sucesor 35)", a, Arrays.asList(10, 20, 35, 40, 50, 60, 65, 70, 80),
                  Arrays.asList(50, 35, 20, 10, 40, 70, 60, 65, 80));

        // Raíz con dos hijos y sucesor que tiene hijo derecho (60 -> 65)
        a = construirArbol();
        a.eliminar(50);
        verificar("Caso 3: raíz (50, sucesor 60 con hijo derecho)", a, Arrays.asList(10, 20, 30, 35, 40, 60, 65, 70, 80),
                  Arrays.asList(60, 30, 20, 10, 40, 35, 70, 65, 80));

        // Clave inexistente: no cambia nada
        a = construirArbol();
        boolean r = a.eliminar(99);
        check("Eliminar clave inexistente devuelve false", !r && a.tamano() == 10);

        // Árbol vacío
        check("Eliminar en árbol vacío devuelve false", !new ArbolBST().eliminar(5));

        // Un solo nodo: queda vacío
        a = new ArbolBST();
        a.insertar(7);
        a.eliminar(7);
        check("Eliminar la única raíz deja el árbol vacío", a.getRaiz() == null && a.tamano() == 0);

        // Eliminar todos los nodos uno a uno: el BST siempre debe seguir ordenado
        List<Integer> claves = new ArrayList<>();
        for (int c : ORDEN_INSERCION) claves.add(c);
        Collections.shuffle(claves, new Random(42));
        a = construirArbol();
        boolean valido = true;
        List<Integer> restantes = new ArrayList<>(claves);
        Collections.sort(restantes);
        for (int c : claves) {
            a.eliminar(c);
            restantes.remove(Integer.valueOf(c));
            if (!a.inorden().equals(restantes)) valido = false;
        }
        check("Eliminar los 10 nodos en orden aleatorio mantiene el BST válido", valido && a.getRaiz() == null);

        System.out.println();
        System.out.println(fallos == 0 ? "Resultado: todas las pruebas pasaron" : "Resultado: " + fallos + " prueba(s) fallaron");
    }

    private static void verificar(String nombre, ArbolBST a, List<Integer> inorden, List<Integer> preorden) {
        check(nombre, a.inorden().equals(inorden) && a.preorden().equals(preorden));
    }

    private static void check(String nombre, boolean ok) {
        System.out.println((ok ? "[OK]    " : "[FALLO] ") + nombre);
        if (!ok) fallos++;
    }
}
