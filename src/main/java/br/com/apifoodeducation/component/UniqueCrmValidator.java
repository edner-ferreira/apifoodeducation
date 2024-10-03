package br.com.apifoodeducation.component;

import br.com.apifoodeducation.repository.UsuarioComumRepository;
import br.com.apifoodeducation.repository.UsuarioMedicoRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCrmValidator implements ConstraintValidator  <UniqueCrm, String>{

    @Autowired
    private UsuarioMedicoRepository usuarioMedicoRepository;

    @Override
    public void initialize (UniqueCrm constraintAnnotation){
    }

    @Override public boolean isValid(String crm, ConstraintValidatorContext context){
        if (crm == null || crm.isEmpty()) {
            return true;
        }
        return usuarioMedicoRepository.findByCrm(crm) == null;
    }
}
