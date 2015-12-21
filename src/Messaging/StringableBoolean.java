package messaging;

public class StringableBoolean implements Stringable
{
    private boolean bool;
    
    public StringableBoolean(){
        this(false);
    }
    
    public StringableBoolean(boolean bool){
        setBoolean(bool);
    }
    
    @Override
    public String toString()
    {
        if(bool){
            return "true";
        }
        return "false";
    }

    @Override
    public void fromString(String str)
    {
        setBoolean(str.equals("true"));
    }
    
    public boolean getBoolean(){
        return bool;
    }
    
    public void setBoolean(boolean bool){
        this.bool = bool;
    }

}
