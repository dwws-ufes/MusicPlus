package br.ufes.informatica.musicplus.core.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.informatica.musicplus.core.domain.Album;
import br.ufes.informatica.musicplus.core.domain.Artista;
import br.ufes.informatica.musicplus.core.domain.Musica;
import br.ufes.informatica.musicplus.core.domain.Usuario;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface AlbumService extends Serializable {

	/** TODO: generated by FrameWeb. Should be documented. */
	public void save(Album album) ;
	
	/** TODO: generated by FrameWeb. Should be documented. */
	public void criarAlbum(String nomeAlbum);

	/** TODO: generated by FrameWeb. Should be documented. */
	public void adicionarMusicaNoAlbum(Usuario usuarioLogado, String nomeAlbum);

	/** TODO: generated by FrameWeb. Should be documented. */
	public void removerMusicaDoAlbum(Usuario usuarioLogado, String nomeAlbum);

	/** TODO: generated by FrameWeb. Should be documented. */
	public void listarMusicasDoAlbum(Usuario usuarioLogado, String nomeAlbum);

}