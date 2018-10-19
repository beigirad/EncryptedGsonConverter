package ir.beigirad.encryptedgsonconverter;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// https://stackoverflow.com/questions/15554296
final public class Encryption {
    private String key;
    private String initVector;
    private String transformation;
    private String algorithm;
    private String charset;

    private Encryption(String key, String initVector, String transformation, String algorithm, String charset) {
        this.key = key;
        this.initVector = initVector;
        this.transformation = transformation;
        this.algorithm = algorithm;
        this.charset = charset;
    }

    public static Encryption getDefault(String key, String initVector) {
        return Builder.getDefaultBuilder(key, initVector).build();
    }

    String encrypt(String value) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(charset));
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(charset), algorithm);

        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        byte[] encrypted = cipher.doFinal(value.getBytes());

        return Base64.encodeBase64String(encrypted);
    }

    String decrypt(String encrypted) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(charset));
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(charset), algorithm);

        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

        byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

        return new String(original);
    }

    public static class Builder {
        private String key;
        private String initVector;
        private String transformation;
        private String algorithm;
        private String charset;

        public Builder(String key, String initVector) {
            this.key = key;
            this.initVector = initVector;
        }

        public static Builder getDefaultBuilder(String key, String initVector) {
            return new Builder(key, initVector)
                    .setAlgorithm("AES")
                    .setCharset("UTF-8")
                    .setTransformation("AES/CBC/PKCS5PADDING");
        }

        public Builder setTransformation(String transformation) {
            this.transformation = transformation;
            return this;
        }

        public Builder setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
            return this;
        }

        public Builder setCharset(String charset) {
            this.charset = charset;
            return this;
        }

        public Encryption build() {
            return new Encryption(key, initVector, transformation, algorithm, charset);
        }
    }
}