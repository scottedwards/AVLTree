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
        node.setLeft(child);

        Optional<Node<Integer>> rightChild = node.getLeft();
        assertTrue(rightChild.isPresent());
        rightChild.ifPresent(n -> {
            assertEquals(2, (int) n.getValue());
        });
    }
}