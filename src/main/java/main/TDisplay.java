package main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Afficheur de tableau
 */
public class TDisplay extends JPanel {

    // ATTRIBUTS

    /**
     * Gestionnaire des valeurs de configuration
     */
    public final ConfigManager CONFIG;

    /**
     * Fenêtre
     */
    private final JFrame frame;

    /**
     * Taille de la fenêtre
     */
    private Dimension size;

    /**
     * Tableau
     */
    private int[][] table;

    // CONSTRUCTEURS

    /**
     * Constructeur par défaut
     */
    public TDisplay() {
        CONFIG = new ConfigManager(this); // Initialiser le gestionnaire de configuration
        this.frame = new JFrame(); // Créer une fenêtre
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer l'application à la fermeture de la fenêtre
        this.frame.setContentPane(this); // Utiliser ce JPanel comme contenu de la fenêtre
        this.frame.setResizable(true); // Permettre le redimensionnement
        this.frame.setLayout(new BorderLayout()); // Utiliser un layout manager

        // Adapter la taille initiale
        size = new Dimension(400, 400); // Taille par défaut
        this.setPreferredSize(size); // Taille préférée
        this.frame.pack(); // Redimensionner la fenêtre pour s'adapter au contenu
        this.frame.setLocationRelativeTo(null); // Centrer la fenêtre
    }
    /**
     * Constructeur avec tableau
     * @param table Tableau
     */
    public TDisplay(int[][] table) {
        this(); // Appeler le constructeur par défaut
        this.updateTable(table); // Mettre à jour le tableau
    }

    // MÉTHODES

    /**
     * Met à jour le tableau [x][y]
     * @param table Tableau
     */
    public void updateTable(int[][] table) {
        this.table = table; // Mettre à jour le tableau

        // Redimensionner le panel en fonction du tableau et de la taille des cases
        Dimension size = new Dimension(
                table.length * CONFIG.caseSize,
                table[0].length * CONFIG.caseSize
        );
        this.setPreferredSize(size);  // Mettre à jour la taille préférée du JPanel
        this.revalidate();  // Demander un recalcul du layout
        this.frame.pack();  // Redimensionner la fenêtre pour s'adapter au contenu
        this.frame.setLocationRelativeTo(null);  // Centrer la fenêtre

        repaint(); // Redessiner le tableau
    }

    /**
     * Met à jour le tableau
     */
    public void updateTable() {
        updateTable(this.table);
    }

    /**
     * Dessine le tableau
     * @param g Graphics
     */
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g); // Appeler la méthode paintComponent de la classe mère

        // Vérifier que le tableau est initialisé
        if (this.table == null) {
            throw new NullPointerException("Tableau non initialisé");
        }

        // Dessiner le tableau
        for (int x = 0; x < this.table.length; x++) {
            for (int y = 0; y < this.table[x].length; y++) {
                // Dessiner la case
                g.setColor(CONFIG.colors.get(this.table[x][y]));
                g.fillRect(x * CONFIG.caseSize, y * CONFIG.caseSize, CONFIG.caseSize, CONFIG.caseSize);
                // Dessiner la valeur
                if (CONFIG.displayValues) {
                    g.setColor(CONFIG.textColors.get(this.table[x][y]));
                    g.drawString(String.valueOf(this.table[x][y]),
                            x * CONFIG.caseSize + CONFIG.caseSize / 2,
                            y * CONFIG.caseSize + CONFIG.caseSize / 2
                    );
                }
            }
        }
    }

    /**
     * Verifie si le tableau contient une valeur
     */
    public boolean contains(int value) {
        for (int[] ints : this.table) {
            for (int anInt : ints) {
                if (anInt == value) {
                    return true;
                }
            }
        }
        return false;
    }

    // COMMANDES FENÊTRE

    /**
     * Affiche le tableau
     * @param value Afficher ou non
     */
    public void display(boolean value) {
        this.frame.setVisible(value);
    }

    /**
     * Définir le titre de la fenêtre
     * @param title Titre
     */
    public void setTitle(String title) {
        this.frame.setTitle(title);
    }

    // COMMANDES TABLEAU

    /**
     * Remplie le tableau avec une valeur
     * @param value Valeur
     */
    public void fill(int value) {
        for (int[] ints : this.table) {
            Arrays.fill(ints, value);
        }
        repaint();
    }

    /**
     * Remplie une ligne du tableau avec une valeur
     * @param line Ligne
     * @param value Valeur
     */
    public void fillLine(int line, int value) {
        for (int x = 0; x < this.table.length; x++) {
            this.table[x][line] = value;
        }
        repaint();
    }

    /**
     * Remplie une colonne du tableau avec une valeur
     * @param column Colonne
     * @param value Valeur
     */
    public void fillColumn(int column, int value) {
        for (int y = 0; y < this.table[0].length; y++) {
            this.table[column][y] = value;
        }
        repaint();
    }

    /**
     * Remplie une zone du tableau avec une valeur
     * @param x Position x
     * @param y Position y
     * @param width Largeur
     * @param height Hauteur
     * @param value Valeur
     */
    public void fillZone(int x, int y, int width, int height, int value) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                this.table[i][j] = value;
            }
        }
        repaint();
    }
    /**
     * Remplie une zone du tableau avec une valeur
     * @param Rect Rectangle
     * @param value Valeur
     */
    public void fillZone(Rectangle Rect, int value) {
        fillZone(Rect.x, Rect.y, Rect.width, Rect.height, value);
    }

    /**
     * Redefinit une case du tableau
     * @param x Position x
     * @param y Position y
     * @param value Valeur
     */
    public void set(int x, int y, int value) {
        this.table[x][y] = value;
        repaint();
    }

    /**
     * Remplie le tableau avec des valeurs aléatoires
     * @param min Valeur minimale
     * @param max Valeur maximale
     */
    public void randomize(int min, int max) {
        for (int x = 0; x < this.table.length; x++) {
            for (int y = 0; y < this.table[x].length; y++) {
                this.table[x][y] = (int) (Math.random() * (max - min + 1) + min);
            }
        }
        repaint();
    }
    /**
     * Remplie le tableau avec des valeurs aléatoires
     */
    public void randomize() {
        // récupérer la valeur la plus basse de CONFIG.colors
        final int MIN = CONFIG.colors.keySet().stream().min(Integer::compareTo).orElse(0);
        // récupérer la valeur la plus haute de CONFIG.colors
        final int MAX = CONFIG.colors.keySet().stream().max(Integer::compareTo).orElse(1);
        randomize(MIN, MAX);
    }

    // GETTERS

    /**
     * Indique si la fenêtre est affichée
     */
    public boolean isShowing() {
        return this.frame.isShowing();
    }

    /**
     * Retourne le tableau
     */
    public int[][] getTable() {
        return this.table;
    }

    /**
     * Retourne la taille du tableau
     */
    public Dimension getSize() {
        return this.size;
    }

}
