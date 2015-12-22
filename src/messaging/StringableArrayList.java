package messaging;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class StringableArrayList<E extends Stringable> extends ArrayList<E> implements Stringable<ArrayList>
{
    public StringableArrayList(String str){
        super();
        fromString(str);
    }
    public StringableArrayList(ArrayList<E> ar){
        super(ar);
    }
	@Override
	public String toString()
	{
		String delim = "" + this.hashCode();
		String str = "";
		str += delim;
		if(size() > 0){
		    str += get(0).getClass().getName();
		    for(E obj: this){
		        //str +=
		    }
		}
		return str;
	}

	@Override
	public void fromString(String str)
	{
	    String delim = str.substring(0, str.indexOf("delim"));
		StringTokenizer st = new StringTokenizer(str.substring(str.indexOf("delim" + 5), str.length()), delim);
		try
        {
		    String className = st.nextToken();
            Class<? extends Stringable> cls = (Class<? extends Stringable>) Class.forName(className);
            while(st.hasMoreTokens()){
                add((E)(new MetaClass(className)).createInstance(st.nextToken()));
            }
        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
    @Override
    public ArrayList getMirror()
    {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void fromMirror(ArrayList e)
    {
        // TODO Auto-generated method stub
        
    }
}
