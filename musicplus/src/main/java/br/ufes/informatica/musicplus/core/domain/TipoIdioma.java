package br.ufes.informatica.musicplus.core.domain;

/** TODO: generated by FrameWeb. Should be documented. */
public enum TipoIdioma {
	Ingles, Portugues, Espanhol, Instrumental, Qualquer_Idioma;
	
	public static TipoIdioma[] todosMenosQualquerUm() {
		return (new TipoIdioma[] { 
				TipoIdioma.Ingles, 
				TipoIdioma.Portugues, 
				TipoIdioma.Espanhol,
				TipoIdioma.Instrumental
				});
	}
}