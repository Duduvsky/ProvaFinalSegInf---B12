package controller;

import javax.crypto.spec.DESKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class CryptoUtils {
    public static String encrypt(String plainText, String secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        KeySpec keySpec = new DESKeySpec(secretKey.getBytes());
        SecretKey key = keyFactory.generateSecret(keySpec);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, String secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        KeySpec keySpec = new DESKeySpec(secretKey.getBytes());
        SecretKey key = keyFactory.generateSecret(keySpec);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }

    public static String gerarChaveSecreta() {
        try {
            // Inicializa o KeyGenerator para o algoritmo DES
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            // Define o tamanho da chave (56 bits para DES)
            keyGenerator.init(56);
            // Gera a chave secreta
            SecretKey secretKey = keyGenerator.generateKey();
            // Converte a chave para bytes e, em seguida, para uma string Base64
            byte[] chaveBytes = secretKey.getEncoded();
            return Base64.getEncoder().encodeToString(chaveBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String sha128(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hash = md.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static boolean validarSenha(String senhaDigitada, String senhaArmazenada) throws Exception {
        String senhaDigitadaHash = sha128(senhaDigitada);
        return senhaDigitadaHash.equals(senhaArmazenada);
    }
}
