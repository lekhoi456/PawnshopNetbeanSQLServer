package Exception;

public class AssetException extends Exception {

    public AssetException(String message) {
        super("Asset Exception: " + message);
    }
}
