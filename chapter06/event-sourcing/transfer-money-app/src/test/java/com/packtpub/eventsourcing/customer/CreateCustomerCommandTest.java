package com.packtpub.eventsourcing.customer;

import com.google.gson.JsonObject;
import com.packtpub.eventsourcing.commands.domain.CommandMetadata;
import com.packtpub.eventsourcing.commands.persistence.CommandRepository;
import com.packtpub.eventsourcing.customer.commands.CreateCustomerCommand;
import com.packtpub.eventsourcing.customer.events.Event;
import com.packtpub.eventsourcing.customer.events.CustomerCreatedEvent;
import com.packtpub.eventsourcing.customer.events.EventRepository;
import org.junit.Test;
import org.mockito.Mockito;

public class CreateCustomerCommandTest {

    @Test
    public void whenExecuteThenProduceACustomerCreatedEvent() throws Exception {
        JsonObject createCustomerData = buildCreateCustomerData();
        CommandRepository commandRepository = Mockito.mock(CommandRepository.class);
        EventRepository eventRepository = Mockito.mock(EventRepository.class);
        CreateCustomerCommand createCustomerCommand = new CreateCustomerCommand(createCustomerData, commandRepository, eventRepository);

        createCustomerCommand.execute();

        Mockito.verify(eventRepository, Mockito.times(1)).save(Mockito.any(CustomerCreatedEvent.class));
    }


    @Test
    public void whenExecuteThenProduceAnAccountCreatedEvent() throws Exception {
        JsonObject createCustomerData = buildCreateCustomerData();
        CommandRepository commandRepository = Mockito.mock(CommandRepository.class);
        EventRepository eventRepository = Mockito.mock(EventRepository.class);
        CreateCustomerCommand createCustomerCommand = new CreateCustomerCommand(createCustomerData, commandRepository, eventRepository);

        createCustomerCommand.execute();

        Mockito.verify(eventRepository, Mockito.times(1)).save(Mockito.any(Event.class));
    }

    @Test
    public void whenTheCommandIsExecutedItIsAlsoPersisted() throws Exception {
        JsonObject createCustomerData = buildCreateCustomerData();
        CommandRepository commandRepository = Mockito.mock(CommandRepository.class);
        EventRepository eventRepository = Mockito.mock(EventRepository.class);
        CreateCustomerCommand createCustomerCommand = new CreateCustomerCommand(createCustomerData, commandRepository, eventRepository);

        createCustomerCommand.execute();

        Mockito.verify(commandRepository, Mockito.times(1)).save(Mockito.any(CommandMetadata.class));
    }


    private JsonObject buildCreateCustomerData() {
        JsonObject createCustomerData = new JsonObject();
        createCustomerData.addProperty("eventName", "Rene");
        createCustomerData.addProperty("last_name", "Enriquez");
        createCustomerData.addProperty("account_type", "savings");
        createCustomerData.addProperty("initial_amount", 1000);
        return createCustomerData;
    }
}
