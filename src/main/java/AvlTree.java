import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class AvlTree <T extends Comparable<T>> implements Tree<T>, Iterable<T> {

    private Node<T> root;
    private Integer size;

    public AvlTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(T value) {

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
    public Optional<T> removeMax(T value) {
        return Optional.empty();
    }

    @Override
    public Optional<T> removeMin(T value) {
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
        return null;
    }

    @Override
    public Integer size() {
        return null;
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
    public Collection<T> values() {
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
