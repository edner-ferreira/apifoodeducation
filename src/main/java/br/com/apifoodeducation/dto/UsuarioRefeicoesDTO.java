package br.com.apifoodeducation.dto;

import java.util.List;

import br.com.apifoodeducation.model.ConsumoAlimento;
import br.com.apifoodeducation.model.Usuario;

public class UsuarioRefeicoesDTO {
	
	private Usuario usuario;
    private List<ConsumoAlimento> consumoAlimentos;
	
    public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<ConsumoAlimento> getConsumoAlimentos() {
		return consumoAlimentos;
	}
	public void setConsumoAlimentos(List<ConsumoAlimento> consumoAlimentos) {
		this.consumoAlimentos = consumoAlimentos;
	}
}
