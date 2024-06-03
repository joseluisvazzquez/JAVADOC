package javadocsql.implementation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javadocsql.Chefs;

public class AddChefs {
    public JFrame jaddchefs;
    public JPanel paddchefs;
    public JLabel namelabel;
    public JTextField namefield;
    public JLabel lastnamelabel;
    public JTextField lastnamefield;
    public JLabel specialtylabel;
    public JTextField specialtyfield;
    public JButton addchefs;
    public Chefs chefs;
/**
 * interfaz de usuario para introducir los detalles del nuevo chef.
 * 
 * @author Desconocido
 */
    public AddChefs(){
     /**
     * Constructor de la clase AddChefs.
     * Inicializa los componentes de la interfaz de usuario y define las acciones de los botones.
     */
        jaddchefs = new JFrame();
        paddchefs = new JPanel();
        namelabel = new JLabel("Name your chef");
        namefield = new JTextField();
        lastnamelabel = new JLabel("Last Name of your chef");
        lastnamefield = new JTextField();
        specialtylabel = new JLabel("Specialty of your chef");
        specialtyfield = new JTextField();
        addchefs = new JButton("Add a chef!!");

        paddchefs.add(namelabel);
        paddchefs.add(namefield);
        paddchefs.add(lastnamelabel);
        paddchefs.add(lastnamefield);
        paddchefs.add(specialtylabel);
        paddchefs.add(specialtyfield);
        paddchefs.add(addchefs);
        jaddchefs.add(paddchefs);
        paddchefs.setLayout(new BoxLayout(paddchefs,BoxLayout.Y_AXIS));
        jaddchefs.pack();
        addchefs.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hola");
                try {
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root", "");
                    PreparedStatement myst = conexion.prepareStatement("insert into chefs (nombre, apellidos, especialidad) VALUES (?, ?, ?)");
                    /**
                    * @param namefield.getText() El nombre del chef a añadir.
                    * @param lastnamefield.getText() El apellido del chef a añadir.
                    * @param specialtyfield.getText() La especialidad del chef a añadir.
                    */
                    
                    myst.setString(1,namefield.getText());
                    myst.setString(2,lastnamefield.getText());
                    myst.setString(3,specialtyfield.getText());
                    myst.executeUpdate();
                    System.out.println(""+ myst.executeUpdate());
                } catch (SQLException ej) {
                    System.out.println("Error de BBDD: " + ej);
                } catch (Exception ex) {
                    System.out.println("Error desconocido: " + ex);

                }
              
            }
            
        });
 
    }
    /**
     * Método para hacer visible la ventana de añadir chef.
     */
    public void visible(){
        jaddchefs.setVisible(true);
    }
}
