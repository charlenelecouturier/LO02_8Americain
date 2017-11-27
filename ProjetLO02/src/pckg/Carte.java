package pckg;

/**
 * 
 */

/**
 * @author Charlene et Robin
 *
 */
public class Carte {

	public final static String[] symboles = {"PIQUE", "COEUR", "CARREAU", "TREFLE"};
	private boolean carteSpeciale;
	private String symbole;
	private String valeur;
	
		
	/**
	 * @return the carteSpeciale
	 */
	public boolean isCarteSpeciale() {
		
		int i;
		for (i=0;i<Partie.getPartie().getVariantePartie().getCarteSpeciale().size();i++) {
			if (this.valeur.equals(Partie.getPartie().getVariantePartie().getCarteSpeciale().get(i).getValeur())) {
				this.setCarteSpeciale(true);
				return this.carteSpeciale;
			}
		}
		this.setCarteSpeciale(false);
		return this.carteSpeciale;
			}

	/**
	 * @param carteSpeciale the carteSpeciale to set
	 */
	public void setCarteSpeciale(boolean carteSpeciale) {
		this.carteSpeciale = carteSpeciale;
	}

	/**
	 * @return the symbole
	 */
	public String getSymbole() {
		return this.symbole;
	}

	/**
	 * @param symbole the symbole to set
	 */
	public void setSymbole(String symbole) {
		this.symbole = symbole;
	}

	/**
	 * @return the valeur
	 */
	public String getValeur() {
		return this.valeur;
	}

	/**
	 * @param valeur the valeur to set
	 */
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public void estSpeciale() {
		
	}
	
	public void appliquerEffet(String effet) {
		if(effet.equals("ObligeRejouer")) {
			Effet obligerRejouer= new ObligeRejouer();
			obligerRejouer.effet();
			
			
		}
		else if(effet.equals("ChangerFamille")) {
			Effet changerFamille= new ChangerFamille();
			changerFamille.effet();
			
		}
		else if(effet.equals("AucunEffet")) {
			Effet aucunEffet= new AucunEffet();
			aucunEffet.effet();
			
		}
		
		else if(effet.equals("ChangerSens")) {
			Effet changerSens= new ChangerSens();
			changerSens.effet();
			
		}
		else if(effet.equals("Pioche2Cartes")) {
			Effet pioche2cartes= new Piocher2Cartes();
			pioche2cartes.effet();
			
		}
		
		
	}
	
	
	public Carte(String valeur, String symbole) {
		this.symbole=symbole;
		this.valeur=valeur;

	}
	public String toString () {
	return valeur + " de " + symbole;
	
	}

}

