package br.ufes.informatica.musicplus.core.persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.informatica.musicplus.core.domain.Artista;
import br.ufes.informatica.musicplus.core.domain.Musica;
import br.ufes.informatica.musicplus.core.domain.Musica_;
import br.ufes.informatica.musicplus.core.domain.TipoGenero;
import br.ufes.informatica.musicplus.core.domain.TipoIdioma;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class MusicaDAOJPA extends BaseJPADAO<Musica> implements MusicaDAO {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(MusicaDAOJPA.class.getCanonicalName());

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
	public List<Musica> buscarPorNome(String nome) {
		// FIXME: auto-generated method stub
		logger.log(Level.FINE, "Recuperando as musicas cujo nome contem \"{0}\"...", nome);
		
		// Constructs the query over the Musica class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Musica> cq = cb.createQuery(Musica.class);
		Root<Musica> root = cq.from(Musica.class);

		// Filters the query with the nome.
		cq.where(cb.like(cb.lower(root.get(Musica_.nome)), "%" + nome.toLowerCase() + "%"));
		List<Musica> result = entityManager.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Recuperação das musicas cujo nome contem \"{0}\" retornou \"{1}\"", new Object[] { nome, result });
		return entityManager.createQuery(cq).getResultList();	}

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public List<Musica> buscarPorArtista(Artista artista) {
		// FIXME: auto-generated method stub
		return null;
	}

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public List<Musica> buscarPorGenero(TipoGenero genero) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		logger.log(Level.FINE, "Recuperando as Musicas do genero \"{0}\"...", genero);

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Musica> cq = cb.createQuery(Musica.class);
		Root<Musica> root = cq.from(Musica.class);
		ListJoin<Musica, TipoGenero> Musica_genero = root.join(Musica_.generos);
		
		cq.where(cb.equal(Musica_genero, genero));
		List<Musica> result = entityManager.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Recuperandos as Musicas pelo genero musical \"{0}\" retornou \"{1}\"", new Object[] { genero, result });

		return result;
	}

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public List<Musica> buscarPorIdioma(TipoIdioma idioma) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		logger.log(Level.FINE, "Recuperando a Musica cuja idioma eh \"{0}\"...", idioma);

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Musica> cq = cb.createQuery(Musica.class);
		Root<Musica> root = cq.from(Musica.class);

		cq.where(cb.equal(root.get(Musica_.idioma), idioma));
		List<Musica> result = entityManager.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Recuperando a Musica pelo pais \"{0}\" retornou \"{1}\"", new Object[] { idioma, result });
		return result;
	}

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public List<Musica> buscarGeral(String nomeDaMusica, String nomeDoArtista, TipoGenero genero, TipoIdioma idioma,
			Boolean ranking, Integer numero) {
		// FIXME: auto-generated method stub
		return null;
	}

}