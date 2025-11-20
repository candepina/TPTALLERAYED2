/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

/**
 *
 * @author cande
 */
public class Arista {
     public Vertice dest; //vértice destino de la arista
        public double cost; //costo de la arista
      

        public Arista(Vertice d, double c) {
            dest = d;
            cost = c;
            
        }
}
