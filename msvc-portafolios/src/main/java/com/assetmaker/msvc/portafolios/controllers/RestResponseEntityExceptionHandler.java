package com.assetmaker.msvc.portafolios.controllers;

import com.assetmaker.msvc.portafolios.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

//Clase que se encarga de manejar las respuestas de los servicios REST para las excepciones requeridas
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    //Metodo que se encarga de manejar la respuesta de los servicios REST cada vez que se lance una BadRequestException
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {

        //Se crea un map que contendra la informacion de la respuesta en el body
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("Error", HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}

