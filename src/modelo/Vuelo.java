/**
 * Representa un vuelo individual dentro del sistema.
 *
 * Un vuelo contiene:
 * - código identificatorio,
 * - ciudad de partida y destino,
 * - precio y duración del tramo,
 * - asignación de asientos divididos en 3 secciones (A, B y C),
 * - registro de asientos reservados mediante un árbol AVL.
 *
 * La distribución de asientos intenta balancear la ocupación entre secciones.
 * Cada vuelo tiene una capacidad máxima de 30 asientos.
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
    public ArbolAVL reservas = new ArbolAVL(); //creamos el árbol AVL reservas con los códigos de asiento. arantiza búsqueda, inserción y eliminación O(log n).
    Random random = new Random();

     /**
     * Agrega un asiento asignado al registro local del vuelo.
     *
     * @param asiento código del asiento asignado
     */
    public void agregarAsiento(String asiento) {
        asientosAsignados.add(asiento);
    }
    
     /**
     * Devuelve la lista de asientos asignados en el vuelo.
     *
     * @return una lista con los códigos de asiento ya reservados
     */
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

    
     /**
     * Asigna un asiento de manera equilibrada entre secciones A, B y C.
     *
     * Selecciona siempre la sección con menor ocupación.
     * Si varias secciones tienen la misma ocupación mínima, elige una al azar.
     *
     * Inserta el asiento en el árbol AVL de reservas.
     *
     * @return código del asiento asignado
     * @throws Exception si no quedan asientos disponibles en ninguna sección
     */
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
        reservas.insertar(asiento); // se almacena en AVL
        System.out.println("Porcentaje de ocupación: " + calcularOcupacion() + "%");
        return asiento;
    }

    /**
     * Calcula el porcentaje total de ocupación del vuelo.
     *
     * @return porcentaje (0 a 100)
     */
    public double calcularOcupacion() {
        final int CAPACIDAD_TOTAL = 30;
        int ocupacion_total = ocupacion_seccion_A + ocupacion_seccion_B + ocupacion_seccion_C;
        double porcentaje_ocupacion = (ocupacion_total * 100) / CAPACIDAD_TOTAL;
        return porcentaje_ocupacion;
    }

    /**
     * Genera un código de vuelo aleatorio entre 0 y 99999.
     *
     * @return código asignado
     */
    public int asignarCodigoVuelo() {
        Random codigo = new Random();
        codigo_vuelo = codigo.nextInt(100000);
        return codigo_vuelo;
    }

    
    /**
     * Muestra una representación textual del vuelo.
     */
    @Override
    public String toString() {
        return "Vuelo"
                + "codigo =" + codigo_vuelo
                + ", partida ='" + partida + '\''
                + ", destino ='" + destino + '\''
                + ", precio =" + precio + '\''
                + ", duración =" + duracion
                ;
    }

}
