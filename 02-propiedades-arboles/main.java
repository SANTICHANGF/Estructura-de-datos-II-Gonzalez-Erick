import java.util.ArrayList;
import java.util.List;

class NodoGeneral<T> {
    private T dato;
    private List<NodoGeneral<T>> hijos;

    // Constructor
    public NodoGeneral(T dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }

    // Métodos Getter y Setter
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public List<NodoGeneral<T>> getHijos() {
        return hijos;
    }

    // Método para agregar un nodo hijo
    public void agregarHijo(NodoGeneral<T> hijo) {
        this.hijos.add(hijo);
    }
}
public class main {

    public static void main(String[] args) {
        // 1. Creación del nodo raíz (Nivel 0)
        NodoGeneral<String> raiz = new NodoGeneral<>("Universidad");
        // 2. Creación de las áreas principales (Nivel 1)
        NodoGeneral<String> facultadIngenieria = new NodoGeneral<>("Facultad de Ingeniería");
        NodoGeneral<String> facultadCiencias = new NodoGeneral<>("Ciencias Administrativas");
        NodoGeneral<String> facultadDerecho = new NodoGeneral<>("Bienestar Universitario");
        // 3. Creación de las subáreas (Nivel 2)
        NodoGeneral<String> IngenieriaSistemas = new NodoGeneral<>("Ingenieria de Sistemas");
        NodoGeneral<String> IngenieriaSoftware = new NodoGeneral<>("Ingenieria de Software");
        NodoGeneral<String> IngenieriaIndustrial = new NodoGeneral<>("Ingenieria Industrial");
        NodoGeneral<String> Administracion = new NodoGeneral<>("Administracion");
        NodoGeneral<String> Contabilidad = new NodoGeneral<>("Contabilidad");
        NodoGeneral<String> Deportes = new NodoGeneral<>("Deportes");
        NodoGeneral<String> Cultura = new NodoGeneral<>("Cultura");
        // 4. Creacion de las subáreas (Nivel 3)
        NodoGeneral<String> IngenieriaSistemas1 = new NodoGeneral<>("Semillero IA");
        NodoGeneral<String> IngenieriaSistemas2 = new NodoGeneral<>("Semillero Datos");
        NodoGeneral<String> IngenieriaSoftware1 = new NodoGeneral<>("Laboratorio Movil");  
        NodoGeneral<String> IngenieriaSoftware2 = new NodoGeneral<>("Laboratorio Web");
        NodoGeneral<String> IngenieriaIndustrial1 = new NodoGeneral<>("Logistica");
        NodoGeneral<String> IngenieriaIndustrial2 = new NodoGeneral<>("Produccion");
        NodoGeneral<String> Administracion1 = new NodoGeneral<>("Emprendimiento");
        NodoGeneral<String> Administracion2 = new NodoGeneral<>("Marketing");
        NodoGeneral<String> Contabilidad1 = new NodoGeneral<>("Finanzas");
        NodoGeneral<String> Contabilidad2 = new NodoGeneral<>("Auditoria");
        NodoGeneral<String> Deportes1 = new NodoGeneral<>("Rendimiento");
        NodoGeneral<String> Deportes2 = new NodoGeneral<>("Recreacion");
        NodoGeneral<String> Cultura1 = new NodoGeneral<>("Musica");
        NodoGeneral<String> Cultura2 = new NodoGeneral<>("Danza");
        // 5. Enlace del Nivel 0 al Nivel 1 (Hijos de Universidad)
        raiz.agregarHijo(facultadIngenieria);
        raiz.agregarHijo(facultadCiencias);
        raiz.agregarHijo(facultadDerecho);

        // 6. Enlace del Nivel 1 al Nivel 2 (Subáreas de Facultad de Ingenieria)
        facultadIngenieria.agregarHijo(IngenieriaSistemas);
        facultadIngenieria.agregarHijo(IngenieriaSoftware);
        facultadIngenieria.agregarHijo(IngenieriaIndustrial);
        // Subáreas de Ciencias Administrativas
        facultadCiencias.agregarHijo(Administracion);
        facultadCiencias.agregarHijo(Contabilidad);
        // Subáreas de Bienestar Universitario
        facultadDerecho.agregarHijo(Deportes);
        facultadDerecho.agregarHijo(Cultura);
        // 7. Enlace del Nivel 2 al Nivel 3 (Subáreas de Ingenieria de Sistemas)
        IngenieriaSistemas.agregarHijo(IngenieriaSistemas1);
        IngenieriaSistemas.agregarHijo(IngenieriaSistemas2);
        // Subáreas de Ingenieria de Software
        IngenieriaSoftware.agregarHijo(IngenieriaSoftware1);
        IngenieriaSoftware.agregarHijo(IngenieriaSoftware2);
        // Subáreas de Ingenieria Industrial
        IngenieriaIndustrial.agregarHijo(IngenieriaIndustrial1);
        IngenieriaIndustrial.agregarHijo(IngenieriaIndustrial2);
        // Subáreas de Administracion
        Administracion.agregarHijo(Administracion1);
        Administracion.agregarHijo(Administracion2);
        // Subáreas de Contabilidad
        Contabilidad.agregarHijo(Contabilidad1);
        Contabilidad.agregarHijo(Contabilidad2);
        // Subáreas de Deportes
        Deportes.agregarHijo(Deportes1);
        Deportes.agregarHijo(Deportes2);
        // Subáreas de Cultura
        Cultura.agregarHijo(Cultura1);
        Cultura.agregarHijo(Cultura2);
        //Mostrar el arbol en la consola
        System.out.println("--- Jerarquía de la Universidad ---");
        imprimirArbol(raiz,  "");
    }

    static void imprimirArbol(NodoGeneral<String> nodo, String prefijo) {
    System.out.println(prefijo + "└── " + nodo.getDato());
    imprimirHijos(nodo.getHijos(), 0, prefijo + "    ");
}

static void imprimirHijos(List<NodoGeneral<String>> hijos, int i, String prefijo) {
    if (i == hijos.size()) return;               // caso base: ya no quedan hermanos
    imprimirArbol(hijos.get(i), prefijo);        // imprime el hijo i y todo lo que cuelga de él
    imprimirHijos(hijos, i + 1, prefijo);        // pasa al siguiente hermano
}
}
