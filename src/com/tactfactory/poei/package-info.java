/**
 * Le but final de cet excercice est de créer un jeu de pendu en console, connecté à une base de données.
 *
 * 1) Faire un programme choisissant un mot aléatoirement à partir de la base de données et qui donne une chance
 *    à l'utilisateur de trouver de quel mot il s'agit. L'utilisateur aura la première lettre dévoilé ainsi que le
 *    nombre de lettres affichées (exemple pour trouver eau => e__). Nous n'accordons qu'une seule chance à
 *    l'utilisateur pour trouver le mot.
 *
 * 2) On accorde maintenant 5 vies.
 *
 * 3) On accepte désormais deux types de saisies.
 *    * lettre => si la lettre est contenue dans le mot, on affiche chaque occurences de cette lettre, sinon on
 *      perd une vie
 *    * mot => on gagne si c'est le bon mot, on perd une vie sinon. Aucun indice ne devra être donné en cas d'erreur.
 *
 * 4) On permettra maintenant à l'utilisateur de rajouter des mots manuellement.
 *
 * 5) On affichera à l'utilisateur ses anciens essais (en ne prennant pas compte des doublons).
 */
package com.tactfactory.poei;
