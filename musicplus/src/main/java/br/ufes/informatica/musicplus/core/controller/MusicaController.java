package br.ufes.informatica.musicplus.core.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.musicplus.core.application.ArtistaService;
import br.ufes.informatica.musicplus.core.application.LoginService;
import br.ufes.informatica.musicplus.core.application.MusicaService;
import br.ufes.informatica.musicplus.core.application.SugestaoService;
import br.ufes.informatica.musicplus.core.application.UsuarioService;
import br.ufes.informatica.musicplus.core.domain.Artista;
import br.ufes.informatica.musicplus.core.domain.Musica;
import br.ufes.informatica.musicplus.core.domain.TipoGenero;
import br.ufes.informatica.musicplus.core.domain.TipoIdioma;
import br.ufes.informatica.musicplus.core.domain.Usuario;

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
	
	@EJB
	private LoginService loginService ;
	
	@EJB
	private UsuarioService usuarioService ;
	
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
				
	private Boolean editando ;
	
	private Integer numSugestoes;
	
	private String porRankingOuAleatorio;

	private boolean allGeneros ;
	
	private boolean allIdiomas ;
	
	private TipoIdioma[] todosOsIdiomas;
	
	private TipoGenero[] todosOsGeneros ;
	
	private TipoIdioma idiomaEscolhido ;
		
	private String duracao;

	private Date dataDeLancamento ;
	
	private String mensagem ;
	
	private List<Artista> artistas ;
	
	@PostConstruct
	public void init() {
		auxCleanForBuscarMusica();
		auxCleanForPedirSugestaoDeMusica();
		auxCadastrarMusica();
	}
	
	public Boolean desabilitarBotaoAdRmArtistaEmBuscar() {
		return musicaEscolhida == null ;
	}
	
	
	public Boolean desabilitarBotaoAdRmArtistaEmAdmRm() {
		return artistaEscolhido == null ;
	}
	
	public void auxCleanForBuscarMusica() {
		editando = false ;
		nomeDoArtista = null ;
		nomeDaMusica = null ;
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
		numSugestoes = null; 
		idiomaEscolhido = null ;
		musicaEscolhida = null ;
		//return "/core/sugestao/Musica.xhtml?faces-redirect=true" ;
	}
	
	public void auxCadastrarMusica() {
		editando = false ;
		mensagem = null ;
		duracao = null ;
		dataDeLancamento = null ;
		nomeDaMusica = null ;
		//return "/core/cadastrar/Musica.xhtml?faces-redirect=true" ;
	}

	public String redirecionaCadastroMusica(){
		return "/core/cadastrar/Musica.xhtml?faces-redirect=true" ;
	}
	
	
	public Boolean auxNomeNull(String nome) {
		return (nome == null || nome.trim() == null) ;
	}
	
	public String buscarMusicas() {
		if (auxNomeNull(nomeDoArtista) && auxNomeNull(nomeDaMusica))  {
			musicas = musicaService.buscarTodasMusicas();
			return musicasEncontradas();
		}
		if (auxNomeNull(nomeDoArtista)) {
			musicas = musicaService.buscarPorNome(nomeDaMusica);
			return musicasEncontradas();
		}
		List<Artista> artistas = artistaService.buscarPorNome(nomeDoArtista);
		if (auxNomeNull(nomeDaMusica)) {
			musicas = musicaService.buscarPorArtistas(artistas);
			return musicasEncontradas();
		}
		musicas = musicaService.buscarMusica(nomeDaMusica, artistas) ;
		return musicasEncontradas();
	}
	
	public String favoritar() {
		Usuario usuarioLogado = loginService.getCurrentUser();
		if (usuarioLogado.getMusicasFavoritadas() == null || !usuarioLogado.getMusicasFavoritadas().contains(musicaEscolhida)) {
			musicaEscolhida.incrementaNumVezesFavoritado();
			musicaService.save(musicaEscolhida);
			usuarioLogado.addMusicaFavorita(musicaEscolhida);
			usuarioService.atualizar(usuarioLogado);
		}
		auxCleanForBuscarMusica();
		auxCleanForPedirSugestaoDeMusica();
		auxCadastrarMusica();
		return paginaInicial() ;
	}
	
	public String paginaInicial() {
		return "/core/home/index.xhtml?faces-redirect=true" ;
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
	
	public boolean desabilitarBotao() {
		return musicaEscolhida == null ;
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
		auxCleanForBuscarMusica();
		auxCadastrarMusica();
		musicaEscolhida = null ;
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
	
	public Boolean estaEditando() {
		return editando ;
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
			auxCleanForPedirSugestaoDeMusica();
	    	return "/core/cadastrar/sucessoMusicaCriada.xhtml?faces-redirect=true";
    }
	
	// Adicionar e Remover Artistas de Música
	
	public String descobrirMusicas(){
		musicaEscolhida = null ;
		artistas = null ;
		musicas = musicaService.buscarPorNome(nomeDaMusica);
		nomeDaMusica = null ;
		return "/core/cadastrar/BuscarMusica.xhtml?faces-redirect=true" ;
	}
	
	public String descobrirArtistas(){
		artistas = artistaService.buscarPorNome(nomeDoArtista);
		List<Artista> artistasRemovidos = new ArrayList<Artista>();
		for (Artista a : artistas) {
			if (musicaEscolhida.getArtistas().contains(a)) {
				artistasRemovidos.add(a) ;
			}
		}
		artistas.removeAll(artistasRemovidos) ;
		nomeDoArtista = null ;
		return redirectToMusicaArtistaAdicionar() ;
	}
	
	public void auxMusicaArtistaDefaults() {
		musicas = null ;
		artistaEscolhido = null ;
	}
	
	public String redirectToMusicaArtistaRemover() {
		auxMusicaArtistaDefaults() ;
		return "/core/cadastrar/MusicaArtistaRemover.xhtml?faces-redirect=true" ;
	}
	
	public String redirectToMusicaArtistaAdicionar() {
		auxMusicaArtistaDefaults() ;
		return "/core/cadastrar/MusicaArtistaAdicionar.xhtml?faces-redirect=true" ;
	}
	
	public void salvarRemoverAux() {
		musicaService.save(musicaEscolhida);
		artistas = null ;
		musicas = null ;
		musicaEscolhida = null ;
	}
	
	public String salvarArtistaMusica() {
		this.musicaEscolhida.addArtista(this.artistaEscolhido) ;
		salvarRemoverAux();
		return paginaInicial() ;
	}
	
	public String removerArtistaMusica() {
		this.musicaEscolhida.removerArtista(this.artistaEscolhido) ;
		salvarRemoverAux() ;
		return paginaInicial() ;
	}
	
	public String deleteMusica() {
		musicaService.delete(musicaEscolhida);
		musicas.remove(musicaEscolhida) ;
		musicaEscolhida = null ;
		return musicasEncontradas() ;
	}
	
	//Só getters e setters a partir daqui


	public Musica getMusicaEscolhida() {
		return musicaEscolhida;
	}

	public void setMusicaEscolhida(Musica musicaEscolhida) {
		this.musicaEscolhida = musicaEscolhida;
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
	
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public void setDataDeLancamento(Date dataDeLancamento) {
		this.dataDeLancamento = dataDeLancamento;
	}
	
	public String getDuracao() {
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

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}
	
	public void suggestMusicAttributes() {
		duracao= null;
		generosEscolhidos=null;
		dataDeLancamento=null;
		
		String name= nomeDaMusica;
		if (name != null && name.length() > 3) {
			String nameToSearch = "\"" + name + "\"" ;
			String query = "";
			query= "PREFIX dbo: <http://dbpedia.org/ontology/>\n"
				+ "PREFIX dbp: <http://dbpedia.org/property/>\n"
				+ "SELECT ?Tempo?Data?Genero\n"
				+ "WHERE {\n"
				+ "?uri a dbo:Song;\n"
				+ "dbp:name" + nameToSearch + "@en;\n"
				+ "dbo:runtime?Tempo;\n"
				+ "dbo:releaseDate?Data;\n"
				+ "dbp:genre?Genero.\n"
				+ "}";
	
			try {
				QueryExecution queryExecution =
					QueryExecutionFactory.sparqlService("https://dbpedia.org/sparql",query);
				ResultSet results = queryExecution.execSelect();
						
				if (results.hasNext()) {
					QuerySolution querySolution = results.next();
					Literal tempoObtido = querySolution.getLiteral("Tempo");
					duracao = "" + tempoObtido.getValue();
					Literal dataLancamentoLiteral = querySolution.getLiteral("Data");
					Literal generoLiteral = querySolution.getLiteral("Genero");
					
					String generoObtido = ""+generoLiteral.getValue();
									
					TipoGenero[] generosList= new TipoGenero[8]; 
					
					List<TipoGenero> tipoGeneroList= new ArrayList<TipoGenero>();
					
					if(generoObtido.contains("pop") || generoObtido.contains("Pop")) {
						tipoGeneroList.add(TipoGenero.Pop);
					}
					
					if(generoObtido.contains("indie") || generoObtido.contains("Indie")) {
						tipoGeneroList.add(TipoGenero.Indie);
					}
					
					if(generoObtido.contains("electronic") || generoObtido.contains("Electronic")) {
						tipoGeneroList.add(TipoGenero.Eletronica);
					}
					
					if(generoObtido.contains("rock") || generoObtido.contains("Rock")) {
						tipoGeneroList.add(TipoGenero.Rock);
					}
					
					if(generoObtido.contains("jazz") || generoObtido.contains("Jazz")) {
						tipoGeneroList.add(TipoGenero.Jazz);
					}
					
					if(generoObtido.contains("country") || generoObtido.contains("Country")) {
						tipoGeneroList.add(TipoGenero.Country);
					}
					
					if(generoObtido.contains("gospel") || generoObtido.contains("Gospel")) {
						tipoGeneroList.add(TipoGenero.Gospel);
					}
					
					generosList = tipoGeneroList.toArray(generosList);
					
					generosEscolhidos = generosList;
					
					Date dataLancamentoObtido;
					try {
						dataLancamentoObtido = new SimpleDateFormat("yyyy-MM-dd").parse("" + dataLancamentoLiteral.getValue());
						dataDeLancamento = dataLancamentoObtido;
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
