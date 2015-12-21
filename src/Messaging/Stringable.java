package messaging;

public interface Stringable
{
    public abstract String toString();
    public abstract void fromString(String str);
    public abstract Class forConstructor();
}
