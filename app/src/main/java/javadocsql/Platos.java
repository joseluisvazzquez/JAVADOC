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
 * gestión de los platos en la aplicación.
 * Proporciona una interfaz de usuario para visualizar los platos.
 * 
 * @author Desconocido
 */

public class Platos {
    public JFrame jplatos;
    public JPanel pplatos;
    public DefaultTableModel modelplatos;
    public JTable tableplatos;
    public JScrollPane scrollplatos;
    /**
     * Constructor de la clase platos.
     * componentes de la interfaz de usuario.
     */
    public Platos(){
        jplatos = new  JFrame();
        pplatos = new JPanel();
        modelplatos = new DefaultTableModel();
        String columnplatos[] = { "id", "nombre", "tipo", "coste" };
        modelplatos.setColumnIdentifiers(columnplatos);
        tableplatos = new JTable(modelplatos);
        scrollplatos = new JScrollPane(tableplatos);
        pplatos.add(scrollplatos);
        jplatos.add(pplatos);
        
        jplatos.pack();
    }
    /**
     * Método para conectar con la base de datos y actualizar la tabla de platos.
     * 
     * @param visible Indica si la ventana debe ser visible o no.
     */
    public void tableConnection(boolean visible){
         try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root", "" );
            PreparedStatement myst = conexion.prepareStatement("Select * from platos");
            ResultSet rs = myst.executeQuery();
            modelplatos.setRowCount(0);
            while (rs.next()) {
                        // Leemos la info de una de las tiendas
            String id = String.valueOf(rs.getInt("id"));
            String nombre = rs.getString("nombre");
            String tipo = rs.getString("tipo");
            String coste = String.valueOf(rs.getInt("coste"));
            jplatos.setVisible(visible);
            modelplatos.addRow(new Object[]{id,nombre,tipo,coste});        
            }   
        } catch (SQLException ej) {
            System.out.println("Error de BBDD: " + ej);
        } catch (Exception ex) {
            System.out.println("Error desconocido: " + ex);
        }
    }
}
