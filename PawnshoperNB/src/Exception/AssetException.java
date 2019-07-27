package Exception;

public class AssetException extends Exception {

    /**
     * Asset Exception
     *
     * @param message
     */
    public AssetException(String message) {
        super("Asset Exception: " + message);
    }
}
