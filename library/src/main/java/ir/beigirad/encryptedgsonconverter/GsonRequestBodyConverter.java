package ir.beigirad.encryptedgsonconverter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;
import se.simbio.encryption.Encryption;

final class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final Encryption encryption;

    public GsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter, Encryption encryption) {
        this.gson = gson;
        this.adapter = adapter;
        this.encryption = encryption;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        adapter.write(jsonWriter, value);
        jsonWriter.close();
        if (encryption != null) {
            String out = buffer.readByteString().toString();
            String encrypted;
            try {
                encrypted = encryption.encrypt(out);
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
            return RequestBody.create(MEDIA_TYPE, encrypted);
        } else
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());


    }
}