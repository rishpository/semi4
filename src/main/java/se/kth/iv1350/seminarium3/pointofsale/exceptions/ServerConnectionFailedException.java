package se.kth.iv1350.seminarium3.pointofsale.exceptions;

/**
 * An exception that is thrown for when the database
 * isn't running or working
 */
public class ServerConnectionFailedException extends Exception {
    public ServerConnectionFailedException(String message) {
        super(message);
    }

}
