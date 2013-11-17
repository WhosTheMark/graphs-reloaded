/*
 * Descripcion: contiene las operaciones y la informacion de una cola.
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 */

public class MiCola<E> extends ColeccionListaEnlazada<E> implements Cola<E>{
    
    private Caja<E> ultimo;
       
    public MiCola() {
        
        /*Se usa una caja centinela*/
        
        super();
        ultimo = primero;
        
    }
    
    /**
     * Encola un elemento.
     * @param element elemento a encolar
     * @return la cola con el elemento encolado.
     */
    
    public boolean add(E element) {
        
             
        Caja<E> nuevaCaja = new Caja(element);
        ultimo.setSigCaja(nuevaCaja);          
        ultimo = nuevaCaja;
        ++tamano;
        return true;
           
    }
    
    /**
     * Verifica si dos colas son iguales
     * @param cola Cola a igualar.
     * @return True si las colas son iguales.
     */
    
    public boolean equals(Cola<E> cola) {
        
        MiCola queue = (MiCola) cola;
        
        if (tamano != queue.tamano) {
            
            return false;
            
        } else {
            
            Caja<E> aux1 = primero.obtenerSig();
            Caja<E> aux2 = queue.primero.obtenerSig();
            
            boolean igual = true;
            
            while (igual && aux1 != null) {
                
                E elem1 = aux1.obtenerElem();
                E elem2 = aux2.obtenerElem();
                
                igual = elem1.equals(elem2);
                
                aux1 = aux1.obtenerSig();
                aux2 = aux2.obtenerSig();
            }
            return igual;
        }
        
    }

    /**
     * Desencola un elemento de la cola
     * @return el elemento desencolado.
     */
    
    public E dequeue() {
        
        if (!this.isEmpty()) {

            Caja<E> aux = primero.obtenerSig();
  
            E elem;
            primero.setSigCaja(aux.obtenerSig());           
            elem = aux.obtenerElem();
            aux = null;
            --tamano;
           
            if (this.isEmpty()) 
               ultimo = primero;
           
            return elem;
            
        } else
            return null;
        
    }
    
      /**
       * Clona una lista
       * @return la lista clonada.
       * NO COPIA LAS REFERENCIAS DE LOS ELEMENTOS.
       */
      
      public Cola<E> clone() {
      
         Caja<E> aux = primero.obtenerSig();
         MiCola<E> cola = new MiCola();
                 
         while (aux != null) {
            E elem = aux.obtenerElem();
            cola.add(elem);
            aux = aux.obtenerSig();
         }
         
         return cola;
      
      }
    
    
}