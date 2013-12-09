
import java.io.*;
import java.util.Scanner;


public class Main {
    
    
    private static void encontrarSalida(Scanner entrada, BufferedWriter salida) throws IOException {
        
        int fila = entrada.nextInt();
        int columna = entrada.nextInt();
                        
        DigraphLista maze = createMaze(entrada,fila,columna);
        String resultado = dijkstra(maze);

        salida.write(resultado);
        salida.newLine();

        
    }
    
    private static DigraphLista createMaze(Scanner entrada, int fila, int columna) {
            
            DigraphLista grafo = new DigraphLista();
            grafo.setFin("("+(fila-1)+","+(columna-1)+")");
            grafo.setInicio("(0,0)");
           
            for(int contFila = 0; contFila < fila; ++contFila) {

                for(int contColum=0; contColum < columna; ++contColum){

                    int costo = entrada.nextInt();

                    String nodId = "("+contFila+","+ contColum+")";
                    Nodo nodo = new Nodo(nodId);
                    nodo.setCosto(costo);
                    grafo.add(nodo);

                    if (contFila != 0) {
                        
                        String nodNorte = "("+(contFila-1)+","+contColum+")";
                        Arco arc1 = new Arco(nodId,nodNorte);
                        Arco arc2 = new Arco(nodNorte,nodId);

                        grafo.add(arc1);
                        grafo.add(arc2);
                    }

                    if (contColum != 0) {

                        String nodOeste = "("+contFila+","+(contColum-1)+")";
                        Arco arc1 = new Arco(nodId,nodOeste);
                        Arco arc2 = new Arco(nodOeste,nodId);

                        grafo.add(arc1);
                        grafo.add(arc2);

                    }
                }
            }
        return grafo;
    }
    
    
    private static String dijkstra(DigraphLista maze){
        
        FibHeap<Nodo> queue = new FibHeap();
        String inicio = maze.getInicio();
        Nodo nodInicio = maze.get(inicio);
        String fin = maze.getFin();
        nodInicio.setNumCaminos(1);
        nodInicio.setCostoAcc(nodInicio.getCosto());
        queue.add(nodInicio);
        Nodo nod = null;
        
        //Siempre voy a encontrar el nodo final.
        
        while (true){
            
            nod = queue.extraerMin();
            
            //Si es el final salgo
            if (nod.getId().equals(fin))
                break;
            
            MiLista<Nodo> sucesores = (MiLista) maze.getSucs(nod.getId());
            
            //Acumulado actual.
            int accActual = nod.getCostoAcc();
            int caminosActual = nod.getNumCaminos();
            
            for(Nodo suc : sucesores) {

                //Si no ha sido visitado 
                if(suc.getNumCaminos() == 0){
                    suc.setCostoAcc(accActual+suc.getCosto());
                    suc.setNumCaminos(caminosActual);
                    queue.add(suc);
                    
                //Si ha sido visitado y llegar a el me cuesta lo mismo.    
                } else if (suc.getCostoAcc() == (accActual + suc.getCosto())){
                    int numCaminosSuc = suc.getNumCaminos();
                    suc.setNumCaminos(numCaminosSuc + caminosActual);
                    
                }
            }
            
        }
        
        return nod.getNumCaminos() + " " + nod.getCostoAcc();
    }

    
    public static void main(String[] args) {
        
        if (args.length == 2) {

           String in = args[0];
           String out = args[1];

           try {

                 File archivoEntrada = new File(in);
                 Scanner entrada = new Scanner(archivoEntrada);
                 FileWriter fstream = new FileWriter(out);
                 BufferedWriter salida = new BufferedWriter(fstream);
                 int escenarios = entrada.nextInt();
                 for(int i = 0; i < escenarios; ++i)
                    encontrarSalida(entrada,salida);
                 salida.close();

           } catch (IOException ioe) {
                System.out.print(ioe.getMessage());
           }
       }
        
        
    }
    
}