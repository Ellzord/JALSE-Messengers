package messengers.entities;

import jalse.entities.Entity;
import jalse.entities.annotations.GetAttribute;
import jalse.entities.annotations.SetAttribute;

import java.util.Optional;

import messengers.attributes.From;
import messengers.attributes.Text;

public interface Message extends Entity {

    @GetAttribute
    Optional<From> getFrom();

    @GetAttribute
    Optional<Text> getText();

    @SetAttribute
    Optional<From> setFrom(From from);

    @SetAttribute
    Optional<Text> setText(Text text);
}
