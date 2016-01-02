package messaging;

import java.util.HashMap;
import java.util.StringTokenizer;

public class StringableHashMap<K extends Stringable, V extends Stringable> extends HashMap<K, V> implements Stringable<HashMap>
{
    Class<? extends Stringable> kc;
    Class<? extends Stringable> vc;
    @Override
    public String toString()
    {
        /*String delim = "" + this.hashCode();
        String str = "";
        str += delim;
        str += "delim";
        if(size() > 0){
            for(K key: keySet()){
                str += key.getClass().getName();
                str += delim;
                str += get(key).getClass().getName();
                break;
            }
            for(K obj: keySet()){
                str += delim;
                str += obj.toString();
                str += delim;
                str += get(obj);
            }
        }
        return str;*/
        String str = "";
        for(Stringable obj: this.keySet()){
            str += "((";
            str += Stringer.printStringable(obj);
            str += ")";
            str += "(";
            str += Stringer.printStringable(get(obj));
            str += "))";
        }
        return str;
    }
    
    public StringableHashMap(){
        super();
    }
    
    public StringableHashMap(String str){
        super();
        fromString(str);
    }

    @Override
    public void fromString(String str)
    {
        /*String delim = str.substring(0, str.indexOf("delim"));
        StringTokenizer st = new StringTokenizer(str.substring(str.indexOf("delim") + 5, str.length()), delim);
        try
        {
            if(st.hasMoreTokens()){
                String keyClass = st.nextToken();
                kc = (Class<? extends Stringable>) Class.forName(keyClass);
                String valueClass = st.nextToken();
                vc = (Class<? extends Stringable>) Class.forName(valueClass);
                while(st.hasMoreTokens()){
                    put((K)(new MetaClass(keyClass)).createInstance(st.nextToken()), (V)(new MetaClass(valueClass).createInstance(st.nextToken())));
                }
            }
        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        for(int index = 0; index < str.length();){
            String pairInfo = Stringer.fromParens(str, index);
            String keyInfo = Stringer.fromParens(pairInfo, 0);
            Stringable key = Stringer.fromString(keyInfo);
            String valueInfo = Stringer.fromParens(pairInfo, keyInfo.length() + 2);
            Stringable value = Stringer.fromString(valueInfo);
            put((K) key, (V) value);
            index += pairInfo.length() + 2;
        }
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
