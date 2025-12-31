package com.example.gematria.core;

import com.example.gematria.api.dto.GematriaMethod;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

class GematriaServiceTest {

    private final GematriaService service = new GematriaService();

    @Test
    void simple_shalom_is_376() {
        var res = service.compute("שלום", EnumSet.of(GematriaMethod.SIMPLE));
        assertEquals(1, res.size());
        assertEquals(376, res.getFirst().value());
    }

    @Test
    void siduri_shalom_is_52() {
        var res = service.compute("שלום", EnumSet.of(GematriaMethod.SIDURI));
        assertEquals(52, res.getFirst().value()); // ש21 + ל12 + ו6 + מ13
    }

    @Test
    void atbash_shalom_transforms_and_sums() {
        var res = service.compute("שלום", EnumSet.of(GematriaMethod.ATBASH)).getFirst();
        assertEquals("בכפי", res.transformedText()); // ש->ב, ל->כ, ו->פ, מ->י
        assertEquals(112, res.value());             // 2 + 20 + 80 + 10
    }

    @Test
    void albam_shalom_transforms_and_sums() {
        var res = service.compute("שלום", EnumSet.of(GematriaMethod.ALBAM)).getFirst();
        assertEquals("יאפב", res.transformedText()); // ש->י, ל->א, ו->פ, מ->ב
        assertEquals(93, res.value());               // 10 + 1 + 80 + 2
    }

    @Test
    void ignores_niqqud_and_non_letters() {
        var res = service.compute("שָׁלוֹם!!!", EnumSet.of(GematriaMethod.SIMPLE)).getFirst();
        assertEquals("שלום", res.normalizedText());
        assertEquals(376, res.value());
    }

    @Test
    void throws_if_no_hebrew_letters() {
        assertThrows(IllegalArgumentException.class,
                () -> service.compute("John 123", EnumSet.of(GematriaMethod.SIMPLE)));
    }
}
