package messengers;

import jalse.JALSE;
import jalse.JALSEBuilder;
import jalse.entities.Entities;
import jalse.listeners.Listeners;

import java.util.ArrayList;
import java.util.List;
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

    public static final long SIMULATION_DURATION = TimeUnit.SECONDS.toMillis(20);

    public static final long MIN_WAIT = 200;

    public static final long MAX_WAIT = 600;

    public static void main(final String[] args) throws InterruptedException {
	final JALSE jalse = JALSEBuilder.buildDefaultJALSE();
	final List<UUID> entityIDs = new ArrayList<>();

	System.out.println("Creating messengers..");

	for (int i = 0; i < MESSENGERS; i++) {
	    final Messenger m = jalse.newEntity(Messenger.class);
	    entityIDs.add(m.getID());
	    System.out.println(String.format("New Messenger created: %s", m.getID()));
	    m.addEntityListener(Listeners.createAttributeListenerSupplier("from", UUID.class, ReplyToMessage::new));
	}

	System.out.println("Sending the first messages..");

	final Random r = ThreadLocalRandom.current();
	jalse.streamEntities().forEach(e -> {
	    final UUID recipient = entityIDs.get(r.nextInt(entityIDs.size()));
	    System.out.println(String.format("Matching %s -> %s", e.getID(), recipient));
	    e.scheduleForActor(new SendMessage(recipient), randomWait(), TimeUnit.MILLISECONDS);
	});

	System.out.println("Ticking the engine..");

	Thread.sleep(SIMULATION_DURATION);
	jalse.stop();

	System.out.println("Stopped the engine..");

	final long totalMessages = Entities.walkEntities(jalse).filter(e -> e.isMarkedAsType(Message.class)).count();
	System.out.println(String.format("A total of %d messages were sent", totalMessages));
    }

    public static long randomWait() {
	return ThreadLocalRandom.current().nextLong(MAX_WAIT - MIN_WAIT) + MIN_WAIT;
    }
}
