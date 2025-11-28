/**
 * Representa una arista dentro de un grafo.
 *
 * Cada arista conecta un vértice origen (implícito en la lista de adyacencia
 * del vértice que la contiene) con un vértice destino, y almacena un
 * costo/peso asociado al recorrido entre ambos.
 *
 * Se utiliza tanto en el grafo de costos como en el grafo de tiempos del
 * sistema de vuelos.
 */
package estructuras;

/**
 *
 * @author cande
 */
public class Arista {

    public Vertice dest; //vértice destino de la arista
    public double cost; //costo de la arista

    /**
     * Crea una nueva arista hacia un vértice destino con un costo determinado.
     *
     * @param d vértice destino de la arista
     * @param c costo o peso asociado a la arista
     */
    public Arista(Vertice d, double c) {
        dest = d;
        cost = c;

    }
}
