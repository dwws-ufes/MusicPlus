package br.ufes.informatica.musicplus.core.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.musicplus.core.application.UsuarioService;
import br.ufes.informatica.musicplus.core.application.EmailService;
import br.ufes.informatica.musicplus.core.domain.TipoGenero;
import br.ufes.informatica.musicplus.core.domain.TipoUsuario;
import br.ufes.informatica.musicplus.core.domain.Usuario;

@Model
@SessionScoped
public class CadastroUsuarioController extends JSFController {
	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioService usuarioService;
	@EJB
	private EmailService emailService;
	private String nomeUsuario;
	private String username;
	private String email;
	private String senha;
	// artistasFavoritados not here
	private TipoGenero[] generosEscolhidos;
	private TipoGenero[] todosOsGeneros;
	private Usuario user;
	private Boolean editando;
	
	@PostConstruct
	public void init() {
		todosOsGeneros = TipoGenero.todos();
		user = new Usuario();
		
	}
	
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}



	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public TipoGenero[] getGenerosEscolhidos() {
		return generosEscolhidos;
	}



	public void setGenerosEscolhidos(TipoGenero[] generosEscolhidos) {
		this.generosEscolhidos = generosEscolhidos;
	}



	public TipoGenero[] getTodosOsGeneros() {
		return todosOsGeneros;
	}



	public void setTodosOsGeneros(TipoGenero[] todosOsGeneros) {
		this.todosOsGeneros = todosOsGeneros;
	}

	
	public String salvarUsuario() {
  
    	user.setNome(nomeUsuario);
    	user.setUsername(username);
    	user.setEmail(email);
    	user.setSenha(senha);// encrypt before saving on service
    	user.setAdmin(TipoUsuario.NonAdmin); 
    	for (TipoGenero g : generosEscolhidos) {
    		user.addGenero(g);
    	}
    	
    	usuarioService.save(user);
    	emailService.enviarEmail("Confirmação de Cadastro", email, "Cadastro Realizado com Sucesso!");
    	generosEscolhidos = null ;
    	nomeUsuario = null;
    	username = null;
    	email=null;
    	senha = null;
    	
    	return "/index.xhtml?faces-redirect=true" ;
    }
	
	public String editar() {
		editando = true ;
		List<TipoGenero> aux = user.getGeneroFavoritados();
		generosEscolhidos =  aux.toArray(new TipoGenero[aux.size()]);
    	nomeUsuario = user.getNome();
    	username = user.getUsername();
    	email=user.getEmail();
    	senha = user.getSenha();
		return redirectCadastrar();
	}
	
	
	public String redirectCadastrar() {
		return "/core/cadastrarusuario/Usuario.xhtml?faces-redirect=true" ;
	}
	
}
