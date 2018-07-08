import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Stack;

import static java.lang.Math.abs;

public class AvlTree <E extends Comparable<E>> implements Tree<E>, Iterable<E> {

    private Node<E> root;
    private Integer size;

    AvlTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void insert(E value) {
        Objects.requireNonNull(value, "You cannot enter null as a value!");
        if (root == null) {
            root = new Node<>(value);
        } else {
            insertIntoSubtree(root, value);
        }
        this.size++;
    }

    @Override
    public void insertAll(List<E> values) {
        for (E value: values) {
            insert(value);
        }
    }

    private Direction insertIntoSubtree(Node<E> parent, E value) {
        Direction direction = (parent.getValue().compareTo(value) <= 0) ? Direction.RIGHT : Direction.LEFT;
        Optional<Node<E>> wantedChild = parent.get(direction);
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

    private void rotate(Node<E> node, Direction dir1, Direction dir2) {
        //TODO: Implement this.
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
    public Optional<E> removeOne(E value) {
        return Optional.empty();
    }

    @Override
    public Optional<E> getMax() {
        Optional<Node<E>> current = Optional.ofNullable(this.root);
        E max = null;
        while (current.isPresent()) {
            max = current.get().getValue();
            current = current.get().getRight();
        }
        return Optional.ofNullable(max);
    }

    @Override
    public Optional<E> getMin() {
        Optional<Node<E>> current = Optional.ofNullable(this.root);
        E min = null;
        while (current.isPresent()) {
            min = current.get().getValue();
            current = current.get().getLeft();
        }
        return Optional.ofNullable(min);
    }

    @Override
    public Optional<E> removeMax() {
        return Optional.empty();
    }

    @Override
    public Optional<E> removeMin() {
        return Optional.empty();
    }

    @Override
    public Optional<Node<E>> getRoot() {
        return Optional.ofNullable(this.root);
    }

    @Override
    public Boolean contains(E value) {
        return null;
    }

    @Override
    public Integer occurrencesOf(E value) {
        return null;
    }

    @Override
    public Integer height() {
        return heightOf(this.root);
    }

    private Integer balanceOf(Node<E> node) {
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

    private Integer heightOf(Node<E> node) {
        Integer leftHeight = 0;
        Integer rightHeight = 0;
        Optional<Node<E>> leftChild = node.getLeft();
        Optional<Node<E>> rightChild = node.getRight();
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
    public List<E> values() {
        List<E> list = new ArrayList<>();
        iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator(this.root);
    }

    private class TreeIterator implements Iterator<E> {

        private Stack<Node<E>> searchStack;
        private Node<E> current;

        TreeIterator(Node<E> root) {
            this.searchStack = new Stack<>();
            current = root;
        }

        @Override
        public boolean hasNext() {
            return current != null || !searchStack.empty();
        }

        @Override
        public E next() {
            while (current != null) {
                searchStack.push(current);
                current = current.getLeft().orElse(null);
            }

            Node<E> next = searchStack.empty() ? null : searchStack.pop();

            if (next != null) {
                current = next.getRight().orElse(null);
                return next.getValue();
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
