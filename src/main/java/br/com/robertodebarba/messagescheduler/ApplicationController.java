package br.com.robertodebarba.messagescheduler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Aplicação")
@RestController
@RequestMapping
public class ApplicationController {

    @ApiOperation("Apresenta o status de execução da aplicação")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greeting() {
        return "Message Scheduler application is running!";
    }

}