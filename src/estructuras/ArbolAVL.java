/**
 * Implementa un Árbol AVL, una estructura de datos auto-balanceada
 * que permite almacenar elementos ordenados con operaciones de inserción,
 * eliminación y búsqueda en tiempo O(log n).
 *
 * Cada inserción o eliminación reorganiza el árbol mediante rotaciones
 * para mantener el factor de balance entre -1 y 1.
 *
 * Se utiliza en el sistema para gestionar asientos reservados en un vuelo
 * utilizando su código de asiento,
 * permitiendo búsquedas e inserciones eficientes.
 */
package estructuras;

/**
 *
 * @author cande
 */
public class ArbolAVL {

    Nodo raiz;

    /**
     * Crea un árbol AVL vacío.
     */
    public ArbolAVL() {
        this.raiz = null;

    }

    /**
     * Inserta un nuevo dato en el árbol AVL.
     *
     * @param x valor que se desea insertar
     * @return Nodo raíz actualizado después de la inserción
     * @throws Exception si el elemento ya existe en el árbol
     */
    public Nodo insertar(String x) throws Exception {
        raiz = insertar(x, raiz);
        return raiz;
    }

    //MÉTODO PARA INSERTAR
    /**
     * Inserta un dato en el subárbol cuya raíz es el nodo recibido. Rebalancea
     * el árbol si es necesario.
     *
     * @param info dato a insertar
     * @param nodo raíz del subárbol donde insertar
     * @return nodo raíz actualizado luego de insertar y balancear
     * @throws Exception si el elemento ya existe
     */
    private Nodo insertar(String info, Nodo nodo) throws Exception {
        if (nodo == null) {
            Nodo nuevo = new Nodo();
            nuevo.setDato(info);
            nuevo.setIzquierdo(null);
            nuevo.setDerecho(null);
            nuevo.actualizarAltura();
            return nuevo;
        } else if (info.compareTo(nodo.getDato()) < 0) {

            nodo.setIzquierdo(insertar(info, nodo.getIzquierdo()));
        } else if (info.compareTo(nodo.getDato()) > 0) {
            nodo.setDerecho(insertar(info, nodo.getDerecho()));
        } else {
            throw new Exception("Elemento duplicado");
        }
        return balancear(nodo);
    }

    //MÉTODO PARA BUSCAR (privado -> el que hace todo)
    /**
     * Busca un dato dentro del subárbol cuya raíz es el nodo recibido.
     *
     * @param dato valor a buscar
     * @param nodo nodo raíz del subárbol actual
     * @return el nodo que contiene el dato, o null si no existe
     */
    private Nodo buscar(String dato, Nodo nodo) {
        if (nodo != null) { //verificamos que el nodo actual exista en el árbol. Si es null, no está en el árbol y devuelve null
            if (nodo.getDato().compareTo(dato) > 0) {
                return buscar(dato, nodo.getIzquierdo());
            } else if (nodo.getDato().compareTo(dato) < 0) {
                return buscar(dato, nodo.getDerecho());
            }

        }
        return nodo; //si ninguna de las condicione s anteriores se cumple, significa que encontramos el nodo. Lo retorna
    }

    //MÉTODO PARA BUSCAR
    /**
     * Busca un dato dentro del árbol desde la raíz. Llama al privado para que
     * empece desde la raíz
     *
     * @param dato valor a buscar
     * @return el nodo encontrado o null si no existe
     */
    public Nodo buscar(String dato) {
        return buscar(dato, this.raiz);
    }

    //MÉTODO BÚSQUEDA EN PRE ORDEN
    /**
     * Recorre el árbol en pre-orden desde un nodo dado.
     *
     * @param recorrer nodo inicial del recorrido
     */
    private void imprimirPre(Nodo recorrer) {
        if (recorrer != null) { //caso base
            System.out.println(recorrer.getDato() + " ");
            imprimirPre(recorrer.getIzquierdo());
            imprimirPre(recorrer.getDerecho());
        }
    }

    /**
     * Imprime los elementos del árbol en recorrido pre-orden. (Nodo - Izquierdo
     * - Derecho)
     */
    public void imprimirPre() {
        imprimirPre(raiz);
        System.out.println();
    }

    //MÉTODO DE BÚSQUEDA EN IN ORDEN
    /**
     * Recorre el subárbol en in-orden desde el nodo dado.
     *
     * @param recorrer nodo inicial
     */
    private void imprimirIn(Nodo recorrer) {
        if (recorrer != null) {
            imprimirIn(recorrer.getIzquierdo());
            System.out.println(recorrer.getDato() + " ");
            imprimirIn(recorrer.getDerecho());
        }
    }

    /**
     * Imprime los elementos del árbol en recorrido in-orden. (Izquierdo - Nodo
     * - Derecho) El resultado es una lista ordenada de elementos.
     */
    public void imprimirIn() {
        imprimirIn(raiz);
        System.out.println();
    }

    //MÉTODO DE BÚSQUEDA EN POST ORDEN
    /**
     * Recorre el subárbol en post-orden desde el nodo dado.
     *
     * @param recorrer nodo inicial
     */
    private void imprimirPost(Nodo recorrer) {
        if (recorrer != null) {
            imprimirPost(recorrer.getIzquierdo());
            imprimirPost(recorrer.getDerecho());
            System.out.println(recorrer.getDato() + " ");
        }
    }

    /**
     * Imprime los elementos del árbol en recorrido post-orden. (Izquierdo -
     * Derecho - Nodo)
     */
    public void imprimirPost() {
        imprimirPost(raiz);
        System.out.println();
    }

    //MÉTODOS DE ELIMINACIÓN
    /**
     * Devuelve el nodo con el valor mínimo del subárbol. Método de apoyo para
     * método eliminar
     *
     * @param nodo nodo raíz del subárbol
     * @return nodo con el valor mínimo
     */
    private Nodo buscarMinimo(Nodo nodo) {
        if (nodo != null) {
            while (nodo.getIzquierdo() != null) {
                nodo = nodo.getIzquierdo();
            }
        }
        return nodo;
    }

    /**
     * Devuelve el nodo con el valor máximo del subárbol. Método de apoyo para
     * método eliminar
     *
     * @param nodo nodo raíz del subárbol
     * @return nodo con el valor máximo
     */
    private Nodo buscarMayor(Nodo nodo) {
        if (nodo != null) {
            while (nodo.getDerecho() != null) {
                nodo = nodo.getDerecho();
            }
        }
        return nodo;
    }

    /**
     * Elimina el nodo con el valor mínimo del subárbol.Método de apoyo para
     * método eliminar
     *
     * @param nodo raíz del subárbol actual
     * @return nueva raíz del subárbol luego de eliminar
     */
    private Nodo eliminarMinimo(Nodo nodo) {
        if (nodo.getIzquierdo() != null) {
            nodo.setIzquierdo(eliminarMinimo(nodo.getIzquierdo()));
            return nodo;
        } else {
            return nodo.getDerecho();
        }
    }

    /**
     * Elimina el nodo con el valor máximo del subárbol.Método de apoyo para
     * método eliminar
     *
     * @param nodo raíz del subárbol actual
     * @return nueva raíz del subárbol luego de eliminar
     */
    private Nodo eliminarMayor(Nodo nodo) {
        if (nodo.getDerecho() != null) {
            nodo.setDerecho(eliminarMayor(nodo.getDerecho()));
            return nodo;
        } else {
            return nodo.getIzquierdo();
        }
    }

    /**
     * Elimina un dato del subárbol cuya raíz es el nodo recibido. Rebalancea el
     * árbol si es necesario.
     *
     * Maneja los tres casos: - nodo hoja - nodo con un hijo - nodo con dos
     * hijos (se reemplaza con su sucesor)
     *
     * @param x dato a eliminar
     * @param nodo raíz del subárbol actual
     * @return nodo raíz actualizado
     */
    private Nodo eliminar(String x, Nodo nodo) {
        if (nodo != null) {
            if (nodo.getDato().compareTo(x) > 0) {
                nodo.setIzquierdo(eliminar(x, nodo.getIzquierdo()));
            } else if (nodo.getDato().compareTo(x) < 0) {
                nodo.setDerecho(eliminar(x, nodo.getDerecho()));
            } else { // encontró el nodo
                // caso 3: nodo con 2 hijos
                if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
                    nodo.setDato(buscarMinimo(nodo.getDerecho()).getDato());
                    nodo.setDerecho(eliminarMinimo(nodo.getDerecho()));
                } else {
                    // casos 1 y 2: hoja o con un hijo
                    nodo = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo() : nodo.getDerecho();
                }
            }
        }

        return balancear(nodo);
    }

    /**
     * Elimina un dato del árbol AVL comenzando desde la raíz.
     *
     * @param x dato a eliminar
     */
    public void eliminar(String x) {
        this.raiz = eliminar(x, this.raiz);
    }

    /**
     * Realiza una rotación simple a la derecha.
     *
     * @param k2 nodo desbalanceado
     * @return nueva raíz del subárbol rotado
     */
    public Nodo rotacionSimpleDer(Nodo k2) {
        Nodo k1 = k2.getIzquierdo();
        k2.setIzquierdo(k1.getDerecho());
        k1.setDerecho(k2);
        return k1;
    }

    /**
     * Realiza una rotación simple a la izquierda.
     *
     * @param k1 nodo desbalanceado
     * @return nueva raíz del subárbol rotado
     */
    public Nodo rotacionSimpleIzq(Nodo k1) {
        if (k1 == null || k1.getDerecho() == null) {
            return k1; // nada que rotar
        }
        Nodo k2 = k1.getDerecho();
        k1.setDerecho(k2.getIzquierdo());
        k2.setIzquierdo(k1);
        return k2;
    }

    /**
     * Realiza una rotación doble derecha-izquierda.
     *
     * @param k3 nodo desbalanceado
     * @return nueva raíz del subárbol rotado
     */
    public Nodo rotacionDobleDerIzq(Nodo k3) {
        if (k3 == null || k3.getIzquierdo() == null) {
            return k3; // nada que rotar
        }
        k3.setIzquierdo(rotacionSimpleDer(k3.getIzquierdo()));
        return rotacionSimpleIzq(k3);
    }

    /**
     * Realiza una rotación doble izquierda-derecha.
     *
     * @param k3 nodo desbalanceado
     * @return nueva raíz del subárbol rotado
     */
    public Nodo rotacionDobleIzqDer(Nodo k3) {
        k3.setDerecho(rotacionSimpleIzq(k3.getDerecho()));
        return rotacionSimpleDer(k3);
    }

    /**
     * Obtiene la altura de un nodo.
     *
     * @param nodo nodo a evaluar
     * @return altura del nodo, o -1 si es null
     */
    private int altura(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        return nodo.getAltura();
    }

    /**
     * Verifica el factor de balance del nodo y aplica la rotación necesaria
     * para restaurar el equilibrio del árbol.
     *
     * @param nodo nodo a balancear
     * @return nueva raíz del subárbol balanceado
     */
    private Nodo balancear(Nodo nodo) {
        if (nodo != null) {
            if (altura(nodo.getIzquierdo()) - altura(nodo.getDerecho()) == 2) {
                //Desequilibrio hacia la izquierda
                int alturaIzqIzq = altura(nodo.getIzquierdo().getIzquierdo());
                int alturaIzqDer = altura(nodo.getIzquierdo().getDerecho());
                if (alturaIzqIzq >= alturaIzqDer) {
                    //Desequilibrio simple hacia la izquierda
                    return rotacionSimpleDer(nodo);
                } else {
                    //Desequilibrio doble hacia la izquierda
                    return rotacionDobleIzqDer(nodo);
                }
            } else {
                if (altura(nodo.getDerecho()) - altura(nodo.getIzquierdo()) == 2) {
                    //Desequilibrio hacia la derecha
                    int alturaDerDer = altura(nodo.getDerecho().getDerecho());
                    int alturaDerIzq = altura(nodo.getDerecho().getIzquierdo());
                    if (alturaDerDer >= alturaDerIzq) {
                        //Desequilibrio simple hacia la derecha
                        return rotacionSimpleIzq(nodo);
                    } else {
                        //Desequilibrio doble hacia la derecha
                        return rotacionDobleDerIzq(nodo);
                    }
                }
            }
        }
        return nodo;
    }

}
