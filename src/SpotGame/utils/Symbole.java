package SpotGame.utils;
public enum Symbole {

    // Attributs du symbole :

    /** Les différents symboles disponibles pour le jeu
     */
    Rouge("R"), Bleue("B"), Blanche("W"), Spot("O"), Destination("X"),Vide(" ");

    /** Le String qui permet d'avoir un symbole spécifique
     */
    private final String symbole;

    /** Crée un symbole
     * @param symbole spécifie un symbole particulier
     */
    Symbole(String symbole) {
        this.symbole = symbole;
    }

    // toString :
    public String toString() {
        return symbole;
    }

    // Getter :
    public String getName() {
        return name();
    }

}