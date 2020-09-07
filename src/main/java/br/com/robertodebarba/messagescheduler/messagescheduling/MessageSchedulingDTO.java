package br.com.robertodebarba.messagescheduler.messagescheduling;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
class MessageSchedulingDTO {

    private UUID id;
    private LocalDateTime sendTime;
    private LocalDateTime sentTime;
    private List<UUID> recipients;
    private String message;

}
