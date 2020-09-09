package br.com.robertodebarba.messagescheduler.messagescheduling;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.robertodebarba.messagescheduler.messagerecipient.MessageRecipientDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Agendamento de envio de comunicação")
public class MessageSchedulingDTO {

    @ApiModelProperty("ID")
    private UUID id;

    @ApiModelProperty("Data de envio programada")
    private LocalDateTime sendTime;

    @ApiModelProperty("Data de envio realizada. Caso o valor seja nulo a comunicação ainda não foi enviada")
    private LocalDateTime sentTime;

    @ApiModelProperty("Lista de destinatários")
    private List<MessageRecipientDTO> recipients;

    @ApiModelProperty("Mensagem da comunicação")
    private String message;

}
