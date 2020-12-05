package net.ddns.scottedwardscoll.avltree;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class AvlTreeTest {

    @Test
    void canAddValueToTree() {
        final Tree<Integer> tree = new AvlTree<>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        assertEquals(List.of(1, 2, 3), tree.values());
    }

    @Test
    void insertAllMethodAddsAllValuesToTree() {
        final Tree<Integer> tree = new AvlTree<>();
        final List<Integer> expectedOutcome = List.of(1, 2, 3, 4);
        tree.insertAll(expectedOutcome);
        assertEquals(4, (int) tree.size());
        assertEquals(expectedOutcome, tree.values());
    }

    @Test
    void insertAllWorksWithEmptyList() {
        final Tree<Integer> tree = new AvlTree<>();
        final List<Integer> expectedOutcome = List.of();
        tree.insertAll(expectedOutcome);
        assertEquals(0, (int) tree.size());
        assertEquals(expectedOutcome, tree.values());
    }

    @Test
    void sizeMethodWorksOnEmptyTree() {
        final Tree<Integer> tree = new AvlTree<>();
        assertEquals(0, (int) tree.size());
    }

    @Test
    void sizeMethodReturnsExpectedValueOnPopulatedTree() {
        final Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 2, 3));
        assertEquals(3, (int) tree.size());
    }

    @Disabled
    void sizeMethodWorksWhenElementsAreAddedAndRemoved() {
        final Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 2, 3));
        tree.removeMax();
        assertEquals(2, (int) tree.size());
        assertEquals(2, tree.values().size());
    }

    @Test
    void getMinReturnsEmptyOptionalOnEmptyTree() {
        final Tree<Integer> tree = new AvlTree<>();
        assertFalse(tree.getMin().isPresent());
    }

    @Test
    void getMinReturnsSmallestElementInTree() {
        final Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 5, -2));
        final Optional<Integer> min = tree.getMin();
        assertTrue(min.isPresent());
        assertEquals(-2, (int) min.get());
    }

    @Test
    void returnMaxReturnsEmptyOptionalOnEmptyTree() {
        final Tree<Integer> tree = new AvlTree<>();
        assertFalse(tree.getMax().isPresent());
    }

    @Test
    void getMaxReturnsLargestElementInTree() {
        final Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(13, 13, -999));
        final Optional<Integer> max = tree.getMax();
        assertTrue(max.isPresent());
        assertEquals(13, (int) max.get());
    }

    @Disabled
    void canRemoveOneOccurrenceOfElementFromTree() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 2, 2));
        final Optional<Integer> rm = tree.removeFirst(2);
        assertTrue(rm.isPresent());
        assertEquals((int) rm.get(), 2);
        assertEquals(List.of(1, 2), tree.values());
    }

    @Test
    void usingRemoveWithValueNotInTreeLeavesTreeUnchanged() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 2, 2));
        final Optional<Integer> rm = tree.removeFirst(3);
        assertFalse(rm.isPresent());
        assertEquals(List.of(1, 2, 2), tree.values());
    }

    @Disabled
    void removeFirstMethodWillLeaveTreeCorrectlyBalanced() {
        final Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 2, 3, 5, 5, 5, 5, 5, 5));
        tree.removeFirst(5);
        tree.removeFirst(5);
        assertEquals(List.of(1, 2, 3), tree.values());
        assertTrue(3 <= tree.height());
        assertTrue(tree.height() <= 4);
    }

    @Disabled
    void removeMinCorrectlyRemovesAndReturnsSmallestElementWhenTreeNotEmpty() {
        final Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(13, 13, -999));
        final Optional<Integer> max = tree.removeMin();
        assertTrue(max.isPresent());
        assertEquals(-999, (int) max.get());
        assertEquals(List.of(13, 13), tree.values());
    }

    @Test
    void removeMinReturnsEmptyOptionalWhenTreeEmpty() {
        final Tree<Integer> tree = new AvlTree<>();
        final Optional<Integer> rm = tree.removeMin();
        assertFalse(rm.isPresent());
    }

    @Disabled
    void removeMaxCorrectlyRemovesAndReturnsLargestElementWhenTreeNotEmpty() {
        final Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(13, 13, -999));
        final Optional<Integer> max = tree.removeMax();
        assertTrue(max.isPresent());
        assertEquals(13, (int) max.get());
        assertEquals(List.of(-999, 13), tree.values());
    }

    @Test
    void removeMaxReturnsEmptyOptionalWhenTreeIsEmpty() {
        final Tree<Integer> tree = new AvlTree<>();
        final Optional<Integer> rm = tree.removeMax();
        assertFalse(rm.isPresent());
    }

    @Test
    void containsMethodReturnsTrueWhenElementInTheTree() {
        final Tree<String> tree = new AvlTree<>();
        tree.insert("Hello");
        assertTrue(tree.contains("Hello"));
    }

    @Test
    void containsMethodReturnsFalseWhenElementNotInTree() {
        final Tree<String> tree = new AvlTree<>();
        tree.insert("Hello");
        assertFalse(tree.contains("World!"));
    }

    @Test
    void getRootWillReturnCorrectRoot() {
        final Tree<Integer> tree1 = new AvlTree<>();
        tree1.insert(1);
        final Tree<Integer> tree2 = new AvlTree<>();
        tree2.insertAll(List.of(1, 2, 3));

        final Optional<Node<Integer>> root1 = tree1.getRoot();
        assertTrue(root1.isPresent());
        assertEquals(1, (int) root1.get().getValue());

        final Optional<Node<Integer>> root2 = tree2.getRoot();
        assertTrue(root2.isPresent());
        assertEquals(2, (int) root2.get().getValue());
    }

    @Test
    void getRootOnEmptyTreeWillReturnEmptyOptional() {
        final Tree<Boolean> tree = new AvlTree<>();
        final Optional<Node<Boolean>> root = tree.getRoot();
        assertFalse(root.isPresent());
    }

    @Test
    void occurrencesOfReturnsCorrectValueForNumberOfInstancesOfCertainValueInTree() {
        final Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 2, 3, 1, 1, 2, 2, 2, 1, 1));
        assertEquals(10, (int) tree.size());
        assertEquals(5, (int) tree.occurrencesOf(1));
        assertEquals(4, (int) tree.occurrencesOf(2));
        assertEquals(1, (int) tree.occurrencesOf(3));
    }

    @Test
    void occurrencesOfReturnsExpectedValueOnEmptyTree() {
        final Tree<Integer> tree = new AvlTree<>();
        assertEquals(0, (int) tree.occurrencesOf(1));
    }

    @Test
    void valuesReturnsExpectedListOfValuesInTreeInSortedOrder() {
        final Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(1, 4, 2, -4));
        assertEquals(List.of(-4, 1, 2, 4), tree.values());
    }

    @Test
    void valuesMethodOnEmptyTreeReturnsEmptyList() {
        final Tree<Integer> tree = new AvlTree<>();
        assertEquals(0, tree.values().size());
    }

    @Test
    void canIterateThroughPopulatedTree() {
        final AvlTree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(4, 7, -2, 10, 13, 14, 15));
        final Iterator<Integer> it = tree.iterator();
        Integer sum = 0;
        while (it.hasNext()) {
            sum += it.next();
        }
        assertEquals(61, (int) sum);
    }

    @Test
    void iteratorGoesThroughTreeInExpectedOrder() {
        final AvlTree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(4, 7, -2, 10, 13, 14, 15));
        final Iterator<Integer> it = tree.iterator();
        Integer current = -2;
        while (it.hasNext()) {
            Integer next = it.next();
            assertTrue(current <= next);
            current = next;
        }
    }

    @Test
    void iteratorFromEmptyTreeHasNoValues() {
        final AvlTree<Integer> tree = new AvlTree<>();
        final Iterator<Integer> it = tree.iterator();
        while (it.hasNext()) fail("There was a value!");
    }

    @Test
    void iteratorNextMethodReturnsNoElementExceptionWhenNoMoreElements() {
        final AvlTree<Integer> tree = new AvlTree<>();
        final AvlTree<String> strTree = new AvlTree<>();
        strTree.insertAll(List.of("Hello", "world!"));
        assertThrows(NoSuchElementException.class, () -> {
            final Iterator<Integer> iterator = tree.iterator();
            iterator.next();
        });
        assertThrows(NoSuchElementException.class, () -> {
            final Iterator<String> stringIterator = strTree.iterator();
            while(stringIterator.hasNext()) stringIterator.next();
            stringIterator.next();
        });
    }

    @Test
    void basicLeftRightRebalancingWorks() {
        final Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(2, 1, 3, 5, 4));
        assertEquals(List.of(1, 2, 3, 4, 5), tree.values());
        assertEquals(2, (int) tree.height());

        assertTrue(tree.getRoot().isPresent());
        final Node<Integer> root = tree.getRoot().get();
        assertTrue(root.getRight().isPresent());
        final Node<Integer> r = root.getRight().get();
        assertTrue(r.getLeft().isPresent());
        assertEquals(4, (int) r.getValue());
        assertEquals(3, (int) r.getLeft().get().getValue());
    }

    @Test
    void basicRightLeftRebalancingWorks() {
        final Tree<Integer> tree = new AvlTree<>();
        tree.insertAll(List.of(2, 1, 3, 4, 5));
        assertEquals(List.of(1, 2, 3, 4, 5), tree.values());
        assertEquals(2, (int) tree.height());

        assertTrue(tree.getRoot().isPresent());
        final Node<Integer> root = tree.getRoot().get();
        assertTrue(root.getRight().isPresent());
        final Node<Integer> r = root.getRight().get();
        assertTrue(r.getLeft().isPresent());
        assertEquals(4, (int) r.getValue());
        assertEquals(3, (int) r.getLeft().get().getValue());
    }
}