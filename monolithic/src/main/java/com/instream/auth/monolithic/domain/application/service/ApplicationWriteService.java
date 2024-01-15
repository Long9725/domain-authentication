package com.instream.auth.monolithic.domain.application.service;


import com.instream.auth.monolithic.domain.application.dto.ApplicationDto;
import com.instream.auth.monolithic.domain.application.dto.CreateApplicationCommand;
import com.instream.auth.monolithic.domain.application.entity.Application;
import com.instream.auth.monolithic.domain.application.repository.ApplicationRepository;
import com.instream.auth.monolithic.domain.user.dto.UserDto;
import com.instream.auth.monolithic.domain.user.dto.UserDtoWithoutApiKey;
import com.instream.auth.monolithic.domain.user.dto.UserJpaDto;
import com.instream.auth.monolithic.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ApplicationWriteService {
    private final ApplicationRepository applicationRepository;

    public ApplicationDto save(UserJpaDto user, CreateApplicationCommand command) {
        Application application = Application.builder()
                .type(command.applicationType())
                .user(user)
                .build();

        applicationRepository.save(application);
        UserDtoWithoutApiKey userDto = new UserDtoWithoutApiKey(user.id(), user.account(), user.nickname());
        return new ApplicationDto(application.getId(), application.getType(), application.getApiKey(), userDto);
    }

    public ApplicationDto resetApiKey(UUID applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow();

        application.resetApiKey();;
        applicationRepository.save(application);

        return new ApplicationDto(application.getId(), application.getType(), application.getApiKey(), null);
    }
}
