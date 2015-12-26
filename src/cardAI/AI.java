package cardAI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JButton;

import abilities.Ability;
import abilities.AbilityRunnable;
import cards.CreatureCard;
import events.GameEvent;

public class AI implements ActionListener
{

    JButton generate = new JButton("Generate Creature");

    HashMap<Integer, ArrayList<AbilityRunnable>> effects = new HashMap<Integer, ArrayList<AbilityRunnable>>();
    ArrayList<Class<? extends GameEvent>> triggers = new ArrayList<Class<? extends GameEvent>>();

    public static void main(String[] args)
    {

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource().equals(generate))
        {
            String c = generateCreature();
        }
    }

    public String generateCreature()
    {
        Random r = new Random();
        int cost = r.nextInt(7) + 1;

        int abilityValue = r.nextInt(cost - 2);

        Ability ability = getAbility(abilityValue);

        return "";
    }

    public Ability getAbility(int value)
    {

        Random r = new Random();
        Class<? extends GameEvent> trigger = triggers.get(r.nextInt(triggers
                .size()));

        AbilityRunnable effect = effects.get(value).get(
                r.nextInt(effects.get(value).size()));

        return new Ability("", "", trigger, effect);
    }
}
