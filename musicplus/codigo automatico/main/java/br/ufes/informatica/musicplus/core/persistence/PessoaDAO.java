package br.ufes.informatica.musicplus.core.persistence;

import javax.ejb.Local;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface PessoaDAO extends BaseDAO<Pessoa> {
	
	/** TODO: generated by FrameWeb. Should be documented. */
	public Pessoa buscarPorEmail(String email);
	
}