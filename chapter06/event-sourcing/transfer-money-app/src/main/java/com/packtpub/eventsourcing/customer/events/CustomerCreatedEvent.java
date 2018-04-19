package com.packtpub.eventsourcing.customer.events;

import com.packtpub.eventsourcing.events.Event;
import org.json.simple.JSONObject;

public class CustomerCreatedEvent extends Event {

    public static final String NAME = "CUSTOMER_CREATED";

    public CustomerCreatedEvent(){
        super();
    }

    public CustomerCreatedEvent(JSONObject customerData, String commandId, String eventId) {
        super(customerData, commandId, eventId);
    }

    @Override
    public String getEventName() {
        return NAME;
    }
}
