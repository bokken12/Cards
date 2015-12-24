package messaging;

public class StringableBoolean implements Stringable<Boolean>
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

    @Override
    public Boolean getMirror()
    {
        return new Boolean(bool);
    }

    @Override
    public void fromMirror(Boolean e)
    {
        bool = e.booleanValue();
    }
    @Override
    public int hashCode(){
        if(bool){
            return 1;
        } else {
            return 0;
        }
    }
}
