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

@Entity
@Table(name = "message_scheduling")
@Getter
@Setter
public class MessageSchedulingEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime sendTime;

    @Column
    private LocalDateTime sentTime;

    @Column
    private LocalDateTime updateTime;

    @Column
    private LocalDateTime deletedTime;

    @ManyToMany
    @JoinTable(name = "schedulling_recipient_messages", joinColumns = { @JoinColumn(name = "recipients_id") }, inverseJoinColumns = { @JoinColumn(name = "schedulling_id") })
    private List<MessageRecipientEntity> recipients;

    @Column(nullable = false, length = 255)
    private String message;

}
