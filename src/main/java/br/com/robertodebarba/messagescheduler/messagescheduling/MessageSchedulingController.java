package br.com.robertodebarba.messagescheduler.messagescheduling;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.robertodebarba.messagescheduler.entity.MessageRecipientEntity;
import br.com.robertodebarba.messagescheduler.entity.MessageSchedulingEntity;
import br.com.robertodebarba.messagescheduler.messagerecipient.MessageRecipientDTO;

@RestController
@RequestMapping(value = "/messagescheduling", consumes = MediaType.APPLICATION_JSON_VALUE)
class MessageSchedulingController {

    @Autowired
    private MessageSchedulingRepository messageSchedulingRepository;

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> getAll(@PathVariable("id") String id) {
        Optional<ResponseEntity<MessageSchedulingDTO>> response = messageSchedulingRepository.findById(UUID.fromString(id)).map(m -> {
            final MessageSchedulingDTO dto = new MessageSchedulingDTO();
            dto.setId(m.getId());
            dto.setMessage(m.getMessage());
            dto.setSendTime(m.getSendTime());
            dto.setSentTime(m.getSentTime());
            dto.setRecipients(m.getRecipients().stream().map(r -> {
                final MessageRecipientDTO recipientDTO = new MessageRecipientDTO();
                recipientDTO.setId(r.getId());
                recipientDTO.setRecipient(r.getRecipient());
                recipientDTO.setMessageType(r.getMessageType());
                return recipientDTO;
            }).collect(Collectors.toList()));

            return ResponseEntity.ok(dto);
        });

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MessageScheduling not found");
        }

        return response.get();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> post(@RequestBody final MessageSchedulingDTO dto) {
        if (dto == null || dto.getRecipients() == null || dto.getRecipients().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"recipients\" is required");
        }

        final MessageSchedulingEntity entity = new MessageSchedulingEntity();
        entity.setSendTime(dto.getSendTime());
        entity.setUpdateTime(LocalDateTime.now());
        entity.setMessage(dto.getMessage());
        entity.setRecipients(dto.getRecipients().stream().map(rDTO -> {
            final MessageRecipientEntity messageRecipientEntity = new MessageRecipientEntity();
            messageRecipientEntity.setId(rDTO.getId());
            return messageRecipientEntity;
        }).collect(Collectors.toList()));

        final MessageSchedulingEntity save = messageSchedulingRepository.save(entity);
        dto.setId(save.getId());

        return ResponseEntity.ok(dto);
    }

}
