package br.ufes.informatica.musicplus.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.musicplus.core.application.ArtistaService;
import br.ufes.informatica.musicplus.core.application.MusicaService;
import br.ufes.informatica.musicplus.core.application.SugestaoService;
import br.ufes.informatica.musicplus.core.domain.Artista;
import br.ufes.informatica.musicplus.core.domain.Musica;
import br.ufes.informatica.musicplus.core.domain.TipoGenero;
import br.ufes.informatica.musicplus.core.domain.TipoIdioma;

/** TODO: generated by FrameWeb. Should be documented. */
@Model
@SessionScoped
public class MusicaController extends JSFController {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private MusicaService musicaService;
	
	@EJB
	private ArtistaService artistaService;
	
	@EJB
	private SugestaoService sugestaoService ;
	
	/** TODO: generated by FrameWeb. Should be documented. */
	private String nomeDoArtista;

	private Artista artistaEscolhido ;
	
	private Musica musicaEscolhida ;

	private TipoIdioma[] idiomasEscolhidos;
	
	private TipoGenero[] generosEscolhidos;

	/** TODO: generated by FrameWeb. Should be documented. */
	private String nomeDaMusica;

	/** TODO: generated by FrameWeb. Should be documented. */
	private List<Musica> musicas;
	
	private Boolean desabilitarBotao ;
	
	private Boolean desabilitarBotaoBusca ;
	
	private Boolean desabilitarBotaoCadastro ;
	
	private Boolean editando ;
	
	private Integer numSugestoes;
	
	private String porRankingOuAleatorio;

	private boolean allGeneros ;
	
	private boolean allIdiomas ;
	
	private TipoIdioma[] todosOsIdiomas;
	
	private TipoGenero[] todosOsGeneros ;
	
	private TipoIdioma idiomaEscolhido ;
		
	private Date duracao;

	private Date dataDeLancamento ;
	
	private String mensagem ;
	
	@PostConstruct
	public void init() {
		auxBuscarMusica();
		auxCleanForPedirSugestaoDeMusica();
		auxCadastrarMusica();
	}
	
	public void habilitarBotaoBusca() {
		if (nomeDoArtista != null || nomeDaMusica != null) {
			desabilitarBotaoBusca = false ;
		}else {
			desabilitarBotaoBusca = true ;
		}
	}
	
	public void auxBuscarMusica() {
		editando = false ;
		nomeDoArtista = null ;
		nomeDaMusica = null ;
		desabilitarBotaoBusca = true ;
		//return "/core/buscar/Musica.xhtml?faces-redirect=true" ;
	}
	
	public void auxCleanForPedirSugestaoDeMusica() {
		editando = false ;
		musicas = null ;
		todosOsGeneros = TipoGenero.todos();
		todosOsIdiomas = TipoIdioma.todos();
		generosEscolhidos = null ;
		allGeneros = false ;
		allIdiomas = false ;
		numSugestoes = 1; 
		desabilitarBotao = true ;
		idiomaEscolhido = null ;
		//return "/core/sugestao/Musica.xhtml?faces-redirect=true" ;
	}
	
	public void auxCadastrarMusica() {
		editando = false ;
		mensagem = null ;
		duracao = null ;
		dataDeLancamento = null ;
		nomeDaMusica = null ;
		desabilitarBotaoCadastro = true ;
		//return "/core/cadastrar/Musica.xhtml?faces-redirect=true" ;
	}

	public String redirecionaCadastroMusica(){
		return "/core/cadastrar/Musica.xhtml?faces-redirect=true" ;
	}
	
	public String buscarMusica() {
		if (nomeDoArtista == null || nomeDoArtista.trim() == null) {
			musicas = musicaService.buscarPorNome(nomeDaMusica);
			return musicasEncontradas();
		}
		List<Artista> artistas = artistaService.buscarPorNome(nomeDoArtista);
		if (nomeDaMusica == null || nomeDaMusica.trim() == null) {
			musicas = musicaService.buscarPorArtistas(artistas);
		}else {
			musicas = musicaService.buscarMusica(nomeDaMusica, artistas) ;
		}
		return musicasEncontradas();
	}
	
	public String favoritar() {
		// IF (USUARIO AINDA NAO FAVORITOU MUSICA) {
		musicaEscolhida.incrementaNumVezesFavoritado();
		musicaService.save(musicaEscolhida);
		// }
		return paginaInicial() ;
	}
	
	public String paginaInicial() {
		return "/index.xhtml?faces-redirect=true" ;
	}
	
	public String editar() {
		editando = true ;
		nomeDaMusica = musicaEscolhida.getNome() ;
		dataDeLancamento = musicaEscolhida.getDataLancamento();
		duracao = musicaEscolhida.getDuracao() ;
		List<TipoGenero> aux = musicaEscolhida.getGenero() ;
		generosEscolhidos = aux.toArray(new TipoGenero[aux.size()]) ;
		idiomaEscolhido = musicaEscolhida.getIdioma() ;
		return redirecionaCadastroMusica() ;
	}
	
	public String musicasEncontradas() {
		return "/core/buscar/MusicasEncontradas.xhtml?faces-redirect=true" ;
	}
	
	public void habilitarBotao() {
		desabilitarBotao = false ;
	}
	
	public String buscarMusicaPorArtista() {
		musicas = musicaService.buscarPorArtista(artistaEscolhido); 
		return musicasEncontradas() ;
	}
	
	
	public void escolherTodosOsGeneros() {
		if (allGeneros == true) {
			generosEscolhidos = todosOsGeneros ;
		}else {
			generosEscolhidos = null ;
		}
	}
	
	public boolean warningVazioGenero() {
		return (generosEscolhidos == null);
	}
	
	public boolean warningVazioIdioma() {
		return (idiomasEscolhidos == null);
	}
	
	public void dispensarTodosOsGeneros() {
		allGeneros = false ;
		if (generosEscolhidos == null) {
			return ;
		}
		if (generosEscolhidos.length == 0) {
			return ;
		}
		allGeneros = true ;
		if (generosEscolhidos.length != todosOsGeneros.length) {
			allGeneros = false ;
		}
		return ;
	}
	
	public void escolherTodosOsIdiomas() {
		if (allIdiomas == true) {
			idiomasEscolhidos = todosOsIdiomas ;
			//warningVazioIdioma = false ;
		}else {
			idiomasEscolhidos = null ;
			//warningVazioIdioma = true ;
		}
	}
	
	public void dispensarTodosOsIdiomas() {
		//warningVazioIdioma = true ;
		allIdiomas = false ;
		if (idiomasEscolhidos == null) {
			return ;
		}
		if (idiomasEscolhidos.length == 0) {
			return ;
		}
		//warningVazioIdioma = false ;
		allIdiomas = true ;
		if (idiomasEscolhidos.length != todosOsIdiomas.length) {
			allIdiomas = false ;
		}
		return ;
	}

	/** TODO: generated by FrameWeb. Should be documented. */
	public String pedirSugestaoMusica() {
		if (generosEscolhidos == null || idiomasEscolhidos == null) {
			musicas = null ;
		}else {
			List<TipoIdioma> idiomas = new ArrayList<TipoIdioma>(); 
			for (TipoIdioma p : idiomasEscolhidos) {
				idiomas.add(p);
			}
			List<TipoGenero> generos = new ArrayList<TipoGenero>(); 
			for (TipoGenero g : generosEscolhidos) {
				generos.add(g);
			}
			musicas = sugestaoService.pedirSugestaoMusica(porRankingOuAleatorio, idiomas, allIdiomas, numSugestoes, generos, allGeneros);
		}
		auxBuscarMusica();
		auxCadastrarMusica();
		return musicasEncontradas() ;
	}
	

	public String salvarMusica() {
		mensagem = "Você deixou de preencher o(s) seguinte(s) campo(s) obrigatório(s): " ;
		boolean limpo = true ;
		if (nomeDaMusica == null || nomeDaMusica.trim() == null){
			mensagem = mensagem + "Nome da Música; " ;
			limpo = false ;
		}	
		if (idiomaEscolhido == null){
			mensagem = mensagem + "Idioma da Música; " ;
			limpo = false ;
		}
		if (duracao == null){
			mensagem = mensagem + "Duração da Música; ";
			limpo = false ;
		}
		if (limpo){
			mensagem = null ;
			if (editando) {
				return salvarMusicaPasso2(musicaEscolhida) ;
			}else {
				return salvarMusicaPasso2(new Musica()) ;
			}
		}else{
			mensagem = mensagem.substring(0, mensagem.length() - 2);
			return redirecionaCadastroMusica() ;
		}
	}
		
	public String salvarMusicaPasso2(Musica musica) {
			if  (editando) {
				musica.getGenero().clear();
			}
			for (TipoGenero g : generosEscolhidos) {
				musica.addGenero(g);
			}
	    	musica.setIdioma(idiomaEscolhido);
	    	musica.setNome(nomeDaMusica);
	    	if (!editando) {
	    		musica.setNumVezesFavoritado(0);
	    	}
	    	musica.setDataLancamento(dataDeLancamento);
	    	musica.setDuracao(duracao);
	    	musicaService.save(musica);
			auxBuscarMusica();
			auxCleanForPedirSugestaoDeMusica();
			editando = false ;
	    	return paginaInicial();
    	//return musicaArtistaAdicionar() ;
    }
	
	
	//Só getters e setters a partir daqui


	public Musica getMusicaEscolhida() {
		return musicaEscolhida;
	}

	public void setMusicaEscolhida(Musica musicaEscolhida) {
		this.musicaEscolhida = musicaEscolhida;
	}
	
	public Boolean getDesabilitarBotao() {
		return desabilitarBotao;
	}

	public void setDesabilitarBotao(Boolean desabilitarBotao) {
		this.desabilitarBotao = desabilitarBotao;
	}
	
	public Artista getArtistaEscolhido() {
		return artistaEscolhido;
	}

	public void setArtistaEscolhido(Artista artistaEscolhido) {
		this.artistaEscolhido = artistaEscolhido;
	}
	
	/** Getter for nomeDoArtista. */
	public String getNomeDoArtista() {
		return nomeDoArtista;
	}

	/** Setter for nomeDoArtista. */
	public void setNomeDoArtista(String nomeDoArtista) {
		this.nomeDoArtista = nomeDoArtista;
	}

	/** Getter for nomeDaMusica. */
	public String getNomeDaMusica() {
		return nomeDaMusica;
	}

	/** Setter for nomeDaMusica. */
	public void setNomeDaMusica(String nomeDaMusica) {
		this.nomeDaMusica = nomeDaMusica;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public TipoIdioma[] getIdiomasEscolhidos() {
		return idiomasEscolhidos;
	}

	public void setIdiomasEscolhidos(TipoIdioma[] idiomasEscolhidos) {
		this.idiomasEscolhidos = idiomasEscolhidos;
	}

	public TipoGenero[] getGenerosEscolhidos() {
		return generosEscolhidos;
	}

	public void setGenerosEscolhidos(TipoGenero[] generosEscolhidos) {
		this.generosEscolhidos = generosEscolhidos;
	}

	public String getPorRankingOuAleatorio() {
		return porRankingOuAleatorio;
	}

	public void setPorRankingOuAleatorio(String porRankingOuAleatorio) {
		this.porRankingOuAleatorio = porRankingOuAleatorio;
	}

	public boolean isAllGeneros() {
		return allGeneros;
	}

	public void setAllGeneros(boolean allGeneros) {
		this.allGeneros = allGeneros;
	}

	public boolean isAllIdiomas() {
		return allIdiomas;
	}

	public void setAllIdiomas(boolean allIdiomas) {
		this.allIdiomas = allIdiomas;
	}

	public TipoIdioma[] getTodosOsIdiomas() {
		return todosOsIdiomas;
	}

	public void setTodosOsIdiomas(TipoIdioma[] todosOsIdiomas) {
		this.todosOsIdiomas = todosOsIdiomas;
	}

	public TipoGenero[] getTodosOsGeneros() {
		return todosOsGeneros;
	}

	public void setTodosOsGeneros(TipoGenero[] todosOsGeneros) {
		this.todosOsGeneros = todosOsGeneros;
	}

	public Boolean getDesabilitarBotaoBusca() {
		return desabilitarBotaoBusca;
	}

	public void setDesabilitarBotaoBusca(Boolean desabilitarBotaoBusca) {
		this.desabilitarBotaoBusca = desabilitarBotaoBusca;
	}
	
	public void setDuracao(Date duracao) {
		this.duracao = duracao;
	}

	public void setDataDeLancamento(Date dataDeLancamento) {
		this.dataDeLancamento = dataDeLancamento;
	}
	
	public Date getDuracao() {
		return duracao;
	}
	
	public Date getDataDeLancamento() {
		return dataDeLancamento;
	}

	public TipoIdioma getIdiomaEscolhido() {
		return idiomaEscolhido;
	}

	public void setIdiomaEscolhido(TipoIdioma idiomaEscolhido) {
		this.idiomaEscolhido = idiomaEscolhido;
	}

	public Boolean getDesabilitarBotaoCadastro() {
		return desabilitarBotaoCadastro;
	}

	public void setDesabilitarBotaoCadastro(Boolean desabilitarBotaoCadastro) {
		this.desabilitarBotaoCadastro = desabilitarBotaoCadastro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Integer getNumSugestoes() {
		return numSugestoes;
	}

	public void setNumSugestoes(Integer numSugestoes) {
		this.numSugestoes = numSugestoes;
	}



}
