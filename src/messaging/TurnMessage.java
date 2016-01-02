package messaging;

public class TurnMessage extends Message
{
    public TurnMessage(){
        super();
    }
    public TurnMessage(boolean turn){
        data.add("turn", new StringableBoolean(turn));
    }
    public boolean getTurn(){
        return ((StringableBoolean) data.get("turn")).getBoolean();
    }
}
