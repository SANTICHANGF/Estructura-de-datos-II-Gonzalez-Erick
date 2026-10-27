# Práctica de Estructura de Datos II: Árbol Binario de Búsqueda (BST) y Eliminación

Este repositorio contiene la implementación y el informe de la práctica de laboratorio sobre **Árboles Binarios de Búsqueda (BST)**, aplicada al caso de estudio del **Directorio de Frutas** mediante códigos numéricos.

---

## 📌 Objetivos de la Actividad

1. **Comprender la estructura BST:** Aplicar las reglas fundamentales de organización de un Árbol Binario de Búsqueda (nodos menores a la izquierda, nodos mayores a la derecha).
2. **Implementar algoritmos recursivos:** Desarrollar e identificar el funcionamiento de inserción, recorrido e interacción con nodos.
3. **Analizar la eliminación de nodos:** Visualizar y resolver de forma lógica y programática los **3 casos de eliminación** dentro de un BST.

---

## 🌳 Estructura del Árbol de Prueba

El árbol fue construido tomando como raíz el código `1008` e insertando la siguiente secuencia de fruta-código:

```text
                  1008 (Raíz)
               /              \
         1004                    1012 (Mora)
        /    \                  /    \
     1002    1006            1010    1014 (Papaya)
    /        /  \            /  \        \
 1001     1005  1007      1009  1011     1015
(Banano) (Durazno)       (Lulo)
```

### Recorrido Inorden Generado
Al recorrer el árbol en **Inorden** (Izquierda -> Raíz -> Derecha), se obtiene la lista ordenada de forma ascendente:
`1001, 1002, 1004, 1005, 1006, 1007, 1008, 1009, 1010, 1011, 1012, 1014, 1015`

---

## ✂️ Casos de Eliminación Analizados

Durante la sesión se evaluaron los 3 escenarios clave para eliminar un nodo en un BST:

1. **Caso 1: Nodo Hoja (Sin Hijos)**
   * *Ejemplo:* Eliminación del nodo `1009` (Lulo).
   * *Solución:* El nodo padre simplemente rompe la referencia hacia el hijo.
2. **Caso 2: Nodo con Un Solo Hijo**
   * *Ejemplo:* Eliminación del nodo `1014` (Papaya) o `1002` (Banano).
   * *Solución:* El hijo único toma la posición del nodo eliminado en el árbol.
3. **Caso 3: Nodo con Dos Hijos**
   * *Ejemplo Clave:* Eliminación del nodo `1012` (Mora).
   * *Solución:* 
     1. Se busca el **sucesor inorden** (el menor elemento del subárbol derecho).
     2. Se reemplaza el valor del nodo `1012` por la clave del sucesor.
     3. Se elimina el nodo del sucesor en su posición original.

---

## 📸 Evidencias del Ejercicio

Aquí puedes incluir las capturas de pantalla de tu ejecución o diagramas realizados en clase:

<img width="1145" height="308" alt="WhatsApp Image 2026-10-27 at 09 31 17" src="https://github.com/user-attachments/assets/dc090c99-a106-4bfa-b503-1f46fa473923" />

---

## 🚀 Conclusiones

* La eficiencia en tiempo de búsqueda e inserción en un BST depende directamente de la **altura del árbol** y del orden de llegada de los datos.
* El recorrido *Inorden* nos garantiza obtener todos los elementos almacenados de forma estrictamente ordenada.
* El manejo adecuado de punteros/referencias al momento de la eliminación con 2 hijos asegura conservar las propiedades fundamentales del árbol sin romper la estructura.
