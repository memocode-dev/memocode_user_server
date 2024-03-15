package dev.memocode.user_server.out.outbox.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.memocode.user_server.domain.user.event.UserCreatedEvent;
import dev.memocode.user_server.out.outbox.dto.OutBoxCreateDTO;
import dev.memocode.user_server.out.outbox.entity.AggregateType;
import dev.memocode.user_server.out.outbox.entity.EventType;
import dev.memocode.user_server.out.outbox.entity.OutBox;
import dev.memocode.user_server.out.outbox.service.OutBoxService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
                .aggregateId(event.getUserId())
                .aggregateType(AggregateType.USER)
                .eventType(EventType.USER_CREATED)
                .payload(objectMapper.valueToTree(event))
                .build();

        OutBox outBox = outboxService.createOutBox(outBoxCreateDTO);
        log.info("UserCreatedEvent to outBox and outBox saved : {}", outBox);
    }
}
