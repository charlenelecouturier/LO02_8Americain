package modele.effets;

/**
 * <b> Interface qui gère les effets des cartes dans la partie, ainsi que les "Dire Carte" et "Dire ContreCarte".</b>
 * <p> Cette interface utilise le pattern strategy. Chaque carte a un attribut effet qui lui est conféré par la Variante, et qui est activé quand la carte est posée. \n
 * La majorité des effets sont simples, et plutôt explicites. 
 * Prenons l'exemple de Piocher1 : l'effet de la carte fait piocher une carte au joueur suivant.
 * L'effet appelle donc la méthode piocher(1) du joueur suivant.
 * Nous ne détaillerons pas ici chaque effet, seulement les plus complexes. </p>
 *   
 * @author Robin et Charlene
 * @version 1.0
 *
 */
public interface Effet {
	public void effet();
}