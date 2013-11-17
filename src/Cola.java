/*
 * Clase: Nodo
 * Descripcion: Clase que contiene las operaciones y la informacion de una
 *              cola.
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 *          Grupo 28
 */

public interface Cola<E> {

    /*
     * Metodo: queue
     * Descripcion: Encola un elemento.
     * Parametros: this: la cola a la que se va a encolar E.
     *             E: El elemento a encolar.
     * Precondicion: true.
     * Postcondicion: el tamano aumenta en uno /\ E esta encolado.
     */
    
    public boolean queue(E element);
    
    /*
     * Metodo: clear
     * Descripcion: Elimina una cola.
     * Parametros: this: La cola a eliminar.
     * Precondicion: this es una cola valida.
     * Postcondicion: tamanano = 0.
     */
    
    public void clear();
    
    /*
     * Metodo: equals
     * Descripcion: Verifica si dos colas son iguales.
     * Parametros: this: la cola a comparar.
     *             cola: la otra cola a comparar.
     * Precondicion: this y cola son colas validas.
     * Postcondicion: Si son iguales devuelve true, de lo contratio, false.
     */
       
    public boolean equals(Cola<E> queue);
    
    /*
     * Metodo: isEmpty
     * Descripcion: Verifica si la cola es vacia.
     * Parametros: this: La cola a ver si es vacia.
     * Precondicion: this es una cola valida.
     * Postcondicion: isEmpty() == tamanano = 0.
     */
    
    public boolean isEmpty();
    
    /*
     * Metodo: dequeue
     * Descripcion: Desencola un elemento y lo devuelve.
     * Parametros: this: la cola a desencolar.
     * Precondicion: this es una cola valida.
     * Postcondicion: La cola contiene unicamente los elementos que tenia
     *                originalmente menos E y conservan el mismo orden.
     */
    
    public E dequeue();
    
    /*
     * Metodo: getSize
     * Descripcion: Obtiene el tamano de la cola.
     * Parametros: this: Cola a obtener tamano.
     * Precondicion: this es una cola valida
     * Postcondicion: getSize() = tamanano
     */
    
    public int getSize();
    

    
}
