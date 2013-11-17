/*
 * Descripcion: Clase que contiene las operaciones y la informacion de una
 *              cola.
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 */

public class MiCola<E> implements Cola<E> {
    
    private Caja<E> primero;
    private Caja<E> ultimo;
    private int tamano;
    
    
    private class Caja<E> {
        
        private E elemento;
        private Caja<E> sig = null;
        
        private Caja(E elem) {
            elemento = elem;
        }
        
        private void setSigCaja(Caja<E> sigCaja) {
            sig = sigCaja;
        }
        
        private Caja<E> obtenerSig() {
            return sig;
        }
        
        private E obtenerElem() {
            return elemento;
        }
    }
    
    /*
     * Metodo: MiCola
     * Descripcion: Construye una cola vacio.
     * Parametros: --
     * Precondicion: true
     * Postcondicion: tamanano = 0.
     */
       
    public MiCola() {
        
        /*Se usa una caja centinela*/
        
        tamano = 0;
        Caja<E> nuevaCaja = new Caja (null);
        primero = nuevaCaja;
        ultimo = nuevaCaja;
        
    }
    
    
    /*
     * Metodo: queue
     * Descripcion: Encola un elemento.
     * Parametros: this: la cola a la que se va a encolar E.
     *             E: El elemento a encolar.
     * Precondicion: true.
     * Postcondicion: el tamano aumenta en uno /\ E esta encolado.
     */
    
    public boolean queue(E element) {
        
             
        Caja<E> nuevaCaja = new Caja(element);

        if (nuevaCaja != null) {

            ultimo.setSigCaja(nuevaCaja);          
            ultimo = nuevaCaja;
            tamano++;
            return true;
            
        } else {
            return false;
        }
    }
    
    
    /*
     * Metodo: equals
     * Descripcion: Verifica si dos colas son iguales.
     * Parametros: this: la cola a comparar.
     *             cola: la otra cola a comparar.
     * Precondicion: this y cola son colas validas.
     * Postcondicion: Si son iguales devuelve true, de lo contratio, false.
     */
    
    public boolean equals(Cola<E> cola) {
        
        MiCola queue = (MiCola) cola;
        
        if (tamano != queue.tamano) {
            
            return false;
            
        } else {
            
            Caja<E> aux1 = primero.obtenerSig();
            Caja<E> aux2 = queue.primero.obtenerSig();
            
            boolean igual = true;
            
            E elem1;
            E elem2;
            
            while (igual && aux1 != null) {
                
                elem1 = aux1.obtenerElem();
                elem2 = aux2.obtenerElem();
                
                igual = elem1.equals(elem2);
                
                aux1 = aux1.obtenerSig();
                aux2 = aux2.obtenerSig();
            }
            return igual;
        }
        
    }

    /*
     * Metodo: isEmpty
     * Descripcion: Verifica si la cola es vacia.
     * Parametros: this: La cola a ver si es vacia.
     * Precondicion: this es una cola valida.
     * Postcondicion: isEmpty() == tamanano = 0.
     */
  
    public boolean isEmpty() {
        
        return (tamano ==0);
        
    }
    
    /*
     * Metodo: dequeue
     * Descripcion: Desencola un elemento y lo devuelve.
     * Parametros: this: la cola a desencolar.
     * Precondicion: this es una cola valida.
     * Postcondicion: La cola contiene unicamente los elementos que tenia
     *                originalmente menos E y conservan el mismo orden.
     */
    
    public E dequeue() {
        
        if (!this.isEmpty()) {

            Caja<E> aux = primero.obtenerSig();
  
            E elem;
            primero.setSigCaja(aux.obtenerSig());           
            elem = aux.obtenerElem();
            aux = null;
            tamano--;
           
            if (this.isEmpty()) {
           
               ultimo = primero;
               
            }
           
            return elem;
            
            
        } else {
            
            return null;
        }
        
    }
    
    /*
     * Metodo: getSize
     * Descripcion: Obtiene el tamano de la cola.
     * Parametros: this: Cola a obtener tamano.
     * Precondicion: this es una cola valida
     * Postcondicion: getSize() = tamanano
     */
    
    public int getSize() {
        return tamano;
    }
   
    
    
}