import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Tree<T extends Comparable<T>> {

    public void insert(T value);

    public Optional<T> getMax();

    public Optional<T> getMin();

    public Optional<T> removeFirst(T value);

    public Optional<T> removeMax(T value);

    public Optional<T> removeMin(T value);

    public Optional<Node<T>> getRoot();

    public Boolean contains(T value);

    public Integer occurrencesOf(T value);

    public Integer height();

    public Integer size();

    public <R extends Comparable<R>> Tree<R> map(Function<T, R> f);

    public Tree<T> filter(Predicate<T> predicate);

    public List<T> values();
}
