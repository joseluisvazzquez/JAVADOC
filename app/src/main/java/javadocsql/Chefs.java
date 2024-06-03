package javadocsql;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javadocsql.implementation.AddChefs;
/**
 * La clase Chefs se encarga de la gestión de los chefs en la aplicación.
 * Proporciona una interfaz de usuario para visualizar y añadir chefs.
 * 
 * @author Desconocido
 */

public class Chefs {
    public JFrame jchefs;
    public JPanel pchefs;
    public DefaultTableModel modelchefs;
    public JTable tablechefs;
    public JScrollPane scrollchefs;
    public JComboBox<String> combo;
    public String sql;
    public JButton addchef;
    /**
     *Constructor de la clase Chefs.
     */
    public Chefs(){
        jchefs = new  JFrame();
        pchefs = new JPanel();
        modelchefs = new DefaultTableModel();
        String columnchefs[] = { "id", "nombre", "plato1","plato2"};
        modelchefs.setColumnIdentifiers(columnchefs);
        tablechefs = new JTable(modelchefs);
        scrollchefs = new JScrollPane(tablechefs);
        combo = new JComboBox<>();
        combo.addItem("-----------------");
        combo.addItem("Carnicero");
        combo.addItem("Comida de Mar");
        combo.addItem("Comida Latina");
        /**
         *Acción que se realiza al seleccionar un elemento del JComboBox.
         *Actualiza la tabla de chefs según la especialidad seleccionada.
        */
        combo.addActionListener((ActionListener) ((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
        
                if(combo.getSelectedItem().equals("Carnicero")){
                        sql = "Select * from chefs WHERE chefs.especialidad = 'Carnicero'";  
                }
                else if(combo.getSelectedItem().equals("Comida de Mar")){
                    sql = "Select * from chefs WHERE chefs.especialidad = 'Comida de Mar'";

                }
                else if(combo.getSelectedItem().equals("Comida Latina")){
                    sql = "Select * from chefs WHERE chefs.especialidad = 'Comida Latina'";

                }else{
                        sql = "Select * from chefs";
                }
                try {
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root", "");
                    PreparedStatement myst = conexion.prepareStatement(sql);
                    ResultSet rs = myst.executeQuery();
                    modelchefs.setRowCount(0);
                    while (rs.next()) {
                         // Leemos la info de una de las tiendas
                        String id = String.valueOf(rs.getInt("id"));
                        String nombre = rs.getString("nombre");
                        String apellidos = rs.getString("apellidos");
                        String especialidad = rs.getString("especialidad");
                    
                        modelchefs.addRow(new Object[]{id,nombre,apellidos,especialidad});
                        jchefs.setVisible(true);
                    }
                } catch (SQLException ej) {
                    System.out.println("Error de BBDD: " + ej);
                } catch (Exception ex) {
                    System.out.println("Error desconocido: " + ex);
                }
            
            }
        }));
        addchef = new JButton("Add a chef");
        AddChefs addChefs = new AddChefs();
        addchef.addActionListener((ActionListener) ((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addChefs.visible();
            }
            
        }));

        pchefs.add(scrollchefs);
        pchefs.add(combo);
        pchefs.add(addchef);
        jchefs.add(pchefs);
        jchefs.pack();
    }
    /**
     *Método para conectar con la base de datos y actualizar la tabla de chefs.
     * 
     *@param visible Indica si la ventana debe ser visible o no.
     */
    public void tableConnection(boolean visible){
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root", "");
            PreparedStatement myst = conexion.prepareStatement("select * from chefs");
            ResultSet rs = myst.executeQuery();
            modelchefs.setRowCount(0);
            while (rs.next()) {
                // Leemos la info de una de las tiendas
                String id = String.valueOf(rs.getInt("id"));
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String especialidad = rs.getString("especialidad");
        
                modelchefs.addRow(new Object[]{id,nombre,apellidos,especialidad});
                jchefs.setVisible(visible);
            }

        } catch (SQLException ej) {
            System.out.println("Error de BBDD: " + ej);
        } catch (Exception ex) {
            System.out.println("Error desconocido: " + ex);
        }
    }
}
