package com.instream.auth.monolithic.application.integration;

import com.instream.auth.monolithic.domain.application.dto.ApplicationDto;
import com.instream.auth.monolithic.domain.application.dto.CreateApplicationCommand;
import com.instream.auth.monolithic.domain.application.service.ApplicationWriteService;
import com.instream.auth.monolithic.domain.user.entity.User;
import com.instream.auth.monolithic.domain.user.service.UserEntityReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CreateApplicationIntegration {
    private final UserEntityReadService userEntityReadService;

    private final ApplicationWriteService applicationWriteService;

    public ApplicationDto execute(UUID userId, CreateApplicationCommand command) {
        User user = userEntityReadService.getUser(userId);
        return applicationWriteService.save(user, command);
    }
}
