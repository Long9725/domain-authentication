package com.instream.auth.monolithic.domain.application.entity;


import com.instream.auth.monolithic.domain.user.dto.UserDto;
import com.instream.auth.monolithic.domain.user.dto.UserJpaDto;
import com.instream.auth.monolithic.domain.user.entity.User;
import com.instream.auth.monolithic.util.attributeConverter.ApplicationTypeAttributeConverter;
import com.instream.auth.monolithic.util.enums.ApplicationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AutoCloseable.class)
public class Application {
    @Id
    @Column(name = "application_id")
    private UUID id;

    @NotBlank
    @Size(max = 60)
    private String apiKey = generateApiKey();

    @NotNull
    @Convert(converter = ApplicationTypeAttributeConverter.class)
    private ApplicationType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 이 부분에서 Application과 User는 서로 domain이 다르다. 근데 usecase layer 같은 상황에서는 dto를 받을텐데...
    // UserDto를 받아서 User로 바꾸고, Applicaiton을 save 했을 때 User entity에는 영향이 안가나?
    @Builder
    public Application(UUID id, ApplicationType type, UserJpaDto user) {
        this.id = id;
        this.type = type;
        this.user = new User(
                user.id(),
                user.account(),
                user.password(),
                user.nickname(),
                user.dbStatus(),
                user.apiKey(),
                user.createdAt()
        );
    }

    public void resetApiKey() {
        apiKey = generateApiKey();
    }

    private  String generateApiKey() {
        return String.format("ck_%s", UUID.randomUUID().toString().replace("-", ""));
    }

    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
