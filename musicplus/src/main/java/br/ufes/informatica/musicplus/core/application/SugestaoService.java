package br.ufes.informatica.musicplus.core.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.informatica.musicplus.core.domain.TipoGenero;
import br.ufes.informatica.musicplus.core.domain.TipoIdioma;
import br.ufes.informatica.musicplus.core.domain.TipoPais;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface SugestaoService extends Serializable {

	/** TODO: generated by FrameWeb. Should be documented. */
	public List pedirSugestaoArtista(String rankingOuAleatorio, TipoPais pais, Integer numero, TipoGenero genero);

	/** TODO: generated by FrameWeb. Should be documented. */
	public List pedirSugestaoMusica(String RankingOuAleatorio, TipoIdioma idioma, Integer numero, TipoGenero genero);

	/** TODO: generated by FrameWeb. Should be documented. */
	public List selecionarArtistasAleatorios(List listaArtistas, Integer numero);

	/** TODO: generated by FrameWeb. Should be documented. */
	public List selecionarMusicasAleatorias(List listaMusicas, Integer numero);

}