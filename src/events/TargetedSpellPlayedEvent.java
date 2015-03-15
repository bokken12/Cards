package events;

public class TargetedSpellPlayedEvent<T> extends SpellPlayedEvent {
	T target;
}
