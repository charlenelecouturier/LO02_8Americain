package pckg;

import java.util.ListIterator;
import java.util.LinkedList;

public class Piocher2Cartes implements Effet {
	
	public void effet() {
		System.out.println("Si le joueur suivant n'a pas de 8 ou d'AS il pioche 2 cartes");
		
		// on cherche le joueur suivant pour signaler qu'on lui applique un effet
		int tour;
		tour= Partie.getPartie().getTourJoueur();
		// On regarde le sens de la partie
		if (Partie.getPartie().getSens()==1) {
			
				tour++;
			}
			// Si on depasse le numéro du dernier joueur, on revient au joueur 1 ( joueur physique)
			if( tour > Partie.getPartie().getNbJoueursEnCours()) {
				tour=1;		
			}
		
		else {// sens =-1
			// si on trouve un numéro négatif, on revient au tour du dernier joueur ( joueur ayant le dernier numéro)
			tour--;
			if (tour<=0) {
				tour=Partie.getPartie().getNbJoueursEnCours();
			}
			
			
		}
		
		Joueur joueurSuivant = Partie.getPartie().getJoueur().get(tour-1);
		
		joueurSuivant.setEffetVariante("Variante5");

		}
		
		
	}


