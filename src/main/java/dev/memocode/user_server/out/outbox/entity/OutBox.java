package dev.memocode.user_server.out.outbox.entity;

import dev.memocode.user_server.domain.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@Table(name = "outbox")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class OutBox extends BaseEntity {

    @Column(name = "aggregate_id")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID aggregateId;

    @Column(name = "aggregate_type")
    @Enumerated(STRING)
    private AggregateType aggregateType;

    @Column(name = "payload")
    private String payload;

    @Column(name = "event_type")
    @Enumerated(STRING)
    private EventType eventType;
}
