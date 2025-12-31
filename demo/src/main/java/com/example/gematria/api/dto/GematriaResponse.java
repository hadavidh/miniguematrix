package com.example.gematria.api.dto;

import java.util.List;

public record GematriaResponse(
        String input,
        List<GematriaResult> results
) {}
