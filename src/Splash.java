import javax.swing.*;
import java.awt.*;

public class Splash extends JDialog {

    private JLabel porcentaje;
    private JProgressBar barra;
    private JLabel l2;

    //PROPIEDADES DIALOOGO
    public Splash() {
        inicio();
        setSize(751,621);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setUndecorated(true);

        inicioHilo();
    }

    //PROPIEDADES ELEMENTOS
    private void inicio() {

        //Imagen de fondo
        ImageIcon imagen = new ImageIcon("rompecabezas-numerico\\src\\splash.png");
        // Crea el objeto JLabel
        JLabel etiqueta = new JLabel(imagen);

        // Agrega la etiqueta al panel
        etiqueta.setSize(751, 621);
        etiqueta.setLocation(0, 0);

        JLabel l1 = new JLabel();
        l1.setFont(new Font("Inter", Font.PLAIN,18));
        l1.setBounds(49,11,147,32);
        getContentPane().add(l1);

        barra = new JProgressBar();
        barra.setBounds(230,424,294,22);
        barra.setOpaque(true);
        barra.setForeground(new Color(190, 162, 187));
        getContentPane().add(barra);

        porcentaje = new JLabel("0%");
        porcentaje.setFont(new Font("Inter", Font.PLAIN,18));
        porcentaje.setBounds(360,380,46,14);
        getContentPane().add(porcentaje);

        l2 = new JLabel();
        l2.setFont(new Font("Tahoma", Font.PLAIN,18));
        l2.setBounds(335,310,341,340);
        getContentPane().add(l2);

        getContentPane().add(etiqueta);
    }

    private void inicioHilo(){
        Thread hilo = new Thread(new Runnable(){

            int x = 0;
            String texto = " ";

            //ACCION DE CARGA
            public void run(){
                try{
                    while(x<=100){
                        barra.setValue(x);
                        porcentaje.setText(x+"%");
                        x++;
                        Thread.sleep(20);

                        //MENSAJES DE CARGA
                        if(x==5){
                            texto="Cargando...";
                            l2.setText(texto);
                        } else if (x==50){
                            texto="Iniciando...";
                            l2.setText(texto);
                        }
                    }

                    //CIERRA SPLASH, ABRE VENTANA DE ACCIONES
                    dispose();
                    Ventana v1 = new Ventana();
                    v1.setVisible(true);

                } catch (Exception e){
                    System.out.println("Excepcion: "+e.getMessage());
                }

            }
        });
        hilo.start();
    }
}