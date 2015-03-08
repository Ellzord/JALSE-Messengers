package messengers;

import jalse.actions.Action;
import jalse.actions.TickInfo;
import jalse.entities.Entity;
import jalse.entities.EntityContainer;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class SendMessage implements Action<Entity> {

    private static final List<String> WORDS = Arrays.asList("Hello", "friend", "I", "am", "talking", "to", "you");

    private static String createText() {

	StringBuilder text = new StringBuilder();
	Random r = ThreadLocalRandom.current();

	for (int i = 0; i < 5; i++) {

	    text.append(WORDS.get(r.nextInt(WORDS.size()))).append(' ');
	}

	return text.replace(text.length() - 1, text.length(), ".").toString();
    }

    private final UUID to;

    public SendMessage(UUID to) {

	this.to = Objects.requireNonNull(to);
    }

    @Override
    public void perform(Entity actor, TickInfo tick) {

	String text = createText();

	System.out.println(String.format("%s -> %s: %s", actor.getID(), to, text));

	EntityContainer container = actor.getContainer().get();
	Messenger recipient = container.getEntityAsType(to, Messenger.class).get();

	Message m = recipient.newEntity(Message.class);
	m.setText(new Text(text.toString()));
	m.setFrom(new From(actor.getID()));
    }
}
