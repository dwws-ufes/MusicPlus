package br.ufes.informatica.musicplus.core.persistence;

import javax.ejb.Local;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface ColecaoDAO extends BaseDAO<Colecao> {
	
	/** TODO: generated by FrameWeb. Should be documented. */
	public List buscarPorArtista(Artista artista);
	
	/** TODO: generated by FrameWeb. Should be documented. */
	public List buscarPorMusica(Musica musica);
	
	/** TODO: generated by FrameWeb. Should be documented. */
	public List buscarPorNome(String nome);
	
}