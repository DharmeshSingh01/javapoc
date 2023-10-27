package com.example.emp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.azure.messaging.servicebus.*;
import com.azure.spring.messaging.servicebus.core.ServiceBusTemplate;


@Service
public class Producer {
	
	
	/*@Autowired
	KafkaTemplate<String, String> kafkaTemplate ;
	
	public void sendMsgToTopic(String message) {
		
		kafkaTemplate.send("codeDecode_Topic",message);*/
		
		static String connectionString="Endpoint=sb://javapocservicebus.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=ooHPpwNYIlyYLPQ6WqPRCrDn6Hx87Fwf8+ASbEfFPVo=" ;
	     static String queueName="queue1";
	     static String subName = "mysub";
	     
	         
	     static String cst="Endpoint=sb://spring-poc.servicebus.windows.net/;SharedAccessKeyName=p3;SharedAccessKey=IcD4X9ssXRTIqN0jwhQYIPEwfpVwLfiwk+ASbKMI9vc=;EntityPath=mytopic";
	     static String topicname="mytopic";
	     public void sendMessage( String message) {
	     	ServiceBusSenderClient sb= new ServiceBusClientBuilder().connectionString(connectionString).
	     			sender().queueName(queueName).buildClient();
	     	
	     	sb.sendMessage(new ServiceBusMessage(message));
	     	 System.out.print("Message sent");
	     }
		
	}

