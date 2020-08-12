package com.keer.bdql.Util;

import com.bigchaindb.util.KeyPairUtils;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.KeyPairGenerator;

import java.security.KeyPair;

public class KeyPairUtil {

    /**
     * 生成新的密钥
     * @return
     */
    public static KeyPair genKeyPair(){
        System.out.println("开始生成公钥……");
        KeyPairGenerator edDsaKpg = new KeyPairGenerator();
        return edDsaKpg.generateKeyPair();
    }

    /**
     * 获取公钥字符串
     * @param keyPair
     * @return
     */
    public static String getPubKey(KeyPair keyPair){
        return KeyPairUtils.encodePublicKeyInBase58((EdDSAPublicKey) keyPair.getPublic());
    }

    /**
     * 获取私钥字符串
     * @param keyPair
     * @return
     */
    public static String getPriKey(KeyPair keyPair){
        return new String(KeyPairUtils.encodePrivateKeyBase64(keyPair).getBytes());
    }

    public static KeyPair getKeypair(String key){
        return KeyPairUtils.decodeKeyPair(key);
    }

    public static void main(String[] args) {
        KeyPair keyPair=genKeyPair();
        System.out.println(getPubKey(keyPair));
    }
}
