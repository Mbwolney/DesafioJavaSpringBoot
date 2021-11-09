package br.com.desafiojavaspringboottest.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
public class ErrorDetails {
//    private HttpStatus status_code;
    private Integer status_code;
    private String message;



    public ErrorDetails(Integer status_code, String message) {
        super();
        this.status_code = status_code;
        this.message = message;
    }
}
