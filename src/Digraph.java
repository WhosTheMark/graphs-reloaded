/*
 * Clase: Digraph
 * Descripcion: Clase abstracta que implementa un grafo dirigido. 
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 *          Grupo 28
 */
       
   public abstract class Digraph {

      protected int numVertices;  // numero de vertices
      protected int numArcos;     // numero de aristas
      

      /*
       * Metodo: Digraph
       * Descripcion: Construye un grafo vacio.
       * Parametros: --
       * Precondicion: true
       * Postcondicion: grafo.numVertices = 0 /\ grafo.numArcos = 0.
       */

      public Digraph() {
      
         numVertices = 0;
         numArcos = 0;
      }
      
      /*
       * Metodo: add
       * Descripcion: Agrega un arco al grafo dado. Si los vertices del arco
       *              no existen o el grafo ya tiene un arco entre los dichos
       *              vertices, el metodo retorna false. Si se agrega el
       *              nuevo arco, retorna true.
       * Parametros: this: Grafo al cual se va a agregar el arco.
       *             arco: Arco a ser agregado.
       * Precondicion: this es un grafo valido /\ e es un arco valido.
       * Postcondicion: agregado == (no existe un arco en el grafo que tenga
       *                             los mismos vertices que el arco e /\
       *                             existen los vertices del arco  /\ 
       *                             el arco e fue agregado al grafo). 
       */
       
      public abstract boolean add(Arco e);
      
      /*
       * Metodo: add 
       * Descripcion: Agrega un nodo al grafo dado. Si el vertice ya existe,
       *              el metodo retorna false. Si se agrega el nodo, retorna
       *              true.
       * Parametros: this: Grafo al cual se va a agregar el nodo.
       *             n: Nodo a ser agregado.
       * Precondicion: this es un grafo valido /\ n es un nodo valido.
       * Postcondicion: agregado == (no existe un nodo igual al nodo n /\
       *                             el nodo n fue agregado al grafo).
       */

      public abstract boolean add(Nodo n);
      
      /*
       * Metodo: clear 
       * Descripcion: Elimina los nodos y los arcos del grafo.
       * Parametros: this: Grafo de donde se van a eliminar los arcos y nodos.
       * Precondicion: this es un grafo valido.
       * Postcondicion: this.numArcos = 0 /\ this.numVertices = 0 /\
       *                todos los nodos fueron eliminados /\
       *                todos los arcos fueron eliminados.
       */
       
      public abstract void clear();
      
      /*
       * Metodo: clone
       * Descripcion: Retorna un nuevo grafo que es copia del grafo dado.
       * Parametros: this: Grafo al ser copiado.
       * Precondicion: this es un grafo valido.
       * Postcondicion: Devuelve un nuevo grafo valido que es contiene
       *                unicamente los nodos y los arcos del grafo original.
       */
       
      public abstract Object clone();
      
      /*
       * Metodo: contains 
       * Descripcion: Revisa si el grafo contiene el arco (src,dst).
       * Parametros: this: Grafo de donde se revisa si existe el arco dado.
       *             src: Vertice de origen del arco.
       *             dst: Vertice de destino del arco.
       * Precondicion: this es un grafo valido. 
       * Postcondicion: contains == (existe un arco con los vertices 
       *                             src y dst en this).         
       */

      public abstract boolean contains(String src, String dst, int costo);
      
      /*
       * Metodo: contains
       * Descripcion: Revisa si el grafo contiene el nodo con id nod.
       * Parametros: this: Grafo de donde se revisa si existe el nodo dado.
       *             nod: id del nodo dado.
       * Precondicion: this es un grafo valido.
       * Postcondicion: contains == (existe un nodo con el id nod en this)
       */
       
      public abstract boolean contains(String nod);

      /*
       * Metodo: get 
       * Descripcion: Retorna el arco del grafo que conecta los vertices src
       *              y dst. Si no existe dicho arco, el metodo retorna null.
       * Parametros: this: Grafo de donde se devuelve el arco.
       *             src: Vertice de origen dado.
       *             dst: Vertice de destino dado.
       * Precondicion: this es un grafo valido.
       * Postcondicion: (this.contains(src,dst) ==> devuelve el arco (src,dst))
       *                /\ (!this.contains(src,dst) ==> devuelve null)
       */
      
      public abstract Arco get(String src, String dst, int costo);
      
      /*
       * Metodo: getArcos
       * Descripcion: Retorna todos los arcos del grafo dado.
       * Parametros: this: Grado de donde se duelve los arcos.
       * Precondicion: this es un grafo valido.
       * Postcondicion: Devuelve una lista valida que contiene unicamente 
       *                como elementos todos los arcos de this.
       */

      public abstract Lista<Arco> getArcos();

      /*
       * Metodo: get
       * Descripcion: Retorna el nodo con id nod. Si no existe dicho nodo,
       *              el metodo retorna null.
       * Parametros: this: Grafo de donde se devuelve el nodo.
       *             nod: El id del nodo a devolver.
       * Precondicion: this es un grafo valido.
       * Postcondicion: (this.contains(nod) ==> devuelve el Nodo(id)) /\
       *                (!this.contains(nod) ==> devuelve null).
       */
    
      public abstract Nodo get(String nod);

      /*
       * Metodo: getNodos
       * Descripcion: Retorna todos los nodos del grafo dado.
       * Parametros: this: Grafo de donde se devuelven los nodos.
       * Precondicion: this es un grafo valido.
       * Postcondicion: Se devuelve una lista valida que contiene unicamente
       *                como elementos todos los nodos del grafo this.
       */

      public abstract Lista<Nodo> getNodos();

      /*
       * Metodo: getNumArcos
       * Descripcion: Retorna el numero de arcos en el grafo dado.
       * Parametros: this: Grafo de donde se devuelve el numero de arcos.
       * Precondicion: this es un grafo valido.
       * Postcondicion: getNumArcos = numArcos.
       */
      
      public int getNumArcos() {
         
         return numArcos;
      }

      /*
       * Metodo: getNumVertices
       * Descripcion: Retorna el numero de nodos en el grafo dado.
       * Parametros: this: Grafo de donde se devuelve el numero de nodos.
       * Precondicion: this es un grafo valido.
       * Postcondicion: getNumVertices = numVertices.
       */
      
      public int getNumVertices() {
      
         return numVertices;
      
      }

      /*
       * Metodo: getInArcos
       * Descripcion: Retorna una lista de lados que tienen al vertice dado
       *              como destino. Si este no existe, retorna null.
       * Parametros: this: Grafo dado de donde se devuelven los lados.
       *             nodo: Nodo de destino.
       * Precondicion: this es un grafo valido.
       * Postcondicion: (this.contains(nodo) ==> Se devuelve una lista con
       *                                        todos los lados que estan
       *                                        en el grafo que tienen
       *                                        a nodo como destino.)
       *               /\ (!this.contains(nodo) ==> Retorna null.)
       */

      public abstract  Lista<Arco> getInArcos(String nodo);
      
      /*
       * Metodo: getPreds 
       * Descripcion: Retorna los predecesores del nodo con id nodo.
       * Parametros: this: Grafo de donde se devuelven los nodos.
       *             nodo: nodo es un nodo valido.
       * Precondicion: this es un grafo valido.
       * Postcondicion: (this.contains(nodo) ==> Devuelve una lista que
       *                                        contiene unicamente a los 
       *                                        nodos que preceden a nodo
       *                                        en el grafo dado.)
       *               /\ (!this.contains(nodo) ==> Devuelve null.)
       */
       
      @SuppressWarnings("unchecked")
      public abstract Lista<Nodo> getPreds(String nodo);

      /*
       * Metodo: getOutArcos
       * Descripcion: Retorna una lista de lados que tienen al vertice dado
       *              como origen. Si este no existe, retorna null.
       * Parametros: this: Grafo dado de donde se devuelven los lados.
       *             nodo: Nodo de origen.
       * Precondicion: this es un grafo valido.
       * Postcondicion: (this.contains(nodo) ==> Se devuelve una lista con
       *                                        todos los lados que estan
       *                                        en el grafo que tienen
       *                                        a nodo como origen.)
       *               /\ (!this.contains(nodo) ==> Retorna null.)
       */
       
      public abstract Lista<Arco> getOutArcos(String nodo);
      
      /*
       * Metodo: getSucs
       * Descripcion: Retorna los sucesores del nodo con id nodo.
       * Parametros: this: Grafo de donde se devuelven los nodos.
       *              nodo: nodo es un nodo valido.
       * Precondicion: this es un grafo valido
       * Postcondicion: (this.contains(nodo) ==> Devuelve una lista que
       *                                        contiene unicamente a los 
       *                                        nodos que suceden a nodo
       *                                        en el grafo dado.)
       *               /\ (!this.contains(nodo) ==> Devuelve null.)
       */
       
      @SuppressWarnings("unchecked")
      public abstract Lista<Nodo> getSucs(String nodo);
      
      /*
       * Metodo: getInDegree
       * Descripcion: Retorna el in-degree del nodo dado. Si no existe,
       *              retorna -1.
       * Parametros: this: Grafo dado.
                     nodo: Nodo del cual se calcula su in-degree.
       * Precondicion: this es un grafo valido.
       * Postcondicion: (this.contains(nodo) ==> Se devuelve el numero de
       *                                         nodos que preceden a nodo
       *                                         en el grafo.)
       *                /\ (!this.contains(nodo) ==> Retorna -1.)
       */
   
      public int getInDegree(String nodo){
    
         if (this.contains(nodo)) {
         
            Lista<Nodo> list;
            list = this.getPreds(nodo);
            
            return list.getSize();            
            
         } else {
            
               return -1;
         }
      }
      
      /*
       * Metodo: getOutDegree
       * Descripcion: Retorna el out-degree del nodo dado. Si no existe,
       *              retorna -1.
       * Parametros: this: Grafo dado.
                     nodo: Nodo del cual se calcula su out-degree.
       * Precondicion: this es un grafo valido.
       * Postcondicion: (this.contains(nodo) ==> Se devuelve el numero de
       *                                         nodos que suceden a nodo
       *                                         en el grafo.)
       *                /\ (!this.contains(nodo) ==> Retorna -1.)
       */
       
      public int getOutDegree(String nodo){
        
         if (this.contains(nodo)) {
           
            Lista<Nodo> list;
            list = this.getSucs(nodo);
            
            return list.getSize();
         
         } else {
               
               return -1;
         }
        
      }

      /*
       * Metodo: remove
       * Descripcion: Remueve el arco del grafo que conecta los nodos src y
       *              dst. Si el grafo no cambia, el metodo retorna false.
       *              Si el grafo cambia, retorna true.
       * Parametros: this: Grafo de donde se remueve el arco dado.
       *             src: Vertice de origen del arco dado.
       *             dst: Vertice de destino del arco dado.
       * Precondicion: this es un grafo valido.
       * Postcondicion: (this.contains(src,dst) ==> el grafo contiene el
       *                                        conjunto de arcos que tenia     
       *                                        originalmente menos el 
       *                                        arco(src,dst) /\ devuelve
       *                                        true.) 
       *              /\ (!this.contains(src,dst) ==> el grafo se
       *                                              mantiene /\ devuelve
       *                                              false.)
       */
       
      public abstract boolean remove(String src, String dst, int costo);

      /*
       * Metodo: remove
       * Descripcion: Remueve el nodo del grafo con id nod. Si el grafo no
       *              no cambia, el metodo retorna false. Si el grafo, cambia
       *              retorna true.
       * Parametros: this: Grafo de donde se remueve el nodo con id nod.
       *             nod: String del nodo dado.
       * Precondicion: this es un grafo valido.
       * Postcondicion: (this.contains(nod) ==> el grafo contiene el conjunto
       *                                        de nodos que tenia originalmente
       *                                        menos el nodo(nod) /\
       *                                        devuelve true.)
       *                /\ (!this.contains(nod) ==> el grafo se mantiene /\
       *                                            devuelve false.)
       */
       
      public abstract boolean remove(String nod);

      /*
       * Metodo: toString
       * Descripcion: Construye una representacion en String del grafo dado.
       * Parametros: this: Grafo de donde se construye una representacion en
       *                   string.
       * Precondicion: this es un grafo valido.
       * Postcondicion: Se devuelve una representacion en string del 
       *                grafo this.
       */
       
       

       
       
      public String toString() {
      
         String ret = numVertices + ":" + numArcos ;
         
         return ret;
      }
      
      
      /*
       * Metodo: ordenarNodos
       * Descripcion: Ordena los nodos de un digrafo en orden alfabetico.
       * Parametros: this: Grafo a ordenar.
       * Precondicion: this es un grafo valido.
       * Postcondicion: Todos los nodos del grafo estan ordenado
       *                alfabeticamente.
       */
       
      
      //public abstract void ordenarNodos();  
      
      /*
       * Metodo: busqueda
       * Descripcion: Busca la posicion de un nodo en un grafo.
       * Parametros: this: Grafo de donde se busca el nodo. 
                     nod: String del nodo a buscar.
       * Precondicion: this es un grafo valido.
       * Postcondicion: (this.contains(nod) ==> Devuelve la posicion del 
       *                                       nodo en el grafo) /\
       *                (!this.contains(nod) ==> pos = -1)
       */
       
      
      //public abstract int busqueda(String nod);
      
      public abstract Nodo getNod(String nod);
   
   }
