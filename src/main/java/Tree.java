import java.util.List;
import java.util.Optional;

public interface Tree<T extends Comparable<T>> {

    public void insert(T value);

    public void insertAll(List<T> values);

    public Optional<T> getMax();

    public Optional<T> getMin();

    public Optional<T> removeFirst(T value);

    public Optional<T> removeMax();

    public Optional<T> removeMin();

    public Optional<Node<T>> getRoot();

    public Boolean contains(T value);

    public Integer occurrencesOf(T value);

    public Integer height();

    public Integer size();

    public List<T> values();
}
