
import java.io.*;
import java.util.Scanner;

public class Main {
    
    
    private static void encontrarSalida(Scanner entrada, BufferedWriter salida) throws IOException {
        
        int fila = entrada.nextInt();
        int columna = entrada.nextInt();
                        
        DigraphLista maze = createMaze(entrada,fila,columna);
        //preprocesar(maze);
        String resultado = bellmanFord(maze,fila*columna); 
        
        System.out.println(resultado);
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
     
    
    private static String bellmanFord(DigraphLista maze, int size){
        
        MiCola<Nodo> nodosActuales = new MiCola();
        MiCola<Nodo> nodosExpandidos = new MiCola();
        String inicio = maze.getInicio();
        Nodo nodInicio = maze.get(inicio);
        nodInicio.setCostoAcc(nodInicio.getCosto());
        nodosActuales.add(nodInicio);
        int contadorCiclos = 0;
        
        for(;contadorCiclos <= size && !nodosActuales.isEmpty(); ++contadorCiclos){

            while(!nodosActuales.isEmpty()){
                Nodo nod = nodosActuales.dequeue();
                MiLista<Nodo> sucesores = (MiLista) maze.getSucs(nod.getId());

                for(Nodo suc : sucesores){

                    if(suc.getCostoAcc() > nod.getCostoAcc() + suc.getCosto()){
                        
                        suc.setCostoAcc(nod.getCostoAcc() + suc.getCosto());
                        if (!nodosExpandidos.contains(suc))
                           nodosExpandidos.add(suc);

                    } 
                }  
            }
            
            MiCola<Nodo> temporal = nodosActuales;
            nodosActuales = nodosExpandidos;
            nodosExpandidos = temporal;
        }
        
        String Fin = maze.getFin();
        Nodo nodFin = maze.get(Fin);

        if(contadorCiclos -1 == size)
            return "-INF";
        
        return String.valueOf(nodFin.getCostoAcc());
    }
    
    private static void preprocesar(DigraphLista maze){
        
        MiCola<Nodo> queue = new MiCola();
        String inicio = maze.getInicio();
        Nodo nodInicio = maze.get(inicio);
        queue.add(nodInicio);
        nodInicio.setVisitado(true);
        
        if(nodInicio.getCosto() == 0)
            manejarCeros(maze,nodInicio,queue);
        
        while(!queue.isEmpty()){
            
            Nodo nod = queue.dequeue();
            MiLista<Nodo> sucesores = (MiLista) maze.getSucs(nod.getId());
            
            for(Nodo suc : sucesores){
                
                if(!suc.getVisitado()){
                    suc.setVisitado(true);
                    if(suc.getCosto() != 0){
                        queue.add(suc);
                    } else {
                        manejarCeros(maze, suc, queue);
                    }
                }
            }
        }
        
        maze.clearVisitados();
        
    }
    
    private static void manejarCeros(DigraphLista maze, Nodo nodInicio,
            MiCola queueMaze){
        
        MiCola<Nodo> queue = new MiCola();
        queue.add(nodInicio);
        MiLista<Nodo> listaCeros = new MiLista();
        MiLista<Nodo> listaAdyacentes = new MiLista();
        
        while(!queue.isEmpty()){
            
            Nodo nod = queue.dequeue();
            MiLista<Nodo> sucesores = (MiLista) maze.getSucs(nod.getId());
            
            for(Nodo suc : sucesores){
                
                if(suc.getCosto() == 0 && !suc.getVisitado()){
                    suc.setVisitado(true);
                    queue.add(suc);
                    listaCeros.add(suc);
                } else if (suc.getCosto() != 0 && !listaAdyacentes.contains(suc) 
                        && nod != nodInicio)
                    listaAdyacentes.add(suc);
                 
            }
        }
        
        if(listaCeros.getSize() == 0)
            return;
        
        Nodo nodFin = maze.get(maze.getFin());
        if(listaCeros.contains(nodFin))
            maze.setFin(nodInicio.getId());
        
        for(Nodo cero: listaCeros){
            maze.remove(cero.getId());
        }
        
        for(Nodo adyacente : listaAdyacentes){
            
            adyacente.setVisitado(true);
            queueMaze.add(adyacente);
            
            Arco arc1 = new Arco(nodInicio.getId(),adyacente.getId());
            Arco arc2 = new Arco(adyacente.getId(),nodInicio.getId());
            
            maze.add(arc1);
            maze.add(arc2);
            
        }
        
        
        
        
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
