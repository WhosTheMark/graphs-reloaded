/*
 * Clase: MiLista
 * Descripcion: Clase que implementa la interfaz lista.
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 *          Grupo 28
 */
  
   public class MiLista<E> implements Lista<E> {
    
      private Caja<E> primero;
      private int tamano;

      /*
       * Modelo de representacion: lista simplemente enlazada 
       * con un centinela.
       * La primera "caja" esta vacia.
       */
    
      private class Caja<E>{
        
         private E elemento;
         private Caja<E> sig = null;
         
         /*
          * Metodo: Caja
          * Descripcion: Constructor de un caja. 
          * Parametros: elem: Elemento a almacenar en una caja.
          * Precondicion: true.
          * Postcondicion: caja.elemento = elem.
          */
          
         private Caja(E elem) {
            elemento = elem;
         }
         
         /*
          * Metodo: setSigCaja
          * Descripcion: Asigna la siguiente caja de this.
          * Parametros: this: La caja a la cual se le asigna como siguiente
                              sigCaja.
          *             sigCaja: La caja que se asigna como siguiente.
          * Precondicion: this y sigCaja son cajas validas.
          * Postcondicion: this.sig = sigCaja.
          */
        
         private void setSigCaja(Caja<E> sigCaja) {
            sig = sigCaja;
         } 
         
         /*
          * Metodo: obtenerSig
          * Descripcion: Devuelve la siguiente caja de this.
          * Parametros: this: La caja de donde se obtiene su siguiente caja.
          * Precondicion: this es una caja valida.
          * Postcondicion: this.obtenerSig() = this.sig.
          */
          
         private Caja<E> obtenerSig(){
            return sig;
         }
         
         /*
          * Metodo: obtenerElem
          * Descripcion: Devuelve el elemento de la caja.
          * Parametros: this: Caja de donde se devuelve el elemento.
          * Precondicion: this es una caja valida.
          * Postcondicion: this.obtenerElem() = this.elemento.
          */
          
         private E obtenerElem(){
            return elemento;
         }  
      }
    
      /*
       * Metodo: constructor MiLista() 
       * Descripcion: Construye una lista de tipo MiLista.
       * Parametros: --
       * Precondicion: true
       * Postcondicion: tamano = 0 /\ contiene como su primera caja una
       *                vacia como centinela.
       */
       
      public MiLista() {
        
         tamano = 0;
         Caja<E> nuevaCaja = new Caja(null);
         primero = nuevaCaja;
      }

      /*
       * Metodo: add
       * Descripcion: Agrega un elemento a la lista.
       * Parametros: element: Elemento a ser agregado.
       *             this: Lista a la cual se va a agregar el elemento.
       * Precondicion: this es una lista valida.
       * Postcondicion: add(element) == element fue agregado a la lista.
       */
       
      public boolean add(E element) {
	
         Caja<E> nuevaCaja = new Caja(element);
         
         if (nuevaCaja != null){
         
            nuevaCaja.setSigCaja(primero.obtenerSig());
            primero.setSigCaja(nuevaCaja);
            tamano++;
            
            return true;
         
         } else {
         
            return false;
         }
      }

      /*
       * Metodo: clear
       * Descripcion: Elimina todos los elementos de la lista. La lista
       *              queda como recien creada.
       * Parametros: this: Lista de donde se eliminan todos sus elementos.
       * Precondicion: this es una lista valida.
       * Postcondicion: Devuelve una lista vacia.
       */
       
      public void clear() {
      
         if (tamano >= 1){
         
            Caja<E> aux = primero.obtenerSig();
            
            while (aux != null){
            
               primero.setSigCaja(aux.obtenerSig());
               aux = null;
               
               aux = primero.obtenerSig();
            }             
            tamano = 0;
         }
      }

      /*
       * Metodo: contains
       * Descripcion: Determina si el elemento dado esta en la lista.
       * Parametros: this: La lista de donde se determina si existe el
       *                   elemento dado.
       *             element: Elemento al verificar si esta en la lista.
       * Precondicion: this es una lista valida.
       * Postcondicion: contains(element) == El elemento pertenece a la lista.
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

      /*
       * Metodo: equals
       * Descripcion: Determina si la lista dada es igual a list.
       * Parametros: this: Lista al comparar.
       *             list: Lista dada al cual se va a comparar. 
       * Precondicion: this y list son listas validas. 
       * Postcondicion: equals(list) == this es igual a list.
       */
    
      public boolean equals(Object o){
           
         MiLista list = (MiLista) o;
         
         if (tamano != list.tamano){
             
            return false;
            
         } else {
            
            Caja<E> aux = primero.obtenerSig();
            
            boolean igual = true;
            
            E elem;
            
            while (igual && aux != null) {
            
               elem = aux.obtenerElem();              
               igual = list.contains(elem);
               aux = aux.obtenerSig();
               
            }   
            return igual;
        }  
      }

      /*
       * Metodo: isEmpty
       * Descripcion: Determina si lista dada es vacia.
       * Parametros: this: Lista al determinar si es vacia.
       * Precondicion: this es una lista valida.
       * Postcondicion:  this.isEmpty() == (this.getSize = 0)
       */
          
      public boolean isEmpty() {
         
         return (tamano == 0);
      }

      /*
       * Metodo: remove 
       * Descripcion: Elimina el elemento dado de la lista. Si la lista 
       *              cambia, retorna true, sino retorna false.
       * Parametros: this: La lista de donde se elimina el elemento.
       *             element: Elemento a eliminar.
       * Precondicion: this es una lista valida.
       * Postcondicion: La lista contiene todos los elementos que tenia
       *                originalmente menos element.
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
               aux2 = null;
            
               aux2 = aux1.obtenerSig();
               
               cambia = true;
               tamano -= 1;
               
            } else {
               
               aux1 = aux1.obtenerSig();
               aux2 = aux2.obtenerSig();
            }
         }
         return cambia;
      }

      /*
       * Metodo: getSize
       * Descripcion: Retorna el numero de elementos en la lista.
       * Parametros: this: Lista de donde se retorna su tamano.
       * Precondicion: this es una lista valida.
       * Postcondicion: this.getSize() = this.tamano.
       */
       
      public int getSize(){
         return tamano;
      }

      /*
       * Metodo: toArray
       * Descripcion: Retorna un arreglo que contiene todos los elementos
       *              de lista dada.
       * Parametros: this: Lista al convertir a un arreglo.
       * Precondicion: this es una lista valida
       * Postcondicion: Retorna un arreglo que contiene unicamente todos los
       *                elementos de la lista dada en el mismo orden.
       */

      public Object[] toArray() {
	
         Object[] arreglo = new Object[this.getSize()];
         Object objElem;
         
         E elem;
         int i = 0;
         
         Caja<E> aux = primero.obtenerSig();
         
         while (aux != null) {
         
            elem = aux.obtenerElem();
            objElem = (Object) elem;
            arreglo[i] = objElem;   
            
            i++;
            aux = aux.obtenerSig();
         }     
         return arreglo;
      }
      
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
