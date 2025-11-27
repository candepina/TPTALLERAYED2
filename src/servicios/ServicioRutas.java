/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import estructuras.Grafo;
import estructuras.Vertice;
import estructuras.Arista;
import static estructuras.Grafo.INFINITY;
import modelo.Vuelo;

/**
 *
 * @author cande
 */
public class ServicioRutas {

    private Grafo grafoCosto;
    private Grafo grafoTiempo;

    public ServicioRutas(Grafo grafoCosto, Grafo grafoTiempo) {
        this.grafoCosto = grafoCosto;
        this.grafoTiempo = grafoTiempo;
    }

    //métodos para mostrar los resultados de Dijkstra para todos los destinos
    public void mostrarResultadoDijkstraTiempo(String origen) {
        System.out.println("\nResultados desde " + origen + ":");
        for (Vertice v : grafoTiempo.getVertices()) {
            if (v.dist == INFINITY) {
                System.out.println("No hay camino hacia " + v.name);
            } else {
                System.out.print("Distancia a " + v.name + " = " + v.dist + "hs " + " | Camino: ");
                imprimirCamino(v);
                System.out.println();
            }
            System.out.println("");
        }
    }

    // Mostrar resultado de Dijkstra solo para un destino específico
    public double mostrarResultadoDijkstraParticularTiempo(String origen, String destino) {
        double res = 0;
        Vertice v = grafoTiempo.getVertice(destino); // obtenemos el vértice destino
        if (v == null) {
            System.out.println("El destino " + destino + " no existe en el grafo.");

        }

        System.out.println("\nResultado desde " + origen + " hasta " + destino + ":");

        if (v.dist == INFINITY) {
            System.out.println("No hay camino disponible hasta " + destino);
        } else {
            System.out.print("Distancia = " + v.dist + "hs " + " | Camino: ");
            imprimirCamino(v);
            System.out.println();
            res = v.dist;
        }
        System.out.println("");
        return res;

    }

    //métodos para mostrar los resultados de Dijkstra para todos los destinos
    public void mostrarResultadoDijkstraCosto(String origen) {
        System.out.println("\nResultados desde " + origen + ":");
        for (Vertice v : grafoCosto.getVertices()) {
            if (v.dist == INFINITY) {
                System.out.println("No hay camino hacia " + v.name);
            } else {
                System.out.print("Costo a " + v.name + " = " + "$" + v.dist + " | Camino: ");
                imprimirCamino(v);
                System.out.println();
            }
            System.out.println("");
        }
    }

    // Mostrar resultado de Dijkstra solo para un destino específico
    public double mostrarResultadoDijkstraParticularCosto(String origen, String destino) {

        double res = 0;
        Vertice v = grafoCosto.getVertice(destino); // obtenemos el vértice destino
        if (v == null) {
            System.out.println("El destino " + destino + " no existe en el grafo.");

        }

        System.out.println("\nResultado desde " + origen + " hasta " + destino + ":");

        if (v.dist == INFINITY) {
            System.out.println("No hay camino disponible hasta " + destino);
        } else {
            System.out.print("Costo = " + "$" + v.dist + " | Camino: ");
            imprimirCamino(v);
            System.out.println();
            res = v.dist;
        }
        System.out.println("");
        return res;

    }

    // método auxiliar recursivo para reconstruir camino
    private void imprimirCamino(Vertice v) {
        if (v.prev != null) {
            imprimirCamino(v.prev);
            System.out.print(" -> ");
        }
        System.out.print(v.name);
    }

    // Método para saber si hay vuelo directo entre origen y destino
    public boolean esDirecto(String partida, String destino) {
        Vertice v = grafoCosto.getVertice(partida);
        if (v == null) {
            System.out.println("La ciudad de partida es inválida"); // origen no existe

        }
        for (Arista a : v.adj) {
            if (a.dest.name.equals(destino)) {
                System.out.println("Existe vuelo directo desde " + partida + " hasta " + destino + "\n"); // vuelo directo
                return true;
            }
        }
        System.out.println("No existe vuelo directo desde " + partida + " hasta " + destino + ". Requiere trasbordo\n"); // no hay vuelo directo
        return false;
    }

    public double recargo(double precioBase, boolean esDirecto, Vuelo vuelo) {
        double precio_final = precioBase;
        if (esDirecto) {
            double recargo_por_vuelo_directo = precioBase * 0.20;
            precio_final = precio_final + recargo_por_vuelo_directo;
        }

        double ocupacion = vuelo.calcularOcupacion();
        if (ocupacion >= 95) {
            double recargo_por_ocupacion = precioBase * 0.10;
            precio_final = precio_final + recargo_por_ocupacion;
        }
        return precio_final;

    }

    public void mostrarDetallesParcial(String partida, String destino) {
        System.out.println("Detalles parciales de su vuelo: ");

        //Calcular costo base del vuelo
        grafoCosto.dijkstra(partida);
        double precioBase = mostrarResultadoDijkstraParticularCosto(partida, destino);

        // Calcular tiempo del viaje por el camino mínimo
        grafoTiempo.dijkstra(partida);
        double tiempoEstimado = mostrarResultadoDijkstraParticularTiempo(partida, destino);

        //Comprobación de vuelo directo
        boolean vueloDirecto = esDirecto(partida, destino);

        double precioParcial = precioBase;
        if (vueloDirecto) {
            precioParcial+= precioBase * 0.20;
        }

        //mostrar informacion
        System.out.println("Ciudad de partida: " + partida);
        System.out.println("Ciudad de destino: " + destino);
        System.out.println("Tiempo estimado de su vuelo: " + tiempoEstimado + " hs");
        if (vueloDirecto) {
            System.out.println("El vuelo es directo. Se aplica un recargo de: $" + (precioBase*0.20));

        }
        System.out.println("Precio parcial de su vuelo: $" + precioParcial);
        

    }

    public void mostrarDetallesFinal(String partida, String destino, Vuelo vuelo, int pasajeros, int codigoVuelo) {
        if(pasajeros>30){
            pasajeros=30;
        }
        System.out.println("Detalles finales del vuelo: ");
        //Calcular costo base del vuelo
        grafoCosto.dijkstra(partida);
        double precioBase = mostrarResultadoDijkstraParticularCosto(partida, destino);

        // Calcular tiempo del viaje por el camino mínimo
        grafoTiempo.dijkstra(partida);
        double tiempoEstimado = mostrarResultadoDijkstraParticularTiempo(partida, destino);

        //Comprobación de vuelo directo
        boolean vueloDirecto = esDirecto(partida, destino);

        //Calcular precio final con recargo si es necesario
        double precioFinal = recargo(precioBase, vueloDirecto, vuelo);

        //mostrar informacion
        System.out.println("Código de vuelo: "+codigoVuelo);
        System.out.println("Ciudad de partida: " + partida);
        System.out.println("Ciudad de destino: " + destino);
        System.out.println("Tiempo estimado de su vuelo: " + tiempoEstimado + " hs");
        System.out.println("Precio base de su vuelo: $" + precioBase);

        if (vueloDirecto) {
            System.out.println("El vuelo es directo. Se aplica un recargo de: $" + (precioBase * 0.20));

        }

        if (vuelo.calcularOcupacion() >= 95) {
            System.out.println("El vuelo está ocupado a más del 95%. Se aplica un recargo de: $" + precioBase * 0.10);
        }
        System.out.println("Precio final de su vuelo: $" + precioFinal +" por persona.");
        System.out.println("El precio final por "+pasajeros+" pasajeros es: $"+precioFinal*pasajeros);
        
    }

}
