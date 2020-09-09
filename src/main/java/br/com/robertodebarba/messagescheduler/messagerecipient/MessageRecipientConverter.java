package br.com.robertodebarba.messagescheduler.messagerecipient;

import org.springframework.stereotype.Service;

import br.com.robertodebarba.messagescheduler.entity.MessageRecipientEntity;

/**
 * Realiza a conversão dos objetos de entidade {@link MessageRecipientEntity} para DTO.
 *
 * As classes de entidade são transversais na aplicação, porém devem ser contidas.
 * Os contratos das APIs devem ser definidos via classes DTO visando a transferência de dados
 * mais especializado e principalmente melhor manutenibilidade, onde a alteração de uma entidade
 * não impacta em todo o sistema e principalmente na assinatura das APIs, facilitando o versionamento.
 */
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
