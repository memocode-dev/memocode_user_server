package dev.memocode.user_server.out.outbox.service;

import dev.memocode.user_server.out.outbox.dto.OutBoxCreateDTO;
import dev.memocode.user_server.out.outbox.entity.OutBox;
import dev.memocode.user_server.out.outbox.repository.OutBoxRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class OutBoxService {

    private final OutBoxRepository outBoxRepository;

    public OutBox createOutBox(@Valid OutBoxCreateDTO dto) {
        OutBox outbox = OutBox.builder()
                .id(UUID.randomUUID())
                .aggregateId(dto.getAggregateId())
                .aggregateType(dto.getAggregateType())
                .eventType(dto.getEventType())
                .payload(dto.getPayload().toPrettyString())
                .build();

        OutBox outBox = outBoxRepository.save(outbox);

        log.info("created outbox : {}", outBox);

        return outBox;
    }
}
