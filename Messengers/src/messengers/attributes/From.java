package messengers.attributes;

import jalse.attributes.NonAttributeWrapper;

import java.util.UUID;

public class From extends NonAttributeWrapper<UUID> {

    public From(final UUID id) {
	super(id);
    }
}
