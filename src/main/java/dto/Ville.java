package dto;

public class Ville {

	private String nomCommune;
	private String codeCommune;
	private String codePostal;
	private String ligne5;
	private String libelleAcheminement;
	private Coordonnee coordonnee;
	
	public Ville() {}
	
	public Ville(String nomCommune, String codeCommune, String codePostal, String ligne5,
			String libelleAcheminement, Coordonnee coordonnee) {
		super();
		this.nomCommune = nomCommune;
		this.codeCommune = codeCommune;
		this.codePostal = codePostal;
		this.ligne5 = ligne5;
		this.libelleAcheminement = libelleAcheminement;
		this.coordonnee = coordonnee;
	}
	
	public String getNomCommune() {
		return nomCommune;
	}
	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}
	public String getCodeCommune() {
		return codeCommune;
	}
	public void setCodeCommune(String codeCommune) {
		this.codeCommune = codeCommune;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getLigne5() {
		return ligne5;
	}
	public void setLigne5(String ligne5) {
		this.ligne5 = ligne5;
	}
	public String getLibelleAcheminement() {
		return libelleAcheminement;
	}
	public void setLibelleAcheminement(String libelleAcheminement) {
		this.libelleAcheminement = libelleAcheminement;
	}
	public Coordonnee getCoordonnee() {
		return coordonnee;
	}
	public void setCoordonnee(Coordonnee coordonnee) {
		this.coordonnee = coordonnee;
	}
	
}
