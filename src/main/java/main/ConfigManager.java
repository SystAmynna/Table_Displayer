package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Gestionnaire des valeurs de configuration
 */
public class ConfigManager {

    // ATTRIBUTS

    private final TDisplay DISPLAY;

    /**
     * Taille d'une case (en pixels)
     */
    protected int caseSize = 20;

    /**
     * Afficher les valeurs
     */
    protected boolean displayValues = false;

    /**
     * Couleur de chaque valeur
     */
    protected HashMap<Integer, Color> colors = new HashMap<>();
    /**
     * Couleur par défaut
     */
    protected Color defaultColor = Color.WHITE;

    /**
     * Couleur de texte de chaqye valeur
     */
    protected HashMap<Integer, Color> textColors = new HashMap<>();
    /**
     * Couleur de texte par défaut
     */
    protected Color defaultTextColor = Color.BLACK;

    // CONSTRUCTEURS

    /**
     * Constructeur
     */
    protected ConfigManager(TDisplay display) {
        DISPLAY = display;

        colors.put(0, Color.WHITE);
        colors.put(1, Color.BLACK);
        colors.put(-1, Color.RED);

        textColors.put(0, Color.BLACK);
        textColors.put(1, Color.WHITE);
        textColors.put(-1, Color.WHITE);
    }

    /**
     * Verifier si le tableau contient une valeur non instanciée dans la HashMap précisée
     * @param list HashMap
     */
    private boolean checkTable(HashMap<Integer, Color> list) {
        // lister les valeurs du tableau
        final ArrayList<Integer> values = new ArrayList<>();
        for (int[] ints : DISPLAY.getTable()) {
            for (int anInt : ints) {
                if (!values.contains(anInt)) {
                    values.add(anInt);
                }
            }
        }
        for (int value : values) {
            if (list.containsKey(value)) {
                return true;
            }
        }
        return false;
    }


    // SETTERS

    /**
     * Définir la taille d'une case
     * @param caseSize taille d'une case
     */
    public void setCaseSize(int caseSize) {
        this.caseSize = caseSize;
        DISPLAY.updateTable();
    }

    /**
     * Définir l'affichage des valeurs
     * @param displayValues affichage des valeurs
     */
    public void setDisplayValues(boolean displayValues) {
        this.displayValues = displayValues;
        DISPLAY.updateTable();
    }

    /**
     * Définir la couleur d'une valeur
     * @param value
     * @param color
     */
    public void addColor(int value, Color color) {
        colors.put(value, color);
        if (DISPLAY.contains(value)) DISPLAY.updateTable();
    }
    /**
     * Définir la couleur de texte d'une valeur
     * @param value Valeur
     * @param r Rouge
     * @param g Vert
     * @param b Bleu
     */
    public void addColor(int value, int r, int g, int b) {
        addColor(value, new Color(r, g, b));
    }

    /**
     * Définir la couleur de texte d'une valeur
     * @param value Valeur
     * @param color Couleur
     */
    public void addTextColor(int value, Color color) {
        textColors.put(value, color);
        if (displayValues && DISPLAY.contains(value)) DISPLAY.updateTable();
    }
    /**
     * Définir la couleur de texte d'une valeur
     * @param value Valeur
     * @param r Rouge
     * @param g Vert
     * @param b Bleu
     */
    public void addTextColor(int value, int r, int g, int b) {
        addTextColor(value, new Color(r, g, b));
    }

    /**
     * Définir la couleur par défaut
     * @param color Couleur
     */
    public void setDefaultColor(Color color) {
        this.defaultColor = color;
        if (checkTable(colors)) DISPLAY.updateTable();
    }
    /**
     * Définir la couleur par défaut
     * @param r Rouge
     * @param g Vert
     * @param b Bleu
     */
    public void setDefaultColor(int r, int g, int b) {
        setDefaultColor(new Color(r, g, b));
    }

    /**
     * Définir la couleur de texte par défaut
     * @param color Couleur
     */
    public void setDefaultTextColor(Color color) {
        this.defaultTextColor = color;
        if (displayValues && checkTable(textColors)) DISPLAY.updateTable();
    }
    /**
     * Définir la couleur de texte par défaut
     * @param r Rouge
     * @param g Vert
     * @param b Bleu
     */
    public void setDefaultTextColor(int r, int g, int b) {
        setDefaultTextColor(new Color(r, g, b));
    }

    // GETTERS

    /**
     * Obtenir la taille d'une case
     * @return taille d'une case
     */
    public int getCaseSize() {
        return caseSize;
    }

    /**
     * Obtenir l'affichage des valeurs
     * @return affichage des valeurs
     */
    public boolean getDisplayValues() {
        return displayValues;
    }

    /**
     * Obtenir la couleur d'une valeur
     * @param value Valeur
     * @return couleur
     */
    public Color getColor(int value) {
        return colors.get(value);
    }

    /**
     * Obtenir la couleur de texte d'une valeur
     * @param value Valeur
     * @return couleur
     */
    public Color getTextColor(int value) {
        return textColors.get(value);
    }

    /**
     * Obtenir la couleur par défaut
     * @return couleur
     */
    public Color getDefaultColor() {
        return defaultColor;
    }

    /**
     * Obtenir la couleur de texte par défaut
     * @return couleur
     */
    public Color getDefaultTextColor() {
        return defaultTextColor;
    }

}
