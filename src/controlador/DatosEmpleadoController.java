/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.*;

/**
 *
 * @author nippo
 */
public class DatosEmpleadoController {

    public boolean crear(DatosEmpleado datos) {
        boolean validar = false;

        return validar;
    }

    public boolean modificar(DatosEmpleado datos) {
        boolean validar = false;

        return validar;
    }

    public DatosEmpleado buscarDatosEmpleado(Usuario usuario) {
        DatosEmpleado datos = null;
        try {
            Connection conexion = Conexion.getConexion();
            String query = "select * from datos_empleados where usuario=?";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.setInt(1, usuario.getId());
            buscar.execute();
            ResultSet rs = buscar.executeQuery();
            if (rs.next()) {
                datos = new DatosEmpleado();
                datos.setId(rs.getInt("id"));
                datos.setUsuario(usuario);
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

}
