/*
 * Clase: Lista
 * Descripcion: Interfaz que define el comportamiento de una lista.
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 *          Grupo 28
 */

   public interface Lista<E> {
      
      public boolean add(E element);
      
      public boolean contains(Object element);

      public boolean equals(Object o);

      public boolean isEmpty();

      public boolean remove(E element);
       
      public int getSize();

      public Object[] toArray();
      
      public Lista<E> clone();

   }
