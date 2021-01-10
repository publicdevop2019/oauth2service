package com.mt.identityaccess.port.adapter.messaging;

import com.mt.common.domain_event.StoredEvent;
import com.mt.identityaccess.application.ApplicationServiceRegistry;
import com.mt.identityaccess.domain.DomainRegistry;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.mt.common.CommonConstant.EXCHANGE_NAME;

@Slf4j
@Component
public class DomainEventMQSubscriber {
    private static final String TASK_QUEUE_NAME = "domain_event_queue";

    static {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
            channel.queueBind(TASK_QUEUE_NAME, EXCHANGE_NAME, "");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                log.debug("received message from mq");
                String s = new String(delivery.getBody());
                StoredEvent o = DomainRegistry.customObjectSerializer().deserialize(s, StoredEvent.class);
                ApplicationServiceRegistry.clientApplicationService().revokeTokenBasedOnChange(o);
                ApplicationServiceRegistry.userApplicationService().revokeTokenBasedOnChange(o);
                ApplicationServiceRegistry.endpointApplicationService().reloadInProxy(o);
            };
            channel.basicConsume(TASK_QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            log.error("error in mq", e);
        }
    }
}
