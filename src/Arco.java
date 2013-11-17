/*
 * Clase: Arco
 * Descripcion: Clase que contiene las operaciones y la informacion de las
 *              aristas en un digrafo.
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 *          Grupo 28
 */

   public class Arco implements Comparable<Arco>{

      private String src = null;
      private String dst = null;
      private int costo = 0;    

      private Arco() {
      
      }
   
      /*
       * Metodo: Arco 
       * Descripcion: Crea una arista entre los vertices src y dst. 
       * Parametros: src: String del vertice de origen.
       *             dst: String del vertice de destino.
       * Precondicion: src y dst son los identificadores de unos nodos.
       * Postcondicion: Devuelve un arco valido entre src y dst. 
       */
       
      public Arco(String src, String dst, int costo) {
      
         this.src = src;
         this.dst = dst;
         this.costo = costo;
      }
      
     public Arco(String src, String dst) {
      
         this.src = src;
         this.dst = dst;

      } 
      
      
      /*
       * Metodo: clone 
       * Descripcion: Retorna un nuevo arco que es una copia de this.
       * Parametros: this: Arco a copiar.
       * Precondicion: this es un arco valido.
       * Postcondicion: Devuelve un nuevo arco valido que es una copia del
       *                original.
       */

      @Override
      protected Object clone() {
	
         Arco ed = new Arco();

         // se copian (clonan) todos los objetos internos, 
         // no solo asignar las referencias
         
         ed.src = new String(src);
         ed.dst = new String(dst);
         ed.costo = costo;

         return ed;
      }
      
      /*
       * Metodo: equals
       * Descripcion: Indica si el arco de entrada es igual a this.
       * Parametros: this: Arco al cual se va a comparar.
       *             o: Arco que se va a comparar a this.
       * Precondicion: this y o son arcos validos.
       * Postcondicion: igual == ((this.src = o.src) /\ (this.dst = o.dst))
       */
       
      public boolean equals(Object o) {
      
         boolean igual;
         Arco otraArista = (Arco) o;
         
         igual = (o instanceof Arco) && 
                (this.src.equals(otraArista.src)) &&
                (this.dst.equals(otraArista.dst)) &&
                (this.costo == otraArista.costo);
         
         return igual;
       
      }
      
      /*
       * Metodo: getSrc
       * Descripcion: Retorna el vertice src del arco.
       * Parametros: this: Arco de donde se devuelve el vertice src.
       * Precondicion: this es un arco valido.
       * Postcondicion: Se devuelve el string src.
       */
       
      public String getSrc() {
      
         String source = new String(src);
         return source;
      }

      /*
       * Metodo: getDst
       * Descripcion: Retorna el vertice dst del arco.
       * Parametros: this: Arco de donde se devuelve el vertice dst.
       * Precondicion: this es un arco valido.
       * Postcondicion: Se devuelve el string dst.
       */
       
      public String getDst() {
      
         String destino = new String(dst);
         return destino;
      }
      
      /*
       * Metodo: toString
       * Descripcion: Retorna la representacion en string del arco.
       * Parametros: this: El arco de donde se devuelve el string.
       * Precondicion: this es un arco valido.
       * Postcondicion: Se devuelve la representacion en string de this.
       */
   
      @Override
      public String toString() {
	
         return "("+src + ", " + dst+ ": " + costo + ")";
      }
    
      public int getCosto() {
          
         return costo;
      }
      
      public int compareTo(Arco arc) {
      
         if (this.costo < arc.costo) {
         
            return -1;
               
         } else if (this.costo == arc.costo) {
         
            return 0;
               
         } else {
         
            return 1;
            
         }
      
      }
      
   }
