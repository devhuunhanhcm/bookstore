package com.devhuunhan.kafka.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class NotificationEventDto {
    private String channel;
    private String recipient;
    private String templateCode;
    private Map<String, Object> param;
    private String subject;
    private String body;
}
