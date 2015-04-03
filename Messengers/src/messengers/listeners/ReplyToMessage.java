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
	// Sender ID
	final UUID sender = event.getValue();
	// The attribute container (message)
	final Entity message = (Entity) event.getContainer();
	// The entity container (messenger)
	final Entity recipient = (Entity) message.getContainer();
	// Send a message back
	recipient.scheduleForActor(new SendMessage(sender), Messengers.randomWait(), TimeUnit.MILLISECONDS);
    }
}
