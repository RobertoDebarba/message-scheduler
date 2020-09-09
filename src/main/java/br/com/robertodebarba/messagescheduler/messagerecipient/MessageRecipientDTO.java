package br.com.robertodebarba.messagescheduler.messagerecipient;

import java.util.List;
import java.util.UUID;

import br.com.robertodebarba.messagescheduler.entity.MessageType;
import br.com.robertodebarba.messagescheduler.messagescheduling.MessageSchedulingDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Destinatário do envio de comunicação")
public class MessageRecipientDTO {

    @ApiModelProperty("ID")
    private UUID id;

    @ApiModelProperty("Endereço do destinatário")
    private String recipient;

    @ApiModelProperty("Tipo de endereço do destinatário")
    private MessageType messageType;

    @ApiModelProperty("Lista de agendamentos de envio de comunicação")
    private List<MessageSchedulingDTO> messageSchedulings;

}
