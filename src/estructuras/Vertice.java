/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author cande
 */
public class Vertice implements Comparable<Vertice> {
     @Override
        public int compareTo(Vertice other) {
            return Double.compare(this.dist, other.dist);
        }

        public String name; //nombre del vértice
        public List<Arista> adj; //vértices adyacentes
        public double dist; //costo
        
        public Vertice prev;

        public Vertice(String nm) {
            name = nm;
            adj = new LinkedList<Arista>();
            reset();
        }

        public void reset() {
            dist = Grafo.INFINITY;
            
            prev = null;
        }
}
