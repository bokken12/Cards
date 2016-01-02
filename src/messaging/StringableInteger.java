package messaging;

public class StringableInteger implements Stringable<Integer>
{
    private int i;

    public StringableInteger(){
        this(0);
    }
    
    public StringableInteger(int i)
    {
        this.i = i;
    }
    
    public StringableInteger(Integer i)
    {
        this(i.intValue());
    }
    
    public StringableInteger(String str){
        fromString(str);
    }

    @Override
    public void fromString(String str)
    {
        i = Integer.parseInt(str);
    }

    @Override
    public String toString()
    {
        return "" + i;
    }
    
    public int getInt(){
        return i;
    }
    
    public void setInt(int i){
        this.i = i;
    }

    @Override
    public Integer getMirror()
    {
        return new Integer(i);
    }

    @Override
    public void fromMirror(Integer e)
    {
        i = e.intValue();
    }
}
