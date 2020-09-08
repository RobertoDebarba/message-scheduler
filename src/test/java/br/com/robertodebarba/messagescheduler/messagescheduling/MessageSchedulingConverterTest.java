package br.com.robertodebarba.messagescheduler.messagescheduling;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.robertodebarba.messagescheduler.entity.MessageRecipientEntity;
import br.com.robertodebarba.messagescheduler.entity.MessageSchedulingEntity;
import br.com.robertodebarba.messagescheduler.messagerecipient.MessageRecipientConverter;
import br.com.robertodebarba.messagescheduler.messagerecipient.MessageRecipientDTO;

@SpringBootTest
class MessageSchedulingConverterTest {

    @Autowired
    private MessageSchedulingConverter converter;

    @MockBean
    private MessageRecipientConverter recipientConverterMock;

    @Test
    void toDTO() {
        MessageRecipientEntity recipientEntity1 = new MessageRecipientEntity();
        MessageRecipientEntity recipientEntity2 = new MessageRecipientEntity();

        MessageRecipientDTO recipientDTO1 = new MessageRecipientDTO();
        MessageRecipientDTO recipientDTO2 = new MessageRecipientDTO();

        Mockito.when(recipientConverterMock.toDTO(recipientEntity1)).thenReturn(recipientDTO1);
        Mockito.when(recipientConverterMock.toDTO(recipientEntity2)).thenReturn(recipientDTO2);

        MessageSchedulingEntity entity = new MessageSchedulingEntity();
        entity.setId(UUID.randomUUID());
        entity.setMessage("message");
        entity.setSendTime(LocalDateTime.now());
        entity.setSentTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        entity.setDeletedTime(LocalDateTime.now());
        entity.setRecipients(Arrays.asList(recipientEntity1, recipientEntity2));

        MessageSchedulingDTO result = converter.toDTO(entity);

        Assertions.assertEquals(entity.getId(), result.getId());
        Assertions.assertEquals(entity.getMessage(), result.getMessage());
        Assertions.assertEquals(entity.getSendTime(), result.getSendTime());
        Assertions.assertEquals(entity.getSentTime(), result.getSentTime());
        Assertions.assertEquals(2, result.getRecipients().size());
        Assertions.assertEquals(recipientDTO1, result.getRecipients().get(0));
        Assertions.assertEquals(recipientDTO2, result.getRecipients().get(1));
    }

    @Test
    void toEntity() {
        MessageRecipientDTO recipientDTO1 = new MessageRecipientDTO();
        recipientDTO1.setId(UUID.randomUUID());
        MessageRecipientDTO recipientDTO2 = new MessageRecipientDTO();
        recipientDTO2.setId(UUID.randomUUID());

        MessageSchedulingDTO dto = new MessageSchedulingDTO();
        dto.setId(UUID.randomUUID());
        dto.setMessage("message");
        dto.setSendTime(LocalDateTime.now());
        dto.setSentTime(LocalDateTime.now());
        dto.setRecipients(Arrays.asList(recipientDTO1, recipientDTO2));

        MessageSchedulingEntity result = converter.toEntity(dto);

        Assertions.assertNull(result.getId());
        Assertions.assertEquals(dto.getMessage(), result.getMessage());
        Assertions.assertEquals(dto.getSendTime(), result.getSendTime());
        Assertions.assertEquals(dto.getSentTime(), result.getSentTime());
        Assertions.assertNull(result.getDeletedTime());
        Assertions.assertEquals(2, result.getRecipients().size());
        Assertions.assertEquals(recipientDTO1.getId(), result.getRecipients().get(0).getId());
        Assertions.assertEquals(recipientDTO2.getId(), result.getRecipients().get(1).getId());
    }
}