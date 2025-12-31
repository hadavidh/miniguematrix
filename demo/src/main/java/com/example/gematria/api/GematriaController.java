package com.example.gematria.api;

import com.example.gematria.api.dto.*;
import com.example.gematria.core.GematriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/gematria")
public class GematriaController {

    private final GematriaService service = new GematriaService();

    @PostMapping
    public ResponseEntity<GematriaResponse> compute(@Valid @RequestBody GematriaRequest req) {
        List<GematriaResult> results = service.compute(req.text(), req.methods());
        return ResponseEntity.ok(new GematriaResponse(req.text(), results));
    }

    // Option pratique: GET /api/gematria?text=שלום&method=SIMPLE
    @GetMapping
    public ResponseEntity<GematriaResponse> computeGet(
            @RequestParam String text,
            @RequestParam(required = false) GematriaMethod method
    ) {
        Set<GematriaMethod> methods = (method == null) ? EnumSet.allOf(GematriaMethod.class) : EnumSet.of(method);
        List<GematriaResult> results = service.compute(text, methods);
        return ResponseEntity.ok(new GematriaResponse(text, results));
    }
}
