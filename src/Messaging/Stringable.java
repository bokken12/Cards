package messaging;

public interface Stringable<E>
{
    public abstract String toString();
    public abstract void fromString(String str);
    public abstract E getMirror();
    public abstract void fromMirror(E e);
}
