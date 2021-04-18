package br.ufes.informatica.musicplus.core.application;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.informatica.musicplus.core.domain.Usuario;
import br.ufes.informatica.musicplus.core.persistence.UsuarioDAO;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
//@RolesAllowed("Admin")
public class UsuarioServiceBean implements UsuarioService {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioDAO usuarioDAO;

	public void save(Usuario usuario) {
		try {
			usuarioDAO.buscarPorEmail(usuario.getEmail());	
			return;
//			System.out.println("email ja existe...");
		}
		catch(Exception e) {
			System.out.println("email nao existe ainda...");
		}
		
		
		try {
			usuario.setSenha(TextUtils.produceBase64EncodedMd5Hash(usuario.getSenha()));
			usuarioDAO.save(usuario);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		catch(EJBAccessException  e) {
			System.out.println("Usuario nao tem permissao para acessar essa pagina.");
			return;
		}
		catch(Exception  e) {
			System.out.println("Erro Generico");
			return;
		}
		
	}


}