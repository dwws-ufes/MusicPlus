package br.ufes.informatica.musicplus.core.controller;
import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.informatica.musicplus.core.application.LoginService;

/**
 * Stateless session bean implementing the login service. See the implemented interface
 * documentation for details.
 * @author Vítor E. Silva Souza (https://github.com/vitorsouza/)
 */
// inspired and adapted from marvin's code

@Named
@SessionScoped
public class SessionController implements Serializable {
private static final long serialVersionUID = 1L;
	
	@EJB
	private LoginService loginService;
	
	private String email;
	
	private String password;
	
//	private Usuario currentUser; (GET BACK HERE)
	
//	public User getCurrentUser() { (GET BACK HERE)
//		return currentUser;
//	}

	/**
	 * Accesses the Login service to authenticate the user given his email and password.
	 */
	public String login() {
		try {
			// Uses the Session Information bean to authenticate the user.
			
			loginService.login(email, password);

			// Also authenticates on JAAS.
			// FIXME: is there a way to do this at the application package (in the EJB)?
			try {
				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				request.login(email, password);
			}
			catch (Exception e) {
				// handling exception
			}
		}
		catch (Exception e) {
			// handling exception
		}

		// If everything is OK, stores the current user and redirects back to the home screen.
//		currentUser = loginService.getCurrentUser();
		return "core/home.xhtml?faces-redirect=true";
	}
	
}