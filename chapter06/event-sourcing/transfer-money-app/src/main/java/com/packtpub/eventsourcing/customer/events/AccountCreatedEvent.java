package com.packtpub.eventsourcing.customer.events;

import com.packtpub.eventsourcing.events.Event;
import org.json.simple.JSONObject;

public class AccountCreatedEvent extends Event {

    public static final String NAME = "ACCOUNT_CREATED";

    public AccountCreatedEvent() {
        super();
    }

    public AccountCreatedEvent(JSONObject customerData, String commandId, String eventId) {
        super(customerData, commandId, eventId);
    }

    @Override
    public String getEventName() {
        return NAME;
    }
}
