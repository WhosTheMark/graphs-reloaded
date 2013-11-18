
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class DigraphListaTest {
    
    DigraphLista digraph;
    String src = "SRC";
    String dst = "DST";
    Arco srcDst = new Arco(src,dst);
    Arco dstSrc = new Arco(dst,src);
    Arco bucle = new Arco(src,src);
    Nodo srcNod = new Nodo(src);
    Nodo dstNod = new Nodo(dst);
    
    public DigraphListaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        digraph = new DigraphLista();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Agregar un arcos al grafo.
     */
    @Test
    public void testAdd_Arco() {
        System.out.println("addArco");
        

        digraph.add(srcNod);
        digraph.add(dstNod);
        
        boolean success = digraph.add(srcDst);

        assertTrue(success && 1 == digraph.getNumArcos());
    }

    /**
     * Agregar dos arcos al grafo.
     */
    @Test
    public void testAdd_Arco2() {
        System.out.println("addArco2");
        
        digraph.add(srcNod);
        digraph.add(dstNod);
        
        boolean success = digraph.add(srcDst);
        boolean success2 = digraph.add(dstSrc);

        assertTrue(success && success2 && 2 == digraph.getNumArcos());
    }
    
    /**
     * Agregar un bucle.
     */
    @Test
    public void testAdd_Arco3() {
        System.out.println("addArco3");
        
        digraph.add(srcNod);
        boolean success = digraph.add(bucle);

        assertTrue(success && 1 == digraph.getNumArcos());
    }
    
    /**
     * Agregar un nodo al grafo.
     */
    @Test
    public void testAdd_Nodo() {
        System.out.println("addNodo");
        
        digraph.add(srcNod);

        assertTrue(1 == digraph.getNumVertices());
    }
    
    /**
     * Agregar dos nodo al grafo.
     */
    @Test
    public void testAdd_Nodo2() {
        System.out.println("addNodo2");
        
        digraph.add(srcNod);
        digraph.add(dstNod);

        assertTrue(2 == digraph.getNumVertices());
    }

    /**
     * Contains un arco.
     */
    @Test
    public void testContains_Arco() {
        System.out.println("containsArco");
        
        digraph.add(srcNod);
        digraph.add(dstNod);
        digraph.add(srcDst);
        
        assertTrue(digraph.contains(src,dst,0));
    }

    /**
     * Contains un arco inexistente.
     */
    @Test
    public void testContains_Arco2() {
        System.out.println("containsArco2");
        
        digraph.add(srcNod);
        digraph.add(dstNod);
        digraph.add(srcDst);
        
        assertFalse(digraph.contains(dst,src,0));
    }
    
    /**
     * Contains de un grafo vacio.
     */
    
    @Test
    public void testContains_Arco3() {
        System.out.println("containsArco3");
        assertFalse(digraph.contains(dst,src,0));
    }
    
    /**
     * Contains de un nodo.
     */
    @Test
    public void testContains_Nodo() {
        System.out.println("containsNodo");

        digraph.add(srcNod);
        assertTrue(digraph.contains(src));
    }

    /**
     * Contains de un nodo inexistente.
     */
    @Test
    public void testContains_Nodo2() {
        System.out.println("containsNodo2");

        digraph.add(srcNod);
        assertFalse(digraph.contains(dst));
    }
    
    /**
     * Contains de un nodo en un grafo vacio.
     */
    @Test
    public void testContains_Nodo3() {
        System.out.println("containsNodo3");

        assertFalse(digraph.contains(dst));
    }
    
    /**
     * Test para verificar que ordena bien los nodos.
     */
    
    @Test
    public void testOrdenar(){
        System.out.println("ordenar");
        
        int MAX = 200;
        
        for(Integer i = 0; i < MAX; i += 2){
            Nodo nod = new Nodo(i.toString());
            digraph.add(nod);
            assertTrue(digraph.contains(i.toString()));
        }
        
        for(Integer i = 1; i < MAX; i += 2){
            Nodo nod = new Nodo(i.toString());
            digraph.add(nod);
            assertTrue(digraph.contains(i.toString()));
        }
        
        for(Integer i = 0; i < MAX; i += 3){
            digraph.remove(i.toString());
            assertFalse(digraph.contains(i.toString()));
        }
        
        for(Integer i = 1; i < MAX; i += 3)
            assertEquals(digraph.get(i.toString()).getId(),i.toString());
        
    }
    
    /**
     * Obtener un Arco de un grafo.
     */
    @Test
    public void testGet_Arco() {
        System.out.println("getArco");
        digraph.add(srcNod);
        digraph.add(dstNod);
        digraph.add(srcDst);
        
        Arco nuevoArco = digraph.get(src, dst, 0);
        
        assertEquals(nuevoArco,srcDst);

    }

    /**
     * Obtener un Arco de un grafo vacio.
     */
    @Test
    public void testGet_Arco2() {
        System.out.println("getArco2");
        
        Arco nuevoArco = digraph.get(src, dst, 0);
        assertNull(nuevoArco);

    }
    
    /**
     * Obtener un Arco que se haya eliminado.
     */
    
    @Test
    public void testGet_Arco3() {
        System.out.println("getArco3");
        digraph.add(srcNod);
        digraph.add(dstNod);
        digraph.add(srcDst);
        
        digraph.remove(src, dst, 0);
        
        Arco nuevoArco = digraph.get(src, dst, 0);
        
        assertNull(nuevoArco);

    }
    
    /**
     * Test of getArcos method, of class DigraphLista.
     */
    @Test
    public void testGetArcos() {
        System.out.println("getArcos");

        digraph.add(srcNod);
        digraph.add(dstNod);
        digraph.add(srcDst);
        digraph.add(dstSrc);
        digraph.add(bucle);
        
        Lista<Arco> listArc = digraph.getArcos();
        MiLista<Arco> expList = new MiLista();
        expList.add(srcDst);
        expList.add(dstSrc);
        expList.add(bucle);
        
        assertEquals(listArc,expList);
        
    }
    
    /**
     * Get arcos de un grafo vacio.
     */
    @Test
    public void testGetArcos2() {
        System.out.println("getArcos2");

        Lista<Arco> listArc = digraph.getArcos();
        assertTrue(listArc.isEmpty());
        
    }
     
    /**
     * Obtener un nodo del grafo.
     */
    @Test
    public void testGet_Nodo() {
        System.out.println("getNodo");
        digraph.add(srcNod);
        assertEquals(digraph.get(src),srcNod);
    }
    
    /**
     * Obtener un nodo de un grafo vacio.
     */
    @Test
    public void testGet_Nodo2() {
        System.out.println("getNodo2");
        assertNull(digraph.get(src));
    }
    
    /**
     * Obtener un nodo que haya sido eliminado.
     */
    @Test
    public void testGet_Nodo3() {
        System.out.println("getNodo3");
        digraph.add(srcNod);
        digraph.remove(src);
        assertNull(digraph.get(src));
    }
    
    /**
     * Obtener los nodos de un grafo.
     */
    @Test
    public void testGetNodos() {
        System.out.println("getNodos");

        digraph.add(srcNod);
        digraph.add(dstNod);
        Lista<Nodo> nodList = digraph.getNodos();
        MiLista<Nodo> expList = new MiLista();
        expList.add(srcNod);
        expList.add(dstNod);
        
        assertEquals(nodList,expList);
        
    }
    
    /**
     * Obtener los nodos de un grafo vacio.
     */

    @Test
    public void testGetNodos2() {
        System.out.println("getNodos2");

        Lista<Nodo> nodList = digraph.getNodos();
        assertTrue(nodList.isEmpty());
        
    }
    
    /**
     * Test of getInArcos method, of class DigraphLista.
     */
    @Test
    public void testGetInArcos() {
        System.out.println("getInArcos");

        digraph.add(srcNod);
        digraph.add(dstNod);
        digraph.add(dstSrc);
        digraph.add(bucle);
        digraph.add(srcDst);
        
        Lista<Arco> listArc = digraph.getInArcos(src);
        MiLista<Arco> expList = new MiLista();
        expList.add(dstSrc);
        expList.add(bucle);
        
        assertEquals(listArc,expList);
        
    }
    
    /**
     * getInArcos de grafo vacio.
     */
    
    @Test
    public void testGetInArcos2() {
        System.out.println("getInArcos2");

        Lista<Arco> listArc = digraph.getInArcos(src);
        assertNull(listArc);
        
    }
    
    /**
     * Get In arcos de un nodo que ya se elimino.
     */

    @Test
    public void testGetInArcos3() {
        System.out.println("getInArcos3");

        digraph.add(srcNod);
        digraph.add(dstNod);
        digraph.add(dstSrc);
        digraph.add(bucle);
        digraph.add(srcDst);
        
        digraph.remove(src);
        
        Lista<Arco> listArc = digraph.getInArcos(src);
        
        assertNull(listArc);
        
    }
    
    /**
     * Test of getOutArcos method, of class DigraphLista.
     */
    @Test
    public void testGetOutArcos() {
        System.out.println("getOutArcos");

        digraph.add(srcNod);
        digraph.add(dstNod);
        digraph.add(dstSrc);
        digraph.add(bucle);
        digraph.add(srcDst);
        
        Lista<Arco> listArc = digraph.getOutArcos(src);
        MiLista<Arco> expList = new MiLista();
        expList.add(srcDst);
        expList.add(bucle);
        
        assertEquals(listArc,expList);
    }
    
    /**
     * getOutArcos con grafo vacio.
     */
    @Test
    public void testGetOutArcos2() {
        System.out.println("getOutArcos2");

        Lista<Arco> listArc = digraph.getInArcos(src);
        assertNull(listArc);
        
    }
    
    /**
     * Get In arcos de un nodo que ya se elimino.
     */

    @Test
    public void testGetOutArcos3() {
        System.out.println("getOutArcos3");

        digraph.add(srcNod);
        digraph.add(dstNod);
        digraph.add(dstSrc);
        digraph.add(bucle);
        digraph.add(srcDst);
        
        digraph.remove(src);
        
        Lista<Arco> listArc = digraph.getInArcos(src);
        
        assertNull(listArc);
        
    }
    
    /**
     * Remover un arco del grafo.
     */
    @Test
    public void testRemove_Arco() {
        System.out.println("removeArco");

        digraph.add(srcNod);
        digraph.add(bucle);
        
        boolean success = digraph.remove(src,src,0);
        
        assertTrue(success && !digraph.contains(src, src, 0));
        
    }
    
    /**
     * Remover un arco del grafo vacio.
     */
    @Test
    public void testRemove_Arco2() {
        System.out.println("removeArco2");
        
        boolean success = digraph.remove(src,src,0);
        assertFalse(success);
        
    }

    /**
     * Remover un nodo del grafo.
     */
    @Test
    public void testRemove_Nodo() {
        System.out.println("removeNodo");
        
        digraph.add(srcNod);
        boolean success = digraph.remove(src);
        
        assertTrue(success && !digraph.contains(src));
    }
    
    /**
     * Remover un nodo con varios arcos.
     */
    @Test
    public void testRemove_Nodo2(){
        System.out.println("removeNodo2");
        
        digraph.add(srcNod);
        digraph.add(dstNod);
        digraph.add(dstSrc);
        digraph.add(bucle);
        digraph.add(srcDst);
        
        boolean success = digraph.remove(src);
        
        assertTrue(success && !digraph.contains(src) 
                && !digraph.contains(src, dst, 0)
                && !digraph.contains(dst, src, 0) 
                && !digraph.contains(src, src, 0));
    }
    
    /**
     * Test of getPreds method, of class DigraphLista.
     */
    @Test
    public void testGetPreds() {
        System.out.println("getPreds");

        digraph.add(srcNod);
        digraph.add(dstNod);
        digraph.add(dstSrc);
        digraph.add(bucle);
        digraph.add(srcDst);
        
        Lista<Nodo> listArc = digraph.getPreds(src);
        MiLista<Nodo> expList = new MiLista();
        expList.add(dstNod);
        expList.add(srcNod);
        
        assertEquals(listArc,expList);
    }
    
    /**
     * getPreds con grafo vacio.
     */
    @Test
    public void testGetPreds2() {
        System.out.println("getPreds2");
        Lista<Nodo> listArc = digraph.getPreds(src);
        
        assertNull(listArc);
    }
    
    /**
     * getpreds de un nodo que ya se elimino.
     */
    @Test
    public void testGetPreds3() {
        System.out.println("getPreds3");

        digraph.add(srcNod);
        digraph.add(dstNod);
        digraph.add(dstSrc);
        digraph.add(bucle);
        digraph.add(srcDst);
        
        digraph.remove(src);
        
        Lista<Nodo> listArc = digraph.getPreds(src);
        assertNull(listArc);
    }
    
    
    /**
     * Test of getSucs method, of class DigraphLista.
     */
    @Test
    public void testGetSucs() {
        System.out.println("getSucs");
        
        digraph.add(srcNod);
        digraph.add(dstNod);
        digraph.add(dstSrc);
        digraph.add(bucle);
        digraph.add(srcDst);
        
        Lista<Nodo> listArc = digraph.getSucs(src);
        MiLista<Nodo> expList = new MiLista();
        expList.add(dstNod);
        expList.add(srcNod);
        
        assertEquals(listArc,expList);
    }
    
    /**
     * getSucs con grafo vacio.
     */
    @Test
    public void testGetSucs2() {
        System.out.println("getSucs2");
        Lista<Nodo> listArc = digraph.getPreds(src);
        
        assertNull(listArc);
    }
    
    /**
     * getSucs de un nodo que ya se elimino.
     */
    @Test
    public void testGetSucs3() {
        System.out.println("getSucs3");

        digraph.add(srcNod);
        digraph.add(dstNod);
        digraph.add(dstSrc);
        digraph.add(bucle);
        digraph.add(srcDst);
        
        digraph.remove(src);
        
        Lista<Nodo> listArc = digraph.getPreds(src);
        assertNull(listArc);
    }
    
    
    
}
