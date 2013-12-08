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
       */
   
      public int getInDegree(String nodo){
    
         Lista<Nodo> list = this.getPreds(nodo);
          
         if (null != list)
            return list.getSize();     
            
         return -1;
         
      }
      
      /*
       * Metodo: getOutDegree
       * Descripcion: Retorna el out-degree del nodo dado. Si no existe,
       *              retorna -1.
       * Parametros: this: Nodo dado.
       */
       
      public int getOutDegree(String nodo){
        
         Lista<Nodo> list = this.getSucs(nodo);
          
         if (null != list) 
            return list.getSize();

         return -1;
          
      }
       
      public abstract boolean remove(String src, String dst, int costo);
       
      public abstract boolean remove(String nod);
       
      public String toString() {
      
         String ret = numVertices + ":" + numArcos ;
         
         return ret;
      }
      
   
   }
