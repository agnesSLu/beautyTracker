package security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Base64;

public class SecureCredentialsStorage {

    private static final String ALGORITHM = "AES";

    // Generates a secret key for AES encryption
    public static SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(128, new SecureRandom());
        return keyGenerator.generateKey();
    }

    // Encrypts a string using the provided secret key
    public static String encrypt(String data, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypts a string using the provided secret key
    public static String decrypt(String encryptedData, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }

    // Inside SecureCredentialsStorage class

    public static void writeToFile(String filePath, String encryptedUsername, String encryptedPassword) throws Exception {
        Files.write(Paths.get(filePath), (encryptedUsername + "\n" + encryptedPassword).getBytes());
    }

    public static String[] readFromFile(String filePath, SecretKey secretKey) throws Exception {
        String[] credentials = new String(Files.readAllBytes(Paths.get(filePath))).split("\n");
        return new String[] { decrypt(credentials[0], secretKey), decrypt(credentials[1], secretKey) };
    }


}