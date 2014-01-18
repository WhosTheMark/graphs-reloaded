
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.pow;
import java.text.DecimalFormat;
import java.util.Scanner;


public class Main {

    public static DigraphLista cargarGrafo(Scanner entrada, FibHeap<Arco> heapArc) {
        
        int numCamps = entrada.nextInt();
        DigraphLista grafo = new DigraphLista(numCamps);
        
        for(int i =0; i< numCamps; ++i) {
            
            Integer idCamp = entrada.nextInt()-1;
            String id = idCamp.toString();
            
            int xCoord = entrada.nextInt();
            int yCoord = entrada.nextInt();
            
            Nodo nod = new Nodo(id,xCoord,yCoord);
            grafo.add(nod);
            
        }
        
        MiLista<Nodo> listNods = (MiLista) grafo.getNodos();
        Object[] arrNods = listNods.toArray();
        int len = arrNods.length;
        
        for (int i=0; i < len - 1; ++i) {
            
            Nodo nodo = (Nodo) arrNods[i];
            
            for (int j=i+1; j < len; ++j ) {
                
                Nodo nodo2 = (Nodo) arrNods[j];
                double diffX = pow(nodo2.getxCoord() - nodo.getxCoord(),2);
                double diffY = pow(nodo2.getyCoord() - nodo.getyCoord(),2);
                
                double distancia = diffX + diffY;
                            
                Arco arc = new Arco(nodo.getId(),nodo2.getId(),distancia);
                
                System.out.println(arc.toString());
                
                grafo.add(arc);                
                heapArc.add(arc);
            }           
        }
        
        return grafo;
    }
    
    public static FibHeap<Arco> kruskal(DigraphLista grafo, FibHeap<Arco> heapArc) {
        
        FibHeap<Arco> mst = new FibHeap();
        
        UnionFind uf = new UnionFind(grafo.numVertices);
        
        while(mst.obtTamano() < grafo.numVertices - 1) {
            
            Arco arc = heapArc.extraerMin();
            String dst = arc.getDst();
            String src = arc.getSrc();
            
            int srcInt = Integer.parseInt(src);
            int dstInt = Integer.parseInt(dst);
            
            if (uf.conectadas(srcInt, dstInt))
                continue;
            uf.union(srcInt, dstInt);
            
            double costo = -arc.getCosto();
            Arco arcNuevo = new Arco(src,dst,costo);
            mst.add(arcNuevo);
            
        }
        
        return mst;
    }
    
    
    public static double ponerSatelites(DigraphLista grafo, FibHeap<Arco> mst, int numSatelites) {
        
        while (!mst.esVacio()) {
            
            Arco arc = mst.extraerMin();
            System.out.println("------------------");
            System.out.println(arc.toString());
            String src = arc.getSrc();
            String dst = arc.getDst();
            Nodo nod1 = grafo.get(src);
            Nodo nod2 = grafo.get(dst);
            
            
            if (numSatelites == 0) {
                return Math.sqrt(-arc.getCosto());
            } 
            
            if (!nod1.getTieneSatelite() && nod2.getTieneSatelite()) {
                
                nod1.setTieneSatelite(true);
                --numSatelites; 
                
            } else if (nod1.getTieneSatelite() && !nod2.getTieneSatelite()) {
                
                nod2.setTieneSatelite(true);
                --numSatelites;
                
            } else {
                
                if (numSatelites == 1) {
                    
                    return Math.sqrt(-arc.getCosto());
                }
                
                nod1.setTieneSatelite(true);
                nod2.setTieneSatelite(true);
                numSatelites -= 2;
                
            }
        }
        return 0;
    }
    
    public static void buscarDistancia(Scanner entrada, BufferedWriter salida) throws IOException {
        
        int satelites = entrada.nextInt();
        FibHeap<Arco> heap = new FibHeap();
        
        
        DigraphLista grafo = cargarGrafo(entrada,heap);
        
        FibHeap<Arco> mst = kruskal(grafo,heap);
        
        double distancia = ponerSatelites(grafo,mst,satelites);
        
        System.out.println(distancia);
        DecimalFormat df = new DecimalFormat("#.00");
        String dist = df.format(distancia);
        
        salida.write(dist);
        salida.newLine();
        
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
                     buscarDistancia(entrada,salida);
                 salida.close();

           } catch (IOException ioe) {
                System.out.print(ioe.getMessage());
           }
       }
        
        
    }
}
