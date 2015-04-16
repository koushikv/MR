package com.cloudwick.team15.Kafka;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by kaushik on 3/29/15.
 */
public class KafkaProducer {
    public static  void main(String args[])
    {
    Properties props = new Properties();
    props.put("metadata.broker.list", "172.17.0.158:9093,172.17.0.158:9094");
    props.put("serializer.class", "kafka.serializer.StringEncoder");
    props.put("request.required.acks", "1");
    ProducerConfig config = new ProducerConfig(props);
    Producer producer = new Producer(config);
    String topic = "mytopic";
    for (int i=1; i<=1000; i++)
    {
        String msg = " This is message " + i ;
        KeyedMessage data = new KeyedMessage(topic, String.valueOf(i), msg);
        producer.send(data);
    }
    producer.close();
    }
}
