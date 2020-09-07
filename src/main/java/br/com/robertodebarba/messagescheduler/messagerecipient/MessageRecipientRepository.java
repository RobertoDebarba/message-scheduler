package br.com.robertodebarba.messagescheduler.messagerecipient;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.robertodebarba.messagescheduler.entity.MessageRecipientEntity;

interface MessageRecipientRepository extends JpaRepository<MessageRecipientEntity, UUID> {

}
