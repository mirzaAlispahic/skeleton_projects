package com.skeleton_project.web_service;


import java.util.concurrent.atomic.AtomicLong;
import com.skeleton_project.web_service.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.MyService;
import service.ServiceProperties;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "World") String name){
        ServiceProperties messageServiceProperties = new ServiceProperties();
        messageServiceProperties.setMessage("Mirza");
        MyService messageService = new MyService(messageServiceProperties);
        return new Greeting(counter.incrementAndGet(),
                String.format(template, messageService.message()));
    }

    @RequestMapping("/greetingFromRoute")
    public Greeting greetingFromRoute(@RequestParam(value="name", defaultValue = "World") String name){
        ServiceProperties messageServiceProperties = new ServiceProperties();
        messageServiceProperties.setMessage(" from the other side.");
        MyService messageService = new MyService(messageServiceProperties);
        return new Greeting(counter.incrementAndGet(),
                String.format(template, messageService.message()));
    }
}
