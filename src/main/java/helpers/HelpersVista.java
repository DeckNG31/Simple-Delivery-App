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

    
 
    
  
    public static <T> JFrame abrirVentana(Class<? extends JFrame> claseVentanaAbrir, T... parametros) {
        try {
            Class<?>[] tiposParametros = new Class[parametros.length];
            for (int i = 0; i < parametros.length; i++) {
                tiposParametros[i] = parametros[i].getClass();
            }

            Constructor<? extends JFrame> constructor = claseVentanaAbrir.getDeclaredConstructor(tiposParametros);

       
           JFrame ventanaAbrir = constructor.newInstance(parametros);

            ventanaAbrir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventanaAbrir.setLocationRelativeTo(null); // Centrar la nueva ventana
            ventanaAbrir.setVisible(true);            // Mostrar la nueva ventana

            return ventanaAbrir;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static <T> void cambiarVentana(JFrame ventanaACerrar, Class<? extends JFrame> claseVentanaAbrir, T... parametros) {
        try {
            
       
            
              Class<?>[] tiposParametros = new Class[parametros.length];
            for (int i = 0; i < parametros.length; i++) {
                tiposParametros[i] = parametros[i].getClass();
            }

            Constructor<? extends JFrame> constructor = claseVentanaAbrir.getDeclaredConstructor(tiposParametros);

       
           JFrame ventanaAbrir = constructor.newInstance(parametros);

            ventanaAbrir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
