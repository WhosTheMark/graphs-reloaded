/*
 * Clase: DigraphLista
 * Descripcion: Implementa la clase abstracta Digraph utilizando listas.
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 *          Grupo 28
 */
 
   public class DigraphLista extends Digraph
                             implements Comparable<DigraphLista> {

      private  Nodo nodos[];
      private  Lista<Arco> arcs[];
      public final int tam = 100;
      private Integer costo = Integer.MAX_VALUE; 
      private MiLista<String> camino = new MiLista();

      /*
       * Metodo: DigraphLista
       * Descripcion: Construye un grafo vacio.
       * Parametros: --
       * Precondicion: true
       * Postcondicion: grafo.numVertices = 0 /\ grafo.numArcos = 0
       *                /\ se crea un arreglo de nodos /\ se crea una
       *                nueva lista de arcos.
       */
       
      public DigraphLista() {
	
         super();
         nodos = new Nodo[tam];
         
         arcs = (Lista<Arco>[]) new MiLista[tam];
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
       
      public  boolean add(Arco e){
        
         String src = e.getSrc();
         String dst = e.getDst();
         int costo = e.getCosto();
        
         boolean estaSrc = false;
         boolean bool;
          
         int i = 0;       
         int posSrc = -1;
         
         if (!(this.contains(src,dst,costo))) {
             
             while (!estaSrc) {
                 
                 if (src.equals(nodos[i].toString())) {
                     posSrc = i;
                     estaSrc = true;
                 }
                 i++;
             }
             
             bool = arcs[posSrc].add(e);
             numArcos++;
               
             return bool;
             
             
         } else {
             return false;
         }
        
       

      }
    
      /*
       * Metodo: add 
       * Descripcion: Agrega un nodo al grafo dado. Si el vertice ya existe,
       *              el metodo retorna false. Si se agrega el nodo, retorna
       *              true.
       * Parametros: this: Grafo al cual se va a agregar el nodo.
       *             n: Nodo a ser agregado.
       * Precondicion: this es un grafo valido /\ n es un nodo valido.
       * Postcondicion: agregado == (no existe un nodo igual al nodo n /\
       *                    pub         el nodo n fue agregado al grafo).
       */
       
      public  boolean add(Nodo n){
 
         boolean esta = false;
        
         esta = this.contains(n.toString());
        
         if (esta) {
            
               return false;
            
         } else {
        
               if (nodos.length <= numVertices) {
                
                  Nodo nodosDoble[] = new Nodo[2*nodos.length];
                  
                  @SuppressWarnings("unchecked")
                  Lista<Arco> arcsDoble[] = new MiLista[2*nodos.length];
                
                  System.arraycopy(nodos, 0, nodosDoble, 0, nodos.length);
                  System.arraycopy(arcs, 0, arcsDoble, 0, nodos.length);
                
                  nodos = null;
                  nodos = nodosDoble;
                
                  arcs = null;
                  arcs = arcsDoble;              
                
               }
            
                      
               nodos[numVertices] = n;
               
               arcs[numVertices] = new MiLista();
               numVertices++;
               this.ordenarNodos();
           
               return true;
         }
      }

      /*
       * Metodo: clear 
       * Descripcion: Elimina los nodos y los arcos del grafo.
       * Parametros: this: Grafo de donde se van a eliminar los arcos y nodos.
       * Precondicion: this es un grafo valido.
       * Postcondicion: this.numArcos = 0 /\ this.numVertices = 0 /\
       *                todos los nodos fueron eliminados /\
       *                todos los arcos fueron eliminados.
       */
       
      public  void clear(){
        
         for (int i = 0; i < numVertices; i++) {

            nodos[i] = null;   
            arcs[i] = null;
         }
        
         numVertices = 0;
         numArcos = 0;
      }

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
       
      public boolean contains(String src, String dst, int costo){
        
         boolean estaSrc = false;
         boolean estaDst = false;
         
         int i = 0;
         int posSrc = -1;
        
         while (i < numVertices && !estaSrc) {
        
            if (src.equals(nodos[i].toString())){
            
               estaSrc = true;
               posSrc = i;
            }
            
            i++;
         }
              
         if (estaSrc) {
      
            Arco arc = new Arco(src,dst,costo);
            estaDst = arcs[posSrc].contains(arc);    
         }
         
         
        
         return estaSrc && estaDst;
      }

      /*
       * Metodo: contains
       * Descripcion: Revisa si el grafo contiene el nodo con id nod.
       * Parametros: this: Grafo de donde se revisa si existe el nodo dado.
       *             nod: id del nodo dado.
       * Precondicion: this es un grafo valido.
       * Postcondicion: contains == (existe un nodo con el id nod en this)
       */
       
      public boolean contains(String nod) {
	
         int i;
	
         i = this.busqueda(nod);
         
         if (i < 0) {
         
            return false;
            
         } else {
         
            return true;
         }
      }
      
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
       
      public int busqueda(String nod) {
        
         boolean esta = false;
         int i = 0;
         int min = 0;
         int max = numVertices -1;
         int med;
         
         if (numVertices != 0) { 
        
         while (max >= min) {
        
               med = ((min + max) / 2);
               
               if ((nodos[med].toString().compareTo(nod)) < 0) {
               
                  min = med + 1;
               
               } else if ((nodos[med].toString().compareTo(nod)) > 0) {
               
                     max = med - 1;
                     
               } else {
                
                  return med;
               
               }
               
        }
        }
        
        return -1;
        
      }
      
      public Nodo getNod(String nod) {
      
	  int pos = busqueda(nod);
	  
	  if (pos != -1) {
	      
	      return nodos[pos];
	
	  } else {
	  
	      return null;
	      
	  }
	  
      }    
      
      
      
      /*
       * Metodo: get 
       * Descripcion: Retorna el arco del grafo que conecta los vertices src
       *              y dst. Si no existe dicho arco, el metodo retorna null.
       * Parametros: this: Grafo de donde se devuelve el arco.
       *             src: Vertice de origen dado.
       *             dst: Vertice de destino dado.
       * Precondicion: this es un grafo valido.
       * Postcondicion: this.contains(src,dst) ==> devuelve el arco (src,dst)
       *                /\ !this.contains(src,dst) ==> devuelve null
       */
       
      public  Arco get(String src, String dst, int costo) {
        
         if (this.contains(src,dst,costo)) {
           
            Arco arc = new Arco(src,dst,costo);
            return arc;
       
         } else {
         
            return null;
         }
      }

      /*
       * Metodo: getArcos
       * Descripcion: Retorna todos los arcos del grafo dado.
       * Parametros: this: Grado de donde se duelve los arcos.
       * Precondicion: this es un grafo valido.
       * Postcondicion: Devuelve una lista valida que contiene unicamente 
       *                como elementos todos los arcos de this.
       */

      public Lista<Arco> getArcos(){
        
         MiLista<Arco> list = new MiLista();
         Object[] arrObj;
         
         boolean agregado;
         
         Arco arc;
         Arco nuevoArc;
        
         for (int i = 0; i < numVertices; i++) {
            
            arrObj = arcs[i].toArray();
            
            for (int j = 0; j < arrObj.length; j++) {
                
               arc = (Arco) arrObj[j];
               nuevoArc = (Arco) arc.clone();
               agregado = list.add(nuevoArc);             
            }
         }
      
         return list;
      }

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
    
      public Nodo get(String nod){
      
         if (this.contains(nod)){
         
            Nodo vertic = new Nodo(nod);
            return vertic;
            
         } else {
           
            return null;       
         }
      }

      /*
       * Metodo: getNodos
       * Descripcion: Retorna todos los nodos del grafo dado.
       * Parametros: this: Grafo de donde se devuelven los nodos.
       * Precondicion: this es un grafo valido.
       * Postcondicion: Se devuelve una lista valida que contiene unicamente
       *                como elementos todos los nodos del grafo this.
       */
       
      public  Lista<Nodo> getNodos() {
      
         @SuppressWarnings("unchecked")
         MiLista<Nodo> list = new MiLista();
         boolean agregado;
         
         for (int i = 0; i < numVertices; i++) {
            Nodo nod = nodos[i];
            
            agregado = list.add(nod);  
         } 
         
         return list;
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
       
      public Lista<Arco> getInArcos(String nodo) {
        
         if (this.contains(nodo)) {
            
            @SuppressWarnings("unchecked")
            MiLista<Arco> list = new MiLista();
            Object[] arrObj;
            
            Arco arc;
            Arco nuevoArc;
            
            boolean agregado;
      
            for (int i = 0; i < numVertices; i++) {
    
               arrObj = arcs[i].toArray();
                
               for (int j = 0; j < arrObj.length; j++) {
                    
                  arc = (Arco) arrObj[j];
                    
                  if (arc.getDst().equals(nodo)) {
                        
                     nuevoArc = (Arco) arc.clone();
                     agregado = list.add(nuevoArc);    
                  }                    
               }                
            }
            
            return list;
            
         } else {
            
            return null;
         }  
      }
       
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
      
      public Lista<Arco> getOutArcos(String nodo) {
    
         if (this.contains(nodo)) {
            
            @SuppressWarnings("unchecked")
            MiLista<Arco> list = new MiLista();
            Object[] arrObj;
            
            Arco arc;
            Arco nuevoArc;
            
            boolean agregado;
            int posSrc = 0;
            
            while (!nodos[posSrc].toString().equals(nodo)) {
            
               posSrc++;              
            }
            
            arrObj = arcs[posSrc].toArray();
            
            for (int i = 0; i < arrObj.length; i++) {
                
               arc = (Arco) arrObj[i];
               nuevoArc = (Arco) arc.clone();
               agregado = list.add(nuevoArc); 
            }
            
            return list;
            
         } else {
            
            return null;
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
       
      public  boolean remove(String src, String dst, int costo) {
        
         if (this.contains(src,dst, costo)) {
        
            int posSrc = 0;
            
            while (!nodos[posSrc].toString().equals(src)){
            
               posSrc++;                 
            }
            
            Arco arc = new Arco(src, dst, costo);
            
            boolean bool = arcs[posSrc].remove(arc);
            
            return bool;
            
         } else {
            
            return false;
         }
      }

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
       
      public  boolean remove(String nod) {
        
         if (this.contains(nod)) {
            
            boolean bool;
            Arco arc;
            
            for (int i = 0; i < numVertices; i++) {
                
               Object[] arrArc = arcs[i].toArray();
               
               for (int j = 0; j < arrArc.length; j++){
                   
                   arc = (Arco) arrArc[j];
                   if (nod.equals(arc.getDst())) {
                       
                       int costo = arc.getCosto();
                       arc = new Arco(nodos[i].toString(),nod,costo);                
                       bool = arcs[i].remove(arc);
                       
                      if (bool) {

                        numArcos--;
                        
                     }
                   } 
               } 
            }         
                        
            int posSrc = 0;
            
            while (!nodos[posSrc].toString().equals(nod)){
            
               posSrc++;                    
            }
            
            numArcos -= arcs[posSrc].getSize();
  
            
            arcs[posSrc] = null;
            numVertices--;
            
            arcs[posSrc] = arcs[numVertices];
            nodos[posSrc] = nodos[numVertices];            

            arcs[numVertices] = null;
            nodos[numVertices] = null;

            this.ordenarNodos();
            
            
            return true;
            
         } else {
            
            return false;
         }
      }
      
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
       
      public Lista<Nodo> getPreds(String nodo){
        
         if (this.contains(nodo)) {
        
            MiLista<Nodo> list = new MiLista();     
            Arco arc;
            Object[] arrArc;
            
            for (int i = 0; i < numVertices; i++) {

               arrArc = arcs[i].toArray();
               
               for (int j = 0; j < arrArc.length; j++){
                   
                   arc = (Arco) arrArc[j];

                    if (nodo.equals(arc.getDst())) {

                       Nodo nod = new Nodo(nodos[i].toString());
                       list.add(nod);

                    } 
               
               }
            }
            
            return list;
            
         } else {
        
            return null;
         }
      }
      
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
       
      public Lista<Nodo> getSucs(String nodo) {
        
         if (this.contains(nodo)) {
            
            MiLista<Nodo> list = new MiLista();

            Object[] arrObj;
            String dst;
            
            Nodo nod;
            Arco arc;
            
            boolean agregado;
            boolean esta;
            int posSrc = 0;
            int j;

            while (!nodos[posSrc].toString().equals(nodo)) {
               posSrc++;
            }
                        
            arrObj = arcs[posSrc].toArray();
            
            for (int i = 0; i < arrObj.length; i++) {
                
               arc = (Arco) arrObj[i];
                
               dst = arc.getDst();
               esta = false;
               j = 0;
                              
               while (!esta){
                   
                   esta = nodos[j].toString().equals(dst);
                   j++;
               }
                 
               agregado = list.add(nodos[j-1]);                
            }
            
            return list;
            
         }  else {
            
            return null;
         }
      }
     
      /*
       * Metodo: ordenarNodos
       * Descripcion: Ordena los nodos de un digrafo en orden alfabetico.
       * Parametros: this: Grafo a ordenar.
       * Precondicion: this es un grafo valido.
       * Postcondicion: Todos los nodos del grafo estan ordenado
       *                alfabeticamente.
       */
          
      public void ordenarNodos() {
      
         Nodo auxNod;
         Lista<Arco> auxArc; 
         boolean ordenado;
      
         for (int i = 0; i < numVertices - 1; i++) {
         
            ordenado = true;
                 
            for (int k = numVertices - 1; k >= i+1; k--) {

               String str1 = nodos[k].toString();
               String str2 = nodos[k-1].toString();
              
               if ((str1.compareTo(str2)) < 0) {

                        auxNod = nodos[k-1];
                        nodos[k-1] = nodos[k];
                        nodos[k] = auxNod;
                        auxArc = arcs[k-1];
                        arcs[k-1] = arcs[k];
                        arcs[k] = auxArc;
                        ordenado = false;

                }
                
            }
            
            if (ordenado){
               return;
               
            }  
         
         }
         
      }
      
      public int getCosto() {
      
         return costo;
      }
      
      public void setCosto(int cost) {
      
         costo = cost;
      
      }

      public int compareTo(DigraphLista grafo) {
      
         if (this.costo > grafo.costo) {
         
            return 1;
            
         } else if (this.costo == grafo.costo) {
         
            return 0;
            
         } else {
         
            return -1;
            
         }
      
      }
      
      public void setCamino(MiLista<String> cam) {
      
         camino = cam;
         
      }
      
      public MiLista<String> getCamino() {
      
         return camino;
         
      }
      
      public Object clone(){
      
         DigraphLista grafo = new DigraphLista();
         grafo.camino = (MiLista) this.camino.clone();
         grafo.costo = this.costo;
         grafo.numVertices = numVertices;
         grafo.numArcos = numArcos;
         grafo.nodos = new Nodo[numVertices];
         grafo.arcs = new MiLista[numVertices];

         for (int i = 0; i < numVertices; i++){
         
            Object obj = nodos[i].clone();
            grafo.nodos[i] = (Nodo) obj; 
         
         }
        
         
         for (int i = 0; i < numVertices; i++){
         
            Object[] arrArc = arcs[i].toArray();
            grafo.arcs[i] = new MiLista();
            
            for (int j = 0; j < arrArc.length; j++){
                
                Arco arc = (Arco) arrArc[j];
                Object arcObj = arc.clone();
                arc = (Arco) arcObj;
                grafo.arcs[i].add(arc);
                
            }
         }
         
         return grafo;
         
      }
      
      public void clearVisitados() {
      
         for (int i = 0; i < numVertices; i++) {
         
               nodos[i].setVisitado(false);
               nodos[i].setPadre(null);
         }
         
      }
      
   }
