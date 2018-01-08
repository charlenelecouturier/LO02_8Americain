package modele;
import modele.effets.Effet;
import modele.effets.*;
/**
 *<b>Classe définissant les cartes. </b> 
 *Chaque carte composant un paquet est un objet défini par : <ul> 
 *<li>sa famille (pique, coeur, carreau, trèfle)</li>
 *<li>sa valeur (As,2,3,4,5,6,7,8,9,10,Valet,Dame,Roi)</li>
 *<li>son effet, implémenté selon le design pattern strategy</li>
 *</ul>
 *Le jeu de cartes est créé dans la variante, car il diffère selon celles-ci.
 *
 *@author Charlene et Robin
 *@version 1.0
 *@see Variante
 *@see Effet
 */
public class Carte {

	public final static String[] symboles = { "PIQUE", "COEUR", "CARREAU", "TREFLE" };
	private String symbole;
	private String valeur;
	private String effet;

	public Carte(String valeur, String symbole) {
		this.symbole = symbole;
		this.valeur = valeur;
		this.effet = "Aucun";
	}
	
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
/**
 * Méthode permettant d'appliquer l'effet d'une carte sur la partie lorsqeu celle-ci est jouée. 
 * Cette méthode est appelée par la méthode "appliquerEffet()" de Joueur au cours de la partie.
 * @see Joueur
 */
	public void appliquerEffet() {
		switch (this.effet) {
		case "Oblige a rejouer" : 
			Effet obligerRejouer = new ObligeRejouer();
			obligerRejouer.effet();
			break;
		case "Changer Famille" :
			Effet changerFamille = new ChangerFamille();
			changerFamille.effet();
			break;
		case "Changer Sens" :
			Effet changerSens = new ChangerSens();
			changerSens.effet();
			break;
		case "+ 2 ou joue AS ou 8" :
			Effet pioche2cartesOuAsOu8 = new Pioche2OuASOu8();
			pioche2cartesOuAsOu8.effet();
			break;
		case "Bloquer Suivant" :
			Effet bloqueSuivant = new BloquerSuivant();
			bloqueSuivant.effet();
			break;
		case "Fait piocher 2 cartes" :
			Effet pioche2 = new Piocher2Cartes();
			pioche2.effet();
			break;
		case "Fait piocher 4 cartes" :
			Effet pioche4 = new Piocher4Cartes();
			pioche4.effet();
			break;
		case "Defausser tous les memes symboles" :
			Effet JouerToutesCartesMemeSymbole = new JouerMemeCouleur();
			JouerToutesCartesMemeSymbole.effet();
			break;
		case "Changer Famille + Piocher 5 cartes" : 
			Effet changerFamilleSec = new ChangerFamille();
			changerFamilleSec.effet();
			Effet piocher5= new Piocher5Cartes();
			piocher5.effet();
			break;
		case "Fait piocher 1 carte" :
			Effet piocher1 = new Piocher1();
			piocher1.effet();
			break;
		case "Pioche 3 cartes ou joue un AS ou un 8" :
			Effet pioche3OuASOu8 = new Pioche3OuAsOu8();
			pioche3OuASOu8.effet();
			break;
		case "Pioche 2 cartes ou joue AS" :
			Effet pioche2OuAS = new Pioche2OuJoueAs();
			pioche2OuAS.effet();
			break;
		case "Pioche 3 cartes" :
			Effet pioche3 = new Piocher3();
			pioche3.effet();
			break;
		}
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