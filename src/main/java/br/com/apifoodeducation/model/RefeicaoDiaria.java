package br.com.apifoodeducation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RefeicaoDiaria {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="descricaoAlimentos")
    private String descricaoAlimentos;
    private Double calorias;
    private Double proteinas;
    private Double lipideos;
    private Double carboidratos;
    
//    @ManyToOne
//    @JoinColumn(name="usuario_id", nullable=false)
//    private Usuario usuario;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricaoAlimentos() {
		return descricaoAlimentos;
	}
	public void setDescricaoAlimentos(String descricaoAlimentos) {
		this.descricaoAlimentos = descricaoAlimentos;
	}
	public Double getCalorias() {
		return calorias;
	}
	public void setCalorias(Double calorias) {
		this.calorias = calorias;
	}
//	public Usuario getUsuario() {
//		return usuario;
//	}
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
	public Double getProteinas() {
		return proteinas;
	}
	public void setProteinas(Double proteinas) {
		this.proteinas = proteinas;
	}
	public Double getLipideos() {
		return lipideos;
	}
	public void setLipideos(Double lipideos) {
		this.lipideos = lipideos;
	}
	public Double getCarboidratos() {
		return carboidratos;
	}
	public void setCarboidratos(Double carboidratos) {
		this.carboidratos = carboidratos;
	}
}
