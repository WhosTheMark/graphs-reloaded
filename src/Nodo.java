
/*
 * Descripcion: Operaciones e informacion de un nodo.
 * Autores: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 */

   public class Nodo implements Comparable<Nodo>, Cloneable {

      private String id = null;
      private int costo = 0;
      private boolean visitado = false;
      private long numCaminos = 0;
      private int costoAcc = 0;
       
      public Nodo(String i) {
         id = new String(i);
      }
      
      /**
       * Clona un objeto Nodo.
       * @returns devuelve una copia del objeto Nodo.
       */

      @Override
      protected Object clone() {
         return new Nodo(id);
      }

      /**
       * Verifica si dos nodos son iguales.
       * @param o Objeto a igualar
       * @return True si los campos de los nodos son iguales, False
       * en caso contrario.
       */

      @Override
      public boolean equals(Object o) {
         if (o instanceof Nodo) {
            Nodo nod = (Nodo) o;
            return nod.id.equals(id);
         } 
         return false;
      }
      
    public int compareToId(Object o){
        Nodo nod = (Nodo) o;
        return this.id.compareTo(nod.id);
    }
    
       public int compareTo(Nodo nod) {
            return (this.costoAcc - nod.costoAcc);
       }
    
    /**
     * Convierte el nodo a String.
     * @return String representando el nodo.
     */
       
      @Override
      public String toString() {
         return id;
      }
      
      public String getId(){
          return id;
      }
      
      public void setVisitado(boolean bool) {
	  visitado = bool;
      }
      
      public boolean getVisitado() {
	  return visitado; 
      } 

    public int getCosto() {
        return costo;
    }

    public long getNumCaminos() {
        return numCaminos;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public void setNumCaminos(long numCaminos) {
        this.numCaminos = numCaminos;
    }

    public int getCostoAcc() {
        return costoAcc;
    }

    public void setCostoAcc(int costoAcc) {
        this.costoAcc = costoAcc;
    }
      
      
      
   }
