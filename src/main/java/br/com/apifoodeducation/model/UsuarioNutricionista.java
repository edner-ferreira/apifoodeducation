package br.com.apifoodeducation.model;

import jakarta.persistence.Entity;

@Entity
public class UsuarioNutricionista extends Usuario{

    private String crn;

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }
}
