package br.ufes.informatica.musicplus.core.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.musicplus.core.domain.Artista;
import br.ufes.informatica.musicplus.core.domain.Musica;
import br.ufes.informatica.musicplus.core.domain.TipoGenero;
import br.ufes.informatica.musicplus.core.domain.TipoIdioma;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface MusicaDAO extends BaseDAO<Musica> {

	/** TODO: generated by FrameWeb. Should be documented. */
	public List buscarPorNome(String nome);

	/** TODO: generated by FrameWeb. Should be documented. */
	public List buscarPorArtista(Artista artista);

	/** TODO: generated by FrameWeb. Should be documented. */
	public List buscarPorGenero(TipoGenero genero);

	/** TODO: generated by FrameWeb. Should be documented. */
	public List buscarPorIdioma(TipoIdioma idioma);

	/** TODO: generated by FrameWeb. Should be documented. */
	public List buscarGeral(String nomeDaMusica, String nomeDoArtista, TipoGenero genero, TipoIdioma idioma,
			Boolean ranking, Integer numero);

}