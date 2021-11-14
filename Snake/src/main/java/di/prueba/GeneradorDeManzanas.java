package di.prueba;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Alejandro González Navarro
 */
public class GeneradorDeManzanas extends Thread {
    private boolean manzanaColocada;
    private boolean parar;
    private final Random rand;
    private final int width;
    private final int height;
    private int x;
    private int y;
    public GeneradorDeManzanas(int width, int height) {
        manzanaColocada = false;
        parar = false;
        rand = new Random();
        this.width = width;
        this.height = height;
    }

    @Override
    public void run(){
        while(!this.parar){
            while(!this.manzanaColocada){
                x = rand.nextInt(width);
                y = rand.nextInt(height);
                // log
                System.out.println("Generador de manzanas: Intentando colocar una manzana en las cordenadas"+x+","+y);
                if(NewJFrame.tablero[x][y].getBackground() == Color.WHITE){
                    NewJFrame.tablero[x][y].setBackground(Color.RED);
                    this.manzanaColocada = true;
                    // log
                    System.out.println("Generador de manzanas: Manzana colocada");
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Generador de manzanas: Interrupción - Hilo parado");
            }
        }
    }
    public void manzanaDevorada(){
        // Añade uno al contador de manzanas
        NewJFrame.jLabelPuntuacionC.setText((Integer.parseInt(NewJFrame.jLabelPuntuacionC.getText())+100)+"");
        NewJFrame.jLabelManzanasC.setText((Integer.parseInt(NewJFrame.jLabelManzanasC.getText())+1)+"");
        this.manzanaColocada = false;
    }
    public void pararDeGenerar(){
        this.parar = true;
    }
    
}
