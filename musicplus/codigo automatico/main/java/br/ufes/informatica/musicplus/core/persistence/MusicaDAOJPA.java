package br.ufes.informatica.musicplus.core.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class MusicaDAOJPA extends BaseJPADAO<Musica> implements MusicaDAO{
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
    @PersistenceContext
    private EntityManager entityManager;

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
    
    
	/** TODO: generated by FrameWeb. Should be documented. */
    @Override
	public List buscarPorNome(String nome) {
		// FIXME: auto-generated method stub
		return null;
	}
	
	/** TODO: generated by FrameWeb. Should be documented. */
    @Override
	public List buscarPorArtista(Artista artista) {
		// FIXME: auto-generated method stub
		return null;
	}
	
	/** TODO: generated by FrameWeb. Should be documented. */
    @Override
	public List buscarPorGenero(TipoGenero genero) {
		// FIXME: auto-generated method stub
		return null;
	}
	
	/** TODO: generated by FrameWeb. Should be documented. */
    @Override
	public List buscarPorIdioma(TipoIdioma idioma) {
		// FIXME: auto-generated method stub
		return null;
	}
	
	/** TODO: generated by FrameWeb. Should be documented. */
    @Override
	public List buscarGeral(String nomeDaMusica, String nomeDoArtista, TipoGenero genero, TipoIdioma idioma, Boolean ranking, Integer numero) {
		// FIXME: auto-generated method stub
		return null;
	}
	

}