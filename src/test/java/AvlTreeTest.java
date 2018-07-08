import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AvlTreeTest {

    @Test
    void canAddValueToTree() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        assertEquals(List.of(1, 2, 3), tree.values());
    }

    @Test
    void insertAllMethodAddsAllValuesToTree() {
        Tree<Integer> tree = new AvlTree<>();
        List<Integer> expectedOutcome = List.of(1, 2, 3, 4);
        tree.insertAll(expectedOutcome);
        assertEquals(4, (int) tree.size());
        assertEquals(expectedOutcome, tree.values());
    }

    @Test
    void insertAllWorksWithEmptyList() {
        Tree<Integer> tree = new AvlTree<>();
        List<Integer> expectedOutcome = List.of();
        tree.insertAll(expectedOutcome);
        assertEquals(0, (int) tree.size());
        assertEquals(expectedOutcome, tree.values());
    }

    @Test
    void sizeMethodWorksOnEmptyTree() {
        Tree<Integer> tree = new AvlTree<>();
        assertEquals(0, (int) tree.size());
    }

    @Test
    void sizeMethodReturnsExpectedValueOnPopulatedTree() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 2, 3));
        assertEquals(3, (int) tree.size());
    }

    @Test
    void sizeMethodWorksWhenElementsAreAddedAndRemoved() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 2, 3));
        tree.removeMax();
        assertEquals(2, (int) tree.size());
    }

    @Test
    void getMinReturnsEmptyOptionalOnEmptyTree() {
        Tree<Integer> tree = new AvlTree<>();
        assertFalse(tree.getMin().isPresent());
    }

    @Test
    void getMinReturnsSmallestElementInTree() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 5, -2));
        Optional<Integer> min = tree.getMin();
        assertTrue(min.isPresent());
        assertEquals(-2, (int) min.get());
    }

    @Test
    void returnMaxReturnsEmptyOptionalOnEmptyTree() {
        Tree<Integer> tree = new AvlTree<>();
        assertFalse(tree.getMax().isPresent());
    }

    @Test
    void getMaxReturnsLargestElementInTree() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(13, 13, -999));
        Optional<Integer> max = tree.getMax();
        assertTrue(max.isPresent());
        assertEquals(13, (int) max.get());
    }

    @Test
    void canRemoveOneOccurrenceOfElementFromTree() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 2, 2));
        Optional<Integer> rm = tree.removeFirst(2);
        assertTrue(rm.isPresent());
        assertEquals((int) rm.get(), 2);
        assertEquals(List.of(1, 2), tree.values());
    }

    @Test
    void usingRemoveWithValueNotInTreeLeavesTreeUnchanged() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 2, 2));
        Optional<Integer> rm = tree.removeFirst(3);
        assertFalse(rm.isPresent());
        assertEquals(List.of(1, 2, 2), tree.values());
    }

    @Test
    void removeFirstMethodWillLeaveTreeCorrectlyBalanced() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 2, 3, 5, 5, 5, 5, 5, 5));
        tree.removeFirst(5);
        tree.removeFirst(5);
        assertEquals(List.of(1, 2, 3), tree.values());
        assertTrue(3 <= tree.height());
        assertTrue(tree.height() <= 4);
    }

    @Test
    void removeMinCorrectlyRemovesAndReturnsSmallestElementWhenTreeNotEmpty() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(13, 13, -999));
        Optional<Integer> max = tree.removeMin();
        assertTrue(max.isPresent());
        assertEquals(-999, (int) max.get());
        assertEquals(List.of(13, 13), tree.values());
    }

    @Test
    void removeMinReturnsEmptyOptionalWhenTreeEmpty() {
        Tree<Integer> tree = new AvlTree<>();
        Optional<Integer> rm = tree.removeMin();
        assertFalse(rm.isPresent());
    }

    @Test
    void removeMaxCorrectlyRemovesAndReturnsLargestElementWhenTreeNotEmpty() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(13, 13, -999));
        Optional<Integer> max = tree.removeMax();
        assertTrue(max.isPresent());
        assertEquals(13, (int) max.get());
        assertEquals(List.of(-999, 13), tree.values());
    }

    @Test
    void removeMaxReturnsEmptyOptionalWhenTreeIsEmpty() {
        Tree<Integer> tree = new AvlTree<>();
        Optional<Integer> rm = tree.removeMax();
        assertFalse(rm.isPresent());
    }

    @Test
    void containsMethodReturnsTrueWhenElementInTheTree() {
        Tree<String> tree = new AvlTree<>();
        tree.insert("Hello");
        assertTrue(tree.contains("Hello"));
    }

    @Test
    void containsMethodReturnsFalseWhenElementNotInTree() {
        Tree<String> tree = new AvlTree<>();
        tree.insert("Hello");
        assertFalse(tree.contains("World!"));
    }

    @Test
    void getRootWillReturnCorrectRoot() {
        Tree<Integer> tree1 = new AvlTree<>();
        tree1.insert(1);
        Tree<Integer> tree2 = new AvlTree<>();
        tree2.insertAll(List.of(1, 2, 3));

        Optional<Node<Integer>> root1 = tree1.getRoot();
        assertTrue(root1.isPresent());
        assertEquals(1, (int) root1.get().getValue());

        Optional<Node<Integer>> root2 = tree2.getRoot();
        assertTrue(root2.isPresent());
        assertEquals(2, (int) root2.get().getValue());
    }

    @Test
    void getRootOnEmptyTreeWillReturnEmptyOptional() {
        Tree<Boolean> tree = new AvlTree<>();
        Optional<Node<Boolean>> root = tree.getRoot();
        assertFalse(root.isPresent());
    }

    @Test
    void occurrencesOfReturnsCorrectValueForNumberOfInstancesOfCertainValueInTree() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 2, 3, 1, 1, 2, 2, 2, 1, 1));
        assertEquals(10, (int) tree.size());
        assertEquals(5, (int) tree.occurrencesOf(1));
        assertEquals(4, (int) tree.occurrencesOf(2));
        assertEquals(1, (int) tree.occurrencesOf(3));
    }

    @Test
    void occurrencesOfReturnsExpectedValueOnEmptyTree() {
        Tree<Integer> tree = new AvlTree<>();
        assertEquals(0, (int) tree.occurrencesOf(1));
    }

    @Test
    void valuesReturnsExpectedListOfValuesInTreeInSortedOrder() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 4, 2, -4));
        assertEquals(List.of(-4, 1, 2, 4), tree.values());
    }

    @Test
    void valuesMethodOnEmptyTreeReturnsEmptyList() {
        Tree<Integer> tree = new AvlTree<>();
        assertEquals(0, (int) tree.values().size());
    }

    @Test
    void canIterateThroughPopulatedTree() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(4, 7, -2, 10, 13, 14, 15));
        Iterator<Integer> it = tree.iterator();
        Integer sum = 0;
        while (it.hasNext()) {
            sum += it.next();
        }
        assertEquals(61, (int) sum);
    }

    @Test
    void iteratorGoesThroughTreeInExpectedOrder() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(4, 7, -2, 10, 13, 14, 15));
        Iterator<Integer> it = tree.iterator();
        Integer current = -2;
        while (it.hasNext()) {
            Integer next = it.next();
            assertTrue(current <= next);
            current = next;
        }
    }

    @Test
    void iteratorFromEmptyTreeHasNoValues() {
        AvlTree<Integer> tree = new AvlTree<>();
        Iterator<Integer> it = tree.iterator();
        while (it.hasNext()) fail();
    }

    @Test
    void iteratorNextMethodReturnsNoElementExceptionWhenNoMoreElements() {
        AvlTree<Integer> tree = new AvlTree<>();
        AvlTree<String> strTree = new AvlTree<>();
        strTree.insertAll(List.of("Hello", "world!"));
        assertThrows(NoSuchElementException.class, () -> {
            Iterator<Integer> iterator = tree.iterator();
            iterator.next();
        });
        assertThrows(NoSuchElementException.class, () -> {
            Iterator<String> stringIterator = strTree.iterator();
            while(stringIterator.hasNext()) stringIterator.next();
            stringIterator.next();
        });
    }

    @Test
    void basicLeftRightRebalancingWorks() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(2, 1, 3, 5, 4));
        assertEquals(List.of(1, 2, 3, 4, 5), tree.values());
        assertEquals(2, (int) tree.height());

        assertTrue(tree.getRoot().isPresent());
        Node<Integer> root = tree.getRoot().get();
        assertTrue(root.getRight().isPresent());
        Node<Integer> r = root.getRight().get();
        assertTrue(r.getLeft().isPresent());
        assertEquals(4, (int) r.getValue());
        assertEquals(3, (int) r.getLeft().get().getValue());
    }

    @Test
    void basicRightLeftRebalancingWorks() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(2, 1, 3, 4, 5));
        assertEquals(List.of(1, 2, 3, 4, 5), tree.values());
        assertEquals(2, (int) tree.height());

        assertTrue(tree.getRoot().isPresent());
        Node<Integer> root = tree.getRoot().get();
        assertTrue(root.getRight().isPresent());
        Node<Integer> r = root.getRight().get();
        assertTrue(r.getLeft().isPresent());
        assertEquals(4, (int) r.getValue());
        assertEquals(3, (int) r.getLeft().get().getValue());
    }
}