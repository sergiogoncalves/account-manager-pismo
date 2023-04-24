package com.pismo.accountmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
class ErrorHandler {

    @ResponseBody
    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundHandler(NotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({BadRequestException.class, })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String BadRequestHandler(BadRequestException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({UnprocessableEntityException.class, })
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String UnprocessableEntityHandler(UnprocessableEntityException ex) {
        return ex.getMessage();
    }


    @ResponseBody
    @ExceptionHandler({ConflictException.class, })
    @ResponseStatus(HttpStatus.CONFLICT)
    String ConflictHandler(ConflictException ex) {
        return ex.getMessage();
    }


    @ResponseBody
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String InternalServerRequestHandler(Exception ex) {
        return ex.getMessage();
    }

}