package com.instream.auth.monolithic.application.controller;

import com.instream.auth.monolithic.application.usecase.CreateApplicationUsecase;
import com.instream.auth.monolithic.domain.application.dto.ApplicationDto;
import com.instream.auth.monolithic.domain.application.dto.CreateApplicationCommand;
import com.instream.auth.monolithic.domain.application.service.ApplicationWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private final CreateApplicationUsecase createApplicationUsecase;

    private final ApplicationWriteService applicationWriteService;

    @PostMapping("/{userId}")
    public ApplicationDto createApplication(@PathVariable UUID userId, @RequestBody CreateApplicationCommand command) {
        return createApplicationUsecase.execute(userId, command);
    }

    @PatchMapping("/{applicationId}")
    public ApplicationDto createApplication(@PathVariable UUID applicationId) {
        return applicationWriteService.resetApiKey(applicationId);
    }
}
