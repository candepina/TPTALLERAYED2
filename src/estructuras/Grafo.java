/**
 * Representa un grafo dirigido con pesos, utilizado para modelar rutas
 * en el sistema de vuelos (por ejemplo, distancias o tiempos).
 *
 * El grafo almacena sus vértices en un mapa (diccionario) y cada vértice
 * contiene una lista de adyacencia con sus aristas.
 *
 * Este grafo permite realizar recorridos DFS, BFS y calcular caminos mínimos
 * utilizando el algoritmo de Dijkstra.
 *
 * Estructuras principales:
 * - Vertice: nodo del grafo, con nombre, distancia mínima, predecesor y adyacentes.
 * - Arista: conexión dirigida entre dos vértices con un costo asociado.
 */
package estructuras;

import java.util.*;

/**
 *
 * @author cande
 */
public class Grafo {

    public static final double INFINITY = Double.MAX_VALUE; //valor infinito utilizado para inicializar distancias
    private Map<String, Vertice> VerticeMap = new HashMap<String, Vertice>();

    /**
     * Agrega una arista dirigida desde un vértice origen hacia un vértice
     * destino.
     *
     *
     * @param nomOrigen nombre del vértice origen
     * @param nomDestino nombre del vértice destino
     * @param cost peso o costo de la arista
     */
    public void agregarArista(String nomOrigen, String nomDestino, double cost) {
        Vertice v = getVertice(nomOrigen);
        Vertice w = getVertice(nomDestino);
        v.adj.add(new Arista(w, cost));
    }

    /**
     * Obtiene un vértice por nombre. Si no existe, lo crea y lo inserta en el
     * mapa.
     *
     * @param VerticeName nombre del vértice
     * @return el vértice correspondiente
     */
    public Vertice getVertice(String VerticeName) {
        Vertice v = VerticeMap.get(VerticeName);
        if (v == null) {
            v = new Vertice(VerticeName);
            VerticeMap.put(VerticeName, v);
        }
        return v;
    }

    //MÉTODOS PARA DFS, BFS, DIJKSTRA
    /**
     * Restablece todos los vértices del grafo para dejar sus distancias en
     * infinito y su predecesor en null.
     *
     * Es obligatorio llamarlo antes de usar BFS, DFS o Dijkstra.
     */
    public void clearAll() {
        for (Vertice v : VerticeMap.values()) {
            v.reset();
        }
    }

    //método DFS (profundidad)
    /**
     * Ejecuta un recorrido en profundidad (DFS) desde un vértice origen.
     *
     * @param origen nombre del vértice de inicio.
     */
    public void DFS(String origen) {
        clearAll();
        System.out.println("Recorrido DFS desde " + origen + ":");
        DFS_Visita(origen); //llama al algoritmo con un vértice inicial (ej: A)
        System.out.println();
    }

    /**
     * Procedimiento recursivo de DFS.
     *
     * @param startName nombre del vértice a explorar.
     */
    private void DFS_Visita(String startName) {
        Vertice v = VerticeMap.get(startName);
        if (v.dist == INFINITY) { //todos los nodos tienen dist=infinity al principio, y no están descubiertos. Si su distancia es 0, son DESCUBIERTOS
            v.dist = 0; // empieza marcando como descubierto al nodo inicial
            System.out.print(v.name + " "); // imprimimos cuando se descubre
            for (Arista e : v.adj) { //ahora recorremos sus adyacentes
                Vertice w = e.dest; //declara que w es un vértice adyacente a v
                if (w.dist == INFINITY) { //si la distancia del adyacente (w) es infinito, todavía no fue descurbierto -> sigue
                    w.prev = v; //guarda el nodo predecesor
                    DFS_Visita(w.name); //llama recursivamente hasta que todos sean descubiertos
                }
            }
            // Al salir de esta función, v ya terminó de explorar todos sus adyacentes -> considerado completamente visitado
        }
    }

    //método BFS (anchura)
    /**
     * Ejecuta un recorrido en anchura (BFS) desde un vértice origen.
     *
     * @param origen vértice inicial
     */
    public void BFS(String origen) {
        clearAll();
        System.out.println("Recorrido BFS desde " + origen + ":");
        BFS_Visita(origen); //llama al algoritmo con un vértice inicial (ej: A)
        System.out.println();
    }

    //método BFS (anchura)
    /**
     * Procedimiento de BFS utilizando una cola para explorar los niveles.
     *
     * @param startName nombre del vértice origen
     */
    private void BFS_Visita(String startName) {
        clearAll(); // Paso 1: marcamos todos los nodos como NO visitados (dist = INFINITY)

        Vertice start = VerticeMap.get(startName); // Obtenemos el nodo de inicio (origen)

        Queue<Vertice> q = new LinkedList<Vertice>(); // Creamos la cola
        q.add(start); // Insertamos el nodo inicial en la cola
        start.dist = 0; // Marcamos el nodo como descubierto (dist = 0)

        while (!q.isEmpty()) { // Mientras haya nodos en la cola
            Vertice v = q.remove(); // Sacamos el nodo de la cabeza de la cola (FIFO)
            System.out.print(v.name + " "); // imprimimos cuando se descubre
            for (Arista e : v.adj) { // Recorremos los adyacentes de v
                Vertice w = e.dest; // Nodo adyacente
                if (w.dist == INFINITY) { // Si aún no fue descubierto
                    w.dist = v.dist + 1; // Marcamos como descubierto (distancia mínima desde el origen)
                    w.prev = v; // Guardamos el predecesor
                    q.add(w); // Lo agregamos a la cola para explorar sus adyacentes después
                }
            }
        }
    }

    /**
     * Ejecuta el algoritmo de Dijkstra para calcular la distancia mínima desde
     * un nodo origen hasta todos los demás.
     *
     * @param startName nombre del vértice inicio del cálculo
     */
    public void dijkstra(String startName) {
        clearAll(); //resetea todos los vértices antes de empezar. pone dist = infinito, prev=null
        Vertice start = VerticeMap.get(startName); //VerticeMap es un diccionario que guarda todos los vértices del grafo, indexados por nombre ("Q", "W", etc).
        //get.(startName) busca el vértice de inicio según el nombre que pasemos como argumento
        start.dist = 0; //start es el vértice donde arranca Dijkstra (ej: Q)

        PriorityQueue<Vertice> pq = new PriorityQueue<Vertice>(); //pq es la cola de prioridad que siempre da el vértice con la menor distancia conocida. Así sabemos cuál fijar en cada paso
        pq.add(start); //al principio, solo está el nodo inicial en la cola (ej: Q)
        while (!pq.isEmpty()) { //mientras haya nodos pendientes de fijar en la cola, sigue
            Vertice v = pq.remove(); //remove() saca el nodo con menor dist -> ese es el nodo a fijar en esa iteración. v es el nodo que se está procesando
            for (Arista e : v.adj) { //va obteniendo las aristas adyacentes a ese vértice v (e es una de ellas)
                Vertice w = e.dest; //guarda en un vértice w el vértice destino de esa arista adyacente e. Ej: si v = Q y la arista Q->R(5), entonces w=R
                double cvw = e.cost; //e.cost es el costo de esa arista -> lo guarda en la variable cvw (costo entre v y w)
                if (w.dist > v.dist + cvw) { //PASO DE RELAJACIÓN: ¿conviene llegar a w pasando por v en lugar de como ya estaba antes? si -> actualiza; no -> no update
                    w.dist = v.dist + cvw; //actualiza la mejor distancia conocida hasta w
                    w.prev = v; //guarda el predecesor en el camino más corto
                    pq.add(w); //como la distancia de w cambió, lo metemos otra vez a la cola para que más adelante pueda salir como candidato a fijarse
                }
            }
        }
    }

    /*public void imprimirGrafo() {
        for (Vertice v : VerticeMap.values()) {
            System.out.print(v.name + " -> ");
            for (Arista a : v.adj) {
                System.out.print(a.dest.name + "(" + a.cost + ") ");
            }
            System.out.println();
        }
    }*/

    /**
     * Retorna la colección completa de vértices del grafo.
     *
     * @return colección de vértices
     */
    public Collection<Vertice> getVertices() {
        return VerticeMap.values();
    }

}
