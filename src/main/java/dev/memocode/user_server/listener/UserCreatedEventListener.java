package dev.memocode.user_server.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.memocode.user_server.domain.outbox.dto.OutBoxCreateDTO;
import dev.memocode.user_server.domain.outbox.entity.OutBox;
import dev.memocode.user_server.domain.outbox.service.OutBoxService;
import dev.memocode.user_server.usecase.event.UserCreatedEvent;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import static dev.memocode.user_server.domain.outbox.entity.AggregateType.USER;
import static dev.memocode.user_server.domain.outbox.entity.EventType.USER_CREATED;

@Slf4j
@Component
@Validated
@Transactional
@RequiredArgsConstructor
public class UserCreatedEventListener {

    private final OutBoxService outboxService;
    private final ObjectMapper objectMapper;

    @EventListener
    public void onUserCreated(@Valid UserCreatedEvent event) {

        OutBoxCreateDTO outBoxCreateDTO = OutBoxCreateDTO.builder()
                .aggregateId(event.getId())
                .aggregateType(USER)
                .eventType(USER_CREATED)
                .payload(objectMapper.valueToTree(event))
                .build();

        OutBox outBox = outboxService.createOutBox(outBoxCreateDTO);
        log.info("UserCreatedEvent to outBox and outBox saved : {}", outBox);
    }
}
