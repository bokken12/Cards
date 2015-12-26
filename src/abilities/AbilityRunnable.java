package abilities;

import java.util.ArrayList;
import java.util.HashMap;

import clientStuff.Board;
import events.GameEvent;

public abstract class AbilityRunnable
{
    //public HashMap<String, Object> args = new HashMap<String, Object>();

    public AbilityRunnable()
    {
    }

    public abstract void run(GameEvent event, Board board, Object... args);
}
