package com.packtpub.eventsourcing.events;

import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public abstract class Event {

    @Id
    protected String eventId;
    protected JSONObject eventData;
    protected String commandId;
    protected String eventName;

    public Event() {
        this.eventName = getEventName();
    }

    public Event(JSONObject eventData, String commandId, String eventId) {
        super();
        this.eventData = eventData;
        this.commandId = commandId;
        this.eventId = eventId;
    }

    public JSONObject getEventData() {
        return eventData;
    }

    public void setEventData(JSONObject eventData) {
        this.eventData = eventData;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public abstract String getEventName();
}
