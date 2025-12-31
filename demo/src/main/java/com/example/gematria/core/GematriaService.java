package com.example.gematria.core;

import com.example.gematria.api.dto.GematriaMethod;
import com.example.gematria.api.dto.GematriaResult;

import java.util.*;

import static com.example.gematria.core.HebrewNormalizer.normalizeFinalForm;

public class GematriaService {

    public List<GematriaResult> compute(String rawText, Set<GematriaMethod> methods) {
        String letters = HebrewNormalizer.keepHebrewLettersOnly(rawText);
        if (letters.isBlank()) {
            throw new IllegalArgumentException("Aucune lettre hébraïque détectée dans le texte.");
        }

        Set<GematriaMethod> chosen = (methods == null || methods.isEmpty())
                ? EnumSet.allOf(GematriaMethod.class)
                : EnumSet.copyOf(methods);

        List<GematriaResult> results = new ArrayList<>();
        for (GematriaMethod m : chosen) {
            results.add(switch (m) {
                case SIMPLE -> computeSimple(letters);
                case SIDURI -> computeSiduri(letters);
                case ATBASH -> computeCipherThenSimple(letters, HebrewMaps.ATBASH, GematriaMethod.ATBASH);
                case ALBAM  -> computeCipherThenSimple(letters, HebrewMaps.ALBAM, GematriaMethod.ALBAM);
            });
        }
        results.sort(Comparator.comparing(r -> r.method().name()));
        return results;
    }

    private GematriaResult computeSimple(String letters) {
        int sum = 0;
        for (int i = 0; i < letters.length(); i++) {
            char ch = normalizeFinalForm(letters.charAt(i));
            Integer v = HebrewMaps.SIMPLE.get(ch);
            if (v == null) throw new IllegalArgumentException("Lettre non supportée: " + ch);
            sum += v;
        }
        return new GematriaResult(GematriaMethod.SIMPLE, letters, null, sum);
    }

    private GematriaResult computeSiduri(String letters) {
        int sum = 0;
        for (int i = 0; i < letters.length(); i++) {
            char ch = normalizeFinalForm(letters.charAt(i));
            Integer v = HebrewMaps.SIDURI.get(ch);
            if (v == null) throw new IllegalArgumentException("Lettre non supportée: " + ch);
            sum += v;
        }
        return new GematriaResult(GematriaMethod.SIDURI, letters, null, sum);
    }

    private GematriaResult computeCipherThenSimple(String letters, Map<Character, Character> cipher, GematriaMethod method) {
        StringBuilder transformed = new StringBuilder();
        int sum = 0;

        for (int i = 0; i < letters.length(); i++) {
            char base = normalizeFinalForm(letters.charAt(i));
            Character out = cipher.get(base);
            if (out == null) throw new IllegalArgumentException("Lettre non chiffrable ("+method+"): " + base);
            transformed.append(out);

            Integer v = HebrewMaps.SIMPLE.get(out);
            if (v == null) throw new IllegalArgumentException("Lettre non supportée après chiffrement: " + out);
            sum += v;
        }

        return new GematriaResult(method, letters, transformed.toString(), sum);
    }
}
