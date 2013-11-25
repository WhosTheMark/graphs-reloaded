

import java.io.*;
import java.util.Scanner;


public class Main {
    
    
    public static void encontrarSalida(Scanner entrada, BufferedWriter salida) throws IOException {
        
        int nivel = entrada.nextInt();
        int fila = entrada.nextInt();
        int columna = entrada.nextInt();
        
        entrada.nextLine();
                        
        while ((nivel != 0) || (fila != 0) || (columna != 0)) {
            
            DigraphLista dungeon = createDungeon(entrada,nivel,fila,columna);
            String resultado = bfs(dungeon);
            
            salida.write(resultado);
            salida.newLine();        
            
            nivel = entrada.nextInt();
            fila = entrada.nextInt();
            columna = entrada.nextInt();
            
            if (nivel != 0)
                entrada.nextLine();
        }
    }
    
    public static DigraphLista createDungeon(Scanner entrada, int nivel,
                                             int fila, int columna) { 
            
            DigraphLista grafo = new DigraphLista();
           
            for (int contNivel = 0; contNivel < nivel; ++contNivel) {
           
                for(int contFila = 0; contFila <fila; ++contFila) {
                
                    String filaStr = entrada.nextLine();
                    
                    for(int contColum=0; contColum < columna; ++contColum){
                        
                        char celda = filaStr.charAt(contColum);
                        
                        if (celda != '$') {
                         
                            String nodId = "("+contNivel+","+contFila+","+
                                          contColum+")";                                    
                            Nodo nodo = new Nodo(nodId);
                            grafo.add(nodo);
                            
                            // El nodo en el nivel de abajo
                            String nodNadir = "("+(contNivel-1)+","
                                                +contFila+","+contColum+")";
                            
                            String nodNorte = "("+contNivel+","+(contFila-1)+
                                              ","+contColum+")";
                            
                            String nodOeste = "("+contNivel+","+contFila+","+
                                              (contColum-1)+")";
                            
                            if (contNivel != 0 && grafo.contains(nodNadir)) {
                                    
                                Arco arc1 = new Arco(nodId,nodNadir);
                                Arco arc2 = new Arco(nodNadir,nodId);                                  
                                    
                                grafo.add(arc1);
                                grafo.add(arc2);
                            }
                            
                            if (contFila != 0 && grafo.contains(nodNorte)) {
                                
                                Arco arc1 = new Arco(nodId,nodNorte);
                                Arco arc2 = new Arco(nodNorte,nodId);
                                
                                grafo.add(arc1);
                                grafo.add(arc2);        
                            }
                            
                            if (contColum != 0 && grafo.contains(nodOeste)) {
                                
                                Arco arc1 = new Arco(nodId,nodOeste);
                                Arco arc2 = new Arco(nodOeste,nodId);
                                
                                grafo.add(arc1);
                                grafo.add(arc2);                                
                                
                            }
                            
                            if (celda == 'I') {
                                grafo.setInicio(nodId);
                            } else if (celda == 'F') {
                                grafo.setFin(nodId);
                            }
                            
                            
                        }
                    } 
                }
                entrada.nextLine();                
            }  
        return grafo;            
    }
    
    public static String bfs(DigraphLista dungeon){
        
        MiCola<Nodo> queue = new MiCola();
        
        String inicio = dungeon.getInicio();  
        Nodo nodInicio = dungeon.get(inicio);
        nodInicio.setVisitado(true);
        queue.add(nodInicio);
        
        cola:
        while (!queue.isEmpty()) {
            
            Nodo nodo = queue.dequeue();
            
            MiLista<Nodo> sucesores = (MiLista) dungeon.getSucs(nodo.getId());
            
            for(Nodo suc : sucesores) {
                
                if (!suc.getVisitado()) {
                    suc.setVisitado(true);
                    suc.setTiempo(nodo.getTiempo()+1);
                    queue.add(suc);
                
                    if (suc.getId().equals(dungeon.getFin()))
                        break cola;
                }
            }
        }
        
        String fin = dungeon.getFin();
        Nodo nodFin = dungeon.get(fin);
        
        if (nodFin.getVisitado())            
            return "Escape en "+ nodFin.getTiempo()+" minuto(s).";
        else
            return "Atrapado!";
        
    }

    
    public static void main(String[] args) {
        
        if (args.length == 2) {

           String in  = args[0];
           String out = args[1];

           try {

                 File archivoEntrada = new File(in);
                 Scanner entrada = new Scanner(archivoEntrada);
                 FileWriter fstream = new FileWriter(out);
                 BufferedWriter salida = new BufferedWriter(fstream);
                 encontrarSalida(entrada,salida);
                 salida.close();

           } catch (IOException ioe) {
                System.out.print(ioe.getMessage());
           }
       }
        
        
    }
    
}
