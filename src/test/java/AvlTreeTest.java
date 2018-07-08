import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AvlTreeTest {

    @Test
    void canAddValueToTree() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        List<Integer> actualContents = tree.values();
        assertEquals(actualContents, List.of(1, 2, 3));
    }

    @Test
    void sizeMethodWorksOnEmptyTree() {
        Tree<Integer> tree = new AvlTree<>();
        assertEquals((int) tree.size(), 0);
    }

    @Test
    void sizeMethodReturnsExpectedValueOnPopulatedTree() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        assertEquals((int) tree.size(), 3);
    }

    @Test
    void sizeMethodWorksWhenElementsAreAddedAndRemoved() {
        Tree<Integer> tree = new AvlTree<>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.removeMax();
        assertEquals((int) tree.size(), 2);
    }

    @Test
    void getMinReturnsEmptyOptionalOnEmptyTree() {
        fail();
    }

    @Test
    void getMinReturnsSmallestElementInTree() {
        fail();
    }

    @Test
    void returnMaxReturnsEmptyOptionalOnEmptyTree() {
        fail();
    }

    @Test
    void returnMaxReturnsLargestElementInTree() {
        fail();
    }

    @Test
    void canRemoveOneOccurrencesOfNumberFromTree() {
        fail();
    }

    @Test
    void usingRemoveWithValueNotInTreeLeavesTreeUnchanged() {
        fail();
    }

    @Test
    void removeFirstMethodWillLeaveTreeCorrectlyBalanced() {
        fail();
    }

    @Test
    void removeMinCorrectlyRemovesAndReturnsSmallestElementWhenTreeNotEmpty() {
        fail();
    }

    @Test
    void removeMinReturnsEmptyOptionalWhenTreeEmpty() {
        fail();
    }

    @Test
    void removeMaxCorrectlyRemovesAndReturnsLargestElementWhenTreeNotEmpty() {
        fail();
    }

    @Test
    void removeMaxReturnsEmptyOptionalWHenTreeIsEmpty() {
        fail();
    }

    @Test
    void getOccurrencesReturnsCorrectValueForNumberOfInstancesOfCertainValueInTree() {
        fail();
    }

    @Test
    void containsMethodReturnsTrueWhenElementInTheTree() {
        fail();
    }

    @Test
    void containsMethodReturnsFalseWhenElementNotInTree() {
        fail();
    }

    @Test
    void getRootWillReturnCorrectRoot() {
        fail();
    }

    @Test
    void getRootOnEmptyTreeWillReturnEmptyOptional() {
        fail();
    }

    @Test
    void occurrencesOfReturnsExpectedValueOnEmptyTree() {
        fail();
    }

    @Test
    void occurrencesOfReturnsExpectedValueOnPopulatedTree() {
        fail();
    }

    @Test
    void mapMethodReturnsExpectedTreeOnPopulatedTree() {
        fail();
    }

    @Test
    void mapMethodReturnsEmptyTreeWhenGivenEmptyTree() {
        fail();
    }

    @Test
    void filterMethodCorrectlyReturnsAVLTreeThatSatisfiesPredicate() {
        fail();
    }

    @Test
    void filterMethodReturnsEmptyTreeWhenUsedOnEmptyTree() {
        fail();
    }

    @Test
    void valuesReturnsExpectedListOfValuesInTree() {
        fail();
    }

    @Test
    void canIterateThroughPopulatedTreeCorrectly() {
        fail();
    }

    @Test
    void iteratorFromEmptyTreeHasNoValues() {
        fail();
    }
}