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

public class Messengers {

    public static final int MESSENGERS = 100;

    public static final int SIMULATION_DURATION = 10;

    public static final int MIN_WAIT = 200;

    public static final int MAX_WAIT = 10000;

    public static int randomWait() {

	return ThreadLocalRandom.current().nextInt(MAX_WAIT - MIN_WAIT) + MIN_WAIT;
    }

    public static void main(String[] args) throws InterruptedException {

	JALSE jalse = JALSEBuilder.createSingleThreadedJALSE(10);
	List<UUID> entityIDs = new ArrayList<>();

	System.out.println("Creating messengers..");

	for (int i = 0; i < MESSENGERS; i++) {

	    Messenger m = jalse.newEntity(Messenger.class);
	    entityIDs.add(m.getID());

	    System.out.println(String.format("New Messenger created: %s", m.getID()));

	    m.addEntityListener(Listeners.createAttributeListenerSupplier(ReplyToMessage::new));
	}

	System.out.println("Sending the first messages..");

	Random r = ThreadLocalRandom.current();

	jalse.streamEntities().forEach(e -> {

	    UUID recipient = entityIDs.get(r.nextInt(entityIDs.size()));

	    System.out.println(String.format("Matching %s -> %s", e.getID(), recipient));

	    e.scheduleAction(new SendMessage(recipient), randomWait(), TimeUnit.MILLISECONDS);
	});

	System.out.println("Ticking the engine..");

	jalse.tick();

	Thread.sleep(TimeUnit.SECONDS.toMillis(SIMULATION_DURATION));

	System.out.println("Stopping the engine..");

	jalse.stop();

	long totalMessages = Entities.walkEntities(jalse).filter(e -> e.isMarkedAsType(Message.class)).count();
	System.out.println(String.format("A total of %d messages were sent", totalMessages));
    }
}
