package br.ufes.informatica.musicplus.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.musicplus.core.application.LoginService;
import br.ufes.informatica.musicplus.core.application.MusicaService;
import br.ufes.informatica.musicplus.core.application.PlaylistService;
import br.ufes.informatica.musicplus.core.application.UsuarioService;
import br.ufes.informatica.musicplus.core.domain.Musica;
import br.ufes.informatica.musicplus.core.domain.Playlist;
import br.ufes.informatica.musicplus.core.domain.Usuario;

/** TODO: generated by FrameWeb. Should be documented. */
@Model
@SessionScoped
public class PlaylistController extends JSFController {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private PlaylistService playlistService;
	
	@EJB
	private LoginService loginService ;
	
	@EJB
	private MusicaService musicaService; 
	

	@EJB
	private UsuarioService usuarioService; 
	
	private Playlist playlistEscolhida; 
	
	private List<Musica> musicasNaPlaylist ;
	
	private List<Playlist> playlists ;
	
	/** TODO: generated by FrameWeb. Should be documented. */
	private Musica musicaEscolhida;
	
	private List<Musica> musicas ;

	/** TODO: generated by FrameWeb. Should be documented. */
	private Usuario usuarioLogado;

	private String nome ;
	
	private String nomeNovo ;

	@PostConstruct
	public void init() {
		usuarioLogado = loginService.getCurrentUser() ;
		playlistEscolhida = null ;
		playlists = playlistService.listarTodasAsPlaylistsDoUsuario(usuarioLogado);
		musicas = new ArrayList<Musica>();
		for (Musica musica : usuarioLogado.getMusicasFavoritadas()) {
			musicas.add(musica) ;
		}
	}
	
	/** TODO: generated by FrameWeb. Should be documented. */
	public String adicionarMusicaNaPlaylist() {
		// FIXME: auto-generated method stub
		return null;
	}
	
	/** TODO: generated by FrameWeb. Should be documented. */
	public String criarPlaylist() {
		if (playlistService.buscarPorNome(usuarioLogado, nome) == null) {
			playlistService.criarPlaylist(usuarioLogado, nome);
			return "/core/playlist/sucessoPlaylistCriada.xhtml?faces-redirect=true";
		}else {
			return "/core/playlist/erroAoCriar.xhtml?faces-redirect=true";
		}
	}
	
	public String renomearPlaylist() {
		if (playlistService.buscarPorNome(usuarioLogado, nomeNovo) == null) {
			playlistEscolhida.setNome(nomeNovo);
			playlistService.salvarPlaylist(playlistEscolhida);
			playlistEscolhida = null ;
			return redirecionarPlaylists() ;
		}else {
			return "/core/playlist/erroAoCriar.xhtml?faces-redirect=true";
		}
	}
	
	public String redirectRenomearPlaylist() {
		return "/core/playlist/renomearPlaylist.xhtml?faces-redirect=true";
	}
	
	public String deletePlaylist() {
		playlists.remove(playlistEscolhida);
		playlistService.deletarPlaylist(playlistEscolhida);
		playlistEscolhida = null ;
		return redirecionarPlaylists() ;
	}
	
	
	public String listarMusicasDaPlaylist() {
		musicasNaPlaylist = musicaService.buscarPorPlaylist(playlistEscolhida) ;
		return "/core/playlist/MusicasDaPlaylist.xhtml?faces-redirect=true";
	}
	
	public String redirecionarCadastro() {
		return "/core/playlist/criarPlaylist.xhtml?faces-redirect=true";
	}
	
	public String redirecionarPlaylists() {
		return "/core/playlist/playlists.xhtml?faces-redirect=true";
	}
	
	public String redirecionarBuscarMusicas() {
		return "/core/buscar/Musica.xhtml?faces-redirect=true";
	}

	public String redirecionarHome() {
		return "/core/home/index.xhtml?faces-redirect=true";
	}

	/** Getter for playlist. */
	public Playlist getPlaylistEscolhida() {
		return playlistEscolhida;
	}

	/** Setter for playlist. */
	public void setPlaylistEscolhida(Playlist playlist) {
		this.playlistEscolhida = playlist;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Musica> getMusicasNaPlaylist() {
		return musicasNaPlaylist;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setMusicasNaPlaylist(List<Musica> musicasNaPlaylist) {
		this.musicasNaPlaylist = musicasNaPlaylist;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public String getNomeNovo() {
		return nomeNovo;
	}

	public void setNomeNovo(String nomeNovo) {
		this.nomeNovo = nomeNovo;
	}

	public Musica getMusicaEscolhida() {
		return musicaEscolhida;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicaEscolhida(Musica musicaEscolhida) {
		this.musicaEscolhida = musicaEscolhida;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

}