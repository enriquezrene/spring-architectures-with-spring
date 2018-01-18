package com.packtpub;

import org.jboss.logging.LogMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
@EnableBinding(Processor.class)
public class CallCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CallCenterApplication.class, args);
    }

    @StreamListener(Processor.INPUT)
    public void enrichLogMessage(Message message) {
        System.out.println("+------------------------------");
        System.out.println(message);
    }
}
