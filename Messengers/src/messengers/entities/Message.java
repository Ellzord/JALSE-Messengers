package messengers.entities;

import jalse.entities.Entity;
import jalse.entities.annotations.GetAttribute;
import jalse.entities.annotations.SetAttribute;

import java.util.Optional;
import java.util.UUID;

public interface Message extends Entity {

    @GetAttribute("from")
    Optional<UUID> getFrom();

    @GetAttribute("text")
    Optional<String> getText();

    @SetAttribute("from")
    Optional<UUID> setFrom(UUID from);

    @SetAttribute("text")
    Optional<String> setText(String text);
}
