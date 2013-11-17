/*
 * Clase: Nodo
 * Descripcion: Clase que contiene las operaciones y la informacion de un
 *              nodo de un digrafo.
 * Nombres: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 *          Grupo 28
 */

   public class Nodo implements Comparable<Nodo>{

      // id es unico
      private String id = null;
      private Nodo padre = null;
      private boolean visitado = false;
      private int costo = Integer.MAX_VALUE;
      private boolean esFunc = false;
      

     
      /*
       * Metodo: Nodo
       * Descripcion: El constructor de un Nodo. 
       * Parametros: i: Informacion del nodo.
       * Precondicion: i es un string valido.
       * Postcondicion: Devuelve un nodo valido.
       */
       
      public Nodo(String i) {
      
         id = new String(i);
      }
      
      /*
       * Metodo: clone
       * Descripcion: Returna un nuevo nodo que es copia de this.
       * Parametros: this: Nodo a clonar.
       * Precondicion: this es un nodo valido.
       * Postcondicion: Devuelve un nuevo nodo valido que es una copia
       *                del original.
       */
    
      @Override
      protected Object clone() {
         
         return new Nodo(id);
      }

      /*
       * Metodo: equals
       * Descripcion: Indica si el nodo de entrada es igual a this.
       * Parametros: this: Un nodo al cual se va a comparar. 
       *             o: Nodo al cual se compara a this.
       * Precondicion: this y o son nodos validos.
       * Postcondicion: igual == (this.id = o.id)
       */

      public boolean equals(Object o) {
	
         Nodo d = (Nodo) o;
         return (o instanceof Nodo) && d.id.equalsIgnoreCase(id);
      }
      
      /*
       * Metodo: toString
       * Descripcion: Retorna la representacion en String del nodo.
       * Parametros: this: Nodo de donde se devuelve el string.
       * Precondicion: this es un nodo valido.
       * Postcondicion: Devuelve el string almacenado en el nodo.
       */
       
      @Override
      public String toString() {
	
         return new String(id);
      }
      
      public void setPadre(Nodo nod) {
          
          padre = nod;
          
      }

      public Nodo getPadre() {
          
          return padre;
      }
      
      public void setVisitado(boolean bool) {
      
	  visitado = bool;
	  
	  
      }
      
      public boolean getVisitado() {
      
	  return visitado;
	  
      }
      
      public void setCosto(int cost) {
      
	  costo = cost;
	  
      }
      
      public int getCosto() {
      
	 return costo;
	 
      }
      
      public void setEsFunc(boolean bool) {

         esFunc = bool;
      
      }
      
      public boolean getEsFunc() {
      
         return esFunc;
      }
      
      
      public int compareTo(Nodo nod) {
      
	
	  
         if (this.costo < nod.costo) {
         
               return -1;
               
         } else if (this.costo == nod.costo) {
         
               return 0;
               
         } else {
         
            return 1;
            
         }
	     
     } 
      
      
      
   }
