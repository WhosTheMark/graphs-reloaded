/*
 * Clase: Lista
 * Descripcion: Interfaz que define el comportamiento de una lista.
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 *          Grupo 28
 */

   public interface Lista<E> {
   
   
      /*
       * Metodo: add
       * Descripcion: Agrega un elemento a la lista.
       * Parametros: element: Elemento a ser agregado.
       *             this: Lista a la cual se va a agregar el elemento.
       * Precondicion: this es una lista valida.
       * Postcondicion: add(element) == element fue agregado a la lista.
       */
       
      public boolean add(E element);

      /*
       * Metodo: clear
       * Descripcion: Elimina todos los elementos de la lista. La lista
       *              queda como recien creada.
       * Parametros: this: Lista de donde se eliminan todos sus elementos.
       * Precondicion: this es una lista valida.
       * Postcondicion: Devuelve una lista vacia.
       */

      public void clear();
      
      /*
       * Metodo: contains
       * Descripcion: Determina si el elemento dado esta en la lista.
       * Parametros: this: La lista de donde se determina si existe el
       *                   elemento dado.
       *             element: Elemento al verificar si esta en la lista.
       * Precondicion: this es una lista valida.
       * Postcondicion: contains(element) == El elemento pertenece a la lista.
       */
       
      public boolean contains(Object element);

      /*
       * Metodo: equals
       * Descripcion: Determina si la lista dada es igual a list.
       * Parametros: this: Lista al comparar.
       *             list: Lista dada al cual se va a comparar. 
       * Precondicion: this y list son listas validas. 
       * Postcondicion: equals(list) == this es igual a list.
       */
    
      public boolean equals(Object o);

      /*
       * Metodo: isEmpty
       * Descripcion: Determina si lista dada es vacia.
       * Parametros: this: Lista al determinar si es vacia.
       * Precondicion: this es una lista valida.
       * Postcondicion:  this.isEmpty() == (this.getSize = 0)
       */
          
      public boolean isEmpty();
      
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

       public boolean remove(E element);
       
      /*
       * Metodo: getSize
       * Descripcion: Retorna el numero de elementos en la lista.
       * Parametros: this: Lista de donde se retorna su tamano.
       * Precondicion: this es una lista valida.
       * Postcondicion: this.getSize() = la cantidad de elementos que tiene
       *                                 la lista.
       */
       
      public int getSize();
      
      /*
       * Metodo: toArray
       * Descripcion: Retorna un arreglo que contiene todos los elementos
       *              de lista dada.
       * Parametros: this: Lista al convertir a un arreglo.
       * Precondicion: this es una lista valida
       * Postcondicion: Retorna un arreglo que contiene unicamente todos los
       *                elementos de la lista dada en el mismo orden.
       */

      public Object[] toArray();
      
      public Lista<E> clone();

   }
