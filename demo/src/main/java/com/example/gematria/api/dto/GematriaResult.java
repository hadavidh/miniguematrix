package com.example.gematria.api.dto;

public record GematriaResult(
        GematriaMethod method,
        String normalizedText,   // lettres hébraïques uniquement, sans niqqud
        String transformedText,  // uniquement pour ATBASH/ALBAM
        int value
) {}
