package br.ufes.informatica.musicplus.core.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.ufes.informatica.musicplus.core.domain.Artista;
import br.ufes.informatica.musicplus.core.domain.Musica;
import br.ufes.informatica.musicplus.core.domain.Playlist;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface MusicaService extends Serializable {
	
	/** TODO: generated by FrameWeb. Should be documented. */
	public List<Musica> buscarMusica(String NomeDaMusica, List<Artista> artistas);

	public List<Musica> buscarPorNome(String NomeDaMusica);

	public List<Musica> buscarPorArtista(Artista artista);

	public List<Musica> buscarPorArtistas(List<Artista> artistas);
	
	public List<Musica> buscarPorPlaylist(Playlist playlist);

	public List<Musica> buscarTodasMusicas() ;
	
	public void save(Musica musica);

	public void delete(Musica musicaEscolhida);
}