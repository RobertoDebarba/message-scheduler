CREATE TABLE message_recipient (
	id uuid NOT NULL,
	message_type int NULL,
	recipient varchar(255) NOT NULL,
	CONSTRAINT message_recipient_pkey PRIMARY KEY (id)
);

CREATE TABLE message_scheduling (
	id uuid NOT NULL,
	deleted_time timestamp NULL,
	message varchar(255) NOT NULL,
	send_time timestamp NOT NULL,
	sent_time timestamp NULL,
	update_time timestamp NULL,
	CONSTRAINT message_scheduling_pkey PRIMARY KEY (id)
);

CREATE TABLE schedulling_recipient_messages (
	recipients_id uuid NOT NULL,
	schedulling_id uuid NOT NULL,
	CONSTRAINT message_scheduling_id_fk FOREIGN KEY (recipients_id) REFERENCES message_scheduling(id),
	CONSTRAINT message_recipient_fk_id FOREIGN KEY (schedulling_id) REFERENCES message_recipient(id)
);