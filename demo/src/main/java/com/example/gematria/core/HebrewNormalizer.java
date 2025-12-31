package com.example.gematria.core;

import java.util.Set;

public final class HebrewNormalizer {
    private HebrewNormalizer() {}

    // Niqqud + te'amim (approx large coverage in Hebrew block)
    private static final String HEBREW_MARKS_REGEX = "[\\u0591-\\u05BD\\u05BF\\u05C1-\\u05C2\\u05C4-\\u05C5\\u05C7]";
    private static final Set<Character> FINAL_FORMS = Set.of('ך','ם','ן','ף','ץ');

    public static String stripMarks(String input) {
        if (input == null) return "";
        return input.replaceAll(HEBREW_MARKS_REGEX, "");
    }

    /** Conserve uniquement les lettres hébraïques (inclut finales). */
    public static String keepHebrewLettersOnly(String input) {
        String s = stripMarks(input);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isHebrewLetter(ch)) sb.append(ch);
        }
        return sb.toString();
    }

    public static boolean isHebrewLetter(char ch) {
        // א..ת plus finales
        return (ch >= 'א' && ch <= 'ת') || FINAL_FORMS.contains(ch);
    }

    /** Convertit les finales en formes “normales” pour le calcul. */
    public static char normalizeFinalForm(char ch) {
        return switch (ch) {
            case 'ך' -> 'כ';
            case 'ם' -> 'מ';
            case 'ן' -> 'נ';
            case 'ף' -> 'פ';
            case 'ץ' -> 'צ';
            default -> ch;
        };
    }
}
