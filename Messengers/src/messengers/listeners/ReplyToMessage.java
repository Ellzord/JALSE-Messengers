package messengers.listeners;

import jalse.entities.Entity;
import jalse.listeners.AttributeAdapter;
import jalse.listeners.AttributeEvent;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import messengers.Messengers;
import messengers.actions.SendMessage;

public class ReplyToMessage extends AttributeAdapter<UUID> {

    @Override
    public void attributeAdded(final AttributeEvent<UUID> event) {
	final UUID sender = event.getValue();
	final Entity message = (Entity) event.getContainer();
	final Entity recipient = (Entity) message.getContainer();
	recipient.scheduleForActor(new SendMessage(sender), Messengers.randomWait(), TimeUnit.MILLISECONDS);
    }
}
