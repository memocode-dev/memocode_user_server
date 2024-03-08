package dev.memocode.user_server.domain.outbox.repository;

import dev.memocode.user_server.domain.outbox.entity.OutBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutBoxRepository extends JpaRepository<OutBox, UUID> {
}
