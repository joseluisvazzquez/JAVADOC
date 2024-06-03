package javadocsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * gestión de los restaurantes en la aplicación.
 * Proporciona una interfaz de usuario para visualizar los restaurantes.
 * 
 * @author Desconocido
 */

public class Restaurant {
    public JFrame jrest;
    public JPanel prest;
    public DefaultTableModel modelrest;
    public JTable tablerest;
    public JScrollPane scrollrest;
    /**
     * Constructor de la clase Restaurant.
     * componentes de la interfaz de usuario.
     */
    public Restaurant(){
        jrest = new  JFrame();
        prest = new JPanel();
        modelrest = new DefaultTableModel();
        String columnrest[] = { "id", "nombre", "ubicacion", "tipo_restaurante", "cantidad", "id_chef", "id_menu" };
        modelrest.setColumnIdentifiers(columnrest);
        tablerest = new JTable(modelrest);
        scrollrest = new JScrollPane(tablerest);
        prest.add(scrollrest);
        jrest.add(prest);
        
        jrest.pack();
    }
    /**
     * Método para conectar con la base de datos y actualizar la tabla de restaurantes.
     * 
     * @param visible Indica si la ventana debe ser visible o no.
     */
    public void tableConnection(boolean visible){
         try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root", "" );
            PreparedStatement myst = conexion.prepareStatement("Select * from restaurantes");
            ResultSet rs = myst.executeQuery();
            modelrest.setRowCount(0);
            while (rs.next()) {
                        // Leemos la info de una de las tiendas
            String id = String.valueOf(rs.getInt("id"));
            String nombre = rs.getString("nombre");
            String ubicacion = rs.getString("ubicacion");
            String tipo = rs.getString("tipo");
            String sitios = String.valueOf(rs.getInt("sitios"));
            String id_chef = rs.getString("id_chef");
            String id_menu = rs.getString("id_menu");
            jrest.setVisible(visible);
            modelrest.addRow(new Object[]{id,nombre,ubicacion,tipo,sitios,id_chef, id_menu});        
            }   
        } catch (SQLException ej) {
            System.out.println("Error de BBDD: " + ej);
        } catch (Exception ex) {
            System.out.println("Error desconocido: " + ex);
        }
    }
}
