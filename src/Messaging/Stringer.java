package messaging;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Stringer
{
    //private static final String separator = "::";
    private Map<String, Stringable> data;
    private Class<? extends Message> type;
    public Stringer(Class<? extends Message> type){
        data = new HashMap<String, Stringable>();
        this.type = type;
    }
    //str = "ExampleMessage::(intToSend::StringableInteger::(4))"
    public Stringer(String str){
        data = new HashMap<String, Stringable>();
        int cs = str.indexOf(", ");
        try
        {
            type = (Class<? extends Message>) Class.forName(str.substring(0, cs));
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        for(int index = cs + 2; index < str.length(); index++){
            int parens = str.indexOf('(', index);
            if(parens == -1){
                break;
            }
            String dataName = str.substring(index, parens);
            String stringableInfo = fromParens(str, parens);
            System.out.println("StringableInfo is: " + stringableInfo);
            Stringable stringable = fromString(stringableInfo);
            data.put(dataName, stringable);
            index = parens + stringableInfo.length() + 3;
        }
        /*StringTokenizer st = new StringTokenizer(str, separator);
        data = new HashMap<String, Stringable>();
        try
        {
            type = (Class<? extends Message>) Class.forName(st.nextToken());
            while(st.hasMoreTokens()){
                String name = st.nextToken();
                String className = st.nextToken();
                Class<? extends Stringable> ableType = (Class<? extends Stringable>) Class.forName(className);
                String args = st.nextToken();
                Stringable able = ableType.newInstance();
                able.fromString(args)	;
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
        }*/
        for(String string: data.keySet()){
            System.out.println("I have a key: " + string);
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
        /*for(String key: data.keySet()){
            str += separator;
            str += key;
            str += separator;
            Stringable out = data.get(key);
            str += out.getClass().getName();
            str += separator;
            str += out.toString();
        }*/
        for(String key: data.keySet()){
            str += ", ";
            str += key;
            str += "(";
            Stringable out = data.get(key);
            str += printStringable(out);
            str += ")";
        }
        return str;
    }
    public Class<? extends Message> getType(){
        return type;
    }
    public static String fromParens(String str, int index){
        System.out.println("index originally was: " + index);
        int opens = 0;
        int closes = 0;
        int current = index;
        while(true){
            char c = str.charAt(current);
            if(c == '('){
                opens++;
            } else if(c == ')'){
                closes++;
            }
            current++;
            if(opens == closes){
                break;
            }
        }
        System.out.println("index is: " + index + ", and current is: " + current);
        return str.substring(index + 1, current - 1);
    }
    public static Stringable fromString(String str){
        if(str.equals("null")){
            return null;
        }
        int parens = str.indexOf('(');
        String className = str.substring(0, parens);
        String info = fromParens(str, parens);
        System.out.println("Classname is: " + className);
        return (Stringable) new MetaClass(className).createInstance(info);
    }
    public static String printStringable(Stringable str){
        if(str == null){
            return "null";
        }
        String string = str.getClass().getName();
        string += "(";
        string += str.toString();
        string += ")";
        return string;
    }
}
