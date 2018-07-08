import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.Math.abs;

public class AvlTree <T extends Comparable<T>> implements Tree<T>, Iterable<T> {

    private Node<T> root;
    private Integer size;

    public AvlTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void insert(T value) {
        Objects.requireNonNull(value, "You cannot enter null as a value!");
        if (root == null) {
            root = new Node<>(value);
        } else {
            insertIntoSubtree(root, value);
        }
        this.size++;
    }

    private Direction insertIntoSubtree(Node<T> parent, T value) {
        Direction direction = (parent.getValue().compareTo(value) <= 0) ? Direction.LEFT : Direction.RIGHT;
        Optional<Node<T>> wantedChild = parent.get(direction);
        if (wantedChild.isPresent()) {
            Direction childDirection = insertIntoSubtree(wantedChild.get(), value);
            Integer balance = balanceOf(parent);
            if (2 <= abs(balance)) {
                // needs rotating!
                rotate(parent, direction, childDirection);
            }
        } else {
            parent.set(direction, new Node<>(value));
        }
        return direction;
    }

    private void rotate(Node<T> node, Direction dir1, Direction dir2) {
        if (dir1 == Direction.RIGHT && dir2 == Direction.RIGHT) {
            // left rotation
        } else if (dir1 == Direction.LEFT && dir2 == Direction.LEFT) {
            // right rotation
        } else if (dir1 == Direction.LEFT && dir2 == Direction.RIGHT) {
            // left-right rotation
        } else {
            // right left rotation
        }
    }

    @Override
    public Optional<T> removeFirst(T value) {
        return Optional.empty();
    }

    @Override
    public Optional<T> getMax() {
        return Optional.empty();
    }

    @Override
    public Optional<T> getMin() {
        return Optional.empty();
    }

    @Override
    public Optional<T> removeMax() {
        return Optional.empty();
    }

    @Override
    public Optional<T> removeMin() {
        return Optional.empty();
    }

    @Override
    public Optional<Node<T>> getRoot() {
        return Optional.empty();
    }

    @Override
    public Boolean contains(T value) {
        return null;
    }

    @Override
    public Integer occurrencesOf(T value) {
        return null;
    }

    @Override
    public Integer height() {
        return heightOf(this.root);
    }

    private Integer balanceOf(Node<T> node) {
        Integer leftHeight = 0;
        Integer rightHeight = 0;
        if (node.getLeft().isPresent()) {
            leftHeight = heightOf(node.getLeft().get());
        }
        if (node.getRight().isPresent()) {
            rightHeight = heightOf(node.getRight().get());
        }
        return leftHeight - rightHeight;
    }

    private Integer heightOf(Node<T> node) {
        Integer leftHeight = 0;
        Integer rightHeight = 0;
        Optional<Node<T>> leftChild = node.getLeft();
        Optional<Node<T>> rightChild = node.getRight();
        if (leftChild.isPresent()) {
            leftHeight = 1 + heightOf(leftChild.get());
        }
        if (rightChild.isPresent()) {
            rightHeight = 1 + heightOf(rightChild.get());
        }
        return (leftHeight < rightHeight) ? rightHeight : leftHeight;
    }

    @Override
    public Integer size() {
        return this.size;
    }

    @Override
    public <R extends Comparable<R>> Tree<R> map(Function<T, R> f) {
        return null;
    }

    @Override
    public Tree<T> filter(Predicate<T> predicate) {
        return null;
    }

    @Override
    public List<T> values() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class TreeIterator implements Iterator<T> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
    }
}
