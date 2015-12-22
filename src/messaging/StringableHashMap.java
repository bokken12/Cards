package messaging;

import java.util.HashMap;

public class StringableHashMap<K extends Stringable, V extends Stringable> extends HashMap<K, V> implements Stringable<HashMap>
{

    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void fromString(String str)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public HashMap getMirror()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void fromMirror(HashMap e)
    {
        // TODO Auto-generated method stub
        
    }

}
