package br.ufes.informatica.musicplus.core.controller;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.musicplus.core.application.SugestaoService;
import br.ufes.informatica.musicplus.core.domain.TipoGenero;
import br.ufes.informatica.musicplus.core.domain.TipoIdioma;
import br.ufes.informatica.musicplus.core.domain.TipoPais;

/** TODO: generated by FrameWeb. Should be documented. */
@Model
public class SugestaoController extends JSFController {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private SugestaoService sugestaoService;

	/** TODO: generated by FrameWeb. Should be documented. */
	private List listaDeArtistas;

	/** TODO: generated by FrameWeb. Should be documented. */
	private List listaDeMusicas;

	/** TODO: generated by FrameWeb. Should be documented. */
	private int numSugestoes;

	/** TODO: generated by FrameWeb. Should be documented. */
	private TipoGenero generoEscolhido;

	/** TODO: generated by FrameWeb. Should be documented. */
	private String porRankingOuAleatorio;

	/** TODO: generated by FrameWeb. Should be documented. */
	private TipoIdioma porIdioma;

	/** TODO: generated by FrameWeb. Should be documented. */
	private TipoPais porPais;

	/** TODO: generated by FrameWeb. Should be documented. */
	public String pedirSugestaoArtista() {
		// FIXME: auto-generated method stub
		return null;
	}

	/** TODO: generated by FrameWeb. Should be documented. */
	public String pedirSugestaoMusica() {
		// FIXME: auto-generated method stub
		return null;
	}

	/** Getter for listaDeArtistas. */
	public List getListaDeArtistas() {
		return listaDeArtistas;
	}

	/** Setter for listaDeArtistas. */
	public void setListaDeArtistas(List listaDeArtistas) {
		this.listaDeArtistas = listaDeArtistas;
	}

	/** Getter for listaDeMusicas. */
	public List getListaDeMusicas() {
		return listaDeMusicas;
	}

	/** Setter for listaDeMusicas. */
	public void setListaDeMusicas(List listaDeMusicas) {
		this.listaDeMusicas = listaDeMusicas;
	}

	/** Getter for numSugestoes. */
	public int getNumSugestoes() {
		return numSugestoes;
	}

	/** Setter for numSugestoes. */
	public void setNumSugestoes(int numSugestoes) {
		this.numSugestoes = numSugestoes;
	}

	/** Getter for generoEscolhido. */
	public TipoGenero getGeneroEscolhido() {
		return generoEscolhido;
	}

	/** Setter for generoEscolhido. */
	public void setGeneroEscolhido(TipoGenero generoEscolhido) {
		this.generoEscolhido = generoEscolhido;
	}

	/** Getter for porRankingOuAleatorio. */
	public String getPorRankingOuAleatorio() {
		return porRankingOuAleatorio;
	}

	/** Setter for porRankingOuAleatorio. */
	public void setPorRankingOuAleatorio(String porRankingOuAleatorio) {
		this.porRankingOuAleatorio = porRankingOuAleatorio;
	}

	/** Getter for porIdioma. */
	public TipoIdioma getPorIdioma() {
		return porIdioma;
	}

	/** Setter for porIdioma. */
	public void setPorIdioma(TipoIdioma porIdioma) {
		this.porIdioma = porIdioma;
	}

	/** Getter for porPais. */
	public TipoPais getPorPais() {
		return porPais;
	}

	/** Setter for porPais. */
	public void setPorPais(TipoPais porPais) {
		this.porPais = porPais;
	}

}