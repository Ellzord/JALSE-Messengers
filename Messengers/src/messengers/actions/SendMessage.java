package messengers.actions;

import jalse.actions.Action;
import jalse.actions.ActionContext;
import jalse.entities.Entity;
import jalse.entities.EntityContainer;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import messengers.attributes.From;
import messengers.attributes.Text;
import messengers.entities.Message;
import messengers.entities.Messenger;

public class SendMessage implements Action<Entity> {

    private static final List<String> WORDS = Arrays.asList("Hello", "friend", "I", "am", "talking", "to", "you");

    private static String createText() {
	final StringBuilder text = new StringBuilder();
	final Random r = ThreadLocalRandom.current();

	for (int i = 0; i < 5; i++) {
	    text.append(WORDS.get(r.nextInt(WORDS.size()))).append(' ');
	}

	return text.replace(text.length() - 1, text.length(), ".").toString();
    }

    private final UUID to;

    public SendMessage(final UUID to) {
	this.to = Objects.requireNonNull(to);
    }

    @Override
    public void perform(final ActionContext<Entity> context) {
	final Entity actor = context.getOrNullActor();

	final String text = createText();
	System.out.println(String.format("%s -> %s: %s", actor.getID(), to, text));

	final EntityContainer container = actor.getOrNullContainer();
	final Messenger recipient = container.getOrNullEntityAsType(to, Messenger.class);

	final Message m = recipient.newMessage();
	m.setText(new Text(text.toString()));
	m.setFrom(new From(actor.getID()));
    }
}
