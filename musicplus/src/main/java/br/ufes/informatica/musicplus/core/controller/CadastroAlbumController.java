package br.ufes.informatica.musicplus.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.musicplus.core.application.AlbumService;
import br.ufes.informatica.musicplus.core.application.ArtistaService;
import br.ufes.informatica.musicplus.core.domain.Album;
import br.ufes.informatica.musicplus.core.domain.Artista;
import br.ufes.informatica.musicplus.core.domain.TipoGenero;
import br.ufes.informatica.musicplus.core.domain.TipoIdioma;

@Model
@SessionScoped
public class CadastroAlbumController extends JSFController {
	
	private static final long serialVersionUID = 1L;	
	
	private String nome ;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private AlbumService albumService;
	
	@EJB
	private ArtistaService artistaService;
	
	private Album album;
	
	private Date dataDeLancamento ;
	
	/** TODO: generated by FrameWeb. Should be documented. */
	private String nomeDoAlbum;
	
	private Artista artistaEscolhido ;
	
	private Boolean desabilitarBotao ;
	
	private List<Artista> artistas ;
	
	@PostConstruct
	public void init() {
		nome = "";
		desabilitarBotao = true ;
	}
	
    public String salvarAlbum() {
    	Album album = new Album();
    	album.setNome(nomeDoAlbum);
    	album.setDataLancamento(dataDeLancamento);
    	albumService.save(album);
    	this.setAlbum(album);
    	this.nomeDoAlbum = "";
    	return albumArtistaAdicionar() ;
    }
    
    public String albumArtistaAdicionar() {
    	auxAlbumArtistaDefaults() ;
    	return "/core/cadastrar/AlbumArtistaAdicionar.xhtml?faces-redirect=true" ;
    }
    
	public void auxAlbumArtistaDefaults() {
		artistaEscolhido = null ;
		desabilitarBotao = true ;
	}
	
	public String descobrirArtistas(){
		artistas = artistaService.buscarPorNome(nome);
		nome = "" ;
		return albumArtistaAdicionar() ;
	}
	
	public void salvarRemoverAux() {
		artistas = null ;
		album = null ;
		desabilitarBotao = true ;
	}
	
	public String salvarAlbumArtista() {
		this.album.addArtista(this.artistaEscolhido) ;
		albumService.save(album); 
		salvarRemoverAux() ;
		return "/index.xhtml?faces-redirect=true" ;
	}
	
	public void habilitarBotao() {
		desabilitarBotao = false ;
	}

	public Date getDataDeLancamento() {
		return dataDeLancamento;
	}

	public void setDataDeLancamento(Date dataDeLancamento) {
		this.dataDeLancamento = dataDeLancamento;
	}

	public String getNomeDoAlbum() {
		return nomeDoAlbum;
	}

	public void setNomeDoAlbum(String nomeDoAlbum) {
		this.nomeDoAlbum = nomeDoAlbum;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Artista getArtistaEscolhido() {
		return artistaEscolhido;
	}

	public void setArtistaEscolhido(Artista artistaEscolhido) {
		this.artistaEscolhido = artistaEscolhido;
	}
	
	public List<Artista> getArtistas() {
		return this.artistas;
	}
	
	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

	public Boolean getDesabilitarBotao() {
		return desabilitarBotao;
	}

	public void setDesabilitarBotao(Boolean desabilitarBotao) {
		this.desabilitarBotao = desabilitarBotao;
	}	
}