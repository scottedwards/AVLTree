import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Stack;

import static java.lang.Math.abs;
import static java.util.Objects.requireNonNull;

public class AvlTree<E extends Comparable<E>> implements Tree<E>, Iterable<E> {

    private Node<E> root;
    private Integer size;

    AvlTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void insert(E value) {
        requireNonNull(value, "You cannot enter null as a value!");
        if (root == null) {
            root = new Node<>(value);
        } else {
            try {
                insertIntoSubtree(root, value);
            } catch (ChildNotFoundException cfe) {
                throw new RuntimeException("Couldn't add element '" + value + "' to tree");
            }
        }
        this.size++;
    }

    @Override
    public void insertAll(List<E> values) {
        for (E value : values) {
            insert(value);
        }
    }

    private Direction insertIntoSubtree(Node<E> parent, E value) throws ChildNotFoundException {
        Direction direction = (parent.getValue().compareTo(value) <= 0) ? Direction.RIGHT : Direction.LEFT;
        Optional<Node<E>> wantedChild = parent.get(direction);
        if (wantedChild.isPresent()) {
            Direction childDirection = insertIntoSubtree(wantedChild.get(), value);
            Integer balance = balanceOf(parent);
            if (2 <= abs(balance)) {
                rotate(parent, direction, childDirection);
            }
        } else {
            parent.set(new Node<>(value), direction);
        }
        return direction;
    }

    private void rotate(Node<E> node, Direction dir1, Direction dir2) throws ChildNotFoundException {
        final String errorMessage = "Child expected to be found but wasn't while rotating tree";
        Node<E> pivot = node.get(dir1).orElseThrow(
                () -> new ChildNotFoundException(errorMessage)
        );
        if (dir1 == Direction.RIGHT && dir2 == Direction.RIGHT) {
            leftRotation(node, pivot);
        } else if (dir1 == Direction.LEFT && dir2 == Direction.LEFT) {
            rightRotation(node, pivot);
        } else if (dir1 == Direction.LEFT && dir2 == Direction.RIGHT) {
            // left-right rotation
            Node<E> lowPivot = pivot.getRight().orElseThrow(() -> new ChildNotFoundException(errorMessage));
            rightRotation(pivot, lowPivot);
            leftRotation(node, lowPivot);
        } else {
            // right-left rotation
            Node<E> lowPivot = pivot.getLeft().orElseThrow(() -> new ChildNotFoundException(errorMessage));
            rightRotation(pivot, lowPivot);
            leftRotation(node, lowPivot);
        }
    }

    private void leftRotation(final Node<E> r, final Node<E> p) {
        //         r                         p
        //      /    \                    /    \
        //    a       p        =>       r       c
        //          /   \             /   \
        //         b     c          a      b
        requireNonNull(r, "The root of the rotation cannot be null");
        requireNonNull(p, "The pivot cannot be null");
        Optional<Node<E>> parent = r.getParent();
        Optional<Node<E>> b = p.getLeft();
        if (parent.isPresent()) {
            Optional<Direction> direction = parent.get().getDirection(r);
            direction.ifPresent(dir -> parent.get().set(p, dir));
            r.removeParent();
        } else {
            p.removeParent();
            this.root = p;
        }
        r.getLeft().ifPresent(node -> {
            if (node == p) r.removeLeft();
        });
        p.setLeft(r);
        r.removeRight();
        b.ifPresent(r::setRight);
    }

    private void rightRotation(final Node<E> r, final Node<E> p) {
        //             r                         p
        //          /    \                    /    \
        //        p       c        =>       a       r
        //     /   \                              /   \
        //    a     b                           b      c
        requireNonNull(r, "The root of the rotation cannot be null");
        requireNonNull(p, "The pivot cannot be null");
        Optional<Node<E>> parent = r.getParent();
        Optional<Node<E>> b = p.getRight();
        if (parent.isPresent()) {
            Optional<Direction> direction = parent.get().getDirection(r);
            direction.ifPresent(dir -> parent.get().set(p, dir));
            r.removeParent();
        } else {
            p.removeParent();
            this.root = p;
        }
        p.setRight(r);
        r.removeLeft();
        b.ifPresent(r::setLeft);
        r.getRight().ifPresent(node -> {
            if (node == p) r.removeRight();
        });
    }

    @Override
    public Optional<E> removeFirst(final E value) {
        requireNonNull(value, "The value to be removed cannot be null");
        if (root == null) return Optional.empty();
        Optional<Node<E>> nodeToRemove = findFirst(value);
        nodeToRemove.ifPresent(this::remove);
        return nodeToRemove.map(Node::getValue);
    }

    void remove(final Node<E> node) {
        requireNonNull(node, "Node cannot be null");
        Optional<Node<E>> parent = node.getParent();

        size--;
    }

    @Override
    public Optional<Node<E>> findFirst(final E value) {
        requireNonNull(value, "The value to search for cannot be null");
        return Optional.empty();
    }

    @Override
    public Optional<E> getMax() {
        if (this.root == null) return Optional.empty();
        return Optional.of(keepGoingWithDirection(this.root, Direction.RIGHT).getValue());
    }

    @Override
    public Optional<E> getMin() {
        if (this.root == null) return Optional.empty();
        return Optional.of(keepGoingWithDirection(this.root, Direction.LEFT).getValue());
    }

    private Node<E> keepGoingWithDirection(final Node<E> start, final Direction direction) {
        requireNonNull(start, "The node to start the walk cannot be null");
        requireNonNull(direction, "The direction cannot be null");
        Node<E> current = start;
        Optional<Node<E>> next = current.get(direction);
        while (next.isPresent()) {
            current = next.get();
            next = current.get(direction);
        }
        return current;
    }

    @Override
    public Optional<E> removeMax() {
        if (this.root == null) return Optional.empty();
        Node<E> max = keepGoingWithDirection(this.root, Direction.RIGHT);
        remove(max);
        return Optional.of(max.getValue());
    }

    @Override
    public Optional<E> removeMin() {
        if (this.root == null) return Optional.empty();
        Node<E> min = keepGoingWithDirection(this.root, Direction.LEFT);
        remove(min);
        return Optional.of(min.getValue());
    }

    @Override
    public Optional<Node<E>> getRoot() {
        return Optional.ofNullable(this.root);
    }

    @Override
    public Boolean contains(final E value) {
        requireNonNull(value, "The value to search for cannot be null");
        return (this.root == null) ? false : containsIn(this.root, value);
    }

    private Boolean containsIn(final Node<E> node, final E value) {
        requireNonNull(node, "The node cannot be null");
        requireNonNull(value, "The value cannot be null");
        int comp = node.getValue().compareTo(value);
        if (comp == 0) {
            return true;
        } else {
            Direction direction = (comp < 0) ? Direction.RIGHT : Direction.LEFT;
            if (node.get(direction).isPresent()) {
                return containsIn(node.get(direction).get(), value);
            }
        }
        return false;
    }

    @Override
    public Integer occurrencesOf(final E value) {
        requireNonNull(value, "The value cannot be null");
        return (root == null) ? 0 : occurrencesOf(this.root, value);
    }

    private Integer occurrencesOf(final Node<E> node, final E value) {
        requireNonNull(node, "The node cannot be null");
        requireNonNull(value, "The value cannot be null");
        Integer count = 0;
        if (node.getValue().compareTo(value) == 0) count += 1;
        if (node.getLeft().isPresent()) count += occurrencesOf(node.getLeft().get(), value);
        if (node.getRight().isPresent()) count += occurrencesOf(node.getRight().get(), value);
        return count;
    }

    @Override
    public Integer height() {
        return (this.root == null) ? 0 : heightOf(this.root);
    }

    private Integer balanceOf(final Node<E> node) {
        requireNonNull(node, "The node cannot be null");
        Integer leftHeight = 0;
        Integer rightHeight = 0;
        if (node.getLeft().isPresent()) {
            leftHeight = 1 + heightOf(node.getLeft().get());
        }
        if (node.getRight().isPresent()) {
            rightHeight = 1 + heightOf(node.getRight().get());
        }
        return leftHeight - rightHeight;
    }

    private Integer heightOf(final Node<E> node) {
        requireNonNull(node, "The node cannot be null");
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

    @NotNull
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
