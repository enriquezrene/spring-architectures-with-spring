package com.packtpub.eventsourcing.commands.domain;

import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class CommandMetadata {

    @Id
    private String commandId;
    private String commandName;
    private JSONObject commandData;

    public CommandMetadata(String commandId, String commandName, JSONObject commandData) {
        this.commandId = commandId;
        this.commandName = commandName;
        this.commandData = commandData;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void setCommandData(JSONObject commandData) {
        this.commandData = commandData;
    }

    public String getCommandId() {
        return commandId;
    }

    public String getCommandName() {
        return commandName;
    }

    public JSONObject getCommandData() {
        return commandData;
    }
}
