package State;

@FunctionalInterface
public interface TriFunction<T, W, S, R> {
    public R apply(T t, W w, S s);

}