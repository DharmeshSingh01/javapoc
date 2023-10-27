package com.example.emp.controller;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.emp.domain.Student;
import com.example.emp.domain.User;
import com.example.emp.service.StudentService;
import com.example.emp.service.UserService;
import com.example.emp.service.Consumer;
import com.example.emp.service.Producer;


/*import com.azure.messaging.servicebus.*;*/

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
import com.azure.storage.file.share.*;

@Controller

public class StudentController {
	
	 @Autowired
     private StudentService service;
     @Autowired
     private UserService userservice;
     
     @Autowired
		Producer producer;
     @Autowired
     Consumer consumer;
     
        @GetMapping("/index")
        public String viewHomePage(Model model) {
            List<Student> liststudent = service.listAll();
            model.addAttribute("liststudent", liststudent);
            System.out.print("Get / "); 
           
            return "index";
        }
        @GetMapping("/new")
        public String add(Model model) {
            model.addAttribute("student", new Student());
            return "new";
        }
        @PostMapping("/save")
        public String saveStudent(@ModelAttribute("student") Student std) {
            service.save(std);
            //postMessage("Add new user "+std.getStudentname());
            producer.sendMessage(std.getStudentname());
           // postMessage("Add new user name"+std.getStudentname());
            System.out.print("Add new user name"+std.getStudentname());
            
            try {
				consumer.receiveMessage();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return "redirect:/index";
        }
        @RequestMapping("/edit/{id}")
        public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id){
            ModelAndView mav = new ModelAndView("new");
            Student std = service.get(id);
            mav.addObject("student", std);
            return mav;
            
        }
        @RequestMapping("/delete/{id}")
        public String deletestudent(@PathVariable int id) {
            service.delete(id);
            return "redirect:/";
        }
        
        @GetMapping("/producerMsg")
    	public String getMessageFromClient(@RequestParam String message) {
    		
    	// producer.sendMessage(message);
    		 return "login";
    	}
              
        @GetMapping("/Login")
        public String login(Model model) {
        	 model.addAttribute("user", new User());
        	
        	 System.out.print("Login Page.................."); 
          return "login";
        }
        @SuppressWarnings("null")
		@PostMapping("/loginChk")
        public String loginChk(@ModelAttribute User user) throws IOException {
        	 List<User> listuser = userservice.listAll();
  
        	 String loginUser=user.getUsername();
        	 String loginPassword=user.getPassword();
        	 System.out.print("Login User.................."+loginUser+loginPassword); 
        	 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        	    String str
        	        = " User "+loginUser+" has trying to login on "+timeStamp;
        	
        	    PrintWriter out = null;
        	    BufferedWriter bw = null;
        	    FileWriter fw = null;
        	    try{
        	    	//fw = new FileWriter("https://javapoclogsa.file.core.windows.net/springappfileshare/loginUser.txt", true);
        	       fw = new FileWriter("C:\\Users\\KafkaApp\\log\\loginUser.txt", true);
        	    	//fw = new FileWriter("https://javapoclogsa.blob.core.windows.net/logfile/loginUser.txt?sp=r&st=2023-10-18T08:13:12Z&se=2023-10-18T16:13:12Z&spr=https&sv=2022-11-02&sr=b&sig=yYMzuTY3rmUTlOqqVXcBT5mqvIRykvejFThqnXEBQTQ%3D", true);
        	       bw = new BufferedWriter(fw);
        	       out = new PrintWriter(bw);
        	       out.println(str);
        	    }
        	    catch( IOException e ){
        	       // File writing/opening failed at some stage.
        	    }
        	    finally{
        	       try{
        	          if( out != null ){
        	             out.close(); // Will close bw and fw too
        	          }
        	          else if( bw != null ){
        	             bw.close(); // Will close fw too
        	          }
        	          else if( fw != null ){
        	             fw.close();
        	          }
        	          else{
        	             // Oh boy did it fail hard! :3
        	          }
        	       }
        	       catch( IOException e ){
        	          // Closing the file writers failed for some obscure reason
        	       }
        	    }
        	 
        	 for (User usr: listuser) {
        		if(loginUser.equalsIgnoreCase(usr.getUsername())) {
        		    	if(loginPassword.equalsIgnoreCase(usr.getPassword())) {
        		    		return "redirect:/index";
        		    	}
        		    }
        		}
            return "redirect:/";
           // return "redirect:Login?error";
        }
      
        //static String connectionString = "Endpoint=sb://javapocservicebus.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=ooHPpwNYIlyYLPQ6WqPRCrDn6Hx87Fwf8+ASbEfFPVo=";
        //static String queueName = "queue1";
       
        /*private JmsTemplate jmsTemplate;
        public String postMessage(@RequestParam String message) 
        {
        	
        	 ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
        	            .connectionString(connectionString)
        	            .sender()
        	            .queueName(queueName)
        	            .buildClient();

        	    // send one message to the queue
        	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        	    senderClient.sendMessage(new ServiceBusMessage("Info: "+timeStamp+" "+message));
        	    System.out.println("Sent a single message to the queue: " + queueName);
         
         return message;
        }*/
    
     public static String connectStr="DefaultEndpointsProtocol=https;AccountName=javapoclogsa;AccountKey=VvPymLdbrV2YeTbppjWBnyVGppFJpt/ubTnKmpeb3I30vMXYiXMYJfN6VMDkAQVZ460+6DF70yc0+AStG1HQuQ==;EndpointSuffix=core.windows.net";
        
     javapoclogsa
     public static Boolean createFileShare(String connectStr, String shareName)
        {
            try
            {
                ShareClient shareClient = new ShareClientBuilder()
                    .connectionString(connectStr).shareName(shareName)
                    .buildClient();

                shareClient.create();
                return true;
            }
            catch (Exception e)
            {
                System.out.println("createFileShare exception: " + e.getMessage());
                return false;
            }
        }
    
  	}

        
