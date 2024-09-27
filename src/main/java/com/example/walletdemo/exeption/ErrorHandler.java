package com.example.walletdemo.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(WalletNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleWalletNotFoundException(final WalletNotFoundException e) {
        return ApiError.builder()
                .timestamp(LocalDateTime.now())
                .errors(List.of(e.getStackTrace()))
                .message(e.getLocalizedMessage())
                .reason(e.getMessage())
                .status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(NotWalletJsonException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleNotWalletJson(final NotWalletJsonException e) {
        return ApiError.builder()
                .timestamp(LocalDateTime.now())
                .errors(List.of(e.getStackTrace()))
                .message(e.getLocalizedMessage())
                .reason(e.getMessage())
                .status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleNotEnough(final NotEnoughFundsException e) {
        return ApiError.builder()
                .timestamp(LocalDateTime.now())
                .errors(List.of(e.getStackTrace()))
                .message(e.getLocalizedMessage())
                .reason(e.getMessage())
                .status(HttpStatus.BAD_REQUEST).build();
    }

}
