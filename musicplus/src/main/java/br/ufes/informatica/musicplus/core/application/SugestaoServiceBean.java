package br.ufes.informatica.musicplus.core.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.ufes.informatica.musicplus.core.domain.TipoGenero;
import br.ufes.informatica.musicplus.core.domain.TipoIdioma;
import br.ufes.informatica.musicplus.core.domain.TipoPais;
import br.ufes.informatica.musicplus.core.persistence.ArtistaDAO;
import br.ufes.informatica.musicplus.core.persistence.MusicaDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.informatica.musicplus.core.domain.Artista;
import br.ufes.informatica.musicplus.core.domain.Musica;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class SugestaoServiceBean implements SugestaoService {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private MusicaDAO musicaDAO;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private ArtistaDAO artistaDAO;

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public List<Artista> pedirSugestaoArtista(String rankingOuAleatorio, List<TipoPais> paises, Boolean allPaises, Integer numero, List<TipoGenero> generos, Boolean allGeneros) {
		List<Artista> artistas1 = new ArrayList<Artista>() ;
		List<Artista> artistas2 = new ArrayList<Artista>() ;
		if (!allGeneros) {
			artistas1.addAll(pedirSugestaoArtistaGenero(generos));
		}
		if (!allPaises)	{
			artistas2.addAll(pedirSugestaoArtistaPais(paises));
		}
		if (allPaises && allGeneros) {
			return artistaDAO.retrieveAll(); 
		}
		if (allPaises) {
			return convertArtistas(artistas1);
		}
		if (allGeneros) {
			return convertArtistas(artistas2);
		}
		return mergeListaArtista(artistas1, artistas2);
	}
	
	@Override
	public List<Musica> pedirSugestaoMusica(String rankingOuAleatorio, List<TipoIdioma> idiomas, Boolean allIdiomas, Integer numero, List<TipoGenero> generos, Boolean allGeneros) {
		List<Musica> musicas1 = new ArrayList<Musica>() ;
		List<Musica> musicas2 = new ArrayList<Musica>() ;
		if (!allGeneros) {
			musicas1.addAll(pedirSugestaoMusicaGenero(generos));
		}
		if (!allIdiomas)	{
			musicas2.addAll(pedirSugestaoMusicaIdioma(idiomas));
		}
		if (allIdiomas && allGeneros) {
			return musicaDAO.retrieveAll(); 
		}
		if (allIdiomas) {
			return convertMusicas(musicas1);
		}
		if (allGeneros) {
			return convertMusicas(musicas2);
		}
		return mergeListaMusica(musicas1, musicas2);
	}
	
	public List<Musica> mergeListaMusica(List<Musica> musicas1, List<Musica> musicas2){
		return musicas1.stream()
			    .filter(musicas2::contains)
			    .collect(Collectors.toList());
	}
	
	public List<Artista> mergeListaArtista(List<Artista> artistas1, List<Artista> artistas2){
		return artistas1.stream()
			    .filter(artistas2::contains)
			    .collect(Collectors.toList());
	}
	
	public List<Artista> convertArtistas(List<Artista> artistas){
		return artistas.stream().distinct().collect(Collectors.toList());
	}
	
	public List<Musica> convertMusicas(List<Musica> musicas){
		return musicas.stream().distinct().collect(Collectors.toList());
	}
	
	public List<Artista> pedirSugestaoArtistaGenero(List<TipoGenero> generos){
		List<Artista> artista_genero = new ArrayList<Artista>();
		try {
			for (TipoGenero g : generos) {
				artista_genero.addAll(artistaDAO.buscarPorGenero(g)) ;
			}
			return artista_genero.stream().distinct().collect(Collectors.toList());
		}catch (PersistentObjectNotFoundException | MultiplePersistentObjectsFoundException e){
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Musica> pedirSugestaoMusicaGenero(List<TipoGenero> generos){
		List<Musica> musica_genero = new ArrayList<Musica>();
		try {
			for (TipoGenero g : generos) {
				musica_genero.addAll(musicaDAO.buscarPorGenero(g)) ;
			}
			return convertMusicas(musica_genero);
		}catch (PersistentObjectNotFoundException | MultiplePersistentObjectsFoundException e){
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Artista> pedirSugestaoArtistaPais(List<TipoPais> paises){
		List<Artista> artista_pais = new ArrayList<Artista>();
		try {
			for (TipoPais p : paises) {
				artista_pais.addAll(artistaDAO.buscarPorPais(p)) ;
			}
			return convertArtistas(artista_pais);
		}catch (PersistentObjectNotFoundException | MultiplePersistentObjectsFoundException e){
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Musica> pedirSugestaoMusicaIdioma(List<TipoIdioma> idiomas){
		List<Musica> musica_idioma = new ArrayList<Musica>();
		try {
			for (TipoIdioma lingua : idiomas) {
				musica_idioma.addAll(musicaDAO.buscarPorIdioma(lingua)) ;
			}
			return convertMusicas(musica_idioma);
		}catch (PersistentObjectNotFoundException | MultiplePersistentObjectsFoundException e){
			e.printStackTrace();
			return null ;
		}
	}

	/** TODO: generated by FrameWeb. Should be documented. */
	private List<Artista> selecionarArtistasAleatorios(List<Artista> listaArtistas, Integer numero) {
		// FIXME: auto-generated method stub
		return null;
	}

	/** TODO: generated by FrameWeb. Should be documented. */
	private List<Musica> selecionarMusicasAleatorias(List<Musica> listaMusicas, Integer numero) {
		// FIXME: auto-generated method stub
		return null;
	}

}