import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void settingNodeValueToNullGivesError() {
        Node<Integer> node = new Node<>(1);
        NullPointerException npe = assertThrows(NullPointerException.class, () -> {
            node.setValue(null);
        });
        assertEquals("The value of a node cannot be null!", npe.getMessage());
    }

    @Test
    void getParentReturnsExpectedNodeWhenItHasAParent() {
        Node<Integer> root = new Node<>(1);
        Node<Integer> child = new Node<>(2);
        child.setParent(root);
        assertTrue(child.getParent().isPresent());
        assertEquals(root, child.getParent().get());
    }

    @Test
    void getParentReturnsEmptyOptionalWhenNoParentIsSet() {
        Node<Integer> root = new Node<>(1);
        Node<Integer> child = new Node<>(2);
        assertFalse(child.getParent().isPresent());
    }

    @Test
    void setChildCorrectlySetsNodeAsChildsParent() {
        Node<Integer> root = new Node<>(1);
        Node<Integer> child = new Node<>(2);
        root.setLeft(child);
        assertTrue(child.getParent().isPresent());
        assertEquals(root, child.getParent().get());
    }

    @Test
    void gettingRightChildThatDoesNotExistReturnsEmptyOptional() {
        Node<Integer> node = new Node<>(1);
        assertFalse(node.getRight().isPresent());
    }

    @Test
    void gettingLeftChildThatDoesNotExistReturnsEmptyOptional() {
        Node<Integer> node = new Node<>(1);
        assertFalse(node.getLeft().isPresent());
    }

    @Test
    void canSetAndReturnLeftChild() {
        Node<Integer> node = new Node<>(1);
        Node<Integer> child = new Node<>(2);
        node.setLeft(child);

        Optional<Node<Integer>> leftChild = node.getLeft();
        assertTrue(leftChild.isPresent());
        leftChild.ifPresent(n -> {
            assertEquals(2, (int) n.getValue());
        });
    }

    @Test
    void canSetAndReturnRightChild() {
        Node<Integer> node = new Node<>(1);
        Node<Integer> child = new Node<>(2);
        node.setRight(child);

        Optional<Node<Integer>> rightChild = node.getRight();
        assertTrue(rightChild.isPresent());
        rightChild.ifPresent(n -> {
            assertEquals(2, (int) n.getValue());
        });
    }

    @Test
    void setMethodWillSetLeftChildCorrectly() {
        Node<Integer> node = new Node<>(1);
        Node<Integer> child = new Node<>(2);
        node.set(child, Direction.LEFT);

        Optional<Node<Integer>> leftChild = node.getLeft();
        assertTrue(leftChild.isPresent());
        leftChild.ifPresent(n -> {
            assertEquals(2, (int) n.getValue());
        });
    }

    @Test
    void setMethodWillSetRightChildCorrectly() {
        Node<Integer> node = new Node<>(1);
        Node<Integer> child = new Node<>(2);
        node.set(child, Direction.RIGHT);

        Optional<Node<Integer>> rightChild = node.getRight();
        assertTrue(rightChild.isPresent());
        rightChild.ifPresent(n -> {
            assertEquals(2, (int) n.getValue());
        });
    }

    @Test
    void getMethodGetsExpectedNodeWhenLeftIsPassedIn() {
        Node<Integer> node = new Node<>(1);
        Node<Integer> child = new Node<>(2);
        node.set(child, Direction.LEFT);

        Optional<Node<Integer>> leftChild = node.get(Direction.LEFT);
        assertTrue(leftChild.isPresent());
        leftChild.ifPresent(n -> {
            assertEquals(2, (int) n.getValue());
        });
    }

    @Test
    void getMethodGetsExpectedNodeWhenRightIsPassedIn() {
        Node<Integer> node = new Node<>(1);
        Node<Integer> child = new Node<>(2);
        node.set(child, Direction.RIGHT);

        Optional<Node<Integer>> rightChild = node.get(Direction.RIGHT);
        assertTrue(rightChild.isPresent());
        rightChild.ifPresent(n -> {
            assertEquals(2, (int) n.getValue());
        });
    }

    @Test
    void swapChildMethodCanSwapChildren() {
        Node<Integer> a = new Node<>(1);
        Node<Integer> b = new Node<>(2);
        Node<Integer> c = new Node<>(3);
        Node<Integer> d = new Node<>(4);
        Node<Integer> e = new Node<>(5);

        a.setLeft(b); a.setRight(c);
        d.setRight(e);

        a.swapChild(b, e);

        assertFalse(b.getParent().isPresent());
        assertFalse(d.getRight().isPresent());

        assertTrue(a.getLeft().isPresent());
        assertEquals(e, a.getLeft().get());

        assertTrue(a.getRight().isPresent());
        assertEquals(c, a.getRight().get());
    }
}