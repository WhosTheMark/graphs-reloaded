
import java.util.Objects;

/*
 * Descripcion: Operaciones e informacion de un arco.
 * Autores: Marcos Campos 10-10108
 *          Andrea Salcedo 10-10666
 */

   public class Arco implements Comparable<Arco>, Cloneable{

      private String src = null;
      private String dst = null;
      private int costo = 0;    

      private Arco() {}
   

    /**
     * Crea una arco entre los vertices src y dst. 
     * @param src id del nodo fuente
     * @param dst id del nodo destino
     * @param costo costo del arco
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
      
    /**
     * Clona un objeto Arco.
     * @returns devuelve una copia del objeto Arco.
     */  
      @Override
      protected Object clone() {
	
         Arco edge = new Arco();
         edge.src = new String(src);
         edge.dst = new String(dst);
         edge.costo = costo;

         return edge;
      }
      
      /**
       * Verifica si dos arcos son iguales.
       * @param o Objeto a igualar
       * @return True si los campos de los arcos son iguales, False
       * en caso contrario.
       */
       
      @Override
      public boolean equals(Object o) {
          
          if (o instanceof Arco){
              Arco otraArista = (Arco) o;
              return (this.src.equals(otraArista.src)) &&
                (this.dst.equals(otraArista.dst)) &&
                (this.costo == otraArista.costo);
          }
          return false;
       
      }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.src);
        hash = 23 * hash + Objects.hashCode(this.dst);
        return hash;
    }
      
    /**
     * Convierte el arco a String.
     * @return String representando el arco.
     */
   
      @Override
      public String toString() {
         return "("+src + ", " + dst+ ": " + costo + ")";
      }
    
      /**
       * Compara el costo de dos arcos.
       * @param arc Arco a comparar
       * @return la diferencia de los costos.
       */
      
      @Override
      public int compareTo(Arco arc) {
         return (this.costo - arc.costo);
      }
      
      public String getSrc() {
         return src;
      }

      public String getDst() {
         return dst;
      }
      public int getCosto() {
         return costo;
      }
      
   }
