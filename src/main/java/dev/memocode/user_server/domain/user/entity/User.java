package dev.memocode.user_server.domain.user.entity;

import dev.memocode.user_server.domain.base.entity.AggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true, deleted_at = NOW() WHERE id = ?")
@SQLRestriction("is_deleted = false")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class User extends AggregateRoot {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "nickname")
    private String nickname;
}
