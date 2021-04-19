package br.ufes.informatica.musicplus.core.persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.informatica.musicplus.core.domain.Playlist;
import br.ufes.informatica.musicplus.core.domain.Playlist_;
import br.ufes.informatica.musicplus.core.domain.Usuario;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class PlaylistDAOJPA extends BaseJPADAO<Playlist> implements PlaylistDAO {
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
	public List<Playlist> buscarPorUsuario(Usuario usuario) {
		logger.log(Level.FINE, "Recuperando as playlists do usuario \"{0}\"...", usuario);

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Playlist> cq = cb.createQuery(Playlist.class);
		Root<Playlist> root = cq.from(Playlist.class);
		Join<Playlist, Usuario> Playlist_usuario = root.join(Playlist_.usuario);
		
		cq.where(cb.equal(Playlist_usuario, usuario));
		List<Playlist> result = entityManager.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Recuperandos as Playlists do usuario \"{0}\" retornou \"{1}\"", new Object[] { usuario, result });

		return result;
	}

}