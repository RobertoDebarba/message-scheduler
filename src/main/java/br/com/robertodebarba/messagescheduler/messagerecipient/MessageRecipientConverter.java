package br.com.robertodebarba.messagescheduler.messagerecipient;

import org.springframework.stereotype.Service;

import br.com.robertodebarba.messagescheduler.entity.MessageRecipientEntity;

@Service
public class MessageRecipientConverter {

    public MessageRecipientEntity toEntity(MessageRecipientDTO dto) {
        final MessageRecipientEntity entity = new MessageRecipientEntity();
        entity.setRecipient(dto.getRecipient());
        entity.setMessageType(dto.getMessageType());

        return entity;
    }

    public MessageRecipientDTO toDTO(MessageRecipientEntity entity) {
        final MessageRecipientDTO recipientDTO = new MessageRecipientDTO();
        recipientDTO.setId(entity.getId());
        recipientDTO.setRecipient(entity.getRecipient());
        recipientDTO.setMessageType(entity.getMessageType());

        return recipientDTO;
    }

}
