package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity ErrorHandler404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity ErrorHandler400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ErrorHandlerData:: new).toList());
    }

    public record ErrorHandlerData(String field, String message) {
        public ErrorHandlerData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity BadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity AuthenticationError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity AccessDeniedException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso inválido.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity Error500(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
    }

}
