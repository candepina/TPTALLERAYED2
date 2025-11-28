/**
 * Clase principal del sistema. Se encarga de interactuar con el usuario,
 * cargar los grafos de rutas (costos y tiempos), ejecutar Dijkstra,
 * construir los vuelos correspondientes, gestionar reservas, y mostrar
 * resultados parciales y finales.
 *
 * Contiene el menú completo, la lógica de entrada del usuario y la
 * prueba de funcionalidades como búsqueda y eliminación de asientos
 * en el árbol AVL.
 */
package sistemadevuelos;

import estructuras.Grafo;
import estructuras.Nodo;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Vuelo;
import servicios.ServicioRutas;

/**
 *
 * @author cande
 */
public class SistemaDeVuelos {

/**
 * Método principal del sistema de vuelos.
 * 
 * Flujo general:
 * 1. Solicita al usuario ciudad de origen y destino.
 * 2. Construye los grafos de costo y tiempo entre las ciudades.
 * 3. Ejecuta Dijkstra para obtener caminos mínimos.
 * 4. Construye los objetos Vuelo para cada tramo del camino.
 * 5. Muestra los detalles parciales del vuelo.
 * 6. Pregunta si el usuario desea confirmar la reserva.
 * 7. Si confirma:
 *      - Reserva asientos para todos los pasajeros.
 *      - Muestra asientos reservados y ocupación.
 *      - Muestra detalles finales con recargos.
 * 8. Realiza pruebas de búsqueda / eliminación de asientos.
 * 9. Ejecuta BFS y DFS como demostración sobre el grafo.
 *
 *
 */
    public static void main(String[] args) {
        String partida;
        String destino;
        Scanner entrada = new Scanner(System.in);
        //Entrada y validación de ciudad de partida
        /**
         * Solicita la ciudad de partida al usuario y la normaliza a mayúsculas.
         * Solo permite ciudades cargadas en el sistema.
         */

        System.out.println("Ingrese ciudad de partida");
        System.out.println("Opciones:\n * BSAS\n * Cordoba\n * Mendoza\n * Bariloche\n * Santa Fe\n * Posadas\n * Santa Cruz");
        partida = entrada.nextLine().toUpperCase();
        switch (partida) {
            case "BSAS":
                partida = "BSAS";
                break;
            case "CORDOBA":
                partida = "CORDOBA";
                break;
            case "MENDOZA":
                partida = "MENDOZA";
                break;
            case "BARILOCHE":
                partida = "BARILOCHE";
                break;
            case "SANTA FE":
                partida = "SANTA FE";
                break;
            case "POSADAS":
                partida = "POSADAS";
                break;
            case "SANTA CRUZ":
                partida = "SANTA CRUZ";
                break;
            default:
                System.out.println("No hay vuelos disponibles desde la ciudad ingresada.");
                break;
        }
        System.out.println("Ingrese ciudad de destino");
        destino = entrada.nextLine().toUpperCase();
        switch (destino) {
            case "BSAS":
                destino = "BSAS";
                break;
            case "CORDOBA":
                destino = "CORDOBA";
                break;
            case "MENDOZA":
                destino = "MENDOZA";
                break;
            case "BARILCOHE":
                destino = "BARILOCHE";
                break;
            case "SANTA FE":
                destino = "SANTA FE";
                break;
            case "POSADAS":
                destino = "POSADAS";
                break;
            case "SANTA CRUZ":
                destino = "SANTA CRUZ";
                break;
            default:
                System.out.println("No hay vuelos disponibles hasta la ciudad ingresada.");
                break;
        }

        Grafo grafoCosto = new Grafo();
        Grafo grafoTiempo = new Grafo();

        //Inicialización del grafo de costos
        /**
         * Carga en el grafo los costos directos entre ciudades disponibles. El
         * grafo es dirigido: cada arista representa un vuelo directo.
         */
        grafoCosto.agregarArista("BSAS", "CORDOBA", 120000);
        grafoCosto.agregarArista("BSAS", "MENDOZA", 150000);
        grafoCosto.agregarArista("BSAS", "BARILOCHE", 220000);
        grafoCosto.agregarArista("BSAS", "SANTA FE", 100000);
        grafoCosto.agregarArista("BSAS", "POSADAS", 140000);

        grafoCosto.agregarArista("CORDOBA", "MENDOZA", 90000);
        grafoCosto.agregarArista("MENDOZA", "CORDOBA", 90000);
        grafoCosto.agregarArista("CORDOBA", "SANTA FE", 70000);
        grafoCosto.agregarArista("SANTA FE", "CORDOBA", 70000);

        grafoCosto.agregarArista("MENDOZA", "BARILOCHE", 120000);
        grafoCosto.agregarArista("BARILOCHE", "MENDOZA", 120000);

        grafoCosto.agregarArista("BARILOCHE", "SANTA CRUZ", 160000);
        grafoCosto.agregarArista("SANTA CRUZ", "BARILOCHE", 160000);

        grafoCosto.agregarArista("MENDOZA", "SANTA CRUZ", 170000);
        grafoCosto.agregarArista("SANTA CRUZ", "MENDOZA", 170000);

        grafoCosto.agregarArista("SANTA FE", "POSADAS", 80000);
        grafoCosto.agregarArista("POSADAS", "SANTA FE", 80000);

        //Inicialización del grafo de tiempos
        /**
         * Carga en el grafo las duraciones estimadas de vuelo entre ciudades.
         * Las aristas son mayormente bidireccionales.
         */
        grafoTiempo.agregarArista("BSAS", "CORDOBA", 1.2);
        grafoTiempo.agregarArista("BSAS", "MENDOZA", 1.7);
        grafoTiempo.agregarArista("BSAS", "BARILOCHE", 2.2);
        grafoTiempo.agregarArista("BSAS", "SANTA FE", 1.0);
        grafoTiempo.agregarArista("BSAS", "POSADAS", 1.5);

        grafoTiempo.agregarArista("CORDOBA", "MENDOZA", 1.1);
        grafoTiempo.agregarArista("MENDOZA", "CORDOBA", 1.1);
        grafoTiempo.agregarArista("CORDOBA", "SANTA FE", 0.8);
        grafoTiempo.agregarArista("SANTA FE", "CORDOBA", 0.8);

        grafoTiempo.agregarArista("MENDOZA", "BARILOCHE", 1.6);
        grafoTiempo.agregarArista("BARILOCHE", "MENDOZA", 1.6);

        grafoTiempo.agregarArista("BARILOCHE", "SANTA CRUZ", 2.0);
        grafoTiempo.agregarArista("SANTA CRUZ", "BARILOCHE", 2.0);

        grafoTiempo.agregarArista("MENDOZA", "SANTA CRUZ", 2.6);
        grafoTiempo.agregarArista("SANTA CRUZ", "MENDOZA", 2.6);

        grafoTiempo.agregarArista("SANTA FE", "POSADAS", 1.2);
        grafoTiempo.agregarArista("POSADAS", "SANTA FE", 1.2);

        //Ejecución del algoritmo de Dijkstra para costo y tiempo
        /**
         * Ejecuta Dijkstra desde la ciudad de partida para: obtener todos los
         * costos mínimos, obtener todas las duraciones mínimas. Luego muestra
         * los resultados al usuario.
         */
        ServicioRutas servicio = new ServicioRutas(grafoCosto, grafoTiempo);

        grafoCosto.dijkstra(partida);
        grafoTiempo.dijkstra(partida);

        System.out.println("\n DIJKSTRA COSTO ");
        servicio.mostrarResultadoDijkstraCosto(partida);

        System.out.println("\nDIJKSTRA TIEMPO ");
        servicio.mostrarResultadoDijkstraTiempo(partida);

        //Construcción de vuelos para cada tramo del camino mínimo
        /**
         * A partir del camino generado con Dijkstra, se crean objetos Vuelo
         * para cada tramo directo del recorrido, asignando costo, duración,
         * código de vuelo y ciudades correspondientes.
         */
        ArrayList<String> camino = servicio.obtenerCamino(partida, destino);
        System.out.println(camino);
        ArrayList<Vuelo> vuelosTramo = new ArrayList<>();

        for (int i = 0; i < camino.size() - 1; i++) {
            String origenTramo = camino.get(i);
            String destinoTramo = camino.get(i + 1);
            double costoTramo = servicio.obtenerCostoTramo(origenTramo, destinoTramo);
            double duracionTramo = servicio.obtenerDuracionTramo(origenTramo, destinoTramo);
            Vuelo v = new Vuelo();
            v.setPartida(origenTramo);
            v.setDestino(destinoTramo);
            v.setCodigo_vuelo(v.asignarCodigoVuelo());
            v.setPrecio(costoTramo);
            v.setDuracion(duracionTramo);
            vuelosTramo.add(v);

        }
        System.out.println(vuelosTramo.toString() + "\n"); //para debug
        servicio.mostrarDetallesParcial(vuelosTramo);

        //Confirmación de la reserva
        /**
         * Pregunta al usuario si desea realizar la reserva. Según la cantidad
         * de pasajeros: - asigna asientos automáticamente - calcula ocupación
         * por sección - almacena los asientos en el AVL de cada vuelo
         */
        System.out.println("¿Desea seguir con la reserva? (Si/No)");
        String confirmacion = entrada.nextLine().toUpperCase();

        switch (confirmacion) {
            case "SI":
                System.out.println("Ingrese la cantidad de pasajeros: ");
                int cantidad_pasajeros = entrada.nextInt();

                for (Vuelo tramo : vuelosTramo) {
                    System.out.println("Reserva para el vuelo con tramo " + tramo.getPartida() + " -> " + tramo.getDestino() + ": ");
                    for (int i = 0; i < cantidad_pasajeros; i++) {
                        try {
                            String asiento = tramo.reservar();
                            System.out.println("Asiento asignado: " + asiento);
                            tramo.agregarAsiento(asiento);
                        } catch (Exception e) {
                            System.out.println("Error al reservar asiento: " + e.getMessage());
                        }
                    }
                    System.out.println("Ocupación del vuelo: ");
                    System.out.println("Sección A: " + tramo.ocupacion_seccion_A);
                    System.out.println("Sección B: " + tramo.ocupacion_seccion_B);
                    System.out.println("Sección C: " + tramo.ocupacion_seccion_C);

                    System.out.println("Asientos reservados para este vuelo: ");
                    tramo.reservas.imprimirIn();

                }

                servicio.mostrarDetallesFinal(vuelosTramo, cantidad_pasajeros, partida, destino);
                break;
            case "NO":

                System.out.println("Reserva cancelada.");
                break;

        }

        //Prueba de búsqueda de asientos
        /**
         * Verifica si el asiento "A2" está reservado en cada tramo del vuelo
         * utilizando búsqueda en el árbol AVL.
         */
        System.out.println("\nPRUEBA DE BÚSQUEDA DE ASIENTOS: ");

        for (Vuelo tramo : vuelosTramo) {
            Nodo existe_buscar = tramo.reservas.buscar("A2");
            if (existe_buscar != null) {
                System.out.println("El asiento se encontró reservado en el tramo " + tramo.getPartida() + " -> " + tramo.getDestino());
                System.out.println(existe_buscar);
            } else {
                System.out.println("El asiento no se encontró reservado en el tramo " + tramo.getPartida() + " -> " + tramo.getDestino());
            }

        }

        //Prueba de eliminación de asientos
        /**
         * Intenta eliminar el asiento "A2" del árbol AVL de cada vuelo,
         * demostrando el funcionamiento del método eliminar().
         */
        System.out.println("\nPRUEBA DE ELIMINACIÓN DE ASIENTOS: ");
        for (Vuelo tramo : vuelosTramo) {
            Nodo existe_eliminar = tramo.reservas.buscar("A2");
            if (existe_eliminar != null) {
                System.out.println("El asiento a eliminar se encontró reservado en el tramo " + tramo.getPartida() + " -> " + tramo.getDestino());
                tramo.reservas.eliminar("A2");
                System.out.println("El asiento A2 se eliminó correctamente");
                System.out.println("La nueva disposición de asientos es: ");
                tramo.reservas.imprimirIn();
            } else {
                System.out.println("El asiento a eliminar no se encontró reservado en el tramo " + tramo.getPartida() + " -> " + tramo.getDestino());
            }

        }

        //Prueba de eliminación de asientos
        /**
         * Intenta eliminar el asiento "A2" del árbol AVL de cada vuelo,
         * demostrando el funcionamiento del método eliminar().
         */
        System.out.println("\nPRUEBA DFS: ");
        grafoCosto.DFS(partida);

        System.out.println("\nPRUEBA BFS:");
        grafoCosto.BFS(partida);
    }
}
