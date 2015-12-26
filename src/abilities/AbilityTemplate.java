package abilities;

import clientStuff.Board;
import events.GameEvent;

public class AbilityTemplate
{
    private String name;
    private String description;
    private Class<? extends GameEvent> activation;
    private AbilityRunnable ability;
    private int priority;
    
    public AbilityTemplate(String name, String description,
            Class<? extends GameEvent> activation, AbilityRunnable ability,
            int priority)
    {
        super();
        this.name = name;
        this.description = description;
        this.activation = activation;
        this.ability = ability;
        this.priority = priority;
    }

    public Ability createInstance(Board board, Object... args){
        return new Ability(name, description, activation, ability, priority, board, args);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Class<? extends GameEvent> getActivation()
    {
        return activation;
    }

    public void setActivation(Class<? extends GameEvent> activation)
    {
        this.activation = activation;
    }

    public AbilityRunnable getAbility()
    {
        return ability;
    }

    public void setAbility(AbilityRunnable ability)
    {
        this.ability = ability;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }
    
}
