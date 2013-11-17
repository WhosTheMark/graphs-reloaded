/*
 * Interfaz de listas.
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 */
  
   public class MiLista<E> extends ColeccionListaEnlazada<E> implements Lista<E> {
    

      public MiLista() {       
         super();
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
