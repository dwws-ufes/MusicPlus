package br.ufes.informatica.musicplus.core.controller;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.musicplus.core.application.MusicaService;

/** TODO: generated by FrameWeb. Should be documented. */
@Model
public class MusicaController extends JSFController {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private MusicaService musicaService;

	/** TODO: generated by FrameWeb. Should be documented. */
	private String nomeDoArtista;

	/** TODO: generated by FrameWeb. Should be documented. */
	private String nomeDaMusica;

	/** TODO: generated by FrameWeb. Should be documented. */
	private List listaDeMusicas;

	/** TODO: generated by FrameWeb. Should be documented. */
	public String buscarMusica() {
		// FIXME: auto-generated method stub
		return null;
	}

	/** Getter for nomeDoArtista. */
	public String getNomeDoArtista() {
		return nomeDoArtista;
	}

	/** Setter for nomeDoArtista. */
	public void setNomeDoArtista(String nomeDoArtista) {
		this.nomeDoArtista = nomeDoArtista;
	}

	/** Getter for nomeDaMusica. */
	public String getNomeDaMusica() {
		return nomeDaMusica;
	}

	/** Setter for nomeDaMusica. */
	public void setNomeDaMusica(String nomeDaMusica) {
		this.nomeDaMusica = nomeDaMusica;
	}

	/** Getter for listaDeMusicas. */
	public List getListaDeMusicas() {
		return listaDeMusicas;
	}

	/** Setter for listaDeMusicas. */
	public void setListaDeMusicas(List listaDeMusicas) {
		this.listaDeMusicas = listaDeMusicas;
	}

}