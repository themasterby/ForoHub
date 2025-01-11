package com.challenge.forohub.infra.errores;

import com.challenge.forohub.ValidacionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.swing.*;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores =  e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity tratarError409(SQLIntegrityConstraintViolationException e){
        String mensaje = "El título o mensaje ya existe";
        return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity tratarError404(ValidacionException e){
        String mensaje = "El ID ingresado no existe en la base de datos";
        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    }


    private record DatosErrorValidacion(String campo, String error){

        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }



}
