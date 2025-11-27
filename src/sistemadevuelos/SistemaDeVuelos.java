/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadevuelos;

import estructuras.Grafo;
import estructuras.Nodo;
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
        System.out.println("Opciones:\n * BSAS\n * Cordoba\n * Mendoza\n * Bariloche\n * Sta Fe\n * Posadas\n * Sta Cruz");
        partida = entrada.nextLine();
        switch (partida) {
            case "BSAS":
                partida = "BSAS";
                break;
            case "Cordoba":
                partida = "Cordoba";
                break;
            case "Mendoza":
                partida = "Mendoza";
                break;
            case "Bariloche":
                partida = "Bariloche";
                break;
            case "Sta Fe":
                partida = "Sta Fe";
                break;
            case "Posadas":
                partida = "Posadas";
                break;
            case "Sta Cruz":
                partida = "Sta Cruz";
                break;
            default:
                System.out.println("No hay vuelos disponibles desde la ciudad ingresada.");
                break;
        }
        System.out.println("Ingrese ciudad de destino");
        destino = entrada.nextLine();
        switch (destino) {
            case "BSAS":
                destino = "BSAS";
                break;
            case "Cordoba":
                destino = "Cordoba";
                break;
            case "Mendoza":
                destino = "Mendoza";
                break;
            case "Bariloche":
                destino = "Bariloche";
                break;
            case "Sta Fe":
                destino = "Sta Fe";
                break;
            case "Posadas":
                destino = "Posadas";
                break;
            case "Sta Cruz":
                destino = "Sta Cruz";
                break;
            default:
                System.out.println("No hay vuelos disponibles hasta la ciudad ingresada.");
                break;
        }

        Grafo grafoCosto = new Grafo();
        Grafo grafoTiempo = new Grafo();
        //grafo para probar aeropuertos

        grafoCosto.agregarArista("BSAS", "Cordoba", 120);
        grafoCosto.agregarArista("BSAS", "Mendoza", 150);
        grafoCosto.agregarArista("BSAS", "Bariloche", 220);
        grafoCosto.agregarArista("BSAS", "Sta Fe", 100);
        grafoCosto.agregarArista("BSAS", "Posadas", 140);
        grafoCosto.agregarArista("Cordoba", "Mendoza", 90);
        grafoCosto.agregarArista("Mendoza", "Cordoba", 90);
        grafoCosto.agregarArista("Cordoba", "Sta Fe", 70);
        grafoCosto.agregarArista("Sta Fe", "Cordoba", 70);
        grafoCosto.agregarArista("Mendoza", "Bariloche", 120);
        grafoCosto.agregarArista("Bariloche", "Mendoza", 120);
        grafoCosto.agregarArista("Bariloche", "Sta Cruz", 160);
        grafoCosto.agregarArista("Sta Cruz", "Bariloche", 160);
        grafoCosto.agregarArista("Mendoza", "Sta Cruz", 170);
        grafoCosto.agregarArista("Sta Cruz", "Mendoza", 170);
        grafoCosto.agregarArista("Sta Fe", "Posadas", 80);
        grafoCosto.agregarArista("Posadas", "Sta Fe", 80);

        //double precio = grafoAeropuertoCosto.mostrarResultadoDijkstraParticularCosto(partida, destino);
        //System.out.println("El precio base del vuelo es: $" + precio);
        // boolean vueloDirecto = grafoAeropuertoCosto.esDirecto(partida, destino);
        //double precioFinal = grafoAeropuertoCosto.recargo(precio, vueloDirecto);
        //System.out.println("El precio final del vuelo es: $" + precioFinal);
        grafoTiempo.agregarArista("BSAS", "Cordoba", 1.2);
        grafoTiempo.agregarArista("BSAS", "Mendoza", 1.7);
        grafoTiempo.agregarArista("BSAS", "Bariloche", 2.2);
        grafoTiempo.agregarArista("BSAS", "Sta Fe", 1.0);
        grafoTiempo.agregarArista("BSAS", "Posadas", 1.5);
        grafoTiempo.agregarArista("Cordoba", "Mendoza", 1.1);
        grafoTiempo.agregarArista("Mendoza", "Cordoba", 1.1);
        grafoTiempo.agregarArista("Cordoba", "Sta Fe", 0.8);
        grafoTiempo.agregarArista("Sta Fe", "Cordoba", 0.8);
        grafoTiempo.agregarArista("Mendoza", "Bariloche", 1.6);
        grafoTiempo.agregarArista("Bariloche", "Mendoza", 1.6);
        grafoTiempo.agregarArista("Bariloche", "Sta Cruz", 2.0);
        grafoTiempo.agregarArista("Sta Cruz", "Bariloche", 2.0);
        grafoTiempo.agregarArista("Mendoza", "Sta Cruz", 2.6);
        grafoTiempo.agregarArista("Sta Cruz", "Mendoza", 2.6);
        grafoTiempo.agregarArista("Sta Fe", "Posadas", 1.2);
        grafoTiempo.agregarArista("Posadas", "Sta Fe", 1.2);

        //grafoAeropuertoTiempo.mostrarResultadoDijkstraParticularTiempo(partida, destino);
        ServicioRutas servicio = new ServicioRutas(grafoCosto, grafoTiempo);
        grafoCosto.dijkstra(partida);
        grafoTiempo.dijkstra(partida);
        System.out.println("\n DIJKSTRA COSTO ");
        servicio.mostrarResultadoDijkstraCosto(partida);

        System.out.println("\nDIJKSTRA TIEMPO ");
        servicio.mostrarResultadoDijkstraTiempo(partida);

        Vuelo vuelo = new Vuelo();
        System.out.println("\nDETALLES DEL VUELO");
        servicio.mostrarDetallesParcial(partida, destino);

        System.out.println("¿Desea seguir con la reserva? (Si/No)");
        String confirmacion = entrada.nextLine();

        switch (confirmacion) {
            case "Si":
                int codigo_vuelo=vuelo.asignarCodigoVuelo();
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
                servicio.mostrarDetallesFinal(partida, destino, vuelo, cantidad_pasajeros,codigo_vuelo);

                break;
            case "No":
                System.out.println("Reserva cancelada.");
                break;
        }

        System.out.println("\nPrueba de búsqueda. Inserte asiento a buscar: ");
        String asiento_buscar=entrada.next();
        Nodo existe_buscar=vuelo.reservas.buscar(asiento_buscar);
        if(existe_buscar!=null){
            System.out.println("El asiento se encontró reservado.");
            System.out.println(existe_buscar);
        }else{
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
    }

}
