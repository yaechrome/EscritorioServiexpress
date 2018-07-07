package controlador;

import bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import modelo.Usuario;

public class UsuarioController {

    public boolean Crear(Usuario usuario) {
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

    public boolean Modificar(Usuario usuario) {

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

    public List<Usuario> ListarTodos() {
        List<Usuario> usuarios = null;
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
                dto.setRut(rs.getString("pass_usuario"));
                dto.setNombre(rs.getString("login_usuario"));

//                dto.setCorreoUsuario(rs.getString("correo_usuario"));
//                dto.setCodigoPerfil(rs.getInt("codigo_perfil"));
//                dto.setFechaNacimiento(rs.getDate("fechaNacimiento_usuario"));
//                usuarios.add(dto);
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
        Usuario usuario = null;

        return usuario;
    }

    public List<Usuario> ListarPorPerfil(int id) {
        List<Usuario> usuarios = null;
        return usuarios;
    }
}
