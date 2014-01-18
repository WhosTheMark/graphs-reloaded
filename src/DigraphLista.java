
import java.util.Iterator;

/*
 * Descripcion: Implementa la clase abstracta Digraph utilizando listas.
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 */
 
   public class DigraphLista extends Digraph {

      private  Nodo nodos[];
      private  Lista<Arco> arcs[];
      public final int tam = 100;

      public DigraphLista() {
         super();
         nodos = new Nodo[tam];
         arcs = (Lista<Arco>[]) new MiLista[tam];
      }
      
      public DigraphLista(int n) {
         super();
         nodos = new Nodo[n];
         arcs = (Lista<Arco>[]) new MiLista[n];
      }

    /**
     * Agrega un Arco al grafo. Supone que los nodos ya estan agregados.
     * @param e arco a agregar. 
     * @return True si se agrego con exito.
     * NO VERIFICA SI ESTA YA AGREGADO!
     */
      public  boolean add(Arco e){
              
        int posSrc = this.busqueda(e.getSrc());
        
        if (0 <= posSrc){
           ++numArcos;
           return arcs[posSrc].add(e);
        }     
        
         return false;
      }
    
    
    /**
     * Agrega un Nodo al grafo.
     * @param n nodo a agregar
     * @return True si se agrego con exito
     * NO VERIFICA SI YA ESTA AGREGADO!
     */
      public  boolean add(Nodo n){
            
        if (nodos.length <= numVertices)
           duplicarArreglo();            
        ++numVertices;
        ordenarAgregar(n);

        return true;
        
      }
      
    /**
     * Auxiliar para duplicar el tamano de los arreglos de nodos y arcos.
     */
    private void duplicarArreglo(){
          
        int dobleTam = 2*nodos.length;
        Nodo nodosDoble[] = new Nodo[dobleTam];

        @SuppressWarnings("unchecked")
        Lista<Arco> arcsDoble[] = new MiLista[dobleTam];

        System.arraycopy(nodos, 0, nodosDoble, 0, nodos.length);
        System.arraycopy(arcs, 0, arcsDoble, 0, nodos.length);

        nodos = nodosDoble;
        arcs = arcsDoble;       
          
    }

    /**
     * Verifica si un arco pertenece al grafo.
     * @param src nodo inicial.
     * @param dst nodo destino.
     * @param costo costo del arco.
     * @return Verdadero si el arco pertenece.
     */
      public boolean contains(String src, String dst, int costo){
        
         boolean estaDst = false;
         int posSrc = busqueda(src);
              
         if (posSrc >= 0) {     
            Arco arc = new Arco(src,dst,costo);
            estaDst = arcs[posSrc].contains(arc);    
         }

         return estaDst;
      }

    /**
     * Verifica si un nodo pertenece al grafo.
     * @param nod nombre del nodo
     * @return Verdadero si el nodo pertenece.
     */
      public boolean contains(String nod) {
         return (this.busqueda(nod) >= 0);
      }
      
    /**
     * Realiza busqueda binaria para encontrar un nodo.
     * @param nod nodo a buscar
     * @return la posicion del nodo
     */
      private int busqueda(String nod) {
        
         int min = 0;
         int max = numVertices -1;
         int med;
         
         if (numVertices != 0) { 
        
            while (max >= min) {

                  med = ((min + max) / 2);

                  if ((nodos[med].toString().compareTo(nod)) < 0)
                     min = med + 1;

                  else if ((nodos[med].toString().compareTo(nod)) > 0)
                     max = med - 1;

                  else
                     return med;
           }
        }    
         
        return -1;
      }

      /**
       * Obtiene una referencia a un arco del grafo
       * @param src nodo inicial.
       * @param dst nodo destino.
       * @param costo costo del arco.
       * @return Devuelve el apuntador al arco si existe
       */
       
      public  Arco get(String src, String dst, int costo) {
        
         int posSrc = busqueda(src);
          
         if (posSrc >= 0) {
            Arco arc = new Arco(src,dst,costo);
            return arcs[posSrc].get(arc);
         } 
         
        return null;
      }

    /**
     * Obtiene todos los arcos del grafo.
     * @return una lista con los arcos
     */

      public Lista<Arco> getArcos(){
        
         MiLista<Arco> list = new MiLista();
                
         for (int i = 0; i < numVertices; ++i) {            
            
            for (Arco arc: arcs[i]){
                Arco nuevoArc = (Arco) arc.clone();
                list.add(nuevoArc);  
            }
         }
      
         return list;
      }

      /**
       * Obtiene el nodo en un grafo
       * @param nod nombre del nodo a buscar
       * @return el apuntador al nodo si existe
       */
      public Nodo get(String nod){
      
	  int pos = busqueda(nod);
	  
	  if (pos >= 0)	      
	      return nodos[pos];
	  
	  return null;
      }

      /**
       * Obitiene todos los nodos del grafo
       * @return una lista con los nodos.
       */
       
      public  Lista<Nodo> getNodos() {
      
         @SuppressWarnings("unchecked")
         MiLista<Nodo> list = new MiLista();
         
         for (int i = 0; i < numVertices; ++i) {
            Nodo nod = nodos[i];
            list.add(nod);  
         } 
         
         return list;
      }

    /**
     * Devuelve todos los arcos que llegan a un nodo.
     * @param nodo nodo a buscar
     * @return una lista con los arcos que llegan al nodo.
     */
       
      public Lista<Arco> getInArcos(String nodo) {
        
         if (this.contains(nodo)) {
            
            @SuppressWarnings("unchecked")
            MiLista<Arco> list = new MiLista(); 
      
            for (int i = 0; i < numVertices; ++i) {
                
               for (Arco arc: arcs[i]){
                   
                  if (arc.getDst().equals(nodo)) {
                     Arco nuevoArc = (Arco) arc.clone();
                     list.add(nuevoArc);    
                  }    
               }               
            }
            
            return list;
         }
            
         return null; 
      }
       
    /**
     * Devuelve todos los arcos que salen de un nodo.
     * @param nodo nodo a buscar
     * @return una lista con los arcos que dalen del nodo.
     */
      public Lista<Arco> getOutArcos(String nodo) {
    
         int posSrc = busqueda(nodo);
         
         if (posSrc >= 0) {
            MiLista<Arco> list = (MiLista) arcs[posSrc].clone();
            return list; 
         }
            
         return null;
      }

      /**
       * Elimina un arco del grafo.
       * @param src nodo inicial.
       * @param dst nodo destino.
       * @param costo costo del arco.
       * @return True si el arco fue eliminado exitosamente
       */
       
      public  boolean remove(String src, String dst, int costo) {
          
         int posSrc = busqueda(src);
          
         if (posSrc >= 0) {

            Arco arc = new Arco(src, dst, costo);
            return arcs[posSrc].remove(arc);   
         } 
         
         return false;
      }

    /**
     * Elimina un nodo del grafo.
     * @param nod Nodo a eliminar
     * @return True si fue eliminado exitosamente.
     */
      public boolean remove(String nod) {
        
          int posSrc = busqueda(nod);
          
         if (posSrc >= 0) {
            
             numArcos -= arcs[posSrc].getSize();
             --numVertices;
             
             ordenarEliminar(posSrc);
            
            for (int i = 0; i < numVertices; ++i) {
                
                Iterator<Arco> itr = arcs[i].iterator();
                
                while(itr.hasNext()){
                    Arco arc = itr.next();
                    if (arc.getDst().equals(nod)){  //Asume que solo hay 
                        itr.remove();               //una copia en la lista
                        break;
                    }
                }
            }
            return true;
         } 
         
         return false;
      } 
      
    /**
     * busca todos los predecesores de un nodo.
     * @param nodo
     * @return 
     */
      public Lista<Nodo> getPreds(String nodo){
        
         if (this.contains(nodo)) {
        
            MiLista<Nodo> list = new MiLista();     
            
            for (int i = 0; i < numVertices; ++i) {
               
               for (Arco arc : arcs[i]){
                   
                    if (nodo.equals(arc.getDst())) {
                       Nodo nod = new Nodo(nodos[i].toString());
                       list.add(nod);

                    } 
               }
            }
            
            return list;
         }
         
         return null;
      }
        
      /**
       * Busca los sucesores de uno nodo
       * @param nodo nodo a buscar
       * @return lista con los sucesores.
       */
      public Lista<Nodo> getSucs(String nodo) {
        
         int posSrc = busqueda(nodo);
          
         if (posSrc >= 0) {
            
            MiLista<Nodo> list = new MiLista();
   
            for(Arco arc: arcs[posSrc]){
                int posDst = busqueda(arc.getDst());
                list.add(nodos[posDst]);
            }
            
            return list;
         }
            
         return null;
      }
      
      /**
       * Ordena el nodo que se acaba de agregar.
       */
      private void ordenarAgregar(Nodo newNode){
          
          int i;
          
          for (i = numVertices -1 ; 
                  i >= 1 && newNode.compareTo(nodos[i-1]) < 0 ; --i){
                nodos[i] = nodos[i-1];
                arcs[i] = arcs[i-1];
          }
          
          nodos[i] = newNode;
          arcs[i] = new MiLista();   
      }
      
      /**
       * Ordena los nodos al eliminar.
       * @param pos la posicion del nodo que se desea eliminar.
       */
      
      private void ordenarEliminar(int pos){
          for (int i = pos; i < numVertices; ++i){
                nodos[i] = nodos[i+1];
                arcs[i] = arcs[i+1];
          }
          
          nodos[numVertices] = null;
          arcs[numVertices] = null;
      }
      
      /**
       * Clona un grafo
       * @return Un grafo con los mismo elementos del original pero con
       * referencias distintas.
       */
      
      public Object clone(){
      
         DigraphLista grafo = new DigraphLista();
         grafo.numVertices = numVertices;
         grafo.numArcos = numArcos;
         grafo.nodos = new Nodo[numVertices];
         grafo.arcs = new MiLista[numVertices];

         for (int i = 0; i < numVertices; ++i){
         
            Object obj = nodos[i].clone();
            grafo.nodos[i] = (Nodo) obj; 
         }
        
         for (int i = 0; i < numVertices; ++i){
         
            grafo.arcs[i] = new MiLista();
            
            for (Arco arc : arcs[i]){
                Arco nuevoArc = (Arco) arc.clone();
                grafo.arcs[i].add(nuevoArc);
            }
         }
         
         return grafo;  
      }
           
     

    
   }
