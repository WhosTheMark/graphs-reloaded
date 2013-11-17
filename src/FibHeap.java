/*
 * Clase: FibHeap
 * Descripcion: Clase donde esta implementado el heap Fibonacci
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 *          Grupo 28
 */

public class FibHeap<E extends Comparable<E>> {
    
    private Caja<E> minimo;                 //Minimo del heap
    private int tamano;                     //Tamano del heap
    private int maxOrden;
    
    
    private class Caja<E extends Comparable<E>> {
        
        private E elemento;                 //El elemento de la caja
        private Caja<E> sig = null;         //El siguiente
        private Caja<E> ant = null;         //El anterior
        private FibHeap<E> hijos = null;    //El hijo
        private int orden = 0;              //Orden de la caja y sus hijos
        private Caja<E> padre = null;       //Padre
        
        
        /* 
         * Metodo: Constructor Caja
         * Descripcion: Crea una nueva caja con el elemento elem.
         * Parametros: elem: el elemento a agregar en la caja.
         * Precondicion: true
         * Postcondicion: La caja esta creada y el elemento agregado.
         */
        
        private Caja(E elem) {
            
            elemento = elem;    
        }
        
        /* 
         * Metodo: setElem
         * Descripcion: Asigna un elemento a la caja.
         * Parametros: this: caja a ser agregada el elemento.
         *             elem: el elemento a agregar en la caja.
         * Precondicion: this esta creado
         * Postcondicion: this.elemento = elem.
         */
        
        private void setElem(E elem){
            
            elemento = elem;
            
        }
        
        /* 
         * Metodo: setSigCaja
         * Descripcion: Asigna el siguiente.
         * Parametros: this: caja a asignar el siguiente.
         *             sigCaja: apuntador a la caja a asignar.
         * Precondicion: this esta creado
         * Postcondicion: this.sig = sigCaja.
         */
        
        private void setSigCaja(Caja<E> sigCaja) {
            
            sig = sigCaja;     
        }
        
        /* 
         * Metodo: setSigCaja
         * Descripcion: Asigna el anterior.
         * Parametros: this: caja a asignar el anterior.
         *             antCaja: apuntador a la caja a asignar.
         * Precondicion: this esta creado
         * Postcondicion: this.ant = antCaja.
         */
        
        private void setAntCaja(Caja<E> antCaja) {
            
            ant = antCaja;
            
        }
        
        /* 
         * Metodo: setHijos
         * Descripcion: Asigna el heap con los hijos.
         * Parametros: this: caja a asignar los hijos.
         *             hijo: apuntador al heap con los hijos.
         * Precondicion: this esta creado y hijos es un heap valido.
         * Postcondicion: this.hijos = hijo.
         */
        
        private void setHijos(FibHeap<E> hijo) {
            
            hijos = hijo;
        }
        
        /* 
         * Metodo: setOrden
         * Descripcion: Asigna el orden.
         * Parametros: this: caja a asignar el orden.
         *             ord: orden a aasignar.
         * Precondicion: this esta creado.
         * Postcondicion: this.orden = ord.
         */
        
        private void setOrden(int ord) {
            
            orden = ord;
            
        }
  
        /* 
         * Metodo: setPadre
         * Descripcion: Asigna el padre.
         * Parametros: this: caja a asignar el padre.
         *             pad: apuntador al padre a asignar.
         * Precondicion: this y pad estan creados.
         * Postcondicion: this.padre = pad.
         */
        
        private void setPadre(Caja<E> pad) {
            
            padre = pad;
        }
        
        /* 
         * Metodo: obtenerElem
         * Descripcion: Obtiene el elemento de la caja.
         * Parametros: this: caja a obtener su elemento.
         * Precondicion: this esta creado.
         * Postcondicion: this.elemento = this.obtenerElem().
         */
        
        private E obtenerElem() {
            
            return elemento;
        }
        
        /* 
         * Metodo: obtenerSig
         * Descripcion: Obtiene el siguiente de la caja.
         * Parametros: this: caja a obtener su siguiente.
         * Precondicion: this esta creado.
         * Postcondicion: this.sig = this.obtenerSig().
         */
        
        private Caja<E> obtenerSig() {
            
            return sig;
        }
        
        /* 
         * Metodo: obtenerAnt
         * Descripcion: Obtiene el anterior de la caja.
         * Parametros: this: caja a obtener su anterior.
         * Precondicion: this esta creado.
         * Postcondicion: this.ant = this.obtenerAnt().
         */
         
        private Caja<E> obtenerAnt() {
            
            return ant;
        }
        
        /* 
         * Metodo: obtenerHijos
         * Descripcion: Obtiene los hijos de la caja.
         * Parametros: this: caja a obtener sus hijos.
         * Precondicion: this esta creado.
         * Postcondicion: this.hijos = this.obtenerHijos().
         */
        
        private FibHeap<E> obtenerHijos() {
            
            return hijos;
        }
        
        /* 
         * Metodo: obtenerPadre
         * Descripcion: Obtiene el padre de la caja.
         * Parametros: this: caja a obtener su padre.
         * Precondicion: this esta creado.
         * Postcondicion: this.padre = this.obtenerPadre().
         */
        
        private Caja<E> obtenerPadre() {
            
            return padre;
        }
        
        /* 
         * Metodo: obtenerOrd
         * Descripcion: Obtiene el orden de la caja.
         * Parametros: this: caja a obtener su orden.
         * Precondicion: this esta creado.
         * Postcondicion: this.orden = this.obtenerOrd().
         */
           
        private int obtenerOrd() {
            
            return orden;
        }

    }
        
   /* 
    * Metodo: Constructor FibHeap
    * Descripcion: Crea un nuevo heap.
    * Parametros: --
    * Precondicion: true.
    * Postcondicion: this.tamano = 0 /\ minimo = null.
    */
          
    public FibHeap() {
        
        tamano = 0;
        maxOrden = 0;
        minimo = null;
    }
    
   /* 
    * Metodo: add
    * Descripcion: Agrega un elemento en el heap.
    * Parametros: this: heap a agregar su elemento.
    *             elem: elemento a agregar en el heap.
    * Precondicion: this y elem estan creados.
    * Postcondicion: elem pertenece a los elementos del heap.
    */
          
    public boolean add(E elem) {
    
        Caja<E> nuevaCaja = new Caja(elem);     //Crea una nueva caja con el 
                                                //elemento
        
        if (nuevaCaja != null){ 

            /* Si el heap esta vacio, asigna el elemento como minimo */
            
            if (minimo == null) {
                
                minimo = nuevaCaja;
                nuevaCaja.setSigCaja(nuevaCaja);
                nuevaCaja.setAntCaja(nuevaCaja);
                
            /* Si no esta vacio, lo introduce en la lista circular del heap */
                
            } else {

                Caja<E> aux;
                aux = minimo.obtenerSig();
                minimo.setSigCaja(nuevaCaja);
                nuevaCaja.setAntCaja(minimo);
                nuevaCaja.setSigCaja(aux);
                aux.setAntCaja(nuevaCaja);

                /* Si el nuevo elemento es menor que el minimo actual,
                 * se actualiza el minimo */
                
                if (minimo.obtenerElem().compareTo(nuevaCaja.obtenerElem()) > 0){

                    minimo = nuevaCaja; 

                }

            }

            tamano++;           //Aumenta el tamano en 1
            maxOrden++;
            return true;
        
        } else {
            
            return false;
            
        }
    }
    
   /* 
    * Metodo: extraerMin
    * Descripcion: Extrae el menor elemento del heap.
    * Parametros: this: heap a obtner su minimo elemento.
    * Precondicion: this esta creado.
    * Postcondicion: El heap esta ordenado y el minimo ya no pertenece al heap.
    */
    
    public E extraerMin(){
        
        E min = null;
        
        /* Si el heap no esta vacio, extrae un elemento */
        
        if (minimo != null){
            
            min = minimo.obtenerElem();
            FibHeap<E> hijos = minimo.obtenerHijos();
            
            /* Si minimo tiene hijos, los coloca en la 
             * lista circular del heap  */
            
            if ((hijos != null) && (!hijos.esVacio())){
                
                Caja<E> aux = hijos.minimo;
                
                /* Todos los padres de los hijos ahora son null */ 
                
                while (aux.obtenerPadre() != null){
                    
                    aux.setPadre(null);
                    aux = aux.obtenerSig();
                    
                }
                
                Caja<E> hijMin = hijos.minimo;
                aux = hijMin.obtenerAnt();
                
                /* Si nada mas tiene un hijo lo conecto directamente con la
                 * lista circular */
                
                if (minimo == minimo.obtenerSig()) {
                    
                    minimo.setSigCaja(hijMin);
                    aux.setSigCaja(minimo);
                    hijMin.setAntCaja(minimo);
                    minimo.setAntCaja(aux);
                    
               /* Si tiene mas de un hijo concateno las dos listas circulares */
                    
                } else {
                
                    hijMin.setAntCaja(minimo.obtenerAnt());
                    aux.setSigCaja(minimo);
                    minimo.obtenerAnt().setSigCaja(hijMin);
                    minimo.setAntCaja(aux);

                }
            }
                
            /* Elimino el minimo de la lista circular */
            
            minimo.obtenerSig().setAntCaja(minimo.obtenerAnt());
            minimo.obtenerAnt().setSigCaja(minimo.obtenerSig());
            
            /* El minimo es cualquier elemento, leugo de ordenar se actualiza */
            
            minimo = minimo.obtenerSig();
            
            tamano--;       // disminuyo en uno el tamano
            
            /* Si el heap esta vacio, no hace falta ordenar */
            
            if (tamano == 0){
                
                minimo = null;
                
            } else {
                
                this.ordenar();
                
            }
            
        }
        
        return min;
        
    }
    
   /* 
    * Metodo: ordenar
    * Descripcion: Luego de extraer orden el heap.
    * Parametros: this: heap a ordenar.
    * Precondicion: this esta creado.
    * Postcondicion: El heap esta ordenado para que cumpla las propiedades del
    *                Fibonaci.
    */
          
    public void ordenar(){
        
        /* Si el tamano es mayor a 1, ordeno */
        
        if(this.tamano != 1){
            
            /* Creo un arreglo auxiliar para ordenar el heap */
            
            Caja<E>[] arreglo = new Caja[maxOrden*2];
            Caja<E> aux2 = minimo;
            
            /* Para todas las raices en el heap, si tienen el mismo orden
             * las mezclo y la nueva raiz la coloco en el arreglo */
            
            do{
                int orden = aux2.obtenerOrd();
                
                /* Si ya hay un heap con el orden de aux2, se mezclan */ 
                
                while ((arreglo[orden] != null) && arreglo[orden] != aux2){
                    
                    Caja<E> aux3 = arreglo[orden];
                    
                    /* Verifico cual es menor para colocarlo de raiz */
                    
                    if (aux2.obtenerElem().compareTo(aux3.obtenerElem()) > 0){

                        Caja<E> changeAux = aux2;
                        aux2 = aux3;
                        aux3 = changeAux;

                    }
                    
                    /* Si el minimo esta en el mayor, muevo el minimo 
                     * al siguiente elemento */
                    
                    if (aux3 == minimo){
                        minimo = minimo.obtenerSig();
                     }
                    
                    /* Elimino el mayor de la lista circular */
                    
                    aux3.obtenerAnt().setSigCaja(aux3.obtenerSig());
                    aux3.obtenerSig().setAntCaja(aux3.obtenerAnt());

                    FibHeap<E> hijos = aux2.obtenerHijos();
                    
                    /* Si el menor tiene hijos concateno la lista de los hijos
                     *  con la mayor de las raices */
                    
                    if (hijos != null){
                        
                        Caja<E> aux4 = hijos.minimo;
                        aux4.obtenerSig().setAntCaja(aux3);
                        aux3.setAntCaja(aux4);
                        aux3.setSigCaja(aux4.obtenerSig());
                        aux4.setSigCaja(aux3);
                        aux3.setPadre(aux2);
                        
                    /* Si no tiene hijos creo un nuevo heap con los hijos */    
                        
                    } else {
                        
                       FibHeap<E> nuevosHijos = new FibHeap();
                       nuevosHijos.add(aux3.obtenerElem());
                       aux2.setHijos(nuevosHijos);
                       nuevosHijos.minimo.setPadre(aux2);
                       
                    }

                    aux2.orden++;           //Sumo uno al orden de la nueva raiz

                    arreglo[orden] = null;  //Elimino la raiz del arreglo
                    orden++;                //Verifico la siguiente casilla del 
                                            //arreglo

                }

                /* Como ya no quedan mas del mismo orden por mezclar
                 * lo coloco en el arreglo */
                
                arreglo[orden] = aux2;
                aux2 = aux2.obtenerSig();

            } while(aux2 != minimo);
                
            minimo = null;
            
            /* El minimo luego de mezclar estara en uno de los indices del 
             * arreglo */
            
            maxOrden = 0;
            
            for (int i= 0; i < arreglo.length; i++){
                
                if(arreglo[i] != null){
                    
                    maxOrden = i;
                    
                    if(minimo == null){
                        minimo = arreglo[i];
                    } else {
                        E elem = arreglo[i].obtenerElem();
                        if (minimo.obtenerElem().compareTo(elem) > 0){
                            minimo = arreglo[i];
                        }
                    }
                }       
            }
        }  
    }
    
   /* 
    * Metodo: esVacio
    * Descripcion: Verifica si un heap es vacio.
    * Parametros: this: heap a verificar si es vacio.
    * Precondicion: this esta creado.
    * Postcondicion: (minimo = null) == this.esVacio().
    */

    public boolean esVacio(){
        return (minimo == null);
    }
  
   /* 
    * Metodo: obtTamano
    * Descripcion: Obtiene el tamano del heap.
    * Parametros: this: heap a aobtener su tamano.
    * Precondicion: this esta creado.
    * Postcondicion:  this.tamano = this.obtTamano().
    */

    public int obtTamano() {
        return tamano;
    }
    
}
