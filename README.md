# Afficheur de Tableaux 2D

Permet d'afficher un tableau 2D sur une fenêtre dédiée.

## Installation

* Créer un dossier 'libs' dans le dossier racine de votre projet.
* Importer le fichier .jar dans le dossier 'libs'.
* L'ajouter à votre build path.  
  **Gradle** : 
```gradle
dependencies {
    implementation files('libs/TableDisplayer.jar')
}
```

## Utilisation

D'abord importer la classe `TDisplay` dans votre code:
```java
import main.TDisplay;
```
Cette classe représente l'afficheur du tableau.
Ensuite, créer une instance de `TDisplay`:
```java
// création classique
TDisplay display = new TDisplay(); 
// création avec un tableau 2D de int
TDisplay display = new TDisplay(table); 
```
Après il sera nécessaire d'afficher la fenêtre:
```java
// afficher la fenêtre
display.display(true);
// cacher la fenêtre
display.display(false);
```
On peut également modifier le titre de la fenêtre:
```java
// modifier le titre de la fenêtre
display.setTitle("Hello, World !");
```


Il est possible de mettre à jour le tableau affiché avec la méthode 'updateTable':
```java
// mettre à jour le tableau affiché
display.updateTable();
// mettre à jour le tableau affiché avec un nouveau tableau
display.updateTable(newTable);
```
Il est possible de modifier le tableau de différentes manières:

```java
import java.awt.Rectangle;
// remplir le tableau avec une valeur 1
display.fill(1);
// remplir la ligne 3 avec la valeur 1
display.

fillLine(3,1);
// remplir la colonne 2 avec la valeur 1
display.fillColumn(2,1);
// remplir une zone qui commence (coin supérieur gauche) en (1, 2) et qui mesure 3 de largeur et 4 de longueur avec la valeur -1
display.fillZone(1,2,3,4,-1);
display.fillZone(new Rectangle(1,2,3,4) -1);
// définit la case (2, 3) avec la valeur 0
display.set(2,3,0);
// definit le contenu du tableau aléatoirement
display.randomize(); // entre les valeurs déjà assignées à une couleur
display.randomize(0, 10); // entre 0 et 10
```
On peut aussi récupérer des informations
```java
import java.awt.Dimension;
// si la fenêtre est visible
display.isVisible(); // retourne un boolean
// récupérer le tableau affiché
display.getTable(); // retourne un tableau 2D de int (int[][])
// récupérer les dimensions du tableau
display.getSize(); // retourne un objet Dimension
```
On peut configurer le programme:
```java
import java.awt.Color;
// definir la definition d'une case en pixels à 15
display.CONFIG.setCaseSize(15);
// afficher les valeurs des cases
display.CONFIG.setDisplayValues(true);

// ajouter une couleur pour une valeur 1
display.CONFIG.addColor(1, Color.RED);
display.CONFIG.addColor(1, 20, 50, 100); // RGB
// retirer la couleur pour la valeur 1
display.CONFIG.removeColor(1);

// ajouter une couleur de texte pour une valeur 1
display.CONFIG.addTextColor(1, Color.RED);
display.CONFIG.addTextColor(1, 20, 50, 100); // RGB
// retirer la couleur de texte pour la valeur 1
display.CONFIG.removeTextColor(1);

// definir la couleur par défaut (pour les valeurs non définies)
display.CONFIG.setDefaultColor(Color.BLACK);
display.CONFIG.setDefaultColor(20, 50, 100); // RGB
// definir la couleur de texte par défaut
display.CONFIG.setDefaultTextColor(Color.WHITE);
display.CONFIG.setDefaultTextColor(20, 50, 100); // RGB
```
On peut récupérer des informations sur la configuration:
```java
// récupérer la taille des cases
display.CONFIG.getCaseSize(); // retourne un int
// récupérer si les valeurs des cases sont affichées
display.CONFIG.isDisplayValues(); // retourne un boolean
// récupérer la couleur pour une valeur
display.CONFIG.getColor(1); // retourne un objet Color
// récupérer la couleur de texte pour une valeur
display.CONFIG.getTextColor(1); // retourne un objet Color
// récupérer la couleur par défaut
display.CONFIG.getDefaultColor(); // retourne un objet Color
// récupérer la couleur de texte par défaut
display.CONFIG.getDefaultTextColor(); // retourne un objet Color
```