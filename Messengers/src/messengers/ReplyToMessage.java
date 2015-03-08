package messengers;

import jalse.entities.Entity;
import jalse.listeners.AttributeAdapter;
import jalse.listeners.AttributeEvent;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ReplyToMessage extends AttributeAdapter<From> {

    @Override
    public void attributeAdded(AttributeEvent<From> event) {

	UUID sender = event.getAttribute().unwrap();

	Entity message = ((Entity) event.getContainer());

	Messenger recipient = ((Entity) message.getContainer().get()).asType(Messenger.class);

	recipient.scheduleAction(new SendMessage(sender), Messengers.randomWait(), TimeUnit.MILLISECONDS);
    }
}
