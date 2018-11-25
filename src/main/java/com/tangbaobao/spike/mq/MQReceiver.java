package com.tangbaobao.spike.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.tangbaobao.spike.mq.MQConfig.*;

/**
 * @author tangxuejun
 * @version 2018-11-25 16:02
 */
@Slf4j
@Service
public class MQReceiver {
    private final AmqpTemplate amqpTemplate;

    @Autowired
    public MQReceiver(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @RabbitListener(queues = QUEUE)
    public void receive(String message) {
        log.info("receive message:" + message);
    }

    @RabbitListener(queues = SPIKE_QUEUE)
    public void spikeReceive(String message) {
        log.info("receive message:" + message);
        JSON.parseObject(message, SpikeMessage.class);
    }

    @RabbitListener(queues = TOPIC_QUEUE_1)
    public void receiveTopic1(String message) {
        log.info("topic queue1 message:" + message);
    }

    @RabbitListener(queues = TOPIC_QUEUE_2)
    public void receiveTopic2(String message) {
        log.info("topic queue2 message:" + message);
    }

    @RabbitListener(queues = HEADER_QUEUE)
    public void receiveHeader(byte[] message) {
        log.info("header queue2 message:" + Arrays.toString(message));

    }

}
