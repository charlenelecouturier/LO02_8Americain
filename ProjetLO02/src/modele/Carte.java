package modele;

/**
 * @author Charlene et Robin
 *
 */
public class Carte {

	public final static String[] symboles = { "PIQUE", "COEUR", "CARREAU", "TREFLE" };
	private String symbole;
	private String valeur;
	private String effet;

	/**
	 * @return the symbole
	 */
	public String getSymbole() {
		return this.symbole;
	}

	/**
	 * @param symbole
	 *            the symbole to set
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
	 * @param valeur
	 *            the valeur to set
	 */
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	/**
	 * @return the effet
	 */
	public String getEffet() {
		return effet;
	}

	/**
	 * @param effet
	 *            the effet to set
	 */
	public void setEffet(String effet) {
		this.effet = effet;
	}

	public void appliquerEffet() {
		if (this.effet.equals("ObligeRejouer")) {
			Effet obligerRejouer = new ObligeRejouer();
			obligerRejouer.effet();

		} else if (this.effet.equals("ChangerFamille")) {
			Effet changerFamille = new ChangerFamille();
			changerFamille.effet();
		} else if (this.effet.equals("ChangerSens")) {
			Effet changerSens = new ChangerSens();
			changerSens.effet();
		} else if (this.effet.equals("Pioche2OuAsOu8")) {
			Effet pioche2cartesOuAsOu8 = new Pioche2OuASOu8();
			pioche2cartesOuAsOu8.effet();
		} else if (this.effet.equals("BloquerSuivant")) {
			Effet bloqueSuivant = new BloquerSuivant();
			bloqueSuivant.effet();
		} else if (this.effet.equals("Piocher2")) {
			Effet pioche2 = new Piocher2Cartes();
			pioche2.effet();
		} else if (this.effet.equals("Piocher4")) {
			Effet pioche4 = new Piocher4Cartes();
			pioche4.effet();
		} else if (this.effet.equals("JouerToutesCartesMemeSymbole")) {
			Effet JouerToutesCartesMemeSymbole = new JouerMemeCouleur();
			JouerToutesCartesMemeSymbole.effet();
		} else if (this.effet.equals("ChangerFamilleEtPioche5")) {
			Effet changerFamilleEtPioche5 = new ChangerFamilleEtPioche5();
			changerFamilleEtPioche5.effet();
		}else if (this.effet.equals("Piocher1Carte")) {
			Effet piocher1 = new Piocher1();
			piocher1.effet();
		}else if (this.effet.equals("Pioche 3 cartes ou joue un AS ou un 8")) {
			Effet pioche3OuASOu8 = new Pioche3OuAsOu8();
			pioche3OuASOu8.effet();
		} 
	}

	public Carte(String valeur, String symbole) {
		this.symbole = symbole;
		this.valeur = valeur;
		this.effet = "Aucun";
	}

	public String toString() {
		if (this.effet.equals("Aucun")) {
			if (this.symbole.equals("JOKER")) {
				return this.symbole;
			} else {

				return valeur + " de " + symbole;
			}
		} else {
			if (this.symbole.equals("JOKER")) {
				return this.symbole + " -- effet : " + this.effet;
			} else {
				return valeur + " de " + symbole + " -- effet : " + this.effet;
			}
		}
	}
}