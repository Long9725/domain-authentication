package com.instream.auth.monolithic.application.usecase;

import com.instream.auth.monolithic.domain.application.dto.ApplicationDto;
import com.instream.auth.monolithic.domain.application.dto.CreateApplicationCommand;
import com.instream.auth.monolithic.domain.application.service.ApplicationWriteService;
import com.instream.auth.monolithic.domain.user.dto.UserJpaDto;
import com.instream.auth.monolithic.domain.user.entity.User;
import com.instream.auth.monolithic.domain.user.service.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CreateApplicationUsecase {
    private final UserReadService userReadService;

    private final ApplicationWriteService applicationWriteService;

    public ApplicationDto execute(UUID userId, CreateApplicationCommand command) {
        UserJpaDto user = userReadService.getJpaUser(userId);
        return applicationWriteService.save(user, command);
    }
}
