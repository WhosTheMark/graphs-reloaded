/*
 * Interfaz de listas.
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 */
  
   public class MiLista<E> implements Lista<E> {
    
      private Caja<E> primero;
      private int tamano;

      /*
       * Modelo de representacion: lista simplemente enlazada 
       * con un centinela. La primera "caja" esta vacia.
       */
    
      private class Caja<E>{
        
         private E elemento;
         private Caja<E> sig = null;
         
          
         private Caja(E elem) {
            elemento = elem;
         }
         
         /**
          * Asigna la siguiente caja de this.
          * @param sigCaja Caja a poner como siguiente
          */
         private void setSigCaja(Caja<E> sigCaja) {
            sig = sigCaja;
         } 
         
         /**
          * Devuelve la siguiente caja de this.
          * @return la caja siguiente a this
          */
         private Caja<E> obtenerSig(){
            return sig;
         }
          
         /**
          * Obtiene el elemento de la caja.
          * @return el elemento de la caja.
          */
         private E obtenerElem(){
            return elemento;
         }  
      }
    
      public MiLista() {       
         tamano = 0;
         Caja<E> nuevaCaja = new Caja(null);
         primero = nuevaCaja;
      }
       
      /**
       * Agrega un elemento a la lista
       * @param element elemento a agregar
       * @return True si fue exitosa la operacion.
       */
      public boolean add(E element) {
	
        Caja<E> nuevaCaja = new Caja(element);
        nuevaCaja.setSigCaja(primero.obtenerSig());
        primero.setSigCaja(nuevaCaja);
        ++tamano;

        return true;
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
       * Verifica si dos listas son iguales.
       * @param o Objeto a igualar
       * @return True si los elementos de las listas son iguales, False
       * en caso contrario.
       */
      public boolean equals(Object o){
           
         MiLista list = (MiLista) o;
         
         if (tamano != list.tamano)
            return false;
            
         else {
            
            MiLista<E> listClone  = (MiLista) list.clone();       
            Caja<E> aux = primero.obtenerSig();
            boolean igual = true;
            
            E elem;
            
            while (igual && aux != null) {
               elem = aux.obtenerElem();              
               igual = listClone.remove(elem);
               aux = aux.obtenerSig();
               
            } 
            
            return igual;
        }  
      }

      /**
       * Verifica si la lista esta vacia.
       * @return True si la lista esta vacia
       */
          
      public boolean isEmpty() {
         
         return (tamano == 0);
      }


       /**
        * Elimina un elemento dado esta en la lista.
        * @param element Elemento a eliminar de la lista.
        * @return True si elemento esta en la lista
        */
       
      public boolean remove(E element) {
      
         Caja<E> aux1 = primero;
         Caja<E> aux2 = primero.obtenerSig();
        
         boolean cambia = false;
         E elem;
        
         while (aux2 != null){
            
            elem = aux2.obtenerElem();
            
            if (elem.equals(element)) {
            
               aux1.setSigCaja(aux2.obtenerSig());
               aux2 = aux1.obtenerSig();
               
               cambia = true;
               --tamano;
               
            } else {
               
               aux1 = aux1.obtenerSig();
               aux2 = aux2.obtenerSig();
            }
         }
         return cambia;
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
      
      /**
       * Clona una lista
       * @return la lista clonada.
       * NO COPIA LAS REFERENCIAS DE LOS ELEMENTOS.
       */
      
      public Lista<E> clone() {
      
         Caja<E> aux = primero.obtenerSig();
         MiLista<E> list = new MiLista();
                 
         while (aux != null) {
         
               E elem = aux.obtenerElem();
               list.add(elem);
               aux = aux.obtenerSig();
         
         }
         
         return list;
      
      }

   }
