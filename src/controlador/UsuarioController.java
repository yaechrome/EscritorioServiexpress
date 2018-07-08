package controlador;

import bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Perfil;
import modelo.Usuario;

public class UsuarioController {

    public boolean crear(Usuario usuario) {
        try {

            Connection conexion = Conexion.getConexion();

            String query = "INSERT INTO usuarios ( nombre, apellidopaterno, rut, direccion, contactotelefonico, perfil, username, password) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement insertar = conexion.prepareStatement(query);

            insertar.setString(1, usuario.getNombre());
            insertar.setString(2, usuario.getApellidoPaterno());
            insertar.setString(3, usuario.getRut());
            insertar.setString(4, usuario.getDireccion());
            insertar.setString(5, usuario.getContactoTelefonico());
            insertar.setInt(6, usuario.getPerfil().getId());
            insertar.setString(7, usuario.getUsername());
            insertar.setString(8, usuario.getPassword());
            insertar.execute();
            insertar.close();
            conexion.close();
            return true;
        } catch (SQLException w) {
            System.out.println("Error SQL al agregar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al agregar " + e.getMessage());
        }
        return false;
    }

    public boolean modificar(Usuario usuario) {

        try {

            Connection conexion = Conexion.getConexion();

            String query = "update Usuarios set nombre=?, apellidopaterno=?, rut=?, direccion=?, contactotelefonico=?, perfil=?, username=?, password =? where id=?";

            PreparedStatement modificar = conexion.prepareStatement(query);

            modificar.setString(1, usuario.getNombre());
            modificar.setString(2, usuario.getApellidoPaterno());
            modificar.setString(3, usuario.getRut());
            modificar.setString(4, usuario.getDireccion());
            modificar.setString(5, usuario.getContactoTelefonico());
            modificar.setInt(6, usuario.getPerfil().getId());
            modificar.setString(7, usuario.getUsername());
            modificar.setString(8, usuario.getPassword());
            modificar.setInt(8, usuario.getId());
            modificar.execute();
            modificar.close();
            conexion.close();
            return true;
        } catch (SQLException w) {
            System.out.println("Error SQL al modificar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al modificar " + e.getMessage());
        }
        return false;
    }

    public ArrayList<Usuario> listarTodos() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM usuario";
            PreparedStatement buscar = conexion.prepareStatement(query);

            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                Usuario dto = new Usuario();
                dto.setId(rs.getInt("id"));
                dto.setNombre(rs.getString("nombre"));
                dto.setApellidoPaterno(rs.getString("apellidopaterno"));
                dto.setRut(rs.getString("rut"));
                dto.setDireccion(rs.getString("direccion"));

                dto.setContactoTelefonico(rs.getString("contactotelefonico"));
                Perfil perfil = new PerfilController().buscarPorID(rs.getInt("perfil"));
                dto.setPerfil(perfil);
                dto.setUsername(rs.getString("username"));
                dto.setPassword(rs.getString("password"));
                usuarios.add(dto);
            }
            buscar.close();
            conexion.close();
        } catch (SQLException w) {
            System.out.println("Error SQL al buscar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        return usuarios;
    }

    public Usuario buscarPorRut(String rut) {
        Usuario dto = new Usuario();
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM usuario where rut = ?";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.setString(1, rut);
            ResultSet rs = buscar.executeQuery();
            PerfilController perfilController = new PerfilController();
            Perfil perfil = new Perfil();

            if (rs.next()) {

                dto.setId(rs.getInt("id"));
                dto.setNombre(rs.getString("nombre"));
                dto.setApellidoPaterno(rs.getString("apellidopaterno"));
                dto.setRut(rs.getString("rut"));
                dto.setDireccion(rs.getString("direccion"));
                dto.setContactoTelefonico(rs.getString("contactotelefonico"));
                perfil = perfilController.buscarPorID(rs.getInt("perfil"));
                dto.setPerfil(perfil);
                dto.setUsername(rs.getString("username"));
                dto.setPassword(rs.getString("password"));
            }
            buscar.close();
            conexion.close();

        } catch (SQLException w) {
            System.out.println("Error SQL al buscar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        return dto;
    }

    public ArrayList<Usuario> listarPorPerfil(int id) {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM usuario where perfil=?";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.setInt(1, id);
            ResultSet rs = buscar.executeQuery();

            PerfilController perfilController = new PerfilController();
            Perfil perfil = perfilController.buscarPorID(id);

            while (rs.next()) {
                Usuario dto = new Usuario();

                dto.setId(rs.getInt("id"));
                dto.setNombre(rs.getString("nombre"));
                dto.setApellidoPaterno(rs.getString("apellidopaterno"));
                dto.setRut(rs.getString("rut"));
                dto.setDireccion(rs.getString("direccion"));
                dto.setContactoTelefonico(rs.getString("contactotelefonico"));
                dto.setPerfil(perfil);
                dto.setUsername(rs.getString("username"));
                dto.setPassword(rs.getString("password"));
                lista.add(dto);
            }
            buscar.close();
            conexion.close();
        } catch (SQLException w) {
            System.out.println("Error SQL al buscar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        return lista;
    }
}
