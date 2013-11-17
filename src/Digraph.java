/*
 * Clase: Digraph
 * Descripcion: Clase abstracta que implementa un grafo dirigido. 
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 */
       
   public abstract class Digraph {

      protected int numVertices;  // numero de vertices
      protected int numArcos;     // numero de aristas

      public Digraph() {
      
         numVertices = 0;
         numArcos = 0;
      }
       
      public abstract boolean add(Arco e);

      public abstract boolean add(Nodo n);
      
      public abstract void clear();
      
      public abstract Object clone();

      public abstract boolean contains(String src, String dst, int costo);
       
      public abstract boolean contains(String nod);
      
      public abstract Arco get(String src, String dst, int costo);
      
      public abstract Lista<Arco> getArcos();
    
      public abstract Nodo get(String nod);

      public abstract Lista<Nodo> getNodos();
      
      public int getNumArcos() {
         
         return numArcos;
      }

      public int getNumVertices() {
      
         return numVertices;
      }
      
      public abstract  Lista<Arco> getInArcos(String nodo);
      
      @SuppressWarnings("unchecked")
      public abstract Lista<Nodo> getPreds(String nodo);
      
      public abstract Lista<Arco> getOutArcos(String nodo);
      
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
       
      public abstract boolean remove(String src, String dst, int costo);
       
      public abstract boolean remove(String nod);
       
      public String toString() {
      
         String ret = numVertices + ":" + numArcos ;
         
         return ret;
      }
      
      public abstract Nodo getNod(String nod);
   
   }
