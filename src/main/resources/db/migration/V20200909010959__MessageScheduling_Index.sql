CREATE INDEX IF NOT EXISTS message_scheduling_idx_sent_deleted ON message_scheduling (sent_time NULLS FIRST, deleted_time NULLS FIRST);

CREATE INDEX IF NOT EXISTS message_scheduling_idx_send_time ON message_scheduling (send_time);