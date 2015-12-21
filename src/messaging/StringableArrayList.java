package messaging;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class StringableArrayList<E extends Stringable> extends ArrayList<E> implements Stringable
{
    Class c;
    @Override
    public String toString()
    {
        int i = hashCode();
        String str = "split(";
        str += i;
        str += ")";
        for(E obj: this){
            str +=i;
            str += String.valueOf(obj);
        }
        return str;
    }

    @Override
    public void fromString(String str)
    {
        E = Object.class;
        this.clear();
        String split = str.substring(6, str.indexOf(")"));
        StringTokenizer t = new StringTokenizer(str, split);
        t.nextToken();
        while(t.hasMoreTokens()){
            this.add(e)
        }
    }
    
    public <c> c hi(){
        
    }
}
