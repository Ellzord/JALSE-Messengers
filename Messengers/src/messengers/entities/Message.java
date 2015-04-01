package messengers.entities;

import jalse.entities.Entity;
import jalse.entities.annotations.GetAttribute;
import jalse.entities.annotations.SetAttribute;

import java.util.UUID;

public interface Message extends Entity {

    @GetAttribute("from")
    UUID getFrom();

    @GetAttribute("text")
    String getText();

    @SetAttribute("from")
    void setFrom(UUID from);

    @SetAttribute("text")
    void setText(String text);
}
