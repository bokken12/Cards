package abilities;

import clientStuff.Board;
import clientStuff.Content;
import events.EventBus;
import events.GameEvent;
import events.GameListener;

public class Ability implements GameListener
{
    private String name;
    private String description;
    private Class<? extends GameEvent> activation;
    private AbilityRunnable a;
    private int priority;
    private Board board;
    private Object[] args;

    public Ability(String name, String desc,
            Class<? extends GameEvent> activation, AbilityRunnable a, int priority, Board board, Object... args)
    {

        this.a = a;
        this.name = name;
        description = desc;
        this.activation = activation;
        this.priority = priority;
        this.board = board;
        this.args = args;
    }

    public void passEvent(GameEvent event, Board board)
    {
        a.run(event, board);
    }

    public void RegisterListeners()
    {
        board.getBus().addGameListener(priority, activation, this);
    }

    public String getText()
    {
        return description;
    }

	/* (non-Javadoc)
	 * @see events.GameListener#passEvent(events.GameEvent)
	 */
	@Override
	public void passEvent(GameEvent event) {
		passEvent(event, board);
	}

}
