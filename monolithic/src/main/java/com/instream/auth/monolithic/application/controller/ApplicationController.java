package com.instream.auth.monolithic.application.controller;

import com.instream.auth.monolithic.application.integration.CreateApplicationIntegration;
import com.instream.auth.monolithic.domain.application.dto.ApplicationDto;
import com.instream.auth.monolithic.domain.application.dto.CreateApplicationCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private final CreateApplicationIntegration createApplicationIntegration;

    @PostMapping("/{userId}")
    public ApplicationDto createApplication(@PathVariable UUID userId, @RequestBody CreateApplicationCommand command) {
        return createApplicationIntegration.execute(userId, command);
    }
}
