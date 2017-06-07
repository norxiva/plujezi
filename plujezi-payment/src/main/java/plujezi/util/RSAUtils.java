package plujezi.util;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

@Slf4j
public class RSAUtils {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALOGRITHM = "MD5WithRSA";
    public static final int KEY_INIT_LENGTH = 1024;
    public static final String PUBLIC_KEY = "PublicKey";
    public static final String PRIVATE_KEY = "PrivateKey";

    public static Map<String, String> initKeyBase64String() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(KEY_INIT_LENGTH);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            Map<String, String> map = Maps.newHashMap();
            map.put(PUBLIC_KEY, Base64.encodeBase64String(publicKey.getEncoded()));
            map.put(PRIVATE_KEY, Base64.encodeBase64String(privateKey.getEncoded()));

            return map;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static Map<String, Object> initKeyBase() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(KEY_INIT_LENGTH);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            Map<String, Object> map = Maps.newHashMap();
            map.put(PUBLIC_KEY, publicKey);
            map.put(PRIVATE_KEY, privateKey);

            return map;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static PublicKey generatePublicKey(byte[] publicKeyBytes){
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static PrivateKey generatePrivateKey(byte[] privateKeyBytes){
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static byte[] encryptPublicKey(byte[] data, String publicKeyBase64String){
        return encryptPublicKey(data, Base64.decodeBase64(publicKeyBase64String));
    }

    public static byte[] encryptPublicKey(byte[] data, byte[] publicKeyBytes){
        return encryptPublicKey(data, generatePublicKey(publicKeyBytes));
    }

    public static byte[] encryptPublicKey(byte[] data, Key publicKey){
        return encryptKey(data, publicKey);
    }

    public static byte[] encryptPrivateKey(byte[] data, String privateKeyBase64String){
        return encryptPrivateKey(data, Base64.decodeBase64(privateKeyBase64String));
    }

    public static byte[] encryptPrivateKey(byte[] data, byte[] privateKeyBytes){
        return encryptPrivateKey(data, generatePrivateKey(privateKeyBytes));
    }

    public static byte[] encryptPrivateKey(byte[] data, Key privateKey){
        return encryptKey(data, privateKey);
    }

    public static byte[] encryptKey(byte[] data, Key key){
        try {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }


    public static byte[] decryptPrivatekey(byte[] data, String privateKeyBase64String){
        return decryptPrivatekey(data, Base64.decodeBase64(privateKeyBase64String));
    }

    public static byte[] decryptPrivatekey(byte[] data, byte[] privateKeyBytes){
        return decryptPrivatekey(data, generatePrivateKey(privateKeyBytes));
    }

    public static byte[] decryptPrivatekey(byte[] data, Key privateKey){
        return decryptKey(data, privateKey);
    }

    public static byte[] decryptPublicKey(byte[] data, String publicKeyBase64String){
        return decryptPublicKey(data, Base64.decodeBase64(publicKeyBase64String));
    }

    public static byte[] decryptPublicKey(byte[] data, byte[] publicKeyBytes){
        return decryptPublicKey(data, generatePublicKey(publicKeyBytes));
    }

    public static byte[] decryptPublicKey(byte[] data, Key publicKey){
        return decryptKey(data, publicKey);
    }

    public static byte[] decryptKey(byte[] data, Key key){
        try {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }


    public static byte[] signPrivatekey(byte[] data, String privateKeyBase64String){
        return signPrivatekey(data, Base64.decodeBase64(privateKeyBase64String));
    }

    public static byte[] signPrivatekey(byte[] data, byte[] privateKeyBytes){
        return signPrivatekey(data, generatePrivateKey(privateKeyBytes));
    }

    public static byte[] signPrivatekey(byte[] data, PrivateKey privateKey){
        return signPrivatekey(data, privateKey, SIGNATURE_ALOGRITHM);
    }

    public static byte[] signPrivatekey(byte[] data, PrivateKey privateKey, String algorithm){
        try {
            Signature signature = Signature.getInstance(algorithm);
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static boolean verifyPublicKey(byte[] data, String publicKeyBase64String, byte[] signBytes){
        return verifyPublicKey(data, Base64.decodeBase64(publicKeyBase64String), signBytes);
    }

    public static boolean verifyPublicKey(byte[] data, byte[] publicKeyBytes, byte[] signBytes){
        return verifyPublicKey(data, generatePublicKey(publicKeyBytes), signBytes);
    }

    public static boolean verifyPublicKey(byte[] data, PublicKey publicKey, byte[] signBytes){
        return verifyPublicKey(data, publicKey, signBytes, SIGNATURE_ALOGRITHM);
    }

    public static boolean verifyPublicKey(byte[] data, PublicKey publicKey, byte[] signBytes, String algorithm){
        try {
            Signature signature = Signature.getInstance(algorithm);
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(signBytes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
