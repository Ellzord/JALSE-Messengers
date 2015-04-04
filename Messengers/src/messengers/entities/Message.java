package messengers.entities;

import jalse.entities.Entity;
import jalse.entities.annotations.GetAttribute;

import java.util.UUID;

public interface Message extends Entity {

    @GetAttribute("from")
    UUID getFrom();

    @GetAttribute("text")
    String getText();
}
