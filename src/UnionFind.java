
public class UnionFind {

    private int[] id;
    private int[] tam;
    private int numComp;
    
    public UnionFind(int n) {
        
        numComp = n;
        id = new int[n];
        tam = new int[n];
        for(int i = 0; i < n; ++i) {
            id[i] = i;
            tam[i] = 1;
        }
    }
    
    public boolean conectadas(int p, int q){   
        return find(p) == find(q);      
    }
    
    public int find(int p){
        
        int padre = p;
        int aux;
        
        while (padre != id[padre])
            padre = id[padre];
        
        while (p != padre){
            aux = id[p];
            id[p] = padre;
            p = id[aux];
        }
               
        return padre;
    }
    
    public void union(int p, int q) {
        
        int i = find(p);
        int j = find(q);
        
        if (i == j) 
            return;
        
        if (tam[i] < tam[j]) {
            
            id[i] = j; 
            tam[j] += tam[i];
            
        } else {
            
            id[j] = i; 
            tam[i] += tam[j];
        }
        
        --numComp; 
        
    }
    /*
    public static void main(String args[]) {
        
        UnionFind uf = new UnionFind(10);
        
        for(int i=0; i < uf.id.length; ++i) {
            System.out.println(i + " " + uf.id[i]);
        }
        System.out.println("---------------------------");
        
        uf.union(3,9);
        for(int i=0; i < uf.id.length; ++i) {
            System.out.println(i + " " + uf.id[i]);
        }
        System.out.println("---------------------------");
                
        uf.union(5,3);
        for(int i=0; i < uf.id.length; ++i) {
            System.out.println(i + " " + uf.id[i]);
        }
        System.out.println("---------------------------");
                
        uf.union(1,2);
        for(int i=0; i < uf.id.length; ++i) {
            System.out.println(i + " " + uf.id[i]);
        }
        System.out.println("---------------------------");
                
        uf.union(2,5);
        for(int i=0; i < uf.id.length; ++i) {
            System.out.println(i + " " + uf.id[i]);
        }
        System.out.println("---------------------------");
                
        uf.union(2,9);
        for(int i=0; i < uf.id.length; ++i) {
            System.out.println(i + " " + uf.id[i]);
        }
        System.out.println("---------------------------");
                
        uf.union(6,7);
        for(int i=0; i < uf.id.length; ++i) {
            System.out.println(i + " " + uf.id[i]);
        }
        System.out.println("---------------------------");
                
        uf.union(6,2);
        for(int i=0; i < uf.id.length; ++i) {
            System.out.println(i + " " + uf.id[i]);
        }
        System.out.println("---------------------------");

        uf.union(7, 8);
        for(int i=0; i < uf.id.length; ++i) {
            System.out.println(i + " " + uf.id[i]);
        }
        System.out.println("---------------------------");

    }
    */
    
}
