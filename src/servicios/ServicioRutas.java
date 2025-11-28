/**
 * Servicio que gestiona las operaciones relacionadas con rutas aéreas,
 * incluyendo obtención de caminos, costos, duraciones y aplicación de recargos.
 *
 * Utiliza dos grafos:
 * - grafoCosto: almacena los costos entre aeropuertos.
 * - grafoTiempo: almacena los tiempos entre aeropuertos.
 */
package servicios;

import estructuras.Grafo;
import estructuras.Vertice;
import estructuras.Arista;
import static estructuras.Grafo.INFINITY;
import java.util.ArrayList;
import modelo.Vuelo;

/**
 *
 * @author cande
 */
public class ServicioRutas {

    private static Grafo grafoCosto;
    private Grafo grafoTiempo;

    /**
     * Crea un servicio de rutas utilizando un grafo de costos y uno de tiempos.
     *
     * @param grafoCosto grafo que contiene los costos de cada tramo
     * @param grafoTiempo grafo que contiene los tiempos de cada tramo
     */
    public ServicioRutas(Grafo grafoCosto, Grafo grafoTiempo) {
        this.grafoCosto = grafoCosto;
        this.grafoTiempo = grafoTiempo;
    }

    //métodos para mostrar los resultados de Dijkstra para todos los destinos
    /**
     * Muestra por consola los resultados del algoritmo de Dijkstra para tiempos
     * desde un origen hacia todos los destinos posibles.
     *
     * @param origen nombre del vértice de partida
     */
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
    /*public double mostrarResultadoDijkstraParticularTiempo(String origen, String destino) {
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

    }*/
    //métodos para mostrar los resultados de Dijkstra para todos los destinos
    /**
     * Muestra por consola los resultados del algoritmo de Dijkstra para costos
     * desde un origen hacia todos los destinos del grafo.
     *
     * @param origen vértice de salida
     */
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

    /*
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

    }*/
    // método auxiliar recursivo para reconstruir camino
    /**
     * Imprime recursivamente el camino desde el origen hasta un vértice dado
     * utilizando los punteros prev calculados por Dijkstra.
     *
     * @param v vértice destino
     */
    private void imprimirCamino(Vertice v) {
        if (v.prev != null) {
            imprimirCamino(v.prev);
            System.out.print(" -> ");
        }
        System.out.print(v.name);
    }

    /**
     * Construye y devuelve el camino mínimo entre dos ciudades en forma de
     * lista de Strings, utilizando la información generada por Dijkstra.
     *
     * @param origen ciudad de origen
     * @param destino ciudad destino
     * @return lista ordenada con los nombres de los vértices que componen el
     * camino
     */
    public ArrayList<String> obtenerCamino(String origen, String destino) {
        ArrayList<String> camino = new ArrayList<>();

        // Buscamos el vértice destino en el grafo
        Vertice v = grafoCosto.getVertice(destino);

        // Si no existe o no tiene camino, devolvemos lista vacía
        if (v == null || v.dist == INFINITY) {
            return camino;
        }

        // Reconstruimos el camino desde el destino hacia atrás usando prev
        while (v != null) {
            camino.add(0, v.name); // lo agregamos al inicio para que quede en orden
            v = v.prev;
        }

        return camino;
    }

    // Método para saber si hay vuelo directo entre origen y destino
    /**
     * Verifica si existe un vuelo directo entre dos ciudades.
     *
     * @param partida ciudad de origen
     * @param destino ciudad de destino
     * @return true si existe una arista directa, false en caso contrario
     */
    public static boolean esDirecto(String partida, String destino) {
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

        return false;
    }

    /**
     * Calcula el precio final de un vuelo aplicando recargos por: - vuelo
     * directo (+20%) - ocupación mayor o igual a 95% (+10%)
     *
     * @param precioBase precio inicial del vuelo sin recargos
     * @param esDirecto indica si el vuelo es directo
     * @param vuelo instancia del vuelo para obtener la ocupación
     * @return precio final con recargos aplicados
     */
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

    /**
     * Muestra por consola los detalles parciales de una lista de vuelos,
     * incluyendo precio base, duración, código de vuelo y tramo.
     *
     * @param vuelos lista de vuelos correspondientes al itinerario
     */
    public void mostrarDetallesParcial(ArrayList<Vuelo> vuelos) {
        System.out.println("\nDETALLES PARCIALES DE SU(S) VUELO(S): \n");

        double total = 0;
        for (Vuelo v : vuelos) {
            double costo = v.getPrecio();

            total = total + costo;
            System.out.println("Tramo del vuelo:" + v.getPartida() + " -> " + v.getDestino());
            System.out.println("Código de vuelo: " + v.getCodigo_vuelo());
            System.out.println("Duración estimada de vuelo: " + v.getDuracion() + "hs");
            System.out.println("Precio parcial del vuelo (sin posibles recargos): $" + v.getPrecio() + "\n");
        }
        System.out.println("Precio total parcial (sin posibles recargos): $" + total);
    }

    /**
     * Muestra los detalles finales completos para un conjunto de vuelos,
     * incluyendo recargos aplicados, asientos asignados y total por pasajero.
     *
     * @param vuelos lista de vuelos del itinerario completo
     * @param pasajeros cantidad de pasajeros
     * @param partida ciudad inicial del recorrido
     * @param destino ciudad final del recorrido
     */
    public void mostrarDetallesFinal(ArrayList<Vuelo> vuelos, int pasajeros, String partida, String destino) {
        if (pasajeros > 30) {
            pasajeros = 30;
        }
        System.out.println("DETALLES FINALES DE SU(S) VUELO(S)");
        double totalFinal = 0;
        for (Vuelo v : vuelos) {
            double precioBase = v.getPrecio();

            boolean vueloDirecto = esDirecto(partida, destino);
            double precioFinal = recargo(precioBase, vueloDirecto, v);

            System.out.println("Tramo del vuelo: " + v.getPartida() + " -> " + v.getDestino());
            System.out.println("Código de vuelo: " + v.getCodigo_vuelo());
            System.out.println("Duración estimada de vuelo: " + v.getDuracion() + "hs");
            if (vueloDirecto) {
                System.out.println("Se aplica un recargo de $" + (precioBase * 0.20) + " (20%) por vuelo directo.");
            }
            if (v.calcularOcupacion() >= 95) {
                System.out.println("Se aplica un recargo de $" + (precioBase * 0.10 + " (10%) por la ocupación del vuelo."));
            }
            System.out.println("Precio final del vuelo: $" + precioFinal);
            totalFinal = totalFinal + precioFinal;
            System.out.println("Asientos asignados en este vuelo: ");
            for (String asiento : v.getAsientosAsignados()) {
                System.out.println("" + asiento);
            }
            System.out.println();
        }

        System.out.println("PRECIO FINAL POR PERSONA: " + totalFinal);
        System.out.println("TOTAL FINAL POR " + pasajeros + " PASAJEROS: $" + (totalFinal * pasajeros));

    }

    /**
     * Devuelve el costo de un tramo directo entre dos ciudades.
     *
     * @param origen ciudad de origen
     * @param destino ciudad destino
     * @return costo del tramo o -1 si no existe
     */
    public double obtenerCostoTramo(String origen, String destino) {
        Vertice v = grafoCosto.getVertice(origen);
        if (v == null) {
            return -1;
        }

        for (Arista a : v.adj) {
            if (a.dest.name.equals(destino)) {
                return a.cost;
            }
        }

        return -1; // no existe esa conexión directa
    }

    /**
     * Devuelve la duración de un tramo directo entre dos ciudades.
     *
     * @param origen ciudad de origen
     * @param destino ciudad destino
     * @return duración del vuelo en horas o -1 si no existe
     */
    public double obtenerDuracionTramo(String origen, String destino) {
        Vertice v = grafoTiempo.getVertice(origen);
        if (v == null) {
            return -1;
        }

        for (Arista a : v.adj) {
            if (a.dest.name.equals(destino)) {
                return a.cost;
            }
        }
        return -1;
    }

}
