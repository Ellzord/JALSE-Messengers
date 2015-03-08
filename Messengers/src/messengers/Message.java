package messengers;

import jalse.entities.Entity;

import java.util.Optional;

public interface Message extends Entity {

    Optional<From> getFrom();

    Optional<Text> getText();

    Optional<From> setFrom(From from);

    Optional<Text> setText(Text text);
}
