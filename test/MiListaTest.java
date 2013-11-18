
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class MiListaTest {
    
    MiLista<Integer> listInt;
    MiLista<Integer> listInt2;
    
    public MiListaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        listInt = new MiLista();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Agrega un elemento.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        
        Integer element = 5;
        boolean success = listInt.add(element);
        assertTrue(success && 1 == listInt.getSize());

    }
    
    /**
     * Agrega dos elementos.
     */
    @Test
    public void testAdd2() {
        System.out.println("add2");
        
        Integer element = 5;
        Integer element2 = 56;
        boolean success = listInt.add(element);
        boolean success2 = listInt.add(element2);
        assertTrue(success && success2 && 2 == listInt.getSize());

    }

    /**
     * Agregar luego de haber quitado en elemento.
     */
    @Test
    public void testAdd3() {
        System.out.println("add3");
        
        Integer element = 5;
        listInt.add(element);
        boolean success = listInt.remove(element);
        Integer element2 = 4534;
        listInt.add(element2);
        assertTrue(success && 1 == listInt.getSize());

    }
       
    /**
     * Equals con dos listas y elementos ingresados en diferente orden.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        Integer element = 9;
        Integer element2 = 35;
        listInt2 = new MiLista();
        
        listInt.add(element);
        listInt.add(element2);
        
        listInt2.add(element2);
        listInt2.add(element);
        
        assertTrue(listInt.equals(listInt2) && listInt2.equals(listInt));
    }
    
    /**
     * Equals con dos listas del mismo tamaño pero elementos diferentes.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        
        Integer element = 9;
        Integer element2 = 35;
        listInt2 = new MiLista();
        
        listInt.add(element);
        listInt.add(element);
        
        listInt2.add(element2);
        listInt2.add(element);
        
        assertFalse(listInt.equals(listInt2) && listInt2.equals(listInt));
    } 
    
    /**
     * Equals con listas de diferentes tamaños.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        
        Integer element = 9999;
        listInt2 = new MiLista();
        
        listInt.add(element);
        listInt.add(element);
        
        listInt2.add(element);
        
        assertFalse(listInt.equals(listInt2));
    } 

    /**
     * Remover un elemento.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        
        Integer element = 5;
        listInt.add(element);
        boolean success = listInt.remove(element);
        assertTrue(success && 0 == listInt.getSize());

    }
    
    /**
     * Eliminar dos elementos.
     */
    @Test
    public void testRemove2() {
        System.out.println("remove2");
        
        Integer element = 5;
        Integer element2 = 12312;
        listInt.add(element);
        listInt.add(element2);
        boolean success = listInt.remove(element);
        boolean success2 = listInt.remove(element2);
        assertTrue(success && success2 && 0 == listInt.getSize());

    }
    
    /**
     * Elimnar un elemento que esta repetido.
     */
    @Test
    public void testRemove3() {
        System.out.println("remove3");
        
        Integer element = 5;
        listInt.add(element);
        listInt.add(element);
        boolean success = listInt.remove(element);
        assertTrue(success && 1 == listInt.getSize());

    }
    
    /**
     * Eliminar de una lista vacia.
     */
    
    @Test
    public void testRemove4(){
        System.out.println("remove4");
        Integer element = 234;
        boolean success = listInt.remove(element);
        assertFalse(success);
        
    }
    
    /**
     * Test del clone de una lista vacia.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        listInt2 = (MiLista) listInt.clone();
        assertTrue(listInt2.isEmpty());
        
    }

    /**
     * Test del clone de dos listas.
     */
    @Test
    public void testClone2() {
        System.out.println("clone2");
        listInt.add(2);
        listInt.add(89);
        listInt.add(234);
        
        listInt2 = (MiLista) listInt.clone();
        assertTrue(listInt.equals(listInt2));
        
    }
    
    /**
     * Test del clone de dos listas, la cula una se modifica.
     */
    @Test
    public void testClone3() {
        System.out.println("clone3");
        listInt.add(2);
        listInt.add(89);
        listInt.add(234);
        
        listInt2 = (MiLista) listInt.clone();
        listInt2.remove(2);
        
        assertFalse(listInt.equals(listInt2));
        
    }
    
    /**
     * Get de una lista vacia.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        assertNull(listInt.get(6));
        
    }

    /**
     * Get de una lista con un elemento.
     */
    @Test
    public void testGet2() {
        System.out.println("get2");
        
        listInt.add(6);
        assertTrue(listInt.get(6).equals(6) && 1 == listInt.getSize());
        
    }
    
    /**
     * Verifica que itera sobre todos los elementos.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");

        
        for (int i = 0; i < 50; ++i)
            listInt.add(i);

        Integer j = 49;
        for(Integer i: listInt)
            assertEquals(i,j--);

    }
    
    /**
     * Contains de una lista vacia.
     */
    @Test
    public void testContains(){
        System.out.println("contains");
        
        boolean esta = listInt.contains(4);
        assertFalse(esta);  
    }
    
    /**
     * Contains de una lista.
     */
    @Test
    public void testContains2(){
        System.out.println("contains2");
        
        listInt.add(4);
        boolean esta = listInt.contains(4);
        assertTrue(esta);
    }
    
    /**
     * Contains de una lista que no tiene al elemento.
     */
    @Test
    public void testContains3(){
        System.out.println("contains3");
        
        listInt.add(5);
        boolean esta = listInt.contains(4);
        assertFalse(esta);
    }
    
    
}
