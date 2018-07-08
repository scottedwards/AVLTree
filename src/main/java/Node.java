import java.util.Objects;
import java.util.Optional;

public class Node<E> {
    private E value;
    private Node<E> left;
    private Node<E> right;

    public Node(E value) {
        setValue(value);
        this.left = null;
        this.right = null;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        Objects.requireNonNull(value, "The value of a node cannot be null!");
        this.value = value;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public void set(Direction dir, Node<E> value) {
        if (dir == Direction.LEFT) {
            setLeft(value);
        } else {
            setRight(value);
        }
    }

    public Optional<Node<E>> getLeft() {
        return Optional.ofNullable(left);
    }

    public Optional<Node<E>> getRight() {
        return Optional.ofNullable(right);
    }
}
