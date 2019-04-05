1 Description des fonctionnalités du programme

Le programme a pour but principal, comme définit dans l’énoncé, de permettre à l’utilisateur de jouer au jeu du 2048.
Dans ce 4ème TP, de nouvelles fonctionnalités ont été ajoutées au 2048 précédent. Cette fois ci le jeu dispose d’une sauvegarde, disponible avec deux nouveaux boutons : le bouton « sauvegarder », pour sauvegarder à tout moment une partie, et un autre bouton « récupérer » qui permet de récupérer à tout instant la partie précédemment sauvegardée.
Nous avions trois exercices différents à réaliser, avec pour chacun différentes contraintes.

Afin de sauvegarder et récupérer un jeu 2048, nous avons créé la classe Sauvegarder. Cette classe prend en paramètre un jeu (Jeu2048). Cette classe contient six méthodes :
- enregistrerGrille() : permet de sauvegarder la grille de jeu. Cette méthode écrit dans un fichier (« enregistrerJeu2048 ») sous forme de String les entiers contenus dans la grille. On utilise un bufferedWriter pour écrire un entier par ligne. On obtient donc un fichier contenant 16 lignes.
- chargerFichier() : permet de récupérer une grille de jeu sauvegardée. Pour cela, nous utilisons un bufferedReader. Il va parcourir le fichier contenant la grille de jeu sauvegardée (« enregistrerJeu2048 ») ligne par ligne. On récupère les entiers pour recréer une grille de jeu. Puis on initialise la grille grâce à la méthode jeu.setGrilleInt(grille).
- enregistrerMeilleurScore() : permet de sauvegarder le meilleur score du jeu. On écrit dans un fichier (« meilleurScore ») le meilleur score du jeu en String.
- recupererMeilleurScore() : permet de récupérer le meilleur score enregistré dans le
fichier (« meilleurScore »). On lit le nombre grâce à un bufferedReader puis on initialise
le meilleur score du jeu avec la méthode jeu.setBestScore(score).
- enregistrerScore() : permet de sauvegarder le score d’une partie en cours. Comme
pour le meilleur score, on enregistre le score dans un fichier (« Score ») en String.
- recupererScore() : permet de récupérer le score enregistré dans le fichier (« Score »).
On récupère le score grâce à un bufferedReader et on initialise le score du joueur avec
la méthode jeu.setScore(score).

Lorsque le joueur clique sur « Sauvegarder », la classe SauvegarderListener, qui prend en
paramètre un jeu de classe Sauvegarder, permet d’enregistrer la grille de jeu ainsi que le score
du joueur.

Lorsque le joueur clique sur « Récupérer », la classe RecupererListener, qui prend en
paramètre un jeu de classe Sauvegarder, permet de récupérer la grille de jeu sauvegardée et
de remettre le score du joueur lors de la sauvegarde.

Le meilleur score est automatiquement sauvegardé lorsqu’une partie est finie ou lorsque le
joueur ferme la fenêtre.

Nous avons créé la classe Logger. Cette classe permet de garder une trace de jeu. Suivant
l’importance du message à écrire, le message est écrit à l’écran ou dans un fichier :
- ALL : écrit dans un fichier
- DEBUG : écrit à l’écran en system.out
- INFO : écrit à l’écran en system.out
- IMPORTANT : écrit à l’écran en system.err
Logger prend en attribut un entier qui indique la valeur minimale d’un message.

L’attribut PrintWriter indique le flot de sortie du message. Il est initialisé à partir de la méthode PrintWriter qui prend en paramètre un level :
- Un message de level = ALL est écrit dans le fichier « traceJeu2048 ». Dans ce fichier, on retrouve toutes les informations du jeu : l’initialisation du jeu, les déplacements, le click sur les boutons (fermer la fenêtre, sauvegarder, récupérer), la fin de partie… Lorsque le joueur lance le jeu, le fichier est automatiquement supprimé au début. Ainsi, seules les traces des parties en cours sont sauvegardées. L’écriture dans le fichier se fait grâce à la méthode traceJeu qui prend en paramètre un message et le fichier dans lequel on écrit la trace du jeu.
- Les messages de level = DEBUG renseignent sur la méthode de jeu : utilisation de la souris ou des touches, sens du décalage, fin de partie si le joueur a gagné, utilisation des boutons Sauvegarder et Récupérer.
- Les messages de level = INFO concernent les informations sur la fusion des cases. Pour cela, on a rajouté dans les écouteurs (Souris et Touches) une méthode caseFusionne() qui permet de renseigner le joueur sur les cases qui viennent d’être fusionnées.
- Les messages de level = IMPORTANT sont des messages d’erreur. Ils indiquent au joueur l’impossibilité de décaler la grille de jeu et la fin d’une partie si le joueur a perdu.
Exemples de messages affichés dans le fichier et dans le terminal

Le but de cet exercice est de récupérer le niveau d’importance et le type de flux de sortie à partir d’un fichier de configuration. Nous avons donc rajouté un constructeur de la classe Logger, qui prend en paramètre le nom du fichier de configuration. Ce constructeur permet d’initialiser les attributs level et printWriter en fonction des informations contenues dans le fichier.
Le fichier de configuration doit contenir une seule ligne. Il contient le niveau d’importance du message ainsi que le flux de sortie à utiliser, séparé par un espace. Il doit être placé au même endroit que le package courant. Si vous voulez écrire les messages dans un fichier, il faut écrire le chemin absolu dans le fichier de configuration.
Nous avons également rajouté une méthode log qui prend en paramètre uniquement un message. L’utilisateur n’a pas besoin d’indiqué le niveau d’importance du message, il est directement initialisé par le fichier de configuration. Le message est affiché dans le flux de sortie correspondant à l’emplacement indiqué par le fichier de configuration.

Dans nos codes java, nous avons mis un exemple d’utilisation avec un niveau d’importance DEBUG et une sortie de flux en System.out.
