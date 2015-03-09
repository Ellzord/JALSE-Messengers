package messengers.listeners;

import jalse.entities.Entity;
import jalse.listeners.AttributeAdapter;
import jalse.listeners.AttributeEvent;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import messengers.Messengers;
import messengers.actions.SendMessage;
import messengers.attributes.From;

public class ReplyToMessage extends AttributeAdapter<From> {

    @Override
    public void attributeAdded(final AttributeEvent<From> event) {
	final UUID sender = event.getAttribute().unwrap();
	final Entity message = (Entity) event.getContainer();
	final Entity recipient = (Entity) message.getContainer().get();
	recipient.scheduleAction(new SendMessage(sender), Messengers.randomWait(), TimeUnit.MILLISECONDS);
    }

}
