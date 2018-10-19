package ir.beigirad.encryptedgsonconverter;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import se.simbio.encryption.Encryption;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final Encryption encryption;

    public GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, Encryption encryption) {
        this.gson = gson;
        this.adapter = adapter;
        this.encryption = encryption;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader;

        if (encryption != null) {
            String decrypt;
            try {
                decrypt = encryption.decrypt(new String(value.bytes()));
            } catch (NoSuchAlgorithmException e) {
                throw new EncryptionException(e.getMessage());
            } catch (InvalidKeySpecException e) {
                throw new EncryptionException(e.getMessage());
            } catch (NoSuchPaddingException e) {
                throw new EncryptionException(e.getMessage());
            } catch (InvalidAlgorithmParameterException e) {
                throw new EncryptionException(e.getMessage());
            } catch (InvalidKeyException e) {
                throw new EncryptionException(e.getMessage());
            } catch (BadPaddingException e) {
                throw new EncryptionException(e.getMessage());
            } catch (IllegalBlockSizeException e) {
                throw new EncryptionException(e.getMessage());
            }
            jsonReader = gson.newJsonReader(new StringReader(decrypt));

        } else {
            jsonReader = gson.newJsonReader(value.charStream());

        }

        try {
            T result = adapter.read(jsonReader);
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }
            return result;
        } finally {
            value.close();
        }
    }
}