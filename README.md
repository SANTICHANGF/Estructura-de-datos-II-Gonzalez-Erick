# Guía 3 – Árbol binario y modelado en Java

Asignatura: **Estructura de Datos II** · Ingeniería de Software · Semestre IV  
Ecosistema: **Java / GitHub**

## Objetivo
Diseñar e implementar de forma nativa un árbol binario con referencias izquierda y derecha en Java.

## Árbol principal (7 nodos)

```text
            50
          /    \
        30      70
               /  \
             60    90
                  /  \
                80    95
```

**Clasificación:** es un árbol binario **lleno** (cada nodo tiene 0 o 2 hijos).
**No** es completo (el nivel 2 no se llena de izquierda a derecha: el 30 es hoja y el 70 tiene hijos),
**no** es perfecto (las hojas están en niveles distintos) y **no** es degenerado.

## Las cuatro clasificaciones (diagramas)

```text
LLENO            COMPLETO         PERFECTO         DEGENERADO
    1                1                1                1
   / \              / \              / \                \
  2   3            2   3            2   3                2
     / \          / \ /            / \ / \                \
    4   5        4  5 6           4  5 6  7                3
                                                             \
                                                              4
```

## Modelo en memoria
Cada `NodoArbol<T>` es un objeto en el *heap* con tres campos: `dato`, `izquierdo`, `derecho`.
Las dos últimas son **referencias** (direcciones) a otros nodos, o `null` si no hay hijo.
El árbol solo guarda la referencia a la raíz; el resto se alcanza siguiendo los enlaces.

```text
ArbolBinario ──► [50 | ● | ●]
                      │   └──► [70 | ● | ●] ...
                      └──────► [30 | null | null]
```

## Archivos
- `NodoArbol.java`: nodo genérico, encapsulado (`dato` es `final`), con `esHoja()` y `grado()`.
- `ArbolBinario.java`: raíz, `imprimirRaizYHijos()`, `imprimirArbol()`, `contarNodos()`, `contarHojas()`, `altura()`.
- `Main.java`: construcción manual del árbol de 7 nodos y pruebas.
- `EjemploArbolExpresion.java`: árbol de expresión `(7 * 2) + (20 / 4)` = 19.
- `salida_esperada.txt`, `salida_arbol_expresion.txt`: salidas reales del programa.

## Compilar y ejecutar
```bash
javac NodoArbol.java ArbolBinario.java Main.java EjemploArbolExpresion.java
java Main
java EjemploArbolExpresion
```

## Null vs. nodo hoja
- `null` significa que **no existe ningún objeto** en esa posición.
- Un **nodo hoja** sí existe, pero sus dos referencias hijas son `null`.

## Manejo de null (evitar NullPointerException)
Antes de usar un hijo se verifica `!= null`. `imprimirRaizYHijos()` además revisa si el árbol está vacío
(`raiz == null`) y retorna con un mensaje, y `evaluar()` lanza `IllegalArgumentException` si recibe `null`.
