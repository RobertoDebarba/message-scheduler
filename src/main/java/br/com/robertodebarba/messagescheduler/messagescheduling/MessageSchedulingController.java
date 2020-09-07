package br.com.robertodebarba.messagescheduler.messagescheduling;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.robertodebarba.messagescheduler.entity.MessageRecipientEntity;
import br.com.robertodebarba.messagescheduler.entity.MessageSchedulingEntity;

@RestController
@RequestMapping(value = "/messagescheduling", consumes = MediaType.APPLICATION_JSON_VALUE)
class MessageSchedulingController {

    @Autowired
    private MessageSchedulingRepository messageSchedulingRepository;

    @RequestMapping
    public List<MessageSchedulingEntity> getAll() {
        return messageSchedulingRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> post(@RequestBody final MessageSchedulingDTO dto) {
        if (dto == null || dto.getRecipients() == null || dto.getRecipients().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"recipients\" is required");
        }

        final MessageSchedulingEntity messageSchedulingEntity = new MessageSchedulingEntity();
        messageSchedulingEntity.setSendTime(dto.getSendTime());
        messageSchedulingEntity.setUpdateTime(LocalDateTime.now());
        messageSchedulingEntity.setMessage(dto.getMessage());
        messageSchedulingEntity.setRecipients(dto.getRecipients().stream().map(r -> {
            MessageRecipientEntity messageRecipientEntity = new MessageRecipientEntity();
            messageRecipientEntity.setId(r);
            return messageRecipientEntity;
        }).collect(Collectors.toList()));

        final MessageSchedulingEntity save = messageSchedulingRepository.save(messageSchedulingEntity);
        dto.setId(save.getId());

        return ResponseEntity.ok(dto);
    }

}
