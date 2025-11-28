/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import estructuras.ArbolAVL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author cande
 */
public class Vuelo {

    public ArrayList<String> asientosAsignados = new ArrayList<>();
    public double precio;
    public double duracion;
    public int codigo_vuelo;
    public String partida, destino;
    public int ocupacion_seccion_A = 0, ocupacion_seccion_B = 0, ocupacion_seccion_C = 0;
    public List<String> asientos_disponibles_A = new ArrayList<>(Arrays.asList("A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10"));
    public List<String> asientos_disponibles_B = new ArrayList<>(Arrays.asList("B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10"));
    public List<String> asientos_disponibles_C = new ArrayList<>(Arrays.asList("C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10"));
    public ArbolAVL reservas = new ArbolAVL(); //creamos el árbol AVL reservas con los códigos de asiento
    Random random = new Random();

    public void agregarAsiento(String asiento) {
        asientosAsignados.add(asiento);
    }

    public ArrayList<String> getAsientosAsignados() {
        return asientosAsignados;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public double getDuracion() {
        return duracion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCodigo_vuelo(int codigo_vuelo) {
        this.codigo_vuelo = codigo_vuelo;
    }

    public int getCodigo_vuelo() {
        return codigo_vuelo;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getPartida() {
        return partida;
    }

    public String getDestino() {
        return destino;
    }

    public String reservar() throws Exception {
        int minimo = Math.min(ocupacion_seccion_A, Math.min(ocupacion_seccion_B, ocupacion_seccion_C));
        List<Character> seccionesPosibles = new ArrayList<>();
        if (ocupacion_seccion_A == minimo && ocupacion_seccion_A < 10) {
            seccionesPosibles.add('A');

        }
        if (ocupacion_seccion_B == minimo && ocupacion_seccion_B < 10) {
            seccionesPosibles.add('B');
        }
        if (ocupacion_seccion_C == minimo && ocupacion_seccion_C < 10) {
            seccionesPosibles.add('C');
        }

        if (seccionesPosibles.isEmpty()) {
            throw new Exception("Avión lleno, no hay asientos disponibles");

        }
        char seccionElegida = seccionesPosibles.get(random.nextInt(seccionesPosibles.size()));

        String asiento = "";
        switch (seccionElegida) {
            case 'A':
                asiento = asientos_disponibles_A.remove(random.nextInt(asientos_disponibles_A.size()));
                ocupacion_seccion_A++;
                break;
            case 'B':
                asiento = asientos_disponibles_B.remove(random.nextInt(asientos_disponibles_B.size()));
                ocupacion_seccion_B++;
                break;
            case 'C':
                asiento = asientos_disponibles_C.remove(random.nextInt(asientos_disponibles_C.size()));
                ocupacion_seccion_C++;
                break;
        }
        reservas.insertar(asiento);
        System.out.println("Porcentaje de ocupación: " + calcularOcupacion() + "%");
        return asiento;
    }

    public double calcularOcupacion() {
        final int CAPACIDAD_TOTAL = 30;
        int ocupacion_total = ocupacion_seccion_A + ocupacion_seccion_B + ocupacion_seccion_C;
        double porcentaje_ocupacion = (ocupacion_total * 100) / CAPACIDAD_TOTAL;
        return porcentaje_ocupacion;
    }

    public int asignarCodigoVuelo() {
        Random codigo = new Random();
        codigo_vuelo = codigo.nextInt(100000);
        return codigo_vuelo;
    }

    @Override
    public String toString() {
        return "Vuelo"
                + "codigo =" + codigo_vuelo
                + ", partida ='" + partida + '\''
                + ", destino ='" + destino + '\''
                + ", precio =" + precio + '\''
                + ", duración =" + duracion
                + +'}';
    }

}
