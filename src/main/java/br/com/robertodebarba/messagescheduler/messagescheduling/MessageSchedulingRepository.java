package br.com.robertodebarba.messagescheduler.messagescheduling;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.robertodebarba.messagescheduler.entity.MessageSchedulingEntity;

interface MessageSchedulingRepository extends JpaRepository<MessageSchedulingEntity, UUID> {

}
