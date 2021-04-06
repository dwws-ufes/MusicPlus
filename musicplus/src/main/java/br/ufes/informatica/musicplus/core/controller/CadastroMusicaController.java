package br.ufes.informatica.musicplus.core.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;

import java.util.Date;
import java.util.List;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.musicplus.core.application.ArtistaService;
import br.ufes.informatica.musicplus.core.application.MusicaService;
import br.ufes.informatica.musicplus.core.domain.Artista;
import br.ufes.informatica.musicplus.core.domain.Musica;
import br.ufes.informatica.musicplus.core.domain.TipoGenero;
import br.ufes.informatica.musicplus.core.domain.TipoIdioma;

@Model
@SessionScoped
public class CadastroMusicaController extends JSFController {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;
	
	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private ArtistaService artistaService;
	
	@EJB
	private MusicaService musicaService;

	private String nome ;
		
	private Date duracao;

	private Date dataDeLancamento ;

	private TipoIdioma[] todosOsIdiomas;

	private TipoIdioma idiomaEscolhido;	
	
	private TipoGenero[] generosEscolhidos;

	private TipoGenero[] todosOsGeneros ;
	
	private int numVezesFavoritado;
	
	private Musica musica ;

	private Artista artistaEscolhido ;

	private List<Artista> artistas ;

	
	@PostConstruct
	public void init() {
		todosOsGeneros = TipoGenero.todosMenosQualquerUm();
		todosOsIdiomas = TipoIdioma.todosMenosQualquerUm();
		numVezesFavoritado = 0;
	}

	public void setDuracao(Date duracao) {
		this.duracao = duracao;
	}

	public void setDataDeLancamento(Date dataDeLancamento) {
		this.dataDeLancamento = dataDeLancamento;
	}

	public void setIdiomaEscolhido(TipoIdioma idiomaEscolhido) {
		this.idiomaEscolhido = idiomaEscolhido;
	}

	public void setGenerosEscolhidos(TipoGenero[] generosEscolhidos) {
		this.generosEscolhidos = generosEscolhidos;
	}

	public void setNumVezesFavoritado(int numVezesFavoritado) {
		this.numVezesFavoritado = numVezesFavoritado;
	}
	
	public Date getDuracao() {
		return duracao;
	}
	
	public Date getDataDeLancamento() {
		return dataDeLancamento;
	}

	public TipoIdioma getIdiomaEscolhido() {
		return idiomaEscolhido;
	}
	
    public TipoGenero[] getTodosOsGeneros() {
        return todosOsGeneros;
    }
    
    public TipoIdioma[] getTodosOsIdiomas() {
        return todosOsIdiomas;
    }
    
    public int getNumVezesFavoritado() {
		return numVezesFavoritado;
	}

	public TipoGenero[] getGenerosEscolhidos() {
        return generosEscolhidos;
    }
    
    public String salvarMusica() {
    	Musica musica = new Musica();
    	for (TipoGenero g : generosEscolhidos) {
    		musica.addGenero(g);
    	}
    	musica.setIdioma(idiomaEscolhido);
    	musica.setNome(nome);
    	musica.setNumVezesFavoritado(numVezesFavoritado);
    	musica.setDataLancamento(dataDeLancamento);
    	musica.setDuracao(duracao);
    	musicaService.save(musica);
    	this.musica = musica ;
    	this.nome = null;
    	return "/core/cadastrar/MusicaArtista.xhtml?faces-redirect=true" ;
    }
    
	public Musica getMusica() {
		return musica;
	}

	public void setMusica(Musica musica) {
		this.musica = musica;
	}
	
	public String descobrirArtistas(){
		artistas = artistaService.buscarPorNome(nome);
		return "/core/cadastrar/MusicaArtista.xhtml?faces-redirect=true" ;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Artista getArtistaEscolhido() {
		return artistaEscolhido;
	}

	public void setArtistaEscolhido(Artista artistaEscolhido) {
		this.artistaEscolhido = artistaEscolhido;
	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

	public String salvarArtistaMusica() {
		this.musica.addArtista(this.artistaEscolhido) ;
		this.artistaEscolhido.addMusica(this.musica);
		artistaService.save(artistaEscolhido);
		musicaService.save(musica);
		return "/index.xhtml?faces-redirect=true" ;
	}
}