package com.april.restingrabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@Service
public class CitiesMessageSender
{
    private final CitiesTemplate rt;
    private final CitiesRepository rabbitrepos;

    public CitiesMessageSender(CitiesTemplate rt, CitiesRepository rabbitrepos)
    {
        this.rt = rt;
        this.citiesrepos = citiesrepos;
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendMessage()
    {
        ArrayList<Cities> cities = new ArrayList<Cities>();
        // var cities = new ArrayList<Cities>();

        cities.addAll(citiesrepos.findAll());

        for (Cities r: cities)
        {
            int rand = new Random().nextInt(10);
            boolean randBool = new Random().nextBoolean();
            final CitiesMessage message = new CitiesMessage(r.toString(), rand, randBool);
            if (rand <= 5)
            {
                rt.convertAndSend(RestingcitiesApplication.QUEUE_NAME_HIGH, message);
            }
            else
            {
                rt.convertAndSend(RestingcitiesApplication.QUEUE_NAME_LOW, message);
            }
        }
    }
}
