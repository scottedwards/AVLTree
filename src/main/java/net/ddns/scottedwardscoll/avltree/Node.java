package net.ddns.scottedwardscoll.avltree;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

class Node<E> {
    private E value;
    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;

    Node(final E value) {
        setValue(value);
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    E getValue() {
        return value;
    }

    void setValue(final E value) {
        requireNonNull(value, "The value of a node cannot be null!");
        this.value = value;
    }

    void setParent(final Node<E> parent) {
        requireNonNull(parent, "The parent cannot be null, use remove parent instead");
        this.parent = parent;
    }

    Optional<Node<E>> getParent() {
        return Optional.ofNullable(parent);
    }

    void removeParent() {
        this.parent = null;
    }

    void setLeft(final Node<E> left) {
        requireNonNull(left, "The node cannot be null");
        this.left = left;
        left.setParent(this);
    }

    void removeLeft() {
        this.left = null;
    }

    void setRight(final Node<E> right) {
        requireNonNull(right, "The node cannot be null");
        this.right = right;
        right.setParent(this);
    }

    void removeRight() {
        this.right = null;
    }

    void set(final Node<E> node, final Direction direction) {
        requireNonNull(node, "The node cannot be null");
        requireNonNull(direction, "The direction cannot be null");
        if (direction == Direction.LEFT) {
            setLeft(node);
        } else {
            setRight(node);
        }
    }

    void remove(final Direction direction) {
        requireNonNull(direction, "The direction cannot be null");
        if (direction == Direction.LEFT)
            removeLeft();
        if (direction == Direction.RIGHT)
            removeRight();
    }

    Optional<Node<E>> getLeft() {
        return Optional.ofNullable(left);
    }

    Optional<Node<E>> getRight() {
        return Optional.ofNullable(right);
    }

    Optional<Node<E>> get(final Direction direction) {
        requireNonNull(direction, "The direction cannot be null");
        return Optional.ofNullable((direction == Direction.LEFT) ? left : right);
    }

    Optional<Direction> getDirection(final Node<E> node) {
        requireNonNull(node, "The node cannot be null");
        if (this.left == node) {
            return Optional.of(Direction.LEFT);
        }
        if (this.right == node)
            return Optional.of(Direction.RIGHT);
        return Optional.empty();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
