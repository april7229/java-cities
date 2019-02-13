package com.april.restingrabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CitiesMessageListener
{
    @CitiesListener(queues = RestingcitiesApplication.QUEUE_NAME_LOW)
    public void receiveMessage(CitiesMessage rm)
    {
        log.info("Received Message: {} ", rm.toString());
    }
}
