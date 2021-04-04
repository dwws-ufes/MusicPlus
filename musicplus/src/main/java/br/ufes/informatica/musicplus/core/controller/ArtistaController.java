package br.ufes.informatica.musicplus.core.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.musicplus.core.application.ArtistaService;
import br.ufes.informatica.musicplus.core.domain.Artista;
import br.ufes.informatica.musicplus.core.domain.TipoGenero;
import br.ufes.informatica.musicplus.core.domain.TipoPais;

@Model
public class ArtistaController extends JSFController {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;
	
	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private ArtistaService artistaService;
	
	private String nome;

	private TipoPais nacionalidade;
	
	private TipoPais[] todosOsPaises;
	
	private TipoGenero[] generosEscolhidos;

	private TipoGenero[] todosOsGeneros ;
	
	private int numVezesFavoritado;
	
	@PostConstruct
	public void init() {
		todosOsGeneros = TipoGenero.class.getEnumConstants();
		todosOsPaises = TipoPais.class.getEnumConstants();
		numVezesFavoritado = 0;
	}
	
    public TipoGenero[] getTodosOsGeneros() {
        return todosOsGeneros;
    }
    
    public TipoPais[] getTodosOsPaises() {
        return todosOsPaises;
    }
    
    public TipoGenero[] getGenerosEscolhidos() {
        return generosEscolhidos;
    }
    
    public TipoPais getNacionalidade() {
    	return nacionalidade ;
    }
    
    public String getNome() {
    	return nome ;
    }
    
    public String salvar() {
    	Artista artista = new Artista();
    	for (TipoGenero g : generosEscolhidos) {
    		artista.addGenero(g);
    	}
    	artista.setNacionalidade(nacionalidade);
    	artista.setNome(nome);
    	artista.setNumVezesFavoritado(numVezesFavoritado);
    	artistaService.save(artista);
    	return "index.xhtml" ;
    	
    }
}
