package com.tangbaobao.spike.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.tangbaobao.spike.mq.MQConfig.*;

/**
 * @author tangxuejun
 * @version 2018-11-25 15:56
 */
@Service
@Slf4j
public class MQSender {
    private final AmqpTemplate amqpTemplate;

    @Autowired
    public MQSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void send(Object message) {
        log.info("sender:"+message);
        String jsonString = JSON.toJSONString(message);
        amqpTemplate.convertAndSend(QUEUE, jsonString);
    }
    public void sendTopic(Object message) {
        log.info("sender:"+message);
        String jsonString = JSON.toJSONString(message);
        amqpTemplate.convertAndSend(TOPIC_EXCHANGE,"topic.key1", jsonString+"1");
        amqpTemplate.convertAndSend(TOPIC_EXCHANGE,"topic.key2", jsonString+"2");
    }
    public void sendFanout(Object message) {
        log.info("sender:"+message);
        String jsonString = JSON.toJSONString(message);
        amqpTemplate.convertAndSend(FANOUT_EXCHANGE,"", jsonString);
    }

    public void sendHeader(Object message) {
        log.info("sender:" + message);
        String jsonString = JSON.toJSONString(message);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("header1", "value1");
        messageProperties.setHeader("header2", "value2");
        Message obj = new Message(jsonString.getBytes(), messageProperties);
        amqpTemplate.convertAndSend(HEADER_EXCHANGE, "", obj);
    }

    public void sendSpikeMessage(SpikeMessage spikeMessage) {
        log.info("sender:"+spikeMessage);
        String jsonString = JSON.toJSONString(spikeMessage);
        amqpTemplate.convertAndSend(SPIKE_QUEUE, jsonString);
    }
}
