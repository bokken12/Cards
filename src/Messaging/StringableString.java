package messaging;

public class StringableString extends Stringable
{

    private String str;
    
    public StringableString(){
        this(null);
    }
    
    public StringableString(String str){
        setString(str);
    }
    
    @Override
    public String toString()
    {
        return str;
    }

    @Override
    public void fromString(String str)
    {
        setString(str);
    }
    
    public String getString(){
        return str;
    }
    
    public void setString(String str){
        this.str = str;
    }

}
