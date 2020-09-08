package br.com.robertodebarba.messagescheduler.messagescheduling;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.robertodebarba.messagescheduler.entity.MessageSchedulingEntity;
import br.com.robertodebarba.messagescheduler.messagerecipient.MessageRecipientDTO;

@SpringBootTest
class MessageSchedulingControllerTest {

    @Autowired
    private MessageSchedulingController controller;

    @MockBean
    private MessageSchedulingRepository repositoryMock;

    @MockBean
    private MessageSchedulingConverter converterMock;

    @Test
    void get() {
        UUID uuid = UUID.randomUUID();
        MessageSchedulingEntity entity = new MessageSchedulingEntity();
        MessageSchedulingDTO dto = new MessageSchedulingDTO();

        Mockito.when(repositoryMock.findById(uuid)).thenReturn(Optional.of(entity));
        Mockito.when(converterMock.toDTO(entity)).thenReturn(dto);

        ResponseEntity<?> result = controller.get(uuid.toString());

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(dto, result.getBody());
    }

    @Test
    void get_notFound() {
        UUID uuid = UUID.randomUUID();

        Mockito.when(repositoryMock.findById(uuid)).thenReturn(Optional.empty());

        ResponseEntity<?> result = controller.get(uuid.toString());

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertEquals("MessageScheduling not found", result.getBody());
    }

    @Test
    void post() {
        UUID uuid = UUID.randomUUID();
        MessageSchedulingEntity entity = new MessageSchedulingEntity();
        MessageSchedulingEntity entityPersisted = new MessageSchedulingEntity();
        entityPersisted.setId(uuid);
        MessageSchedulingDTO dto = new MessageSchedulingDTO();
        dto.setRecipients(Collections.singletonList(new MessageRecipientDTO()));

        Mockito.when(converterMock.toEntity(dto)).thenReturn(entity);
        Mockito.when(repositoryMock.save(entity)).thenReturn(entityPersisted);

        ResponseEntity<?> result = controller.post(dto);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(dto, result.getBody());
        Assertions.assertEquals(dto.getId(), ((MessageSchedulingDTO) result.getBody()).getId());
    }

    @Test
    void post_nullDTO() {
        ResponseEntity<?> result = controller.post(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertEquals("\"recipients\" is required", result.getBody());
    }

    @Test
    void post_nullRecipients() {
        MessageSchedulingDTO dto = new MessageSchedulingDTO();
        dto.setRecipients(null);

        ResponseEntity<?> result = controller.post(dto);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertEquals("\"recipients\" is required", result.getBody());
    }

    @Test
    void post_emptyRecipients() {
        MessageSchedulingDTO dto = new MessageSchedulingDTO();
        dto.setRecipients(Collections.emptyList());

        ResponseEntity<?> result = controller.post(dto);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertEquals("\"recipients\" is required", result.getBody());
    }

    @Test
    void delete() {
        UUID uuid = UUID.randomUUID();
        MessageSchedulingEntity entity = new MessageSchedulingEntity();

        Mockito.when(repositoryMock.findById(uuid)).thenReturn(Optional.of(entity));

        ResponseEntity<?> result = controller.delete(uuid.toString());

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Mockito.verify(repositoryMock).delete(entity);
    }

    @Test
    void delete_notFound() {
        UUID uuid = UUID.randomUUID();

        Mockito.when(repositoryMock.findById(uuid)).thenReturn(Optional.empty());

        ResponseEntity<?> result = controller.delete(uuid.toString());

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertEquals("MessageScheduling not found", result.getBody());
    }

}