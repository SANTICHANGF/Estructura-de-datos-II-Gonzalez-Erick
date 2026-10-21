# Guía 5 – BST II: eliminación de nodos

**Asignatura:** Estructura de Datos II  
**Programas:** Ingeniería de Software – Ingeniería de Sistemas  
**Semestre:** IV  
**Ecosistema:** Java / GitHub

## Objetivo

Modificar un BST eliminando nodos sin romper su propiedad de ordenación.

## Casos de eliminación

### 1. Nodo hoja

Ejemplo: eliminar `15`.

Antes:

```text
        45
       /  \
     25    65
    / \    / \
   15 35  55 85
```

Después:

```text
        45
       /  \
     25    65
      \    / \
      35  55 85
```

La referencia del padre (`25`) hacia `15` pasa a `null`.

---

### 2. Nodo con un hijo

Para probar este caso se agrega `60` como hijo derecho de `55`.

Antes:

```text
        65
       /
      55
        \
         60
```

Al eliminar `55`:

```text
        65
       /
      60
```

La referencia izquierda de `65` pasa a apuntar directamente a `60`.

---

### 3. Nodo con dos hijos

Se elimina la raíz `45`.

Antes:

```text
        45
       /  \
     25    65
    / \    / \
   15 35  55 85
```

El sucesor inorden es `55`, porque es el mínimo del subárbol derecho.

Después:

```text
        55
       /  \
     25    65
    / \      \
   15 35      85
```

Pasos:

1. Buscar el sucesor inorden.
2. Copiar `55` en el nodo que contenía `45`.
3. Eliminar el `55` original del subárbol derecho.

## Archivos

- `NodoArbol.java`
- `ArbolBST.java`
- `Main.java`
- `Resultado.txt`

## Compilar

```bash
javac NodoArbol.java ArbolBST.java Main.java
```

## Ejecutar

```bash
java Main
```

## Método central

```java
public void eliminar(int valor) {
    raiz = eliminarRec(raiz, valor);
}
```

Es importante reasignar `raiz` porque el valor eliminado puede ser precisamente la raíz.

## Sucesor en inorden

```java
public int minValor(NodoArbol nodo) {
    NodoArbol actual = nodo;

    while (actual.getIzquierdo() != null) {
        actual = actual.getIzquierdo();
    }

    return actual.getDato();
}
```

## Cambios de referencias

- **Hoja:** la referencia del padre pasa a `null`.
- **Un hijo:** la referencia del padre pasa directamente al hijo del nodo eliminado.
- **Dos hijos:** el nodo conserva sus referencias principales, pero su dato se reemplaza por el sucesor; después se elimina la copia del sucesor.

## Validación

Después de cada prueba, el programa:

- imprime el árbol;
- ejecuta Inorden;
- confirma que el valor eliminado ya no existe;
- valida que la propiedad BST siga siendo correcta.
