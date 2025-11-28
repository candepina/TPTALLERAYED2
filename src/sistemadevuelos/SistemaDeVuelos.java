/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*Vuelo vuelo = new Vuelo();

        for (int i = 0; i < 30; i++) {
            try {
                String asiento = vuelo.reservar();
                System.out.println("Asiento asignado: " + asiento);
            } catch (Exception e) {
                System.out.println("Error al reservar asiento: " + e.getMessage());
            }
        }
        System.out.println("Ocupación final: ");
        System.out.println("Sección A: " + vuelo.ocupacion_seccion_A);
        System.out.println("Sección B: " + vuelo.ocupacion_seccion_B);
        System.out.println("Sección C: " + vuelo.ocupacion_seccion_C);

        System.out.println("Reservas: ");
        vuelo.reservas.imprimirIn();
         */
        String partida;
        String destino;
        Scanner entrada = new Scanner(System.in);
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
        //grafo para probar aeropuertos

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

        //double precio = grafoAeropuertoCosto.mostrarResultadoDijkstraParticularCosto(partida, destino);
        //System.out.println("El precio base del vuelo es: $" + precio);
        // boolean vueloDirecto = grafoAeropuertoCosto.esDirecto(partida, destino);
        //double precioFinal = grafoAeropuertoCosto.recargo(precio, vueloDirecto);
        //System.out.println("El precio final del vuelo es: $" + precioFinal);
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

        //grafoAeropuertoTiempo.mostrarResultadoDijkstraParticularTiempo(partida, destino);
        ServicioRutas servicio = new ServicioRutas(grafoCosto, grafoTiempo);
        grafoCosto.dijkstra(partida);
        //System.out.println("DEBUG - grafoCosto.dist Bariloche = " + grafoCosto.getVertice("Bariloche").dist);
        grafoTiempo.dijkstra(partida);
        //System.out.println("DEBUG - grafoTiempo.dist Bariloche = " + grafoTiempo.getVertice("Bariloche").dist);
        System.out.println("\n DIJKSTRA COSTO ");
        servicio.mostrarResultadoDijkstraCosto(partida);

        System.out.println("\nDIJKSTRA TIEMPO ");
        servicio.mostrarResultadoDijkstraTiempo(partida);

        //Vuelo vuelo = null;
        // boolean resultado = ServicioRutas.esDirecto(partida, destino);
        // if (resultado == true) {
        //vuelo = new Vuelo();
        //vuelo.setPartida(partida);
        //vuelo.setDestino(destino);
        //System.out.println(vuelo.partida);
        //System.out.println(vuelo.destino);
        // } else {
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

            //System.out.println("Vuelo creado para tramo: " + origenTramo + " → " + destinoTramo);
        }
        System.out.println(vuelosTramo.toString() + "\n"); //para debug
        servicio.mostrarDetallesParcial(vuelosTramo);
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

        System.out.println("\nPRUEBA DFS: ");
        grafoCosto.DFS(partida);

        System.out.println("\nPRUEBA BFS:");
        grafoCosto.BFS(partida);
    }
}
/*System.out.println("\nDETALLES DEL VUELO");
            servicio.mostrarDetallesParcial(partida, destino);

            System.out.println("¿Desea seguir con la reserva? (Si/No)");
            String confirmacion = entrada.nextLine();

            switch (confirmacion) {
                case "Si":

                    //int codigo_vuelo = vuelo.asignarCodigoVuelo();
                    System.out.println("Ingrese la cantidad de pasajeros: ");
                    int cantidad_pasajeros = entrada.nextInt();

                    for (int i = 0; i < cantidad_pasajeros; i++) {
                        try {
                            String asiento = vuelo.reservar();
                            System.out.println("Asiento asignado: " + asiento);
                        } catch (Exception e) {
                            System.out.println("Error al reservar asiento: " + e.getMessage());
                        }
                    }
                    System.out.println("Ocupación final: ");
                    System.out.println("Sección A: " + vuelo.ocupacion_seccion_A);
                    System.out.println("Sección B: " + vuelo.ocupacion_seccion_B);
                    System.out.println("Sección C: " + vuelo.ocupacion_seccion_C);

                    System.out.println("Los asientos asignados son: ");
                    vuelo.reservas.imprimirIn();
                    servicio.mostrarDetallesFinal(partida, destino, vuelo, cantidad_pasajeros, codigo_vuelo);

                    break;
                case "No":
                    System.out.println("Reserva cancelada.");
                    break;
            }

            System.out.println("\nPrueba de búsqueda. Inserte asiento a buscar: ");
            String asiento_buscar = entrada.next();
            Nodo existe_buscar = vuelo.reservas.buscar(asiento_buscar);
            if (existe_buscar != null) {
                System.out.println("El asiento se encontró reservado.");
                System.out.println(existe_buscar);
            } else {
                System.out.println("El asiento no se encontró reservado.");
            }

            System.out.println("\nPrueba de eliminación. Indicar asiento a eliminar: ");
            String asiento_eliminar = entrada.next();

            Nodo existe_eliminar = vuelo.reservas.buscar(asiento_eliminar);
            if (existe_eliminar != null) {
                vuelo.reservas.eliminar(asiento_eliminar);
                System.out.println("Asiento " + asiento_eliminar + " eliminado correctamente.");
            } else {
                System.out.println("El asiento " + asiento_eliminar + " no se encontró reservado. No se puede eliminar.");
            }
            System.out.println("\nDisposición de asientos en el vuelo: ");
            vuelo.reservas.imprimirIn();

            System.out.println("\nPrueba de DFS: ");
            grafoCosto.DFS(partida);

            System.out.println("\nPrueba de BFS:");
            grafoCosto.BFS(partida);
        }*/
                
        
//}
