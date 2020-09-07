package br.com.robertodebarba.messagescheduler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @RequestMapping("/")
    public String greeting() {
        return "Message Scheduler application is running!";
    }

}