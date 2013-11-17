/*
 * Descripcion: Clase que contiene las operaciones y la informacion de una
 *              cola.
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 */

public interface Cola<E> {
    
    public boolean queue(E element);
       
    public boolean equals(Cola<E> queue);
    
    public boolean isEmpty();

    public E dequeue();

    public int getSize();

}
