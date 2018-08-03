import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void settingNodeValueToNullGivesError() {
        final Node<Integer> node = new Node<>(1);
        final NullPointerException npe = assertThrows(NullPointerException.class, () -> node.setValue(null));
        assertEquals("The value of a node cannot be null!", npe.getMessage());
    }

    @Test
    void getParentReturnsExpectedNodeWhenItHasAParent() {
        final Node<Integer> root = new Node<>(1);
        final Node<Integer> child = new Node<>(2);
        child.setParent(root);
        assertTrue(child.getParent().isPresent());
        assertEquals(root, child.getParent().get());
    }

    @Test
    void getParentReturnsEmptyOptionalWhenNoParentIsSet() {
        final Node<Integer> child = new Node<>(2);
        assertFalse(child.getParent().isPresent());
    }

    @Test
    void setChildCorrectlySetsParent() {
        final Node<Integer> root = new Node<>(1);
        final Node<Integer> child = new Node<>(2);
        root.setLeft(child);
        assertTrue(child.getParent().isPresent());
        assertEquals(root, child.getParent().get());
    }

    @Test
    void gettingRightChildThatDoesNotExistReturnsEmptyOptional() {
        final Node<Integer> node = new Node<>(1);
        assertFalse(node.getRight().isPresent());
    }

    @Test
    void gettingLeftChildThatDoesNotExistReturnsEmptyOptional() {
        final Node<Integer> node = new Node<>(1);
        assertFalse(node.getLeft().isPresent());
    }

    @Test
    void canSetAndReturnLeftChild() {
        final Node<Integer> node = new Node<>(1);
        final Node<Integer> child = new Node<>(2);
        node.setLeft(child);

        final Optional<Node<Integer>> leftChild = node.getLeft();
        assertTrue(leftChild.isPresent());
        leftChild.ifPresent(n -> assertEquals(2, (int) n.getValue()));
    }

    @Test
    void canSetAndReturnRightChild() {
        final Node<Integer> node = new Node<>(1);
        final Node<Integer> child = new Node<>(2);
        node.setRight(child);

        final Optional<Node<Integer>> rightChild = node.getRight();
        assertTrue(rightChild.isPresent());
        rightChild.ifPresent(n -> assertEquals(2, (int) n.getValue()));
    }

    @Test
    void setMethodWillSetLeftChildCorrectly() {
        final Node<Integer> node = new Node<>(1);
        final Node<Integer> child = new Node<>(2);
        node.set(child, Direction.LEFT);

        final Optional<Node<Integer>> leftChild = node.getLeft();
        assertTrue(leftChild.isPresent());
        leftChild.ifPresent(n -> assertEquals(2, (int) n.getValue()));
    }

    @Test
    void setMethodWillSetRightChildCorrectly() {
        final Node<Integer> node = new Node<>(1);
        final Node<Integer> child = new Node<>(2);
        node.set(child, Direction.RIGHT);

        final Optional<Node<Integer>> rightChild = node.getRight();
        assertTrue(rightChild.isPresent());
        rightChild.ifPresent(n -> assertEquals(2, (int) n.getValue()));
    }

    @Test
    void removeLeftWillRemoveReferenceToChildFromParent() {
        final Node<Integer> node = new Node<>(1);
        final Node<Integer> child = new Node<>(2);
        node.set(child, Direction.LEFT);

        node.removeLeft();

        assertFalse(node.getLeft().isPresent());
    }

    @Test
    void removeRightWillRemoveReferenceToChildFromParent() {
        final Node<Integer> node = new Node<>(1);
        final Node<Integer> child = new Node<>(2);
        node.set(child, Direction.RIGHT);

        node.removeRight();

        assertFalse(node.getRight().isPresent());
    }

    @Test
    void getMethodGetsExpectedNodeWhenLeftIsPassedIn() {
        final Node<Integer> node = new Node<>(1);
        final Node<Integer> child = new Node<>(2);
        node.set(child, Direction.LEFT);

        Optional<Node<Integer>> leftChild = node.get(Direction.LEFT);
        assertTrue(leftChild.isPresent());
        leftChild.ifPresent(n -> assertEquals(2, (int) n.getValue()));
    }

    @Test
    void getMethodGetsExpectedNodeWhenRightIsPassedIn() {
        final Node<Integer> node = new Node<>(1);
        final Node<Integer> child = new Node<>(2);
        node.set(child, Direction.RIGHT);

        Optional<Node<Integer>> rightChild = node.get(Direction.RIGHT);
        assertTrue(rightChild.isPresent());
        rightChild.ifPresent(n -> assertEquals(2, (int) n.getValue()));
    }

    @Test
    void getDirectionReturnsCorrectDirection() {
        final Node<Integer> node = new Node<>(1);
        final Node<Integer> lChild = new Node<>(2);
        final Node<Integer> rChild = new Node<>(2);
        node.set(rChild, Direction.RIGHT);
        node.set(lChild, Direction.LEFT);

        Optional<Direction> rDirection = node.getDirection(rChild);
        Optional<Direction> lDirection = node.getDirection(lChild);

        assertTrue(rDirection.isPresent());
        assertTrue(lDirection.isPresent());
        assertEquals(Direction.RIGHT, rDirection.get());
        assertEquals(Direction.LEFT, lDirection.get());
    }

    @Test
    void getDirectionReturnsEmptyOptionalWhenNodeNotPresent() {
        final Node<Integer> node = new Node<>(1);
        final Node<Integer> child = new Node<>(2);

        Optional<Direction> direction = node.getDirection(child);

        assertFalse(direction.isPresent());
    }
}