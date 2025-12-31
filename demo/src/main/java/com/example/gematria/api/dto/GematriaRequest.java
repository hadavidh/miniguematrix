package com.example.gematria.api.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public record GematriaRequest(
        @NotBlank String text,
        Set<GematriaMethod> methods
) {}
