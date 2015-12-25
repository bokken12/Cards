package clientStuff;

import javax.swing.JPanel;

public class BoardDisplay extends JPanel
{
    private Board displaying;
    public BoardDisplay(Board toDisplay){
        displaying = toDisplay;
    }
    public Board getDisplaying()
    {
        return displaying;
    }
    public void setDisplaying(Board displaying)
    {
        this.displaying = displaying;
    }
    
}
