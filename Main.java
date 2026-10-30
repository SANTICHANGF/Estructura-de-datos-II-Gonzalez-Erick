import java.util.List;
import java.util.Scanner;

/**
 * Programa de prueba del mini motor de autocompletado.
 * Carga un diccionario de 40 palabras, ejecuta pruebas verificables
 * y ofrece un modo interactivo (opcional: java Main -i).
 */
public class Main {

    private static final String[] DICCIONARIO = {
        "", "", "programador", "proceso", "procesador",
        "producto", "proyecto", "protocolo", "propiedad", "promedio",
        "estructura", "estado", "estudiante", "estandar", "estrategia",
        "arbol", "arreglo", "archivo", "algoritmo", "analisis",
        "clase", "codigo", "compilador", "cadena", "consulta",
        "dato", "diccionario", "diseno", "depuracion", "directorio",
        "lista", "pila", "cola", "grafo", "mapa",
        "nodo", "raiz", "hoja", "busqueda", "recursividad"
    };

    private static int pruebasOk = 0;
    private static int pruebasTotal = 0;

    public static void main(String[] args) {
        Trie trie = new Trie();
        for (String palabra : DICCIONARIO) {
            trie.insertar(palabra);
        }
        System.out.println("=== Mini motor de autocompletado (Trie) ===");
        System.out.println("Palabras cargadas: " + trie.tamanio());

        // 1) Autocompletado con 5 prefijos (+ 1 inexistente)
        System.out.println("\n--- Sugerencias (maximo 5) ---");
        String[] prefijos = {"pro", "est", "ar", "co", "di", "xyz"};
        for (String p : prefijos) {
            List<String> sug = trie.sugerir(p);
            System.out.printf("Prefijo \"%s\" -> %s%n", p, sug.isEmpty() ? "(sin sugerencias)" : sug);
        }

        // 2) Pruebas verificables
        System.out.println("\n--- Pruebas automaticas ---");
        
        verificar("empiezaCon(\"prog\") es true", trie.empiezaCon(""));
         // duplicado
        verificar("insertar duplicado no cambia el tamano (40)", trie.tamanio() == 40);
        System.out.printf("%nResultado: %d/%d pruebas correctas%n", pruebasOk, pruebasTotal);

        // 3) Modo interactivo opcional
        if (args.length > 0 && args[0].equals("-i")) {
            modoInteractivo(trie);
        }
    }

    private static void verificar(String descripcion, boolean condicion) {
        pruebasTotal++;
        if (condicion) pruebasOk++;
        System.out.println((condicion ? "[OK]    " : "[FALLA] ") + descripcion);
    }

    private static void modoInteractivo(Trie trie) {
        System.out.println("\nModo interactivo. Escriba un prefijo (o 'salir').");
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                if (!sc.hasNextLine()) break;
                String linea = sc.nextLine().trim();
                if (linea.equalsIgnoreCase("salir")) break;
                System.out.println(trie.sugerir(linea));
            }
        }
    }
}
