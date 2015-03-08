package messengers;

import jalse.entities.Entity;

import java.util.Set;
import java.util.stream.Stream;

public interface Messenger extends Entity {

    Set<Message> getMessages();
    
    Stream<Message> streamMessages();
}
