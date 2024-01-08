package com.instream.auth.monolithic.domain.application.dto;

import com.instream.auth.monolithic.domain.user.dto.UserDto;
import com.instream.auth.monolithic.domain.user.dto.UserDtoWithoutApiKey;
import com.instream.auth.monolithic.util.enums.ApplicationType;

import java.util.UUID;

public record ApplicationDto(
        UUID applicationId,

        ApplicationType type,

        String apiKey,

        UserDtoWithoutApiKey creator
) {
}
