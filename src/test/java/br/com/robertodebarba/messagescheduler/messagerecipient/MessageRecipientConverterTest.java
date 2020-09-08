package br.com.robertodebarba.messagescheduler.messagerecipient;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.robertodebarba.messagescheduler.entity.MessageRecipientEntity;
import br.com.robertodebarba.messagescheduler.entity.MessageType;
import br.com.robertodebarba.messagescheduler.messagescheduling.MessageSchedulingDTO;

@SpringBootTest
class MessageRecipientConverterTest {

    @Autowired
    private MessageRecipientConverter converter;

    @Test
    void toEntity() {
        MessageSchedulingDTO schedulingDTO1 = new MessageSchedulingDTO();
        schedulingDTO1.setId(UUID.randomUUID());

        MessageRecipientDTO dto = new MessageRecipientDTO();
        dto.setId(UUID.randomUUID());
        dto.setMessageType(MessageType.EMAIL);
        dto.setRecipient("roberto.debarba@gmail.com");
        dto.setMessageSchedulings(Collections.singletonList(schedulingDTO1));

        MessageRecipientEntity result = converter.toEntity(dto);

        Assertions.assertNull(result.getId());
        Assertions.assertEquals(dto.getMessageType(), result.getMessageType());
        Assertions.assertEquals(dto.getRecipient(), result.getRecipient());
        Assertions.assertNull(result.getMessageSchedulings());
    }
}