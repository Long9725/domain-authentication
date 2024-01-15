package com.instream.auth.monolithic.domain.user.dto;

import com.instream.auth.monolithic.util.attributeConverter.DbStatusAttributeConverter;
import com.instream.auth.monolithic.util.enums.DbStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserJpaDto(
        UUID id,

        String account,

        String password,

        String nickname,

        DbStatus dbStatus,

        String apiKey,

        LocalDateTime createdAt
) {
}
