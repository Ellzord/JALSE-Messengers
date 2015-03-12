package messengers.entities;

import jalse.entities.Entity;

import java.util.Optional;

import messengers.attributes.From;
import messengers.attributes.Text;

public interface Message extends Entity {

    Optional<From> getFrom();

    Optional<Text> getText();

    Optional<From> setFrom(From from);

    Optional<Text> setText(Text text);
}
