package messaging;

public abstract class Message
{
    protected Stringer data;
    public static Message fromString(String args){
        Stringer newData = new Stringer(args);
        return fromData(newData);
    }
    public String toString(){
        return data.toString();
    }
    
    public Message() {
    	data = new Stringer(getClass());
    }
    
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
    public Object getMirror(Stringable str){
    	if(str == null){
    		return str;
    	}
    	return str.getMirror();
    }
}
