package messengers.entities;

import jalse.entities.Entity;
import jalse.entities.annotations.GetEntities;
import jalse.entities.annotations.NewEntity;
import jalse.entities.annotations.StreamEntities;

import java.util.Set;
import java.util.stream.Stream;

public interface Messenger extends Entity {

    @GetEntities
    Set<Message> getMessages();

    @NewEntity
    Message newMessage();

    @StreamEntities
    Stream<Message> streamMessages();
}
