package Messaging;

public interface MessageListener<E extends Message>
{
    public void MessageRecieved(E message);
}
