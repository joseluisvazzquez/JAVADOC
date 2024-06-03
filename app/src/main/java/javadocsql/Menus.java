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
 * La clase Menus se encarga de la gestión de los menús en la aplicación.
 * Proporciona una interfaz de usuario para visualizar los menús.
 * 
 * @author Desconocido
 */

public class Menus {
    public JFrame jmenus;
    public JPanel pmenus;
    public DefaultTableModel modelmenus;
    public JTable tablemenus;
    public JScrollPane scrollmenus;
  
    /**
     * Constructor de la clase Menus.
     * Inicializa los componentes de la interfaz de usuario.
     */
    public Menus(){
        jmenus = new  JFrame();
        pmenus = new JPanel();
        modelmenus = new DefaultTableModel();
        String columnmenus[] = { "id", "nombre", "plato1","plato2"};
        modelmenus.setColumnIdentifiers(columnmenus);
        tablemenus = new JTable(modelmenus);
        scrollmenus = new JScrollPane(tablemenus);

        pmenus.add(scrollmenus);
        jmenus.add(pmenus);
        
        jmenus.pack();
    }
    /**
     * Método para conectar con la base de datos y actualizar la tabla de menús.
     * 
     * @param visible Indica si la ventana debe ser visible o no.
     */
    public void tableConnection(boolean visible){
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root", "");
            PreparedStatement myst = conexion.prepareStatement("Select * from menu");
            ResultSet rs = myst.executeQuery();
            modelmenus.setRowCount(0);
            while (rs.next()) {
                // Leemos la info de una de las tiendas
                String id = String.valueOf(rs.getInt("id"));
                String nombre = rs.getString("nombre");
                String plato1 = String.valueOf(rs.getInt("plato1"));
                String plato2 = String.valueOf(rs.getInt("plato2"));
                
                jmenus.setVisible(visible);
                modelmenus.addRow(new Object[]{id,nombre,plato1,plato2});
            }

        } catch (SQLException ej) {
            System.out.println("Error de BBDD: " + ej);
        } catch (Exception ex) {
            System.out.println("Error desconocido: " + ex);
        }
    }
}
