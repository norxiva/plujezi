package plujezi.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;


@Slf4j
public class RSAUtilsTest {

    @Test
    public void testInitKeyBase64String(){
        Map<String, String> keyMap = RSAUtils.initKeyBase64String();

        log.info(keyMap.get(RSAUtils.PUBLIC_KEY));
        log.info(keyMap.get(RSAUtils.PRIVATE_KEY));
    }

    @Test
    public void testInitKey(){
        Map<String, Object> keyMap = RSAUtils.initKeyBase();

        log.info(Base64.encodeBase64String(((PublicKey)keyMap.get(RSAUtils.PUBLIC_KEY)).getEncoded()));
        log.info(Base64.encodeBase64String(((PrivateKey)keyMap.get(RSAUtils.PRIVATE_KEY)).getEncoded()));
    }

    @Test
    public void testEncryptAndDecrypt(){
        Map<String, String> keyMap = RSAUtils.initKeyBase64String();
        String publicKeyBase64String = keyMap.get(RSAUtils.PUBLIC_KEY);
        String privateKeyBase64String = keyMap.get(RSAUtils.PRIVATE_KEY);

//        String dataOrigin = "1234567";
//        byte[] dataRSABytes = RSAUtils.encryptPublicKey(StringUtils.getBytesUtf8(dataOrigin), publicKeyBase64String);
//
//        byte[] dataDecryptBytes =RSAUtils.decryptPrivatekey(dataRSABytes, privateKeyBase64String);
//        assertEquals(dataOrigin, StringUtils.newStringUtf8(dataDecryptBytes));

        String data2Origin = "98765987659876598765987659876598765987659876598" +
                "987659876598765987659876598765";
        byte[] data2RSABytes = RSAUtils.encryptPrivateKey(StringUtils.getBytesUtf8(data2Origin), privateKeyBase64String);
        byte[] data2SignBytes = RSAUtils.signPrivatekey(data2RSABytes, privateKeyBase64String);
        byte[] data2DecryptBytes = RSAUtils.decryptPublicKey(data2RSABytes, publicKeyBase64String);
        boolean verify = RSAUtils.verifyPublicKey(data2RSABytes, publicKeyBase64String, data2SignBytes);
        assertEquals(data2Origin, StringUtils.newStringUtf8(data2DecryptBytes));
        assertTrue(verify);
    }

}
