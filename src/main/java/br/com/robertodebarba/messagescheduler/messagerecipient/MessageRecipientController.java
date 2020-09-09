package br.com.robertodebarba.messagescheduler.messagerecipient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.robertodebarba.messagescheduler.entity.MessageRecipientEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Destinatário de envio de comunicação")
@RestController
@RequestMapping(value = "/messagerecipient", consumes = MediaType.APPLICATION_JSON_VALUE)
class MessageRecipientController {

    @Autowired
    private MessageRecipientRepository repository;

    @Autowired
    private MessageRecipientConverter converter;

    @ApiOperation("Insere um destinatário de envio de comunicação")
    @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    public ResponseEntity<?> post(@RequestBody final MessageRecipientDTO dto) {
        final MessageRecipientEntity save = repository.save(converter.toEntity(dto));
        dto.setId(save.getId());

        return ResponseEntity.ok(dto);
    }

}
