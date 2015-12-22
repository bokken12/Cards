package messaging;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class StringableArrayList<E extends Stringable> extends ArrayList<E> implements Stringable<ArrayList>
{

    private Class<? extends Stringable> cls;

	public StringableArrayList(){
		super();
	}

    public StringableArrayList(String str){
        super();
        fromString(str);
    }
    public StringableArrayList(ArrayList<E> ar){
        super(ar);
    }
    public StringableArrayList(Class<? extends E> cls, ArrayList ar){
        super();
        fromMirror(ar);
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
            cls = (Class<? extends Stringable>) Class.forName(className);
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
        ArrayList a = new ArrayList();
        for(E obj: this){
            a.add(obj.getMirror());
        }
        return a;
    }
    @Override
    public void fromMirror(ArrayList e)
    {
        this.clear();
        for(Object obj: e){
            try
            {
                Stringable newItem = cls.newInstance();
                newItem.fromMirror(obj);
            } catch (InstantiationException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IllegalAccessException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}
