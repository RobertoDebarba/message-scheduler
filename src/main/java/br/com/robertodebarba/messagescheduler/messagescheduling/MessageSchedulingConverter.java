package br.com.robertodebarba.messagescheduler.messagescheduling;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.robertodebarba.messagescheduler.entity.MessageRecipientEntity;
import br.com.robertodebarba.messagescheduler.entity.MessageSchedulingEntity;
import br.com.robertodebarba.messagescheduler.messagerecipient.MessageRecipientConverter;

@Service
public class MessageSchedulingConverter {

    @Autowired
    private MessageRecipientConverter recipientConverter;

    public MessageSchedulingDTO toDTO(MessageSchedulingEntity entity) {
        final MessageSchedulingDTO dto = new MessageSchedulingDTO();
        dto.setId(entity.getId());
        dto.setMessage(entity.getMessage());
        dto.setSendTime(entity.getSendTime());
        dto.setSentTime(entity.getSentTime());
        dto.setRecipients(entity.getRecipients().stream().map(e -> recipientConverter.toDTO(e)).collect(Collectors.toList()));

        return dto;
    }

    public MessageSchedulingEntity toEntity(MessageSchedulingDTO dto) {
        final MessageSchedulingEntity entity = new MessageSchedulingEntity();
        entity.setSendTime(dto.getSendTime());
        entity.setSentTime(dto.getSentTime());
        entity.setUpdateTime(LocalDateTime.now());
        entity.setMessage(dto.getMessage());
        entity.setRecipients(dto.getRecipients().stream().map(rDTO -> {
            final MessageRecipientEntity messageRecipientEntity = new MessageRecipientEntity();
            messageRecipientEntity.setId(rDTO.getId());
            return messageRecipientEntity;
        }).collect(Collectors.toList()));

        return entity;
    }

}
