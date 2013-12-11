
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;


public class Main {
    
    
    private static void encontrarSalida(Scanner entrada, BufferedWriter salida) throws IOException {
        
        int fila = entrada.nextInt();
        int columna = entrada.nextInt();
                        
        DigraphLista maze = createMaze(entrada,fila,columna);
        String resultado = dijkstra(maze);

        Nodo nodInic = maze.get(maze.getInicio());
        Nodo nodFin = maze.get(maze.getFin());
        
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
        Nodo nodFin = maze.get(fin);
        int costoFinal = nodFin.getCosto();
        nodFin.setCosto(costoFinal+1);
        nodInicio.setNumCaminos(1);
        nodInicio.setCostoAcc(nodInicio.getCosto());
        if (nodInicio.getCosto() != 0)
            queue.add(nodInicio);
        else 
            manejadorDeCeros(nodInicio,maze,0,1,queue);
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
                    
                    // Si el nodo no tiene peso 0, 
                    if (suc.getCosto() != 0){
                        suc.setCostoAcc(accActual+suc.getCosto());
                        suc.setNumCaminos(caminosActual);
                        queue.add(suc);
                        
                     //Si el nodo tiene peso 0 busco todos los 0 adyacentes   
                    } else
                        manejadorDeCeros(suc,maze,accActual,caminosActual,queue);                        
                    
                //Si ha sido visitado y llegar a el me cuesta lo mismo.    
                } else if (suc.getCostoAcc() == (accActual + suc.getCosto())){
                    if (suc.getCosto() != 0){
                        int numCaminosSuc = suc.getNumCaminos();
                        suc.setNumCaminos(numCaminosSuc + caminosActual);
                    } else 
                        manejadorDeCeros(suc,maze,accActual,caminosActual,queue);
                    
                }
            }
            
        }
        
        return nod.getNumCaminos() + " " + (nod.getCostoAcc() -1);
    }

    private static void manejadorDeCeros(Nodo nod, Digraph maze, int accActual, 
            int caminosActual, FibHeap<Nodo> queue){
                
        MiLista<Nodo> ceros = buscarCeros(nod,maze,accActual);
        nod.setVisitado(false);

          //Por cada cero adjacente busco la cantidad de caminos

        for (Nodo c : ceros){
            c.setVisitado(false);
            int numCaminos = enumerarCaminos(nod,c,maze);
            MiLista<Nodo> sucCeros = (MiLista) maze.getSucs(c.getId());

            //Para los sucesores de cada cero agrego el camino/peso 
            //a los otros nodos que no son parte del conjunto de 0s

            for (Nodo sucC: sucCeros){

                if(sucC.getNumCaminos() == 0 && sucC.getCosto() != 0){                                    
                    sucC.setCostoAcc(accActual+sucC.getCosto());
                    sucC.setNumCaminos(caminosActual*numCaminos);
                    queue.add(sucC);

                } else if (sucC.getCostoAcc() == (accActual + sucC.getCosto()) 
                        && sucC.getCosto() != 0){

                        int numCaminosSuc = sucC.getNumCaminos();
                        sucC.setNumCaminos(numCaminosSuc + caminosActual * numCaminos);
                }

            }
        }
    }
    
    private static MiLista<Nodo> buscarCeros(Nodo nodInic, Digraph grafo, int costoAcc){
        
        MiLista <Nodo> lista  = new MiLista();
        lista.add(nodInic);
        MiCola<Nodo> cola = new MiCola();
        nodInic.setCostoAcc(costoAcc);
        nodInic.setVisitado(true);
        
        cola.add(nodInic);
        
        while(!cola.isEmpty()){
            
            Nodo aux = cola.dequeue();
            MiLista<Nodo> sucs = (MiLista) grafo.getSucs(aux.toString());
            
            for(Nodo suc : sucs){
                if (suc.getCosto() == 0 && !suc.getVisitado()){
                    suc.setCostoAcc(costoAcc);
                    suc.setVisitado(true);
                    lista.add(suc);
                    cola.add(suc);
                }
            }
            
            
        }

        return lista;
    }
    
    private static int enumerarCaminos(Nodo nodInic, Nodo nodFin, Digraph grafo){
        
        if (nodInic.equals(nodFin))
            return 1;
        
        Stack<Nodo> stackNodo = new Stack();
        Stack<Iterator<Nodo>> stackIter = new Stack();
        
        stackNodo.add(nodInic);
        Iterator<Nodo> itr = grafo.getSucs(nodInic.getId()).iterator();
        stackIter.add(itr);
        
        int contador = 0;
        
        while (!stackNodo.empty()){

            Iterator<Nodo> itrActual = stackIter.peek();
            
            if (itrActual.hasNext()){
                
                Nodo suc = itrActual.next();
                
                if(suc.equals(nodFin)){  
                    ++contador;
                } else if(suc.getCosto() == 0 && !stackNodo.contains(suc)){
                    stackNodo.add(suc);
                    itr = grafo.getSucs(suc.getId()).iterator();
                    stackIter.add(itr);
                }
                
            } else {
                
                stackIter.pop();
                stackNodo.pop();
                
            }
            
            
        }
        
        return contador;
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