package messengers.entities;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import jalse.entities.Entity;
import jalse.entities.EntityEvent;
import jalse.entities.EntityListener;
import messengers.Messengers;
import messengers.actions.SendMessage;

public class ReplyToMessage implements EntityListener {

    @Override
    public void entityCreated(final EntityEvent event) {
	// The entity the event was for (message)
	final Message message = event.getEntity().asType(Message.class);
	// The message container (messenger)
	final Entity recipient = (Entity) message.getContainer();
	// The sender of the received message
	final UUID sender = message.getFrom();
	// Send a message back
	recipient.scheduleForActor(new SendMessage(sender), Messengers.randomWait(), TimeUnit.MILLISECONDS);
    }
}
