# 02 - Propiedades de los árboles

**Asignatura:** Estructura de Datos II – Ingeniería de Software, IV semestre
**Estudiante:** Erick Santiago Gonzalez Gutierrez

## Contenido de la carpeta

- `NodoGeneral.java`: clase genérica con un dato y una lista de hijos.
- `Main.java`: construye una jerarquía de 25 nodos y 4 niveles, la imprime en consola con un método recursivo y calcula su altura con el método `altura()`.
- `arbol-universidad.drawio`: diagrama de la jerarquía de la universidad modelada como árbol.

## Convenciones

La raíz tiene profundidad 0 y el nivel se toma igual a la profundidad. El grado de un nodo es su número de hijos y el grado del árbol es el mayor grado entre todos los nodos. La altura de una hoja es 0 y se mide en número de aristas.

## Identificación de conceptos

| Concepto | Resultado | Justificación |
|---|---|---|
| Raíz | Universidad | Es el único nodo sin padre. |
| Hojas | 14 | Son los nodos sin hijos: los semilleros, laboratorios, líneas y áreas del nivel 3 (Semillero IA, Semillero Datos, Laboratorio Movil, Laboratorio Web, Logistica, Produccion, Emprendimiento, Marketing, Finanzas, Auditoria, Rendimiento, Recreacion, Musica y Danza). |
| Nodos internos | 11 | Tienen al menos un hijo: la raíz, las 3 áreas del nivel 1 y los 7 programas del nivel 2. Son 10 si no se cuenta la raíz. |
| Grado del árbol | 3 | Lo alcanzan Universidad (3 hijos) y Facultad de Ingeniería (3 hijos). |
| Altura del árbol | 3 | Es la altura de la raíz: el camino más largo hasta una hoja tiene 3 aristas. |
| Número de niveles | 4 | Del nivel 0 (raíz) al nivel 3 (hojas). |

## Tabla de métricas

Los nodos que están en el mismo nivel y tienen la misma cantidad de hijos comparten sus métricas, así que se agrupan en una sola fila.

| Nodo(s) | Tipo | Grado | Nivel | Profundidad | Altura |
|---|---|---|---|---|---|
| Universidad | Raíz | 3 | 0 | 0 | 3 |
| Facultad de Ingeniería | Interno | 3 | 1 | 1 | 2 |
| Ciencias Administrativas, Bienestar Universitario | Interno | 2 | 1 | 1 | 2 |
| Ingenieria de Sistemas, Ingenieria de Software, Ingenieria Industrial, Administracion, Contabilidad, Deportes, Cultura | Interno | 2 | 2 | 2 | 1 |
| Las 14 hojas del nivel 3 | Hoja | 0 | 3 | 3 | 0 |

Comprobación: los 25 nodos son 14 hojas + 11 internos, y la suma de los grados es 3 + 3 + 2 + 2 + 7·2 = 24, que es igual a n − 1.

## Relaciones de parentesco

- Universidad es padre de Facultad de Ingeniería, Ciencias Administrativas y Bienestar Universitario, que son hermanos entre sí.
- Facultad de Ingeniería es padre de Ingenieria de Sistemas, Ingenieria de Software e Ingenieria Industrial.
- Ciencias Administrativas es padre de Administracion y Contabilidad.
- Bienestar Universitario es padre de Deportes y Cultura.
- Cada programa del nivel 2 es padre de dos hojas; por ejemplo, Ingenieria de Sistemas es padre de Semillero IA y Semillero Datos.
- Universidad es ancestro de los otros 24 nodos.

## Método de la altura

```java
static int altura(NodoGeneral<?> nodo) {
    if (nodo.getHijos().isEmpty()) return 0;
    int max = 0;
    for (NodoGeneral<?> hijo : nodo.getHijos()) {
        max = Math.max(max, altura(hijo));
    }
    return 1 + max;
}
```

La altura de un nodo es 1 más la altura de su hijo más alto, y una hoja vale 0. Por ejemplo:

```text
altura(Contabilidad) = 1 + máx(altura(Finanzas) = 0, altura(Auditoria) = 0) = 1
altura(Ciencias Administrativas) = 1 + máx(altura(Administracion) = 1, altura(Contabilidad) = 1) = 2
altura(Universidad) = 1 + máx(2, 2, 2) = 3
```

El resultado calculado a mano (3) coincide con el que devuelve el método en Java.

## Ejecución

```bash
javac -encoding UTF-8 *.java
java Main
```

## Ejercicios

**Profundidad de cada hoja**

Las 14 hojas están en el nivel 3, así que todas tienen profundidad 3. Esto ocurre porque cada rama de la jerarquía llega hasta el mismo nivel.

**Dos caminos distintos y su longitud**

1. Universidad → Facultad de Ingeniería → Ingenieria de Software → Laboratorio Web: longitud 3 (3 aristas, 4 nodos).
2. Universidad → Bienestar Universitario → Deportes: longitud 2 (2 aristas, 3 nodos).

**Agregar un nuevo nivel y explicar cómo cambia la altura**

Si se agrega `Proyecto IA Generativa` como hijo de `Semillero IA`, el nuevo nodo queda en profundidad 4. La altura del árbol pasa de 3 a 4 y el número de niveles pasa de 4 a 5. Las alturas del camino afectado suben en 1:

| Nodo | Altura antes | Altura después |
|---|---|---|
| Semillero IA | 0 | 1 |
| Ingenieria de Sistemas | 1 | 2 |
| Facultad de Ingeniería | 2 | 3 |
| Universidad | 3 | 4 |

Las demás ramas conservan su altura. En cambio, si se agrega un nodo como `Gimnasio` a `Deportes`, queda en profundidad 3, un nivel que ya existía, y la altura del árbol sigue siendo 3; solo cambia el grado de Deportes, que pasa de 2 a 3.

## Reflexión

Trabajar esta guía me permitió separar conceptos que a primera vista parecen iguales. La profundidad se mide desde la raíz hacia abajo hasta un nodo, mientras que la altura se mide desde un nodo hacia abajo hasta su hoja más lejana. Por eso la raíz tiene profundidad 0 y la mayor altura, y las hojas tienen altura 0 y la mayor profundidad. Calcular la tabla a mano y comprobar que la suma de los grados da n − 1 me ayudó a detectar errores antes de pasar al código.

También vi que el método `altura()` es un buen ejemplo de recursión sobre árboles: cada subárbol tiene la misma estructura que el árbol completo, así que basta con definir el caso base (una hoja mide 0) y combinar los resultados de los hijos con `1 + máximo`. Recorre cada nodo una sola vez, por lo que su costo crece de forma lineal con el número de nodos.

Por último, el ejercicio de agregar un nivel mostró que la altura solo aumenta cuando el nodo nuevo queda más profundo que cualquier hoja existente. Como en este árbol todas las hojas están en el nivel 3, agregar un hijo a cualquiera de ellas sube la altura, pero agregar una hoja nueva a un nodo interno del nivel 2 no la cambia.
