package com.instream.auth.monolithic.domain.user.entity;

import com.instream.auth.monolithic.util.attributeConverter.DbStatusAttributeConverter;
import com.instream.auth.monolithic.util.enums.DbStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @Column(name = "user_id")
    private UUID id;

    @NotBlank
    @Size(min = 10, max = 20)
    private String account;

    @NotBlank
    @Size(min = 60, max = 60)
    private String password;

    @NotBlank
    @Size(min = 3, max = 20)
    private String nickname;

    @Convert(converter = DbStatusAttributeConverter.class)
    @NotNull
    private DbStatus dbStatus = DbStatus.USE;

    @NotBlank
    @Size(max = 60)
    private String apiKey = String.format("ck_%s", UUID.randomUUID().toString().replace("-", ""));

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";

    private static final int NAME_MIN_LENGTH = 3;

    private static final int NAME_MAX_LENGTH = 10;

    @Builder
    public User(String account, String password, String nickname, DbStatus dbStatus) {
        this.account = account;
        this.password = password;
        this.nickname = nickname;
        this.dbStatus = dbStatus;
    }

    public void changeNickname(String other) {
        validateNickname(other);
        nickname = other;
    }

    private void validateNickname(String nickname) {
        Assert.isTrue(Objects.requireNonNull(nickname).length() >= NAME_MIN_LENGTH, "최소 길이보다 부족합니다.");
        Assert.isTrue(Objects.requireNonNull(nickname).length() <= NAME_MAX_LENGTH, "최대 길이를 초과했습니다.");
    }

    @PrePersist
    private void prePersist() {
        // JPA에서 필드 기본값을 지정하면 ID 값은 충돌이 발생할 수 있음.
        // JPA는 Proxy Object를 만들고 Reflection을 사용해 필드값을 set 하기 때문이다.
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
