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
public class ArbolAVL {
     

        Nodo raiz;

        public ArbolAVL() {
            this.raiz = null;

        }

        public Nodo insertar(String x) throws Exception {
            raiz = insertar(x, raiz);
            return raiz;
        }

        //MÉTODO PARA INSERTAR
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

        //MÉTODO PARA BUSCAR (público -> llama al privado para que empiece desde la raíz)
        public Nodo buscar(String dato) {
            return buscar(dato, this.raiz);
        }

        //MÉTODO BÚSQUEDA EN PRE ORDEN
        //Método privado
        private void imprimirPre(Nodo recorrer) {
            if (recorrer != null) { //caso base
                System.out.println(recorrer.getDato() + " ");
                imprimirPre(recorrer.getIzquierdo());
                imprimirPre(recorrer.getDerecho());
            }
        }

        //método público
        public void imprimirPre() {
            imprimirPre(raiz);
            System.out.println();
        }

        //MÉTODO DE BÚSQUEDA EN IN ORDEN
        //Método privado
        private void imprimirIn(Nodo recorrer) {
            if (recorrer != null) {
                imprimirIn(recorrer.getIzquierdo());
                System.out.println(recorrer.getDato() + " ");
                imprimirIn(recorrer.getDerecho());
            }
        }

        //método público
        public void imprimirIn() {
            imprimirIn(raiz);
            System.out.println();
        }

        //MÉTODO DE BÚSQUEDA EN POST ORDEN
        //Método privado
        private void imprimirPost(Nodo recorrer) {
            if (recorrer != null) {
                imprimirPost(recorrer.getIzquierdo());
                imprimirPost(recorrer.getDerecho());
                System.out.println(recorrer.getDato() + " ");
            }
        }

        //método público
        public void imprimirPost() {
            imprimirPost(raiz);
            System.out.println();
        }

        //MÉTODOS DE ELIMINACIÓN
        //Método de soporte: buscar mínimo
        private Nodo buscarMinimo(Nodo nodo) {
            if (nodo != null) {
                while (nodo.getIzquierdo() != null) {
                    nodo = nodo.getIzquierdo();
                }
            }
            return nodo;
        }

        //Método de soporte: buscar mayor
        private Nodo buscarMayor(Nodo nodo) {
            if (nodo != null) {
                while (nodo.getDerecho() != null) {
                    nodo = nodo.getDerecho();
                }
            }
            return nodo;
        }

        //Método de soporte: eliminar mínimo
        private Nodo eliminarMinimo(Nodo nodo) {
            if (nodo.getIzquierdo() != null) {
                nodo.setIzquierdo(eliminarMinimo(nodo.getIzquierdo()));
                return nodo;
            } else {
                return nodo.getDerecho();
            }
        }

        //Método de soporte: eliminar mayor
        private Nodo eliminarMayor(Nodo nodo) {
            if (nodo.getDerecho() != null) {
                nodo.setDerecho(eliminarMayor(nodo.getDerecho()));
                return nodo;
            } else {
                return nodo.getIzquierdo();
            }
        }

        //Método eliminar privado
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

        //Método eliminar público
        public void eliminar(String x) {
            this.raiz = eliminar(x, this.raiz);
        }

        //Método rotación simple derecha
        public Nodo rotacionSimpleDer(Nodo k2) {
            Nodo k1 = k2.getIzquierdo();
            k2.setIzquierdo(k1.getDerecho());
            k1.setDerecho(k2);
            return k1;
        }

        //Método rotación simple izquierda
        public Nodo rotacionSimpleIzq(Nodo k1) {
            if (k1 == null || k1.getDerecho() == null) {
                return k1; // nada que rotar
            }
            Nodo k2 = k1.getDerecho();
            k1.setDerecho(k2.getIzquierdo());
            k2.setIzquierdo(k1);
            return k2;
        }

        //Método rotación doble izquierda derecha
        public Nodo rotacionDobleDerIzq(Nodo k3) {
            if (k3 == null || k3.getIzquierdo() == null) {
                return k3; // nada que rotar
            }
            k3.setIzquierdo(rotacionSimpleDer(k3.getIzquierdo()));
            return rotacionSimpleIzq(k3);
        }

        //Método rotación doble derecha izquierda
        public Nodo rotacionDobleIzqDer(Nodo k3) {
            k3.setDerecho(rotacionSimpleIzq(k3.getDerecho()));
            return rotacionSimpleDer(k3);
        }

        //Método para altura
        private int altura(Nodo nodo) {
            if (nodo == null) {
                return -1;
            }
            return nodo.getAltura();
        }

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

