package javadocsql;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * La clase Mainn es la clase principal que ejecuta la interfaz de usuario.
 * Crea botones para acceder a las ventanas de Restaurantes, Chefs y Menús.
 * 
 * @author alumne-DAM
 */
public class mainn {

    /**
     * El método principal que se ejecuta al iniciar el programa.
     * Crea una ventana con botones para acceder a las ventanas de Restaurantes, Chefs y Menús.
     * 
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        Restaurant restWindow = new Restaurant();
        Menus menuWindow = new Menus();
        Chefs chefsWindow = new Chefs();
        Platos platosWindow = new Platos();
        JFrame j = new JFrame();
        
        JPanel p = new JPanel();
       
        JButton rest = new JButton("Restaurantes");
        JButton chefs = new JButton("Chefs");
        JButton menus = new JButton("Menus");
        JButton platos = new JButton("Platos");
        
        p.add(rest);
        p.add(chefs);
        p.add(menus);
        p.add(platos);

        j.add(p);
        j.setVisible(true);
        j.pack();

        /**
         * Acción que se realiza al hacer clic en el botón "Restaurantes".
         * Abre la ventana de Restaurantes.
         */
        rest.addActionListener((ActionListener) ((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restWindow.tableConnection(true);
            }
        }));

        /**
         * Acción que se realiza al hacer clic en el botón "Chefs".
         * Abre la ventana de Chefs.
         */
        chefs.addActionListener((ActionListener) ((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chefsWindow.tableConnection(true);
            }
        }));

        /**
         * Acción que se realiza al hacer clic en el botón "Menus".
         * Abre la ventana de Menús.
         */
        menus.addActionListener((ActionListener) ((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuWindow.tableConnection(true);
            }

        }));
        platos.addActionListener((ActionListener) ((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                platosWindow.tableConnection(true);
            }
        }));
    }
}
