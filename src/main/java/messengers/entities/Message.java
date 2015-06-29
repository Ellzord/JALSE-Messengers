package messengers.entities;

import java.util.UUID;

import jalse.entities.Entity;
import jalse.entities.annotations.GetAttribute;

public interface Message extends Entity {

    @GetAttribute
    UUID getFrom();

    @GetAttribute
    String getText();
}
