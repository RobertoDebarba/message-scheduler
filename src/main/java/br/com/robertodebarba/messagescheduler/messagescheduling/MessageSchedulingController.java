package br.com.robertodebarba.messagescheduler.messagescheduling;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.robertodebarba.messagescheduler.entity.MessageSchedulingEntity;

@RestController
@RequestMapping(value = "/messagescheduling", consumes = MediaType.APPLICATION_JSON_VALUE)
class MessageSchedulingController {

    @Autowired
    private MessageSchedulingRepository repository;

    @Autowired
    private MessageSchedulingConverter converter;

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        final Optional<ResponseEntity<MessageSchedulingDTO>> response = repository. //
                findById(UUID.fromString(id)). //
                map(m -> ResponseEntity.ok(converter.toDTO(m)));

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

        final MessageSchedulingEntity save = repository.save(converter.toEntity(dto));
        dto.setId(save.getId());

        return ResponseEntity.ok(dto);
    }

}
