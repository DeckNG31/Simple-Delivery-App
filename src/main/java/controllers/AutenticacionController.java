/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import exceptions.UsuarioNoEncontradoException;
import isi.deso.tp.usuarios.Cliente;
import isi.deso.tp.usuarios.Usuario;
import isi.deso.tp.usuarios.Vendedor;

/**
 *
 * @author mariano
 */
public class AutenticacionController {

    private static AutenticacionController instance;
    private static Usuario usuarioAutenticado;

    private static String tipoUsuario = "admin";

    public static String getTipoUsuario() {
        return tipoUsuario;
    }

    private AutenticacionController() {
    }

    public static AutenticacionController getInstance() {

        if (instance == null) {
            instance = new AutenticacionController();
        }
        return instance;
    }

    public Usuario autenticarUsuario(String tipoUsuario, Integer id) {
        // Si el usuario ya está autenticado, no hacer nadaautenticarUsuario
        if (usuarioAutenticado != null) {
            System.out.println("Ya hay un usuario autenticado.");
            return null;
        }

        switch (tipoUsuario.toLowerCase()) {
            case "cliente":
                try {
                    usuarioAutenticado = ClienteController.getInstance().buscarClientePorId(id);
                } catch (Exception e) {
                    throw new UsuarioNoEncontradoException("Usuario no encontrado");
                }

                if (usuarioAutenticado == null) {
                    return null;
                }
                break;
            case "vendedor":
                try {
                    usuarioAutenticado = VendedorController.getInstance().buscarVendedorPorId(id);

                } catch (Exception e) {
                    throw new UsuarioNoEncontradoException("Usuario no encontrado");

                }
                if (usuarioAutenticado == null) {
                    return null;
                }
                break;
            default:
                throw new IllegalArgumentException("Tipo de usuario desconocido: " + tipoUsuario);
        }

        this.tipoUsuario = tipoUsuario;

        return usuarioAutenticado;

    }

    public Cliente obtenerClienteAutenticado() {
        if (usuarioAutenticado instanceof Cliente) {
            return (Cliente) usuarioAutenticado;
        }
        throw new IllegalStateException("El usuario autenticado no es un Cliente.");
    }

    // Método para obtener el usuario autenticado como Vendedor
    public Vendedor obtenerVendedorAutenticado() {
        if (usuarioAutenticado instanceof Vendedor) {
            return (Vendedor) usuarioAutenticado;
        }
        throw new IllegalStateException("El usuario autenticado no es un Vendedor.");
    }

    // Método para verificar si hay un usuario autenticado
    public boolean estaAutenticado() {
        return usuarioAutenticado != null;
    }

    // Método para cerrar sesión y limpiar la autenticación
    public void cerrarSesion() {
        usuarioAutenticado = null;
        tipoUsuario = "admin";
    }

}
