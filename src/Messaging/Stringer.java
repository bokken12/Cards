package Messaging;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Stringer
{
    private static final String separator = "::";
    private Map<String, Stringable> data;
    private Class<? extends Message> type;
    public Stringer(Class<? extends Message> type){
        data = new HashMap<String, Stringable>();
        this.type = type;
    }
    public Stringer(String str){
        StringTokenizer st = new StringTokenizer(str, separator);
        data = new HashMap<String, Stringable>();
        try
        {
            type = (Class<? extends Message>) Class.class.forName(st.nextToken());
            while(st.hasMoreTokens()){
                String name = st.nextToken();
                String className = st.nextToken();
                Class<? extends Stringable> ableType = (Class<? extends Stringable>) Class.class.forName(className);
                String args = st.nextToken();
                Stringable able = ableType.newInstance();
                able.fromString(args);
                data.put(name, able);
            }
        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void add(String name, Stringable s){
        data.put(name, s);
    }
    public Stringable get(String key){
        return data.get(key);
    }
    @Override
    public String toString(){
        String str = type.getName();
        for(String key: data.keySet()){
            str += separator;
            str += key;
            str += separator;
            Stringable out = data.get(key);
            str += out.getClass().getName();
            str += separator;
            str += out.toString();
        }
        return str;
    }
    public Class<? extends Message> getType(){
        return type;
    }
}
