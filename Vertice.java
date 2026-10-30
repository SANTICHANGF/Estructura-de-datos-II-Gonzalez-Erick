/**
 * Representa un vértice (nodo) del grafo.
 *
 * <p>En el dominio elegido (transporte urbano), cada vértice es una localidad
 * de Bogotá D.C. que actúa como nodo de una red de rutas (troncales,
 * alimentadores y vías arteriales).</p>
 *
 * <p>Formalmente, el conjunto V del grafo G = (V, A) es el conjunto de objetos
 * {@code Vertice} registrados en {@link Grafo}.</p>
 */
class Vertice {

    /** Identificador único del vértice (se usa como llave en las aristas). */
    String nombre;

    /** Zona de la ciudad a la que pertenece la localidad (dato descriptivo). */
    String zona;

    /**
     * Crea un vértice.
     *
     * @param nombre nombre único de la localidad
     * @param zona   zona de Bogotá (Norte, Occidente, Centro, Sur)
     */
    Vertice(String nombre, String zona) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del vértice no puede ser vacío");
        }
        this.nombre = nombre;
        this.zona = zona;
    }

    @Override
    public String toString() {
        return nombre + " (" + zona + ")";
    }
}
