import java.util.Objects;
import java.util.Optional;

class Node<E> {
    private E value;
    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;

    Node(E value) {
        setValue(value);
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    E getValue() {
        return value;
    }

    void setValue(E value) {
        Objects.requireNonNull(value, "The value of a node cannot be null!");
        this.value = value;
    }

    void setParent(Node<E> parent) {
        this.parent = parent;
    }

    Optional<Node<E>> getParent() {
        return Optional.ofNullable(parent);
    }

    void setLeft(Node<E> left) {
        this.left = left;
        left.setParent(this);
    }

    void setRight(Node<E> right) {
        this.right = right;
        right.setParent(this);
    }

    void set(Direction dir, Node<E> value) {
        if (dir == Direction.LEFT) {
            setLeft(value);
        } else {
            setRight(value);
        }
    }

    Optional<Node<E>> getLeft() {
        return Optional.ofNullable(left);
    }

    Optional<Node<E>> getRight() {
        return Optional.ofNullable(right);
    }

    Optional<Node<E>> get(Direction direction) {
        return Optional.ofNullable((direction == Direction.LEFT) ? left : right);
    }
}
