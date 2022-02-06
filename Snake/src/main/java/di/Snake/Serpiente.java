package di.Snake;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Serpiente extends Thread{
    // Indicador para para cuando se muera la serpiente
    private boolean estaViva;
    // Hilo que genera manzanas en el tablero
    private GeneradorDeManzanas generadorDeManzanas;
    // Hilo que genera bonificadores de velocidad
    private GeneradorDeVelocidad generadorDeVelocidad;
    // Cronometro que cuenta el tiempo de juego
    private Cronometro cronometro;
    // Posiciones de la serpiente
    private List<int[]> cuerpo;
    // parte de la cola especifica, para poder añadir secciones
    private int[] cola;
    // Tamaño del tablero
    private final int width;
    private final int height;
    // velocidad a la que se mueve la ser
    private double velocidad;
    private jDialogPuntuacion dialogPuntuacion;
    public Serpiente(int width,int height, int velocidad){
        this.width = width;
        this.height = height;
        this.velocidad = velocidad;
        init();
    }

    @Override
    public void run() {
        try {
            cronometro.start();
            generadorDeManzanas.start();
            generadorDeVelocidad.start();
            while(estaViva){
                // Guarda la posición de la cola por si hay que añadir segmentos
                this.cola = this.cuerpo.get(ultimoSegmento()).clone();
                // TODO no mover en la direccion que viene
                // Mueve el cuerpo
                for (int i = ultimoSegmento(); i >= 0; i--) {
                    switch (this.cuerpo.get(i)[2]) {
                        case KeyEvent.VK_A: // Izquierda
                        case KeyEvent.VK_LEFT:
                            this.cuerpo.get(i)[0]--;
                            break;
                        case KeyEvent.VK_D: // Derecha
                        case KeyEvent.VK_RIGHT:
                            this.cuerpo.get(i)[0]++;
                            break;
                        case KeyEvent.VK_W: // Arriba
                        case KeyEvent.VK_UP:
                            this.cuerpo.get(i)[1]--;
                            break;
                        case KeyEvent.VK_S:  // Abajo
                        case KeyEvent.VK_DOWN:
                            this.cuerpo.get(i)[1]++;
                            break;
                    }
                    // Cambia la dirección para la siguiente vuelta
                    if(i > 0){
                        this.cuerpo.get(i)[2] = this.cuerpo.get(i-1)[2];
                    }
                }
                // Comprueba que la cabeza no ha chocado con nada
                if(NewJFrame.tablero[this.cuerpo.get(0)[0]][this.cuerpo.get(0)[1]].getBackground() == Color.BLACK){
                    morir();
                    break;
                // Si con lo que choca es una manzana añade una sección al cuerpo
                } else if(NewJFrame.tablero[this.cuerpo.get(0)[0]][this.cuerpo.get(0)[1]].getBackground() == Color.RED){
                    añadirCuerpo();
                // Si con lo que choca es un boost de velocidad aumenta la velocidad y continua su camino
                } else if (NewJFrame.tablero[this.cuerpo.get(0)[0]][this.cuerpo.get(0)[1]].getBackground() == Color.BLUE){
                    // Aumenta la velocidad
                    this.velocidad = this.velocidad*0.85;
                    if(generadorDeVelocidad.isAlive()){
                        generadorDeVelocidad.velocidadObtenida();
                    }
                    // Borra la cola
                    NewJFrame.tablero[this.cola[0]][this.cola[1]].setBackground(Color.WHITE);
                // Si no choca con nada continua con su camino de forma normal
                } else {
                    // Borra la cola
                    NewJFrame.tablero[this.cola[0]][this.cola[1]].setBackground(Color.WHITE);
                }
                // Colorea el cuerpo
                NewJFrame.tablero[this.cuerpo.get(0)[0]][this.cuerpo.get(0)[1]].setBackground(Color.BLACK);
                try {
                    Thread.sleep((long)velocidad);
                } catch (InterruptedException ex) {
                    System.out.println("Serpiente: Interrupción - Hilo parado");
                }
            }
        } catch(IndexOutOfBoundsException e){
            morir();
        }
    }
    
    private int ultimoSegmento(){
        return this.cuerpo.size()-1;
    }
    public void setDireccion(int direccion){
        // Establece la dirección de la cabeza
        this.cuerpo.get(0)[2] = direccion;
    }
    /**
     * Añade un segmento al cuerpo de la serpiente
     */
    private void añadirCuerpo(){
        this.cuerpo.add(this.cola.clone());
        //generadorDeManzanas.notify();
        generadorDeManzanas.manzanaDevorada();
        //generadorDeManzanas.wait();
        //log
        System.out.println("Serpiente: Manzana devorada");
    }
    /**
     * Para la ejecución del hilo
     */
    public void morir(){
        cronometro.pararCronometro();
        generadorDeManzanas.pararDeGenerar();
        generadorDeVelocidad.pararDeGenerar();
        //log
        System.out.println("Serpiente: La serpiente ha muerto");
        dialogPuntuacion.iniciar();
        dialogPuntuacion.setVisible(true);
        this.estaViva = false;
    }
    private void init(){
        // Inicia la serpiente
        this.estaViva = true;
        // Inicia el cuerpo de la serpiente
        this.cuerpo = new ArrayList<>();
        /*
        Cuerpo se compone de arrays de 3 ints:
        posición 0 = La posición x en el tablero
        posición 1 = La posición y en el tablero
        posición 2 = La dirección de la parte del cuerpo
        */
        // Cabeza
        NewJFrame.tablero[this.width/2][this.height/2].setBackground(Color.BLACK);
        this.cuerpo.add(new int[3]);
        this.cuerpo.get(0)[0] = (this.width/2);
        this.cuerpo.get(0)[1] = (this.height/2);
        this.cuerpo.get(0)[2] = 87;
        // Medio
        NewJFrame.tablero[this.width/2][(this.height/2)+1].setBackground(Color.BLACK);
        this.cuerpo.add(new int[3]);
        this.cuerpo.get(1)[0] = (this.width/2);
        this.cuerpo.get(1)[1] = (this.height/2)+1;
        this.cuerpo.get(1)[2] = 87;
        // Cola
        NewJFrame.tablero[this.width/2][(this.height/2)+2].setBackground(Color.BLACK);
        // Posiciones del cuerpo
        this.cuerpo.add(new int[3]);
        this.cuerpo.get(2)[0] = (this.width/2);
        this.cuerpo.get(2)[1] = (this.height/2)+2;
        this.cuerpo.get(2)[2] = 87;
        // Guardamos la cola
        this.cola = new int[3];
        this.cola = this.cuerpo.get(ultimoSegmento()).clone();
        generadorDeManzanas = new GeneradorDeManzanas(this.width, this.height);
        generadorDeVelocidad = new GeneradorDeVelocidad(this.width, this.height);
        cronometro = new Cronometro();
        dialogPuntuacion = new jDialogPuntuacion(NewJFrame.getFrames()[0], true);
    }
}