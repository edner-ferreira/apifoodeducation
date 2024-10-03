package br.com.apifoodeducation.component;

import br.com.apifoodeducation.repository.UsuarioComumRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator  <UniqueEmail, String>{

    @Autowired
    private UsuarioComumRepository usuarioComumRepository;

    @Override
    public void initialize (UniqueEmail constraintAnnotation){
    }

    @Override public boolean isValid(String email, ConstraintValidatorContext context){
        if (email == null || email.isEmpty()) {
            return true;
        }
        return usuarioComumRepository.findByEmail(email) == null;
    }
}
