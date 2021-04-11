package br.ufes.informatica.musicplus.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.musicplus.core.application.ArtistaService;
import br.ufes.informatica.musicplus.core.application.MusicaService;
import br.ufes.informatica.musicplus.core.application.SugestaoService;
import br.ufes.informatica.musicplus.core.domain.TipoGenero;
import br.ufes.informatica.musicplus.core.domain.TipoPais;
import br.ufes.informatica.musicplus.core.persistence.ArtistaDAO;
import br.ufes.informatica.musicplus.core.domain.Artista;
import br.ufes.informatica.musicplus.core.domain.Musica;

/** TODO: generated by FrameWeb. Should be documented. */
@Model
@SessionScoped
public class SugestaoArtistaController extends JSFController {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private SugestaoService sugestaoService;

	@EJB
	private ArtistaService artistaService ;
	
	@EJB
	private MusicaService musicaService ;
	
	/** TODO: generated by FrameWeb. Should be documented. */
	private List<Artista> artistas;

	/** TODO: generated by FrameWeb. Should be documented. */
	private Integer numSugestoes;

	/** TODO: generated by FrameWeb. Should be documented. */
	private String porRankingOuAleatorio;

	private TipoPais[] paisesEscolhidos;
	
	private TipoPais[] todosOsPaises;
	
	private TipoGenero[] generosEscolhidos;

	private TipoGenero[] todosOsGeneros ;
	
	private boolean allGeneros ;
	
	private boolean allPaises ;

	private boolean warningVazioGenero ;
	
	private boolean warningVazioPais ;
	
	private Boolean desabilitarBotao ;

	private Artista artistaEscolhido ;
	
	@PostConstruct
	public void init() {
		auxClean() ;
	}
	
	public void auxClean() {
		todosOsGeneros = TipoGenero.todos();
		todosOsPaises = TipoPais.todos();
		allGeneros = true ;
		allPaises = true; 
		paisesEscolhidos = todosOsPaises ;
		generosEscolhidos = todosOsGeneros ;
		numSugestoes = null; 
		warningVazioGenero = false ;
		warningVazioPais = false ;
		desabilitarBotao = true ;
	}
	
	public Boolean getDesabilitarBotao() {
		return desabilitarBotao;
	}

	public void setDesabilitarBotao(Boolean desabilitarBotao) {
		this.desabilitarBotao = desabilitarBotao;
	}
	
	public void habilitarBotao() {
		desabilitarBotao = false ;
	}

	public Artista getArtistaEscolhido() {
		return artistaEscolhido;
	}

	public void setArtistaEscolhido(Artista artistaEscolhido) {
		this.artistaEscolhido = artistaEscolhido;
	}

	/** Getter for artistas. */
	public List<Artista> getArtistas() {
		return artistas;
	}

	/** Setter for artistas. */
	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

	/** Getter for numSugestoes. */
	public Integer getNumSugestoes() {
		return numSugestoes;
	}

	/** Setter for numSugestoes. */
	public void setNumSugestoes(Integer numSugestoes) {
		this.numSugestoes = numSugestoes;
	}

	/** Getter for porRankingOuAleatorio. */
	public String getPorRankingOuAleatorio() {
		return porRankingOuAleatorio;
	}

	/** Setter for porRankingOuAleatorio. */
	public void setPorRankingOuAleatorio(String porRankingOuAleatorio) {
		this.porRankingOuAleatorio = porRankingOuAleatorio;
	}

	public TipoPais[] getPaisesEscolhidos() {
		return paisesEscolhidos;
	}

	public void setPaisesEscolhidos(TipoPais[] paisesEscolhidos) {
		this.paisesEscolhidos = paisesEscolhidos;
	}

	public TipoPais[] getTodosOsPaises() {
		return todosOsPaises;
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
	
	public void escolherTodosOsGeneros() {
		if (allGeneros == true) {
			generosEscolhidos = todosOsGeneros ;
			warningVazioGenero = false ;
		}else {
			generosEscolhidos = null ;
			warningVazioGenero = true ;
		}
	}
	
	public void dispensarTodosOsGeneros() {
		warningVazioGenero = true ;
		allGeneros = false ;
		if (generosEscolhidos == null) {
			return ;
		}
		if (generosEscolhidos.length == 0) {
			return ;
		}
		warningVazioGenero = false ;
		allGeneros = true ;
		if (generosEscolhidos.length != todosOsGeneros.length) {
			allGeneros = false ;
		}
		return ;
	}
	
	public void escolherTodosOsPaises() {
		if (allPaises == true) {
			paisesEscolhidos = todosOsPaises ;
			warningVazioPais = false ;
		}else {
			paisesEscolhidos = null ;
			warningVazioPais = true ;
		}
	}
	
	public void dispensarTodosOsPaises() {
		warningVazioPais = true ;
		allPaises = false ;
		if (paisesEscolhidos == null) {
			return ;
		}
		if (paisesEscolhidos.length == 0) {
			return ;
		}
		warningVazioPais = false ;
		allPaises = true ;
		if (paisesEscolhidos.length != todosOsPaises.length) {
			allPaises = false ;
		}
		return ;
	}

	public boolean isAllGeneros() {
		return allGeneros;
	}

	public void setAllGeneros(boolean allGeneros) {
		this.allGeneros = allGeneros;
	}

	public boolean isAllPaises() {
		return allPaises;
	}

	public void setAllPaises(boolean allPaises) {
		this.allPaises = allPaises;
	}

	public boolean isWarningVazioGenero() {
		return warningVazioGenero;
	}

	public void setWarningVazioGenero(boolean warningVazioGenero) {
		this.warningVazioGenero = warningVazioGenero;
	}

	public boolean isWarningVazioPais() {
		return warningVazioPais;
	}

	public void setWarningVazioPais(boolean warningVazioPais) {
		this.warningVazioPais = warningVazioPais;
	}

	public void auxSugestaoArtistaDefaults() {
		artistas = null ;
		artistaEscolhido = null ;
		desabilitarBotao = true ;
	}
	public String favoritar() {
		// IF (USUARIO AINDA NAO FAVORITOU ARTISTA){
		artistaEscolhido.incrementaNumVezesFavoritado();
		artistaService.save(artistaEscolhido);
		// }
		auxSugestaoArtistaDefaults() ;
		return "/index.xhtml?faces-redirect=true" ;
	}
	
	public String pedirSugestaoArtista() {
		if (warningVazioGenero || warningVazioPais) {
			artistas = null ;
		}else {
			List<TipoPais> paises = new ArrayList<TipoPais>(); 
			for (TipoPais p : paisesEscolhidos) {
				paises.add(p);
			}
			List<TipoGenero> generos = new ArrayList<TipoGenero>(); 
			for (TipoGenero g : generosEscolhidos) {
				generos.add(g);
			}
			artistas = sugestaoService.pedirSugestaoArtista(porRankingOuAleatorio, paises, allPaises, numSugestoes, generos, allGeneros);
		}
		auxClean();
		return "/core/sugestao/ArtistasEncontrados.xhtml?faces-redirect=true" ;
	}
}