package br.com.apifoodeducation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class UsuarioComum extends Usuario{

    private Double altura;
    private Double peso;

    @OneToMany(mappedBy="usuarioComum")
    private List<ConsumoAlimento> consumoAlimentos;

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public List<ConsumoAlimento> getConsumoAlimentos() {
        return consumoAlimentos;
    }
    public void setConsumoAlimentos(List<ConsumoAlimento> consumoAlimentos) {
        this.consumoAlimentos = consumoAlimentos;
    }
}
