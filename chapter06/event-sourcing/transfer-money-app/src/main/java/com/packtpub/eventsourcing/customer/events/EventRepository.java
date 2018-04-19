package com.packtpub.eventsourcing.customer.events;

import com.packtpub.eventsourcing.events.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}
