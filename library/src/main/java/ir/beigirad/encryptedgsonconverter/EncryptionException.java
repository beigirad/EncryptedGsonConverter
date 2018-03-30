package ir.beigirad.encryptedgsonconverter;

import java.io.IOException;

class EncryptionException extends IOException {
    public EncryptionException(String message) {
        super(message);
    }
}
