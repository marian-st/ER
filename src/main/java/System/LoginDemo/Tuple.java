package System.LoginDemo;

public class Tuple<T,W> {
    private T first;
    private W second;

    public Tuple(T fst, W snd) {
        first=fst;
        second=snd;
    }

    public T fst() {
        return this.first;
    }
    public W snd() {
        return this.second;
    }

    public String toString() {
        return "<" + first + ", " + second + ">";
    }
}
