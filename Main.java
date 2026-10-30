import java.util.List;

/**
 * Programa de demostración de la Guía 12 – Introducción a grafos y redes complejas.
 *
 * <p><b>Dominio:</b> transporte urbano entre 12 localidades de Bogotá D.C.<br>
 * <b>Vértices:</b> 12 localidades. <b>Aristas:</b> 21 rutas (16 de doble sentido,
 * no dirigidas, y 5 expresos de un solo sentido, dirigidos).<br>
 * <b>Peso:</b> distancia aproximada por vía entre los centros de las localidades,
 * en km (valores referenciales con fines académicos).</p>
 *
 * <p>Compilar: {@code javac -encoding UTF-8 -d out *.java}<br>
 * Ejecutar: {@code java -cp out Main}</p>
 */
public class Main {

    public static void main(String[] args) {
        Grafo red = construirRedDeLocalidades();

        imprimirTablaVertices(red);
        imprimirTablaAristas(red);
        imprimirClasificacion(red);
        imprimirEjercicios(red);
    }

    /** Construye el grafo del dominio de transporte (12 vértices, 21 aristas). */
    static Grafo construirRedDeLocalidades() {
        Grafo g = new Grafo();

        // ---- Vértices (12) ----
        g.agregarVertice(new Vertice("Usaquén", "Norte"));
        g.agregarVertice(new Vertice("Suba", "Norte"));
        g.agregarVertice(new Vertice("Chapinero", "Centro-Norte"));
        g.agregarVertice(new Vertice("Engativá", "Occidente"));
        g.agregarVertice(new Vertice("Teusaquillo", "Centro"));
        g.agregarVertice(new Vertice("Santa Fe", "Centro"));
        g.agregarVertice(new Vertice("Fontibón", "Occidente"));
        g.agregarVertice(new Vertice("Puente Aranda", "Centro-Occidente"));
        g.agregarVertice(new Vertice("Kennedy", "Suroccidente"));
        g.agregarVertice(new Vertice("Tunjuelito", "Sur"));
        g.agregarVertice(new Vertice("Ciudad Bolívar", "Sur"));
        g.agregarVertice(new Vertice("Usme", "Sur"));

        // ---- Aristas NO dirigidas y ponderadas (16): vías de doble sentido ----
        g.agregarArista(new Arista("Usaquén", "Chapinero", 8, false));
        g.agregarArista(new Arista("Usaquén", "Suba", 10, false));
        g.agregarArista(new Arista("Suba", "Engativá", 10, false));
        g.agregarArista(new Arista("Chapinero", "Teusaquillo", 4, false));
        g.agregarArista(new Arista("Chapinero", "Santa Fe", 5, false));
        g.agregarArista(new Arista("Teusaquillo", "Santa Fe", 4, false));
        g.agregarArista(new Arista("Teusaquillo", "Engativá", 9, false));
        g.agregarArista(new Arista("Teusaquillo", "Puente Aranda", 6, false));
        g.agregarArista(new Arista("Santa Fe", "Puente Aranda", 6, false));
        g.agregarArista(new Arista("Engativá", "Fontibón", 9, false));
        g.agregarArista(new Arista("Fontibón", "Kennedy", 11, false));
        g.agregarArista(new Arista("Puente Aranda", "Kennedy", 8, false));
        g.agregarArista(new Arista("Puente Aranda", "Tunjuelito", 12, false));
        g.agregarArista(new Arista("Kennedy", "Ciudad Bolívar", 13, false));
        g.agregarArista(new Arista("Tunjuelito", "Ciudad Bolívar", 8, false));
        g.agregarArista(new Arista("Tunjuelito", "Usme", 12, false));

        // ---- Aristas DIRIGIDAS y ponderadas (5): expresos de un solo sentido ----
        g.agregarArista(new Arista("Suba", "Teusaquillo", 13, true));
        g.agregarArista(new Arista("Fontibón", "Teusaquillo", 12, true));
        g.agregarArista(new Arista("Kennedy", "Santa Fe", 14, true));
        g.agregarArista(new Arista("Usme", "Santa Fe", 20, true));
        g.agregarArista(new Arista("Santa Fe", "Usaquén", 15, true));

        return g;
    }

    // ------------------------------------------------------------------
    // Salida por consola (evidencias para el informe)
    // ------------------------------------------------------------------

    static void imprimirTablaVertices(Grafo g) {
        System.out.println("=== TABLA DE VÉRTICES (V) ===");
        System.out.printf("%-4s %-16s %-18s %-7s %-7s %-7s%n",
                "N°", "Localidad", "Zona", "Grado", "G.sal", "G.ent");
        int i = 1;
        for (Vertice v : g.getVertices()) {
            System.out.printf("%-4d %-16s %-18s %-7d %-7d %-7d%n", i++, v.nombre, v.zona,
                    g.gradoIncidente(v.nombre), g.gradoSalida(v.nombre), g.gradoEntrada(v.nombre));
        }
        System.out.println("|V| = " + g.numVertices());
        System.out.println();
    }

    static void imprimirTablaAristas(Grafo g) {
        System.out.println("=== TABLA DE ARISTAS (A) ===");
        System.out.printf("%-4s %-16s %-16s %-10s %-10s%n", "N°", "Origen", "Destino", "Peso (km)", "Tipo");
        int i = 1;
        for (Arista a : g.getAristas()) {
            System.out.printf("%-4d %-16s %-16s %-10.0f %-10s%n", i++, a.origen, a.destino, a.peso,
                    a.dirigida ? "Dirigida" : "No dirig.");
        }
        System.out.println("|A| = " + g.numAristas() + " (" + g.numNoDirigidas()
                + " no dirigidas + " + g.numDirigidas() + " dirigidas)");
        System.out.println();
    }

    static void imprimirClasificacion(Grafo g) {
        System.out.println("=== CLASIFICACIÓN FORMAL: G = (V, A) ===");
        System.out.println("Orden |V|          : " + g.numVertices());
        System.out.println("Tamaño |A|         : " + g.numAristas());
        System.out.println("Arcos reales       : " + g.numArcos() + " (dirigida = 1, no dirigida = 2)");
        System.out.println("Dirección          : " + g.tipoDireccion());
        System.out.println("Ponderado          : " + (g.esPonderado() ? "sí (km)" : "no"));
        System.out.println("Conexo (débil)     : " + (g.esConexo() ? "sí" : "no"));
        System.out.println("Fuertemente conexo : " + (g.esFuertementeConexo() ? "sí" : "no"));
        System.out.println("Cíclico            : " + (g.esCiclico() ? "sí" : "no (acíclico)"));
        System.out.printf("Densidad D         : %d / (%d·%d) = %.3f -> %s%n",
                g.numArcos(), g.numVertices(), g.numVertices() - 1, g.densidad(),
                g.esDenso() ? "DENSO" : "DISPERSO");
        System.out.printf("Peso total         : %.0f km%n", g.pesoTotal());
        System.out.println();
    }

    static void imprimirEjercicios(Grafo g) {
        System.out.println("=== EJERCICIOS ===");

        // Ejercicio 1: conversión a dirigido
        Grafo dirigido = g.convertirADirigido();
        System.out.println("1) Conversión a grafo dirigido:");
        System.out.println("   Original : " + g.numAristas() + " aristas ("
                + g.numNoDirigidas() + " no dirigidas, " + g.numDirigidas() + " dirigidas)");
        System.out.println("   Dirigido : " + dirigido.numAristas() + " aristas (tipo: "
                + dirigido.tipoDireccion() + ")");
        System.out.println("   Cada vía de doble sentido pasó a ser 2 rutas de un solo sentido.");

        // Ejercicio 2: ciclo
        List<String> ciclo = g.buscarCiclo();
        System.out.println("2) Ciclo encontrado: " + (ciclo.isEmpty() ? "ninguno" : String.join(" -> ", ciclo)));

        // Ejercicio 3: densidad
        System.out.printf("3) Densidad = %.3f -> el grafo es %s (umbral %.1f)%n",
                g.densidad(), g.esDenso() ? "denso" : "disperso", Grafo.UMBRAL_DENSIDAD);
    }
}
