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
public class Nodo {

    private String info;
    private Nodo izq, der;
    private int altura;

    // Setters
    public void setDato(String dato) {
        this.info = dato;
    }

    public void setIzquierdo(Nodo izq) {
        this.izq = izq;
    }

    public void setDerecho(Nodo der) {
        this.der = der;
    }

    // Getters
    public String getDato() {
        return this.info;
    }

    public Nodo getIzquierdo() {
        return this.izq;
    }

    public Nodo getDerecho() {
        return this.der;
    }

    public int getAltura() {
        return altura;
    }

    // Actualizar altura
    public void actualizarAltura() {
        int alturaIzq = (izq != null) ? izq.getAltura() : 0;
        int alturaDer = (der != null) ? der.getAltura() : 0;
        this.altura = Math.max(alturaIzq, alturaDer) + 1;
    }

    @Override
    public String toString() {
        return "Nodo(" + info + ")";
    }
}
