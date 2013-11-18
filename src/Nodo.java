
import java.util.Objects;

/*
 * Descripcion: Operaciones e informacion de un nodo.
 * Autores: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 */

   public class Nodo implements Cloneable {

      private String id = null;
      private Nodo padre = null;
      private boolean visitado = false;
       
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }
      
    
    public int compareTo(Object o){
        Nodo nod = (Nodo) o;
        return this.id.compareTo(nod.id);
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
   }
