package messengers.entities;

import java.util.Set;
import java.util.stream.Stream;

import jalse.attributes.AttributeContainer;
import jalse.entities.Entity;
import jalse.entities.annotations.GetEntities;
import jalse.entities.annotations.NewEntity;
import jalse.entities.annotations.StreamEntities;

public interface Messenger extends Entity {

    @GetEntities
    Set<Message> getMessages();

    @NewEntity
    Message newMessage(AttributeContainer contents);

    @StreamEntities
    Stream<Message> streamMessages();
}
