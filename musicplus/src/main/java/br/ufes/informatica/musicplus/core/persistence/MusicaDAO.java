package br.ufes.informatica.musicplus.core.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.informatica.musicplus.core.domain.Artista;
import br.ufes.informatica.musicplus.core.domain.Musica;
import br.ufes.informatica.musicplus.core.domain.Playlist;
import br.ufes.informatica.musicplus.core.domain.TipoGenero;
import br.ufes.informatica.musicplus.core.domain.TipoIdioma;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface MusicaDAO extends BaseDAO<Musica> {

	/** TODO: generated by FrameWeb. Should be documented. */
	public List<Musica> buscarPorNome(String nome);

	/** TODO: generated by FrameWeb. Should be documented. */
	public List<Musica> buscarPorArtista(Artista artista);

	public List<Musica> buscarPorPlaylist(Playlist playlist);

	/** TODO: generated by FrameWeb. Should be documented. 
	 * @throws MultiplePersistentObjectsFoundException 
	 * @throws PersistentObjectNotFoundException */
	public List<Musica> buscarPorGenero(TipoGenero genero) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;

	/** TODO: generated by FrameWeb. Should be documented. 
	 * @throws MultiplePersistentObjectsFoundException 
	 * @throws PersistentObjectNotFoundException */
	public List<Musica> buscarPorIdioma(TipoIdioma idioma) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;


}