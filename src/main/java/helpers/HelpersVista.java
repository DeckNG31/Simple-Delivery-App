/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helpers;

import java.lang.reflect.Constructor;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author mariano
 */
public class HelpersVista {

    public static void cambiarVentana(JFrame ventanaACerrar, Class<? extends JFrame> claseVentanaAbrir) {
        try {
            // Crear una instancia de la nueva ventana a abrir
            JFrame ventanaAbrir = claseVentanaAbrir.getDeclaredConstructor().newInstance();

            ventanaAbrir.setLocationRelativeTo(null); // Centrar la nueva ventana
            ventanaAbrir.setVisible(true);            // Mostrar la nueva ventana

            ventanaACerrar.setVisible(false);         // Ocultar la ventana actual

            ventanaACerrar.dispose(); //libera recursos
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void cambiarVentana(JFrame ventanaACerrar, Class<? extends JFrame> claseVentanaAbrir, T parametroVentanaAbrir) {
        try {
            // Obtener el constructor que toma el tipo de parametroVentanaAbrir
            Constructor<? extends JFrame> constructor = claseVentanaAbrir.getDeclaredConstructor(parametroVentanaAbrir.getClass());

            // Crear la nueva instancia de ventana pasando el parámetro
            JFrame ventanaAbrir = constructor.newInstance(parametroVentanaAbrir);
            
            ventanaAbrir.setLocationRelativeTo(null); // Centrar la nueva ventana
            ventanaAbrir.setVisible(true);            // Mostrar la nueva ventana

            ventanaACerrar.setVisible(false);         // Ocultar la ventana actual

            ventanaACerrar.dispose(); //libera recursos
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mostrarMensaje(String mensaje, String tipoDeMensaje, String titulo) {
        JOptionPane optionpane = new JOptionPane(mensaje);
        if (tipoDeMensaje.equals("Info")) {
            optionpane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (tipoDeMensaje.equals("Error")) {
                optionpane.setMessageType(JOptionPane.ERROR_MESSAGE);
            }
        }
        JDialog dialog = optionpane.createDialog(titulo);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
}
