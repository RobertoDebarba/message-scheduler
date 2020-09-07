package br.com.robertodebarba.messagescheduler.messagerecipient;

import java.util.List;
import java.util.UUID;

import br.com.robertodebarba.messagescheduler.entity.MessageSchedulingEntity;
import br.com.robertodebarba.messagescheduler.entity.MessageType;
import lombok.Data;

@Data
class MessageRecipientDTO {

    private UUID id;
    private String recipient;
    private MessageType messageType;
    private List<MessageSchedulingEntity> messageSchedulings;

}
