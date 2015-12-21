package messaging;

import java.util.HashMap;

public class StringableHashMap<K extends Stringable, V extends Stringable> extends HashMap<K, V> implements Stringable
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

}
