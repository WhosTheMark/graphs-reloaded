
import java.io.*;
import java.util.Scanner;

public class Main {
    
    
    private static void encontrarSalida(Scanner entrada, BufferedWriter salida) throws IOException {
        
        int fila = entrada.nextInt();
        int columna = entrada.nextInt();
                        
        DigraphLista maze = createMaze(entrada,fila,columna);
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
        String Fin = maze.getFin();
        Nodo nodFin = maze.get(Fin);
        
        for(;contadorCiclos <= size && !nodosActuales.isEmpty(); ++contadorCiclos){

            while(!nodosActuales.isEmpty()){
                Nodo nod = nodosActuales.dequeue();
                MiLista<Nodo> sucesores = (MiLista) maze.getSucs(nod.getId());

                for(Nodo suc : sucesores){

                    if((null == nod.getPadre() || suc != nod.getPadre()) &&
                            suc.getCostoAcc() > nod.getCostoAcc() + suc.getCosto()){
                        
                        suc.setCostoAcc(nod.getCostoAcc() + suc.getCosto());
                        suc.setPadre(nod);
                        if (!nodosExpandidos.contains(suc))
                           nodosExpandidos.add(suc);

                    } else if (suc.getPadre() != nod && 
                            (suc.getPadre() == null || suc.getPadre().getCosto() < nod.getCosto()) &&
                           suc.getCostoAcc() == nod.getCostoAcc() + suc.getCosto()
                            && suc != nod.getPadre()){
                        
                        if(suc.getCostoAcc() < 0)
                            return "-INF";
                        
                        suc.setPadre(nod);
                        
                        if (!nodosExpandidos.contains(suc))
                            nodosExpandidos.add(suc);

                    }
                }
                        
            }
            
            MiCola<Nodo> temporal = nodosActuales;
            nodosActuales = nodosExpandidos;
            nodosExpandidos = temporal;
            
            
        }
        
        nodFin.setVisitado(true);
        boolean hayCiclo = false;
        Nodo act = nodFin;
        while(null != act.getPadre()){
            if(act.getPadre().getVisitado()){
                hayCiclo = true;
                break;
            } else {
                act = act.getPadre();
                act.setVisitado(true);
            }
        }
        
        if(hayCiclo)
            return "-INF";
        
        
        return String.valueOf(nodFin.getCostoAcc());
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