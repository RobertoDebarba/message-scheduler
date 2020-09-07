package br.com.robertodebarba.messagescheduler.messagerecipient;

import java.util.List;
import java.util.UUID;

import br.com.robertodebarba.messagescheduler.entity.MessageType;
import br.com.robertodebarba.messagescheduler.messagescheduling.MessageSchedulingDTO;
import lombok.Data;

@Data
public class MessageRecipientDTO {

    private UUID id;
    private String recipient;
    private MessageType messageType;
    private List<MessageSchedulingDTO> messageSchedulings;

}
