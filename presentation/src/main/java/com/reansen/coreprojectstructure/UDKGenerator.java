package com.reansen.coreprojectstructure;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class UDKGenerator {
    public static byte[] deriveUDK(byte[] mdk, byte[] pan, byte panSequenceNumber, String derivationOption, String keyParity) throws Exception {
        // Concatenate PAN and PAN sequence number based on the derivation option
        byte[] derivationData = new byte[16];
        System.arraycopy(pan, 0, derivationData, 0, Math.min(pan.length, 16)); // Copy up to 16 bytes of PAN
        derivationData[15] = panSequenceNumber; // Last byte is PAN sequence number

        // Encrypt the concatenated value using the MDK
        SecretKeySpec keySpec = new SecretKeySpec(mdk, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] udk = cipher.doFinal(derivationData);

        // Adjust key parity
        adjustParity(udk, keyParity);

        return udk;
    }

    private static void adjustParity(byte[] key, String keyParity) {
        if ("none".equals(keyParity)) {
            return; // No parity adjustment needed
        }

        for (int i = 0; i < key.length; i++) {
            int parity = 0;
            for (int bit = 0; bit < 8; bit++) {
                parity += (key[i] >> bit) & 1;
            }

            boolean isEvenParity = (parity % 2 == 0);
            if (("right odd".equals(keyParity) && isEvenParity) || ("right even".equals(keyParity) && !isEvenParity)) {
                key[i] ^= 1; // Flip the last bit to adjust parity
            }
        }
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
