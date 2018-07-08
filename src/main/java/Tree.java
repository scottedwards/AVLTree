import java.util.List;
import java.util.Optional;

public interface Tree<E extends Comparable<E>> {

    void insert(E value);

    void insertAll(List<E> values);

    Optional<E> getMax();

    Optional<E> getMin();

    Optional<E> removeFirst(E value);

    Optional<Node<E>> findFirst(E value);

    Optional<E> removeMax();

    Optional<E> removeMin();

    Optional<Node<E>> getRoot();

    Boolean contains(E value);

    Integer occurrencesOf(E value);

    Integer height();

    Integer size();

    List<E> values();
}
