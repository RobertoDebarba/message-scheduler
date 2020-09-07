package br.com.robertodebarba.messagescheduler.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "message_recipient")
@Getter
@Setter
public class MessageRecipientEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private MessageType messageType;

    @ManyToMany(mappedBy = "recipients")
    private List<MessageSchedulingEntity> messageSchedulings;

}
