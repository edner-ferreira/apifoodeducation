package br.com.apifoodeducation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ConsumoAlimento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_comum_id")
    private UsuarioComum usuarioComum;

    @ManyToOne
    @JoinColumn(name = "alimento_id")
    private RefeicaoDiaria alimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuarioComum() {
		return usuarioComum;
	}

	public void setUsuarioComum(UsuarioComum usuarioComum) {
		this.usuarioComum = usuarioComum;
	}

	public RefeicaoDiaria getAlimento() {
		return alimento;
	}

	public void setAlimento(RefeicaoDiaria alimento) {
		this.alimento = alimento;
	}
    
    
}
