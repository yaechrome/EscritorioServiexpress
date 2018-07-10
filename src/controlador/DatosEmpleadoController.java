package controlador;

import bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.*;

public class DatosEmpleadoController {

    public boolean crear(DatosEmpleado datos) {
        try {

            Connection conexion = Conexion.getConexion();

            String query = "INSERT INTO datos_empleados (usuario, fechacontratacion, sueldo, cargo, obsadministrativas, id) VALUES ( ?, ?, ?, ?, ?,?)";

            PreparedStatement insertar = conexion.prepareStatement(query);

            insertar.setInt(1, datos.getUsuario().getId());
            insertar.setDate(2, new java.sql.Date(datos.getFechaContratacion().getTime()));
            insertar.setInt(3, datos.getSueldo());
            insertar.setString(4, datos.getCargo());
            insertar.setString(5, datos.getObservacion());
            insertar.setInt(6, datos.getId());
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

    public boolean modificar(DatosEmpleado datos) {
        try {

            Connection conexion = Conexion.getConexion();

            String query = "update datos_empleados set fechacontratacion=?, sueldo=?, cargo=?,"
                    + " obsadministrativas=? where usuario=?";

            PreparedStatement modificar = conexion.prepareStatement(query);

            modificar.setDate(1, new java.sql.Date(datos.getFechaContratacion().getTime()));
            modificar.setInt(2, datos.getSueldo());
            modificar.setString(3, datos.getCargo());
            modificar.setString(4, datos.getObservacion());
            modificar.setInt(5, datos.getUsuario().getId());
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

    public DatosEmpleado buscarDatosEmpleado(int idUsuario) {
        DatosEmpleado datos = null;
        try {
            Connection conexion = Conexion.getConexion();
            String query = "select * from datos_empleados where usuario=?";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.setInt(1, idUsuario);
            buscar.execute();
            ResultSet rs = buscar.executeQuery();
            if (rs.next()) {
                datos = new DatosEmpleado();
                datos.setId(rs.getInt("id"));
                datos.setUsuario(null);
                datos.setFechaContratacion(rs.getDate("fechaContratacion"));
                datos.setSueldo(rs.getInt("sueldo"));
                datos.setCargo(rs.getString("cargo"));
                datos.setObservacion(rs.getString("obsadministrativas"));
            }
            buscar.close();
            conexion.close();

        } catch (SQLException w) {
            System.out.println("Error  " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return datos;
    }

    public int secuenciaId() {
        int numero = 1;
        try {
            Connection conexion = Conexion.getConexion();
            String query = "select max(id) \"id\" from datos_empleados";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.execute();
            ResultSet rs = buscar.executeQuery();
            if (rs.next()) {
                numero = rs.getInt("id");
            }
            buscar.close();
            conexion.close();

        } catch (SQLException w) {
            System.out.println("Error  " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return numero;
    }

}
