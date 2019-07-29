
package interfazmdi;

import java.awt.GridLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * @author Jonathan Nicio <jnicion@gmail.com>
 * @version 1.0
 */
public class VentanaHija extends JInternalFrame{
    private final JScrollPane jSPnotas;
    private final JTextArea jTAnotas;
    private final JPanel JPNotas;
    
    public VentanaHija() {
        jTAnotas = new JTextArea(12,25);
        jSPnotas = new JScrollPane(jTAnotas);
        JPNotas = new JPanel();
        JPNotas.setLayout(new GridLayout(1, 1));
        
}
    
    public void crearFrame(int i){
        
        JPNotas.add(jSPnotas);
        add(JPNotas);
        setTitle("Ventana "+(i+1));
        visualizar();
    }
    
    public void crearFrame(String path, String contenido){
        jTAnotas.insert(contenido, 0);
        JPNotas.add(jSPnotas);
        add(JPNotas);
        setTitle(path);
        visualizar();
        
    }
    
    public void visualizar(){
        pack();
        setVisible(true);
        setLocation(10, 10);
        setResizable(true);
        setMaximizable(true);
        setClosable(true);
        setIconifiable(true);  
        
    }
    
    public String obtenerTexto(){
        String contenido="";
        contenido=jTAnotas.getText();
        return contenido;
        
    }
    
}
