# AndroidStrategic #

[Documentation en Français.](#french)  
[Doc in English.](#english)

<a id = "french"></a>## Généralités ##

Ce projet est un projet étudiant déstiné à réaliser un jeu vidéo sous plateforme Android.  
Plus précisément, il s'agit de réaliser un jeu de stratégie au tour par tour, en utilisant les règles de très grands jeux déjà existants.  
Le but n'est en aucun cas de concurrencer de tels jeux: il s'agit d'un projet étudiant visant à mieux comprendre la complexité d'une tel réalisation.  
C'est pourquoi, le jeu sera toujours délivré à titre gratuit, sans obligation d'achat, et sans publicité.

## Déroulement du jeu ##

Ce jeu comporte deux phases : 

 * Une partie Solo, dans lequel le joueur avance dans un scénario, composé de chapitre, chaque chapitre représente une bataille. Le joueur doit donc remporter une bataille pour atteindre le chapitre suivant.
 * Une parite Online, dans lequel le joueur peut défier un autre joueur. D'autres joueurs peuvent suivre la partie.

## Mode solo ##

Le mode solo propose d'évoluer dans divers histoires, en combattant l'Ordinateur.  
Le joueur choisit donc un scénario dans lequel il veut jouer, puis participe aux multiples batailles. A chaque bataille, il fait évoluer ses combattants.

### Déroulement d'un chapitre ###

Un chapitre est composé de plusieurs parties:

 * Un monologue, qui permet de raconter l'histoire,
 * un dialogue avant la bataille,
 * une bataille,
 * un dialogue après la bataille.

### Sauvegardes ###

Pour chaque scénario, le joueur peut sauvegarder sa partie. Ainsi, il sauvegarde:
 * son avancée globale dans les chapitres de ce scénario,
 * ses combattants, ainsi que leurs niveaux,
 * les objets et l'argent qu'il a gagné.

### Création d'un scénario ###

Le jeu est livré avec quelques scénarii déjà créés. Vous pouvez également créer vos propres scénarii.  
Comme expliqué précédemment, un scénario est un ensemble de chapitres.  
Un chapitre est composé:  

 * d'un fichier JSON pour le monologue,
 * d'un fichier JSON pour le dialogue avant la bataille,
 * de 4 fichiers PNG pour la bataille,
 * d'un fichier JSON pour le dialogue après la bataille.

Chaque scénario est placé dans un dossier dont le nom correspond au nom du scénario. Ensuite, les fichiers de chaque chapitre possèdent le numéro du chapitre, ainsi qu'un attribut dans le nom qui permet de différencier les différents fichiers du chapitre
.


## Mode online ##

Le mode online a pour but de faire combattre deux joueurs rééls.  
Au début du jeu, un joueur possède quelques combattants, tous au niveau 1, ainsi qu'une réserve d'argent.  
Le but est de défier un joueur afin de le combattre. Ainsi, les combattants de chaque joueur gagnent de l'expérience, et donc des niveaux.  
De plus, le joueur qui remporte le combat remporte la mise. Le gain initial est défini par l'ordinateur, en prenant en compte la différence de force des deux équipes, ainsi que les résultats des deux joueurs lors d'autres batailles online.  
Chaque joueur a donc un gain initial différent.  
De plus, les joueurs peuvent convenir d'une somme de pari permettant de faire monter la mise du gagnant.

### Watchers ###
Les Watchers sont les joueurs qui regardent le combat. Ceux-ci peuvent également parier sur le gagnant, à condition que la partie n'ai pas commencé.  
L'Ordinateur fixe une quote, sur les mêmes bases que le gain initial des joueurs. Les watchers peuvent donc parier sur un des deux joueurs et remporter la mise en fonction de la quote.


## Déroulement d'une bataille ##

Lors d'une bataille, deux armées s'affrontent. Une armée est composée de combattants, qui ont tous des caractéristiques (force, défense, chance ...), et appartiennent à une et une seule classe. Cette classe détermine notamment les armes qu'ils peuvent utiliser, ou les cases de la carte qu'ils peuvent traverser.

### Initialisation ###

A l'initialisation, chaque joueur place plusieurs combattants sur des cases qui lui sont réservées (le nombre de cases diffère selon les cartes).  
Le jeu démarre lorsque les deux joueurs ont placés leurs combattants.

### Système tour par tour ###

Dans ce jeu, les batailles sont au tour par tour. Autrement dit, l'un après l'autre, les joueurs décident de déplacer leurs combattants, et de les faire attaquer les combattants adverses.  
Chaque combat ne concerne que deux combattants. En fonction de ses caractéristiques, ainsi que de l'arme qu'il équipe (notamment la portée), les deux combattants attaquent mutuellement, 0, 1 ou 2 fois.  
Un combattant est mort lorsque son nombre de points de vie tombe à 0. Il ne peut donc plus combattre. De plus, un combattant mort est déclaré mort tout au long du jeu, et ne pourra donc jamais réapparaître (sauf à recommencer le scénario dans le mode solo).

### Conditions de victoire ###

La partie est remportée lorsque au moins une condition de victoire est satisfaite.  
Les conditions de victoires diffèrent selon les cartes.  
On distingue:

 * Décimer: tuer tous les combattants adverses.
 * Général: tuer tous les généraux adverses.
 * Atteindre: votre général doit prendre possession d'une case particulière de la carte.
 * Fuir: tous les combattants doivent prendre possession d'une case particulière de la carte. Lorsqu'une unité prend la case, il disparaît de la carte.
 * Position: Vous devez empêcher l'adversaire de prendre une case pendant un certain nombre de tours.

------------------------------------------------------------------------------------------------------------------------

<a id = "english"></a>## Generals ##

This project is a student projet to create a video game for Android plateforms.  
Specifically, it's a turn-based video-game, using basics rules of greats existing video-games.  
Goal of this project isn't to concurrence professionnals video-games, but it's just a student project to learn development's complexity of this kind of projet.  
Moreover, this game is, and will be free delivered, without any obligation to purchase, or ad.

## The Game ##
This game has two kinds of playing:
 * A solo game, where the player run a scenario, composed by differents chapters. Any chapter is a battle. Player have to win a battle to player the next chapter.
 * An online game, where two players fights. Others users can watch the battle.

## Solo Game ##

Solo game is composed by several stories (named scenarii), fighting Computer.  
Player choose a scenarii and fights into differents battles. For each bataille, it upgrade their fighters.

## Chapter progress ##

un chapter is composed like this:
 * un monologue, to narrate the story,
 * a dialog, before the battle,
 * the bataille,
 * a dialogue, after the battle.

### Save System ###
For each scenario, player can save his game. he save:
 * his global progress, i.e. the won chapters,
 * his fighters, & their levels,
 * any objects & money that he won.

### Scenario writting ###

Game is delivered with several scenarri. But you can write your own scenarii.  
Like explain, a scenario is composed by chapters.
A chapter is composed by:
 * a JSON file for the monolog,
 * a JSON file for the dialog before the battle,
 * 4 PNG files for the map & the battle,
 * a JSON file for the dialog after the battle.

Each scenario is positioned into it own directory (named by the name of the scenario). Each chapter's file of each scenario have a number and a name to difference every files.  


## Online Game ##
On the Online mode, two players fights together.  
At the beginning, each player have seveval lvl 1 fighters, and few money.  
Goal is defeat others players to win experiences points and money.  
The player who win a battle won profit. The basics profit is calculated by the Computer, depending on the difference of the both players strength team, and their previous battles.  
Moreover, players can increase the profits on bet some money.

### Watchers ###
Watchers are players who just watch the battle. They can bet on the winner, but just if the battle haven't yet begin.  
Computer compute the quote, using the same bases than the basic profits.


## Fighter Rules ##
On a battle, two teams are fighting. A team is composed by fighters, which have some attributes (like strength, defense, luck, skill ...), and have one class type. This class is used to know if he can equip a weapon, or if he can cross terrains.  

## Initialization ###

At initialization, each player take fighters in the reserved cases (number of reserved cases can be change depending on maps).  
The battle begin when players have take all fighters on the map.

### Turn-based System ###

This game is turn-based. This is means that the first player can move their fighters, or attack ennemy's fighters. When he have finished, it's ennemy's turn.  
Only two fighters attacks in a fight. The fight depends on both fighters attributes. For example, the number of hit, depends on their speed, and their equiped weapon.  
Moreover, a fighter is dead when their Health Points decrease to 0. A dead fighter is always dead for all of the game, and never reappear (except if you replay a scenario in solo game).

### Victory conditions ###

A battle is won if at least one of the victory conditions is cleared.  
Victory conditions depends on maps and chapters.  
They are several conditions:

 * exterminate: kill all ennemy's fighters.
 * General: kill all ennemy's generals.
 * Take: your general have to take an ennemy Terrain.
 * Run Away: all of your fighters have to take an ennemy Terrain. When he take it, it disappears.
 * Guard: guard a Terrain severals turns.