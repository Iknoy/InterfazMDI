
package interfazmdi;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.JInternalFrame;

/**
 * @author Jonathan Nicio <jnicion@gmail.com>
 * @version 1.0
 */

public final class Escritorio {
    JMenuBar jMBBarra;
    JMenu jMArchivo;
    JMenu jMDisposicion;
    JMenu jMAcercaDe;
    JMenuItem jMISalir;
    JMenuItem jMINuevo;
    JMenuItem jMIGuardar;
    JMenuItem jMIAbrir;
    JMenuItem jMICascada;
    JMenuItem jMIMosaico;
    JMenuItem jMIAyuda;
    JPanel JPNotas;
    JFrame principal;
    private final VentanaHija[] hija;
    private final oyenteMenu OidoMenu;
    private final JDesktopPane escritorio;
    private int contador=0;
    private String path="";
    private String selected="";
    private int x;
    private int y;
    
    public Escritorio(){
        //Declaración Menú
        jMBBarra = new JMenuBar();
        jMArchivo = new JMenu("Archivo");
        jMDisposicion = new JMenu("Disposición");
        jMAcercaDe = new JMenu("Acerca de");
        jMIAbrir= new JMenuItem("Abrir",new ImageIcon(getClass().getResource("/images/Abrir.png")));
        jMINuevo= new JMenuItem("Nuevo",new ImageIcon(getClass().getResource("/images/Nuevo.png")));
        jMIGuardar = new JMenuItem("Guardar",new ImageIcon(getClass().getResource("/images/Guardar.png")));
        jMISalir = new JMenuItem("Salir",new ImageIcon(getClass().getResource("/images/Salir.png")));
        jMICascada = new JMenuItem("Cascada",new ImageIcon(getClass().getResource("/images/Cascada.png")));
        jMIMosaico = new JMenuItem("Mosaico",new ImageIcon(getClass().getResource("/images/Mosaico.png")));
        jMIAyuda = new JMenuItem("Ayuda",new ImageIcon(getClass().getResource("/images/web.png")));
        principal = new JFrame();
        //Ejecuta la ayuda dinámica
        cargarMenu();
        hija=new VentanaHija[10];
        escritorio = new JDesktopPane();
        OidoMenu=new oyenteMenu();           
    }
    //Metodo dedicada para aplicar estilo al proyecto
    public void lookAdnFell(){
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        // If Nimbus is not available, you can set the GUI to another look and feel. "Nimbus", "Motif", "Metal", "Windows"
        }
    }
    
    public void crearEscritorio(){
        
        //aplciación del estilo
        lookAdnFell();
        
        //creación Menú
        jMArchivo.add(jMINuevo);
        jMArchivo.add(jMIAbrir);
        jMArchivo.add(jMIGuardar);
        jMArchivo.addSeparator();
        jMArchivo.add(jMISalir);
        jMDisposicion.add(jMICascada);
        jMDisposicion.add(jMIMosaico);
        jMAcercaDe.add(jMIAyuda);
        jMBBarra.add(jMArchivo);
        jMBBarra.add(jMDisposicion);
        jMBBarra.add(jMAcercaDe);
        
        
        //Asignación de tecla para el mnemotécnico
        jMArchivo.setMnemonic('A');
        jMDisposicion.setMnemonic('D');
        jMAcercaDe.setMnemonic('e');
        jMINuevo.setMnemonic('n');
        jMIAbrir.setMnemonic('b');
        jMIGuardar.setMnemonic('G');
        jMISalir.setMnemonic('S');
        jMIMosaico.setMnemonic('M');
        jMICascada.setMnemonic('C');
        jMIAyuda.setMnemonic('y');
        
        //Asignación de la pulsación de tecla
        jMINuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        jMIAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));        
        jMIGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));        
        jMISalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,ActionEvent.ALT_MASK));
        jMIMosaico.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,ActionEvent.CTRL_MASK));
        jMICascada.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UP,ActionEvent.CTRL_MASK));
        jMIAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
        
        //Asignación del menu al frame
        principal.setJMenuBar(jMBBarra); 
        
        //Aplicar oyentes
        jMISalir.addActionListener(OidoMenu);
        jMINuevo.addActionListener(OidoMenu);
        jMIAbrir.addActionListener(OidoMenu);
        jMICascada.addActionListener(OidoMenu);
        jMIMosaico.addActionListener(OidoMenu);
        jMIAyuda.addActionListener(OidoMenu);
        jMIGuardar.addActionListener(OidoMenu);
        
        
   
        //Visibilidad del Frame
        principal.add(escritorio);
        principal.pack();
        principal.setTitle("MDI_Notepad");
        principal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        principal.setMinimumSize(new Dimension(400, 300));
        principal.setLocationRelativeTo(null);
        principal.setVisible(true);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //Obtención de las medidas de la pantalla
    public void medidasPantalla(int i){
        if (i<6){
            x=(principal.getWidth()/6)*(i);
            y=(principal.getHeight()/4);
        }else{
            x=(principal.getWidth()/6)*(i-5);
            y=(principal.getHeight()/4)*2;
        }
        
    }
    
    //crear nota nueva
    public void crearNota(){
        JInternalFrame actual[] = escritorio.getAllFrames();
        if (actual.length<10){
            medidasPantalla(actual.length+1 );
                
                    hija[actual.length]=new VentanaHija();
                    hija[actual.length].crearFrame(contador);
                    hija[actual.length].setSize(new Dimension(250, 200));
                    hija[actual.length].setLocation((actual.length+1)*20, (actual.length+1)*20);
                    escritorio.add(hija[actual.length]);
                    contador++;
                    
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Sólo se pueden tener abiertas diez ventanas al mismo tiempo","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void abrirNota(){
        JInternalFrame actual[] = escritorio.getAllFrames();
        if(actual.length <10){
                 abrirArchivo();  
                 medidasPantalla(contador+1 );
                try{
                    hija[actual.length]=new VentanaHija();
                    hija[actual.length].crearFrame(path, selected);
                    hija[actual.length].setSize(new Dimension(250, 200));
                    hija[actual.length].setLocation((actual.length+1)*20, (actual.length+1)*20);
                    escritorio.add(hija[actual.length]);
                    contador++;
                }catch(ArrayIndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(new JFrame(), "No se puede crear/adicionar más ventanas","Error",JOptionPane.ERROR_MESSAGE);
                }
                }else{
            JOptionPane.showMessageDialog(new JFrame(), "Sólo se pueden tener abiertas diez ventanas al mismo tiempo","Error",JOptionPane.ERROR_MESSAGE);
        }
                
    }
    
    //Clase para obtener la información de un archivo de una ubicación dada
    public void abrirArchivo(){
        
        File archivo = null;
        String aux="";   
        String texto="";
        try
            {
             JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if(JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(principal)){
                    archivo = fileChooser.getSelectedFile();
                    //Se asigna la dirección del archivo a un string 
                    path=archivo.getPath();
                }
             if(archivo!=null)
             {     
                FileReader archivos=new FileReader(archivo);
                 try (BufferedReader lee = new BufferedReader(archivos)) {
                     while((aux=lee.readLine())!=null)
                     {
                         texto+= aux+ "\n";
                     }}
              }    
             }
             catch(IOException ex)
             {
               JOptionPane.showMessageDialog(null,ex+"\nNo se ha encontrado el archivo", "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
              }
        //El texto se almacena dentro de un string para utilizarlo despues 
        selected=texto;
            
    }
    
    //Guarda una nota existente
    public void guardarNota(){
        JInternalFrame guardar[] = escritorio.getAllFrames();
        for (int i = 0; i < guardar.length; i++) {
            if (guardar[i].isSelected()){
                try{
                    JFileChooser file=new JFileChooser();
                    file.showSaveDialog(principal);
                    File guarda =file.getSelectedFile();
                    path=guarda.getPath();
                    guardar[i].setTitle(path);
                    if(guarda !=null){ /*guardamos el archivo y le damos el formato directamente, si queremos que se guarde en formato doc lo definimos como .doc*/
                        try (FileWriter save = new FileWriter(guarda+".txt")) {
                            save.write(hija[i].obtenerTexto());
                        }
                        JOptionPane.showMessageDialog(null,"El archivo se a guardado Exitosamente", "Guardado",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                catch(IOException ex){
                    JOptionPane.showMessageDialog(null, "Su archivo no se ha guardado", "Advertencia",JOptionPane.WARNING_MESSAGE);
                }
                break;
            }
        }
    }
    
    //acomodo de casacada de las ventanas
    public void acomodoCascada(){
        int i;
        int xC,xH;
        int yC,yH;
        
        xC=principal.getWidth()/15;
        yC=principal.getHeight()/20;
        
        JInternalFrame v[] = escritorio.getAllFrames();
        x=0;
        y=0;
        for(i=v.length-1;i>=0; i--){
            v[i].setSize(250,200);
            yH=v[i].getHeight()/2;
            xH=v[i].getWidth()/2;
            v[i].setLocation((xC*(i+3))-xH, (yC*(i+3))-yH);
            v[i].moveToFront();
            v[i].setResizable(true); 
            }
    }
    //Acomodo en mosaico de las ventanas
    public void acomodoMosaico(){
        int xH;
        int yH;
        int i;
        
        JInternalFrame v[] = escritorio.getAllFrames();
        for(i=v.length-1;i>=0; i--){
            medidasPantalla(i+1);
            v[i].setSize(new Dimension(250, 200));
            yH=v[i].getHeight()/2;
            xH=v[i].getWidth()/2;
            v[i].setLocation(x-xH, y-yH);
            v[i].moveToFront();
            v[i].setResizable(true);
        }
    }
    
    //Ayuda orientada
    public void cargarMenu() {
        try{
            File archivo = new File("src/interfazmdi/help/help.hs");
            URL hsURL =archivo.toURI().toURL();
            HelpSet helpset = new HelpSet(getClass().getClassLoader(),hsURL);
            HelpBroker hb = helpset.createHelpBroker();
            hb.enableHelpOnButton(jMIAyuda, "manual", helpset);
        }catch(HelpSetException | MalformedURLException ex ){  
            
        }        
    }
    
    //Oyentes
    class oyenteMenu implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jMISalir) {                //Oyente del menú Archivo-salir
                System.exit(0);
            }else if(e.getSource() == jMINuevo){            //Oyente del menú Archivo-Nuevo
                 crearNota();
            }else if(e.getSource() == jMIAbrir){            //Oyente del menú Archivo-Abrir
                abrirNota();
            }else if (e.getSource() == jMIMosaico){         //Oyente del menú Acomodo-Mosaico
                acomodoMosaico();
            }else if (e.getSource() == jMICascada){         //Oyente del menú Acomodo-Cascada
                acomodoCascada();
            }else if (e.getSource() == jMIGuardar){
                guardarNota();
            }     
        }
    }
    
}