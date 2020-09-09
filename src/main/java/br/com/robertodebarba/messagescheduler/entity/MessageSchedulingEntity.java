package br.com.robertodebarba.messagescheduler.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * Agendamento de envio de comunicação.
 */
@Entity
@Table(name = "message_scheduling")
@Getter
@Setter
public class MessageSchedulingEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    /**
     * Data e hora de agendamento do envio de uma comunicação.
     */
    @Column(nullable = false)
    private LocalDateTime sendTime;

    /**
     * Data e hora de envio de uma comunicação.
     * O valor nulo representa que o envio ainda não foi realizado.
     */
    @Column
    private LocalDateTime sentTime;

    /**
     * Data e hora e alteração do registro.
     * Caso o registro não tenha sido editado o valor será a data e hora de criação.
     */
    @Column
    private LocalDateTime updateTime;

    /**
     * Data e hora de remoção do registro.
     *
     * Caso o valor seja nulo o registro não foi removido.
     * Caso possua um valor menor ao igual ao atual o registro foi removido.
     *
     * Implementa a exclusão lógica.
     */
    @Column
    private LocalDateTime deletedTime;

    /**
     * Destinatários que a comunicação deve ser enviada.
     */
    @ManyToMany
    @JoinTable(name = "schedulling_recipient_messages", joinColumns = { @JoinColumn(name = "recipients_id") }, inverseJoinColumns = { @JoinColumn(name = "schedulling_id") })
    private List<MessageRecipientEntity> recipients;

    /**
     * Conteúdo da comunicação.
     */
    @Column(nullable = false, length = 255)
    private String message;

}
