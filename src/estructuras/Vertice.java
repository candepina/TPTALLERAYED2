/**
 * Representa un vértice dentro de un grafo dirigido o no dirigido.
 *
 * Cada vértice contiene:
 * - un nombre identificador,
 * - una lista de aristas adyacentes (sus conexiones),
 * - la distancia mínima calculada (utilizada por Dijkstra y BFS),
 * - una referencia al vértice predecesor en un camino mínimo.
 *
 * Además, implementa {@code Comparable} para poder ser usado dentro de una
 * {@code PriorityQueue} en el algoritmo de Dijkstra.
 */
package estructuras;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author cande
 */
public class Vertice implements Comparable<Vertice> {

    public String name; //nombre del vértice
    public List<Arista> adj; //lista de vértices adyacentes
    public double dist; //distancia mínima desde el origen
    public Vertice prev; //vértice previo en el camino más corto encontrado

    /**
     * Crea un nuevo vértice con un nombre específico. Inicializa la lista de
     * adyacencias y llama a {@link #reset()}.
     *
     * @param nm nombre del vértice
     */
    public Vertice(String nm) {
        name = nm;
        adj = new LinkedList<Arista>();
        reset();
    }

    /**
     * Reinicia los valores del vértice: - distancia infinita, - predecesor en
     * null.
     *
     * Este método se utiliza antes de ejecutar BFS, DFS o Dijkstra.
     */
    public void reset() {
        dist = Grafo.INFINITY;

        prev = null;
    }

    /**
     * Comparación entre vértices según su distancia. Esto permite que una
     * {@code PriorityQueue} los ordene correctamente durante Dijkstra.
     *
     * @param other vértice a comparar
     * @return valor negativo, cero o positivo dependiendo de la comparación
     */
    @Override
    public int compareTo(Vertice other) {
        return Double.compare(this.dist, other.dist);
    }
}
