package br.com.robertodebarba.messagescheduler.messagerecipient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.robertodebarba.messagescheduler.entity.MessageRecipientEntity;

@RestController
@RequestMapping(value = "/messagerecipient", consumes = MediaType.APPLICATION_JSON_VALUE)
class MessageRecipientController {

    @Autowired
    private MessageRecipientRepository messageRecipientRepository;

    @RequestMapping
    public List<MessageRecipientEntity> getAll() {
        return messageRecipientRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> post(@RequestBody final MessageRecipientDTO dto) {
        final MessageRecipientEntity messageRecipientEntity = new MessageRecipientEntity();
        messageRecipientEntity.setRecipient(dto.getRecipient());
        messageRecipientEntity.setMessageType(dto.getMessageType());
        messageRecipientEntity.setMessageSchedulings(dto.getMessageSchedulings());

        final MessageRecipientEntity save = messageRecipientRepository.save(messageRecipientEntity);
        dto.setId(save.getId());

        return ResponseEntity.ok(dto);
    }

}
