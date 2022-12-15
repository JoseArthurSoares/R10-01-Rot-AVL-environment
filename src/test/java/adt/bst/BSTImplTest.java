package adt.bst;

import junit.framework.TestCase;
import org.junit.Before;

import java.util.Optional;

import static org.junit.Assert.assertArrayEquals;

public class BSTImplTest extends TestCase {

    BSTImpl<Integer> tree;

    public void fillTree(){
        Integer[] array = {20, 37, 1, 0, 21, 42, 10, 3, 74};
        for (Integer i: array){
            tree.insert(i);
        }
    }

    @Before
    public void setUp(){
        tree = new BSTImpl<>();
        fillTree();
    }

    public void testHeight() {
        assertEquals(3, tree.height());
    }

    public void testSearch() {
        assertEquals((Integer) 74, tree.search(74).getData());
        assertNull(tree.search(4).getData());
    }

    public void testMaximum() {
        assertEquals((Integer)74,  tree.maximum().getData());
    }

    public void testMinimum() {
        assertEquals((Integer)0,  tree.minimum().getData());
    }

    public void testSucessor() {
        assertEquals((Integer)1,  tree.sucessor(0).getData());
        assertEquals((Integer)21, tree.sucessor(20).getData());
        assertNull(tree.sucessor(74));
        assertNull(tree.sucessor(80));

    }

    public void testPredecessor() {
        assertEquals((Integer)10, tree.predecessor(20).getData());
        assertEquals((Integer)42, tree.predecessor(74).getData());
        assertNull(tree.predecessor(0));
    }

    public void testRemove() {
    }

    public void testPreOrder() {
    }

    public void testOrder() {
    }

    public void testPostOrder() {
    }
}