package messaging;

public class StringableInteger implements Stringable
{
    private int i;

    public StringableInteger(){
        this(0);
    }
    
    public StringableInteger(int i)
    {
        this.i = i;
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
}
