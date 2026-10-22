import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Genera diagramas SVG "antes / después" de una eliminación en el BST.
 * Los dibujos salen del árbol real, así que siempre coinciden con el código.
 */
public class Diagramas {
    static final String ROJO = "#f4a3a3";     // nodo eliminado
    static final String VERDE = "#a8e0b5";    // nodo que ocupa el lugar / sube
    static final String AMARILLO = "#ffe08a"; // nodo cuyo enlace cambia

    private static final int DX = 70, DY = 80, R = 21, M = 40, TOP = 90;

    public static void guardar(String ruta, String titulo, String nota,
                               ArbolBST antes, Map<Integer, String> colAntes,
                               ArbolBST despues, Map<Integer, String> colDespues) throws IOException {
        int anchoA = Math.max(antes.tamano(), 1) * DX;
        int anchoD = Math.max(despues.tamano(), 1) * DX;
        int ox2 = M + anchoA + 90;
        int ancho = Math.max(ox2 + anchoD + M, 760);
        int alto = TOP + (Math.max(antes.altura(), despues.altura()) - 1) * DY + R + 90;

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\" viewBox=\"0 0 %d %d\" font-family=\"Arial, Helvetica, sans-serif\">\n",
                ancho, alto, ancho, alto));
        sb.append(String.format("<rect width=\"%d\" height=\"%d\" fill=\"white\"/>\n", ancho, alto));
        sb.append(texto(ancho / 2, 28, titulo, 18, "bold", "#1f3a68"));

        panel(sb, antes.getRaiz(), M, anchoA, "ANTES", colAntes);
        panel(sb, despues.getRaiz(), ox2, anchoD, "DESPUÉS", colDespues);

        // flecha entre paneles
        int fx = M + anchoA + 15, fy = TOP + 10;
        sb.append(String.format("<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"#555\" stroke-width=\"3\"/>\n", fx, fy, fx + 45, fy));
        sb.append(String.format("<polygon points=\"%d,%d %d,%d %d,%d\" fill=\"#555\"/>\n", fx + 60, fy, fx + 44, fy - 9, fx + 44, fy + 9));

        // leyenda y nota
        int ly = alto - 48;
        leyenda(sb, M, ly, ROJO, "Nodo eliminado");
        leyenda(sb, M + 170, ly, VERDE, "Nodo que ocupa su lugar");
        leyenda(sb, M + 410, ly, AMARILLO, "Enlace modificado");
        sb.append(texto(M, alto - 14, nota, 13, "normal", "#333").replace("text-anchor=\"middle\"", "text-anchor=\"start\""));
        sb.append("</svg>\n");

        Path p = Path.of(ruta);
        if (p.getParent() != null) Files.createDirectories(p.getParent());
        Files.writeString(p, sb.toString(), StandardCharsets.UTF_8);
    }

    private static void panel(StringBuilder sb, NodoArbol<Integer> raiz, int ox, int anchoPanel,
                              String subtitulo, Map<Integer, String> col) {
        sb.append(texto(ox + anchoPanel / 2, 62, subtitulo, 15, "bold", "#444"));
        if (raiz == null) {
            sb.append(texto(ox + anchoPanel / 2, TOP + 5, "(árbol vacío)", 14, "normal", "#777"));
            return;
        }
        Map<NodoArbol<Integer>, int[]> pos = new IdentityHashMap<>();
        colocar(raiz, 0, new int[]{0}, pos, ox);

        // aristas primero, para que los círculos queden encima
        for (Map.Entry<NodoArbol<Integer>, int[]> e : pos.entrySet()) {
            NodoArbol<Integer> n = e.getKey();
            int[] a = e.getValue();
            arista(sb, a, pos.get(n.getIzquierdo()));
            arista(sb, a, pos.get(n.getDerecho()));
        }
        for (Map.Entry<NodoArbol<Integer>, int[]> e : pos.entrySet()) {
            int clave = e.getKey().getDato();
            int[] a = e.getValue();
            String fill = col.getOrDefault(clave, "#e8eef9");
            sb.append(String.format("<circle cx=\"%d\" cy=\"%d\" r=\"%d\" fill=\"%s\" stroke=\"#3b5998\" stroke-width=\"2\"/>\n", a[0], a[1], R, fill));
            sb.append(texto(a[0], a[1] + 5, String.valueOf(clave), 15, "bold", "#111"));
        }
    }

    private static void arista(StringBuilder sb, int[] a, int[] b) {
        if (b == null) return;
        sb.append(String.format("<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"#3b5998\" stroke-width=\"2\"/>\n", a[0], a[1], b[0], b[1]));
    }

    /** x según el orden inorden (evita cruces), y según la profundidad. */
    private static void colocar(NodoArbol<Integer> n, int prof, int[] cont,
                                Map<NodoArbol<Integer>, int[]> pos, int ox) {
        if (n == null) return;
        colocar(n.getIzquierdo(), prof + 1, cont, pos, ox);
        pos.put(n, new int[]{ox + cont[0] * DX + DX / 2, TOP + prof * DY});
        cont[0]++;
        colocar(n.getDerecho(), prof + 1, cont, pos, ox);
    }

    private static void leyenda(StringBuilder sb, int x, int y, String color, String txt) {
        sb.append(String.format("<circle cx=\"%d\" cy=\"%d\" r=\"8\" fill=\"%s\" stroke=\"#3b5998\"/>\n", x + 8, y, color));
        sb.append(texto(x + 22, y + 4, txt, 12, "normal", "#333").replace("text-anchor=\"middle\"", "text-anchor=\"start\""));
    }

    private static String texto(int x, int y, String t, int size, String peso, String color) {
        return String.format("<text x=\"%d\" y=\"%d\" text-anchor=\"middle\" font-size=\"%d\" font-weight=\"%s\" fill=\"%s\">%s</text>\n",
                x, y, size, peso, color, t.replace("&", "&amp;").replace("<", "&lt;"));
    }
}
