package br.ufes.informatica.musicplus.core.controller;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.musicplus.core.application.AlbumService;
import br.ufes.informatica.musicplus.core.domain.Album;
import br.ufes.informatica.musicplus.core.domain.Artista;

@Model
@SessionScoped
public class AlbumController extends JSFController {
	
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;
	
	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private AlbumService albumService;
	
	private Date dataDeLancamento ;
	
	/** TODO: generated by FrameWeb. Should be documented. */
	private String nomeDoAlbum;
	
    public String salvarAlbum() {
    	Album album = new Album();
    	album.setNome(nomeDoAlbum);
    	album.setDataLancamento(dataDeLancamento);
    	albumService.save(album);
    	album.setNome("");
    	return "/core/cadastrar/Album.xhtml?faces-redirect=true" ;
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
}