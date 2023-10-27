package com.example.emp.service;

import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.azure.spring.messaging.servicebus.implementation.core.annotation.ServiceBusListener;


@Service
public class Consumer {
	
	
	/*@KafkaListener(topics="codeDecode_Topic" , groupId="codeDecode_Group")
	public void listToTopic(String receivedMessage) {
		
		System.out.println("The message is received ::::::"+receivedMessage);
		Path path
        = Paths.get("C:\\Users\\KafkaApp\\log\\newuser.txt");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    String str
        = "New User "+receivedMessage+" has created on "+timeStamp;
    System.out.println("File Store message ::::::"+str);
    try {
       
        Files.writeString(path, str,
                          StandardCharsets.UTF_8);
    }
    catch (IOException ex) {
       
        System.out.print("Invalid Path");
    }
	}
*/
	
	static String connectionString="Endpoint=sb://javapocservicebus.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=ooHPpwNYIlyYLPQ6WqPRCrDn6Hx87Fwf8+ASbEfFPVo=" ;
	public static final String QUEUE_NAME = "queue1";
	
	static String subName = "mysub";
    
    
    static String cst="Endpoint=sb://spring-poc.servicebus.windows.net/;SharedAccessKeyName=p3;SharedAccessKey=IcD4X9ssXRTIqN0jwhQYIPEwfpVwLfiwk+ASbKMI9vc=;EntityPath=mytopic";
    static String topicname="mytopic";

	public static void receiveMessage() throws InterruptedException{
    
    CountDownLatch countdownLatch = new CountDownLatch(1);
    
    ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
            .connectionString(connectionString)
                        .processor()
            .queueName(QUEUE_NAME)
            //.subscriptionName(subName)
            .processMessage(Consumer::processMessage)
            .processError(context -> processError(context, countdownLatch))
            .buildProcessorClient();

    System.out.println("Starting the processor");
    processorClient.start();

    TimeUnit.SECONDS.sleep(10);
    System.out.println("Stopping and closing the processor");
    processorClient.close();
	}
	
	public static void processMessage(ServiceBusReceivedMessageContext context) {
	    ServiceBusReceivedMessage message = context.getMessage();
	    System.out.printf("inside process");
	    System.out.printf("Processing message. Session: %s, Sequence #: %s. Contents: %s%n", message.getMessageId(),
	        message.getSequenceNumber(), message.getBody());
	    //System.out.println("The message is received ::::::"+receivedMessage);
	    
	    Path  path = Paths.get("C:\\Users\\KafkaApp\\log\\newuser.txt"); 
	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		  String str = "New User "+message.getBody()+" has created on "+timeStamp;
		  System.out.println("File Store message ::::::"+str); try {
		  
		  Files.writeString(path, str, StandardCharsets.UTF_8); } catch (IOException
		  ex) {
		 
		  System.out.print("Invalid Path"); } 
	}
	
	public static void processError(ServiceBusErrorContext context, CountDownLatch countdownLatch) {
	    System.out.printf("Error when receiving messages from namespace: '%s'. Entity: '%s'%n",
	        context.getFullyQualifiedNamespace(), context.getEntityPath());
	}

}

