
public abstract class ColeccionListaEnlazada<E>{
    
    protected Caja<E> primero;
    protected int tamano;
    
    protected class Caja<E>{
        
         private final E elemento;
         private Caja<E> sig = null;
         
          
         protected Caja(E elem) {
            elemento = elem;
         }
         
         /**
          * Asigna la siguiente caja de this.
          * @param sigCaja Caja a poner como siguiente
          */
         protected void setSigCaja(Caja<E> sigCaja) {
            sig = sigCaja;
         } 
         
         /**
          * Devuelve la siguiente caja de this.
          * @return la caja siguiente a this
          */
         protected Caja<E> obtenerSig(){
            return sig;
         }
          
         /**
          * Obtiene el elemento de la caja.
          * @return el elemento de la caja.
          */
         protected E obtenerElem(){
            return elemento;
         }  
      }

    
    public ColeccionListaEnlazada() {       
       tamano = 0;
       Caja<E> nuevaCaja = new Caja(null);
       primero = nuevaCaja;
    }
    
    /**
     * Determina si el elemento dado esta en la lista.
     * @param element Elemento a verificar si esta en la lista.
     * @return True si elemento esta en la lista
     */
    public boolean contains(Object element) {

       Caja<E> aux = primero.obtenerSig();
       boolean esta = false;

       while (aux != null && !(esta)){

          E elem = aux.obtenerElem();
          esta = elem.equals(element);
          aux = aux.obtenerSig();
       }

       return esta;
    }
    
    /**
     * Verifica si la lista esta vacia.
     * @return True si la lista esta vacia
     */
    public boolean isEmpty() {
        return (tamano == 0);
     }

    /**
     * Devuelve el tamaño de la lista.
     * @return el tamaño de la lista
     */
    public int getSize(){
       return tamano;
    }
    
    
    /**
     * Convierte la lista en un arreglo.
     * @return el arreglo que representa la lista.
     */
    public Object[] toArray() {

       Object[] arreglo = new Object[this.getSize()];
       int i = 0;
       Caja<E> aux = primero.obtenerSig();

       while (aux != null) {

          E elem = aux.obtenerElem();
          Object objElem = (Object) elem;
          arreglo[i] = objElem;   
          ++i;
          aux = aux.obtenerSig();
       }    

       return arreglo;
    }
 
    
}
