package clientStuff;

import java.util.List;

import cards.Card;
import uselessSubclasses.Lane;

public class GameState
{
    public Lane[] getLanes() throws Exception{
        throw new Exception("Not yet implemented");
    }
    public Lane getLane(int i) throws Exception{
        return getLanes()[i];
    }
    public List<Card> getHandCards() throws Exception{
        throw new Exception("Not yet implemented");
    }
}
