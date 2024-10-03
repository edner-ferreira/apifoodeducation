package br.com.apifoodeducation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class UsuarioMedico extends Usuario{

    @Column(unique = true)
    private String crm;

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
