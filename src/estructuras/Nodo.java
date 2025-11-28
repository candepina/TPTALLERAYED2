/**
 * Representa un nodo dentro de un Árbol AVL.
 * 
 * Cada nodo contiene:
 * - un dato de tipo String (en este caso usado para representar códigos de asientos),
 * - referencias a su hijo izquierdo y derecho,
 * - y su altura dentro del árbol AVL.
 *
 * La clase proporciona métodos para acceder y modificar estos valores,
 * así como un método para recalcular la altura del nodo.
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
    
     /**
     * Establece el dato almacenado en el nodo.
     *
     * @param dato valor a guardar en este nodo
     */
    public void setDato(String dato) {
        this.info = dato;
    }

     /**
     * Asigna el hijo izquierdo del nodo.
     *
     * @param izq nodo que quedará como hijo izquierdo
     */
    public void setIzquierdo(Nodo izq) {
        this.izq = izq;
    }
    
    /**
     * Asigna el hijo derecho del nodo.
     *
     * @param der nodo que quedará como hijo derecho
     */
    public void setDerecho(Nodo der) {
        this.der = der;
    }

    // Getters
    
     /**
     * Retorna el dato almacenado en el nodo.
     *
     * @return cadena almacenada en el nodo
     */
    public String getDato() {
        return this.info;
    }

    /**
     * Obtiene el hijo izquierdo.
     *
     * @return referencia al nodo izquierdo, o null si no existe
     */
    public Nodo getIzquierdo() {
        return this.izq;
    }

     /**
     * Obtiene el hijo derecho.
     *
     * @return referencia al nodo derecho, o null si no existe
     */
    public Nodo getDerecho() {
        return this.der;
    }

    /**
     * Retorna la altura del nodo dentro del árbol AVL.
     *
     * @return altura del nodo
     */
    public int getAltura() {
        return altura;
    }

    // Actualizar altura
    /**
     * Actualiza la altura del nodo basado en las alturas de sus hijos.
     * 
     * Este método debe llamarse cada vez que se modifique la estructura
     * del subárbol (inserciones, eliminaciones o rotaciones).
     */
    public void actualizarAltura() {
        int alturaIzq = (izq != null) ? izq.getAltura() : 0;
        int alturaDer = (der != null) ? der.getAltura() : 0;
        this.altura = Math.max(alturaIzq, alturaDer) + 1;
    }
    
    /**
     * Devuelve una representación en texto del nodo para depuración.
     *
     * @return representación simple del nodo
     */
    @Override
    public String toString() {
        return "Nodo(" + info + ")";
    }
}
