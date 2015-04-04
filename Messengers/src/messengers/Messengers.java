package messengers;

import jalse.JALSE;
import jalse.JALSEBuilder;
import jalse.entities.Entities;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import messengers.actions.SendMessage;
import messengers.entities.Message;
import messengers.entities.Messenger;
import messengers.listeners.ReplyToMessage;

public class Messengers {

    public static final int MESSENGERS = 100;

    public static final long MIN_WAIT = 200;

    public static final long MAX_WAIT = 600;

    public static void main(final String[] args) throws InterruptedException {
	// Create a default parent container
	final JALSE jalse = JALSEBuilder.buildDefaultJALSE();

	System.out.println("Creating messengers..");

	for (int i = 0; i < MESSENGERS; i++) {
	    // Create entity marked as a messenger
	    final Messenger m = jalse.newEntity(Messenger.class);
	    // Reply to messages
	    m.addEntityListener(new ReplyToMessage());

	    System.out.println(String.format("New Messenger created: %s", m.getID()));
	}

	System.out.println("Sending the first messages..");

	final Random r = ThreadLocalRandom.current();
	jalse.streamEntities().forEach(e -> {
	    UUID recipient;
	    do {
		// Random recipient
		recipient = jalse.streamEntities().skip(r.nextInt(MESSENGERS)).findFirst().get().getID();
	    } while (e.getID().equals(recipient));

	    System.out.println(String.format("Matching %s -> %s", e.getID(), recipient));
	    // Send the first message
	    e.scheduleForActor(new SendMessage(recipient), randomWait(), TimeUnit.MILLISECONDS);
	});

	// Sleep and let the messengers communicate
	Thread.sleep(TimeUnit.SECONDS.toMillis(10));
	// Cancel actions
	jalse.stop();

	System.out.println("Stopped the engine..");

	// Find all messages sent
	final long totalMessages = Entities.walkEntities(jalse).filter(e -> e.isMarkedAsType(Message.class)).count();
	System.out.println(String.format("A total of %d messages were sent", totalMessages));
    }

    public static long randomWait() {
	return ThreadLocalRandom.current().nextLong(MAX_WAIT - MIN_WAIT) + MIN_WAIT;
    }
}
