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
import java.util.ArrayList;
import java.util.List;
import modelo.Perfil;

/**
 *
 * @author nippo
 */
public class PerfilController {
    
    public ArrayList<Perfil> listarPerfiles(){
        ArrayList<Perfil> perfiles = new ArrayList<>();
        
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM PERFIL";
            PreparedStatement buscar = conexion.prepareStatement(query);
            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                Perfil dto = new Perfil();
                dto.setId(rs.getInt("ID"));
                dto.setDetallePerfil(rs.getString("DETALLEPERFIL"));
                perfiles.add(dto);
            }
            buscar.close();
            conexion.close();
        } catch (SQLException w) {
            System.out.println("Error SQL al buscar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        
        return perfiles;
    }
    
    public Perfil buscarPorID(int id){
        Perfil perfil = null;
        
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM PERFIL where ID=?";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.setInt(1, id);
            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                perfil = new Perfil();
                perfil.setId(rs.getInt("ID"));
                perfil.setDetallePerfil(rs.getString("DETALLEPERFIL"));

            }
            buscar.close();
            conexion.close();
        } catch (SQLException w) {
            System.out.println("Error SQL al buscar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        
        return perfil;
    }
    
    public Perfil buscarPorNombre(String nombre){
        Perfil perfil = null;
        
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM PERFIL where DETALLEERFIL=?";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.setString(1, nombre);
            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                perfil = new Perfil();
                perfil.setId(rs.getInt("ID"));
                perfil.setDetallePerfil(rs.getString("DETALLEPERFIL"));

            }
            buscar.close();
            conexion.close();
        } catch (SQLException w) {
            System.out.println("Error SQL al buscar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        
        return perfil;
    }
}
