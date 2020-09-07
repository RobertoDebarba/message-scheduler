package br.com.robertodebarba.messagescheduler.messagescheduling;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.robertodebarba.messagescheduler.messagerecipient.MessageRecipientDTO;
import lombok.Data;

@Data
public class MessageSchedulingDTO {

    private UUID id;
    private LocalDateTime sendTime;
    private LocalDateTime sentTime;
    private List<MessageRecipientDTO> recipients;
    private String message;

}
