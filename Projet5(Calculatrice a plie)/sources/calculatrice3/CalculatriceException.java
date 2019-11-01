package calculatrice3;

/**
 * Classe représentant une exception de la calculatrice.
 */
public class CalculatriceException extends Exception{

    /**
     * Constructeur de l'exception calculatrice
     * @param message - Le message avec l'exception
     */
    public CalculatriceException(String message) {
        super(message);
    }
}
