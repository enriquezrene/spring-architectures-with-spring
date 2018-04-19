package com.packtpub.eventsourcing.customer.commands;

import com.packtpub.eventsourcing.commands.Command;
import com.packtpub.eventsourcing.commands.domain.CommandMetadata;
import com.packtpub.eventsourcing.commands.persistence.CommandRepository;
import com.packtpub.eventsourcing.customer.events.AccountCreatedEvent;
import com.packtpub.eventsourcing.customer.events.CustomerCreatedEvent;
import com.packtpub.eventsourcing.customer.events.EventRepository;
import com.packtpub.eventsourcing.events.EventProcessor;
import org.json.simple.JSONObject;

import java.util.UUID;

public class CreateCustomerCommand extends Command {

    public CreateCustomerCommand(JSONObject data, CommandRepository commandRepository, EventRepository eventRepository, EventProcessor eventProcessor) {
        super(data, commandRepository, eventRepository, eventProcessor);
    }

    @Override
    public void execute() {

        String commandId = UUID.randomUUID().toString();
        CommandMetadata commandMetadata = new CommandMetadata(commandId, getName(), this.data);
        commandRepository.save(commandMetadata);

        JSONObject customerData = new JSONObject();
        String customerUuid = UUID.randomUUID().toString();
        customerData.put("name", this.data.get("name"));
        customerData.put("last_name", this.data.get("last_name"));
        customerData.put("customer_id", customerUuid);
        com.packtpub.eventsourcing.events.Event customerCreatedEvent = new CustomerCreatedEvent(customerData, commandId, UUID.randomUUID().toString());
        eventRepository.save(customerCreatedEvent);
        eventProcessor.process(customerCreatedEvent);

        JSONObject accountData = new JSONObject();
        accountData.put("balance", this.data.get("initial_amount"));
        accountData.put("account_type", this.data.get("account_type"));
        accountData.put("customer_id", customerUuid);
        accountData.put("account_id", UUID.randomUUID().toString());
        com.packtpub.eventsourcing.events.Event accountCreatedEvent = new AccountCreatedEvent(accountData, commandId, UUID.randomUUID().toString());
        eventRepository.save(accountCreatedEvent);
        eventProcessor.process(accountCreatedEvent);

    }

    @Override
    public String getName() {
        return "CREATE_CUSTOMER_COMMAND";
    }
}
