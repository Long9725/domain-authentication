package com.instream.auth.monolithic.domain.application.dto;

import com.instream.auth.monolithic.util.enums.ApplicationType;

public record CreateApplicationCommand(
        ApplicationType applicationType
) {
}
