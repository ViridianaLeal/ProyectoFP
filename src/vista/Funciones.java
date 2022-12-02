/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Image;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Funciones {

    public String encriptarPassword(String password) {
        String passwordEncriptado = "";
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            Key key = keyGenerator.generateKey();
            key = new SecretKeySpec("una clave de 16 bytes".getBytes(), 0, 16, "AES");
            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            aes.init(Cipher.ENCRYPT_MODE, key);
            byte[] encriptado = aes.doFinal(password.getBytes());
            for (byte b : encriptado) {
                passwordEncriptado += String.valueOf(Integer.toHexString(0xFF & b));
            }
            aes.init(Cipher.DECRYPT_MODE, key);
            byte[] desencriptado = aes.doFinal(encriptado);
            //passwordEncriptado = new String(encriptado);
        } catch (NoSuchAlgorithmException ex) {
        } catch (InvalidKeyException ex) {
        } catch (NoSuchPaddingException ex) {
        } catch (IllegalBlockSizeException ex) {
        } catch (BadPaddingException ex) {
        }
        return passwordEncriptado;
    }

    public ImageIcon cambiar(ImageIcon img, int ancho, int alto) {
        Image imgEscalada = img.getImage().getScaledInstance(ancho,
                alto, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(imgEscalada);
        return image;
    }

}
