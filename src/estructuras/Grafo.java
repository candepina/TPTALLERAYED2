/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import java.util.*;

/**
 *
 * @author cande
 */
public class Grafo {

    public static final double INFINITY = Double.MAX_VALUE;
    private Map<String, Vertice> VerticeMap = new HashMap<String, Vertice>();
    //agrega una nueva arista al grafo

    public void agregarArista(String nomOrigen, String nomDestino, double cost) {
        Vertice v = getVertice(nomOrigen);
        Vertice w = getVertice(nomDestino);
        v.adj.add(new Arista(w, cost));
    }

    //si el nombre del vértice no existe, lo agrega al mapa. En caso contrario, retorna el vértice existente
    public Vertice getVertice(String VerticeName) {
        Vertice v = VerticeMap.get(VerticeName);
        if (v == null) {
            v = new Vertice(VerticeName);
            VerticeMap.put(VerticeName, v);
        }
        return v;
    }
    //métodos para bfs, dfs, dijkstra

    public void clearAll() {
        for (Vertice v : VerticeMap.values()) {
            v.reset();
        }
    }
    //método DFS (profundidad)

    public void DFS(String origen) {
        clearAll();
        System.out.println("Recorrido DFS desde " + origen + ":");
        DFS_Visita(origen); //llama al algoritmo con un vértice inicial (ej: A)
        System.out.println();
    }

    public void DFS_Visita(String startName) {
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
    public void BFS(String origen) {
        clearAll();
        System.out.println("Recorrido BFS desde " + origen + ":");
        BFS_Visita(origen); //llama al algoritmo con un vértice inicial (ej: A)
        System.out.println();
    }

    //método BFS (anchura)
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

    public void imprimirGrafo() {
        for (Vertice v : VerticeMap.values()) {
            System.out.print(v.name + " -> ");
            for (Arista a : v.adj) {
                System.out.print(a.dest.name + "(" + a.cost + ") ");
            }
            System.out.println();
        }
    }

    public Collection<Vertice> getVertices() {
        return VerticeMap.values();
    }
    

}
