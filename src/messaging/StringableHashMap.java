package messaging;

import java.util.HashMap;

public class StringableHashMap<K extends Stringable, V extends Stringable> extends HashMap<K, V> implements Stringable<HashMap>
{
    Class<? extends Stringable> kc;
    Class<? extends Stringable> vc;
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
        HashMap hm = new HashMap();
        for(K key: keySet()){
            hm.put(key.getMirror(), get(key).getMirror());
        }
        return hm;
    }

    @Override
    public void fromMirror(HashMap e)
    {
        clear();
        for(Object obj: e.keySet()){
            put((K)(new MetaClass(kc)).createInstance(obj), (V)(new MetaClass(vc)).createInstance(e.get(obj)));
        }
    }

}
