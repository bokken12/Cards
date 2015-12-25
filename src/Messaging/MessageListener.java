package messaging;

public interface MessageListener<E extends Message>
{
    public abstract void MessageRecieved(E message);
}
