package com.example.gematria.core;

import java.util.Map;

public final class HebrewMaps {
    private HebrewMaps() {}

    // Mispar Hechrechi (simple)
    public static final Map<Character, Integer> SIMPLE = Map.ofEntries(
            Map.entry('א',1), Map.entry('ב',2), Map.entry('ג',3), Map.entry('ד',4), Map.entry('ה',5),
            Map.entry('ו',6), Map.entry('ז',7), Map.entry('ח',8), Map.entry('ט',9), Map.entry('י',10),
            Map.entry('כ',20), Map.entry('ל',30), Map.entry('מ',40), Map.entry('נ',50), Map.entry('ס',60),
            Map.entry('ע',70), Map.entry('פ',80), Map.entry('צ',90), Map.entry('ק',100), Map.entry('ר',200),
            Map.entry('ש',300), Map.entry('ת',400)
    );

    // Mispar Siduri (position)
    public static final Map<Character, Integer> SIDURI = Map.ofEntries(
            Map.entry('א',1), Map.entry('ב',2), Map.entry('ג',3), Map.entry('ד',4), Map.entry('ה',5),
            Map.entry('ו',6), Map.entry('ז',7), Map.entry('ח',8), Map.entry('ט',9), Map.entry('י',10),
            Map.entry('כ',11), Map.entry('ל',12), Map.entry('מ',13), Map.entry('נ',14), Map.entry('ס',15),
            Map.entry('ע',16), Map.entry('פ',17), Map.entry('צ',18), Map.entry('ק',19), Map.entry('ר',20),
            Map.entry('ש',21), Map.entry('ת',22)
    );

    // Atbash pairs (normalize finals before mapping)
    public static final Map<Character, Character> ATBASH = Map.ofEntries(
            Map.entry('א','ת'), Map.entry('ת','א'),
            Map.entry('ב','ש'), Map.entry('ש','ב'),
            Map.entry('ג','ר'), Map.entry('ר','ג'),
            Map.entry('ד','ק'), Map.entry('ק','ד'),
            Map.entry('ה','צ'), Map.entry('צ','ה'),
            Map.entry('ו','פ'), Map.entry('פ','ו'),
            Map.entry('ז','ע'), Map.entry('ע','ז'),
            Map.entry('ח','ס'), Map.entry('ס','ח'),
            Map.entry('ט','נ'), Map.entry('נ','ט'),
            Map.entry('י','מ'), Map.entry('מ','י'),
            Map.entry('כ','ל'), Map.entry('ל','כ')
    );

    // Albam: א..כ <-> ל..ת (11 pairs)
    public static final Map<Character, Character> ALBAM = Map.ofEntries(
            Map.entry('א','ל'), Map.entry('ל','א'),
            Map.entry('ב','מ'), Map.entry('מ','ב'),
            Map.entry('ג','נ'), Map.entry('נ','ג'),
            Map.entry('ד','ס'), Map.entry('ס','ד'),
            Map.entry('ה','ע'), Map.entry('ע','ה'),
            Map.entry('ו','פ'), Map.entry('פ','ו'),
            Map.entry('ז','צ'), Map.entry('צ','ז'),
            Map.entry('ח','ק'), Map.entry('ק','ח'),
            Map.entry('ט','ר'), Map.entry('ר','ט'),
            Map.entry('י','ש'), Map.entry('ש','י'),
            Map.entry('כ','ת'), Map.entry('ת','כ')
    );
}
