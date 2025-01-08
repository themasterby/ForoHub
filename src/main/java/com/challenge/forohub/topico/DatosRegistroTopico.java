package com.challenge.forohub.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        @Future
        LocalDateTime fechaCreacion,
        @NotBlank
        String status,
        @NotBlank
        String autor,
        @NotBlank
        String curso,
        @NotNull
        String respuestas
) {
}
