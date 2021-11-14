package di.prueba;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Alejandro González Navarro
 */
public class GeneradorDeVelocidad extends Thread {
    private boolean velocidadColocada;
    private boolean parar;
    private final Random rand;
    private final int width;
    private final int height;
    private int x;
    private int y;
    private int velocidadObtenida;
    public GeneradorDeVelocidad(int width, int height) {
        this.velocidadColocada = false;
        this.parar = false;
        this.rand = new Random();
        this.velocidadObtenida = 0;
        this.width = width;
        this.height = height;
    }

    @Override
    public void run(){
        while(!this.parar){
            while(!this.velocidadColocada){
                x = rand.nextInt(width);
                y = rand.nextInt(height);
                // log
                System.out.println("Generador de velocidad: Intentando colocar velocidad en las cordenadas"+x+","+y);
                if(NewJFrame.tablero[x][y].getBackground() == Color.WHITE){
                    NewJFrame.tablero[x][y].setBackground(Color.BLUE);
                    this.velocidadColocada = true;
                    // log
                    System.out.println("Generador de velocidad: velocidad colocada");
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Generador de velocidad: Interrupción - Hilo parado");
            }
        }
    }
    public void velocidadObtenida(){
        this.velocidadObtenida++;
        NewJFrame.jLabelVelocidadC.setText(velocidadObtenida+"/10");
        // Limite de velocidad 10
        if(this.velocidadObtenida < 10){
            this.velocidadColocada = false;
        } else {
            pararDeGenerar();
        }
    }
    public void pararDeGenerar(){
        this.parar = true;
    }
    
}
