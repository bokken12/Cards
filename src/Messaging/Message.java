package Messaging;

public abstract class Message
{
    private Stringer data;
    public abstract Message fromString(String args);
    public abstract String toString();
    public static Message fromData(Stringer data){
        try
        {
            Message message = data.getType().newInstance();
            message.setData(data);
            return message;
        } catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public Stringer getData()
    {
        return data;
    }
    public void setData(Stringer data)
    {
        this.data = data;
    }
}
