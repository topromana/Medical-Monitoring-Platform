This system is composed of several modules:
- A web application for managing the patients
- An automated pillbox notifying the patient when he needs to take his medication using RPC
- Sensors monitoring the patient's activity to detect if something bad happened, using RabbitMq as a message oriented middleware
- The frontend part, developed using ReactJs

The web application 
- Was developed using Spring technology, and PostgreSql for the database. For the security part of the project, the main components  we used are: WebSecurityConfigurerAdapter to provide Http security configurations, the annotation EnableGlobalMethodSecurity (to provide @PreAuthorize and @PostAuthorize, so as to make sure that only users that have certain roles can perform certain tasks), and PasswordEncoder, in order to make sure that the password for each account will be encoded, and not just displayed in the database as plain text, visible to anyone. It also contains the consumer side enabling the communnication using RabbitMq, and the server side of the PillboxGUI.



The PillBox
- Contains the client side of the Pill Dispenser App. It contains a desktop user interface which displays the current time, and the medications that are supposed to be taken at that certain hour. Every 24 hours, at a predefined time the client downloads the medication plans for the next 24 hours using the Http invoker RPC technology.

![image](https://user-images.githubusercontent.com/45190679/110201725-5f47ad00-7e6d-11eb-963b-eed573d1a104.png)
  
![image](https://user-images.githubusercontent.com/45190679/110201800-aa61c000-7e6d-11eb-9912-08da5841082d.png)
  

The RabbitMQ producer side
- The producer reads the data from activity.txt, then using a file reader and a scanner it reads the data from the file line by line, transforms the start_time and end_time into the desired miliseconds format, then puts all the data together in the JSON format in order to finally publish that message on the channel.

![image](https://user-images.githubusercontent.com/45190679/110201781-9a49e080-7e6d-11eb-9b62-6f80713c07f5.png)

![image](https://user-images.githubusercontent.com/45190679/110201788-9fa72b00-7e6d-11eb-86aa-cc61021ac134.png)
