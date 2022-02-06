package di.Snake;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

/**
 *
 * @author Alejandro González Navarro
 */
public class TablaDePuntuacion {
    // Atributos
    private final String archivo;
    private String[][] tabla;
    // Constructor
    public TablaDePuntuacion() {
        this.archivo = "/tabla/tabla.txt";
    }
    
    // Funciones
    public void mostrarTabla(){
        this.tabla = recuperarLista();
        NewJFrame.jLabelPuntNombre1.setText(tabla[0][0]);
        NewJFrame.jLabelPuntNombre2.setText(tabla[1][0]);
        NewJFrame.jLabelPuntNombre3.setText(tabla[2][0]);
        NewJFrame.jLabelPuntPuntuacion1.setText(tabla[0][1]);
        NewJFrame.jLabelPuntPuntuacion2.setText(tabla[1][1]);
        NewJFrame.jLabelPuntPuntuacion3.setText(tabla[2][1]);
    }
    public void guardarTabla(int posicion, String nombre, String puntuacion){
        this.tabla = recuperarLista();
        switch(posicion){
            case 0: // Guarda el primer nombre en el segundo puesto y guarda el nombre del vencedor
            this.tabla[2][0] = this.tabla[1][0];
            this.tabla[2][1] = this.tabla[1][1];
            this.tabla[1][0] = this.tabla[0][0];
            this.tabla[1][1] = this.tabla[0][1];
            break;
            case 1: // Guarda el segundo nombre en el tercer puesto y guarda el nombre del vencedor
            this.tabla[2][0] = this.tabla[1][0];
            this.tabla[2][1] = this.tabla[1][1];
            break;
        }
        this.tabla[posicion][0] = nombre;
        this.tabla[posicion][1] = puntuacion;
        escribirLista(this.tabla[0][0]+":"+this.tabla[0][1]+";"+this.tabla[1][0]+":"+this.tabla[1][1]+";"+this.tabla[2][0]+":"+this.tabla[2][1]);
    }
    public String[][] recuperarLista(){
        this.tabla = new String[3][2];
        ;
        FileReader lector = null;
        BufferedReader br;
        String[] array = null;
        try{
            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(this.archivo)));
            array = br.readLine().split(";");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    this.tabla[i][j] = array[i].split(":")[j];
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("No se encuentra el archivo.");
        } catch(java.io.IOException e) {
            System.out.println("No se puede leer el archivo.");
        } finally {
            try{
                if( null != lector ){
                   lector.close();
                }
            }catch (IOException e2){
                System.out.println("Error :"+ e2);
            }
        }
        return this.tabla;
    }
    public void escribirLista(String texto){
        FileWriter escritor = null;
        try{
            // No se puede escribir en un archivo jar, por lo tanto se necesitaria de un archivo externo para escribir
            escritor = new FileWriter(new File(getClass().getResource(this.archivo).toURI()));
            escritor.write(texto.replaceAll("\\n+", ""));
        } catch(FileNotFoundException e) {
            System.out.println("No se encuentra el archivo.");
        } catch(java.io.IOException e){
            System.out.println("No se puede escribir en escribir el archivo.");
        } catch (URISyntaxException ex) {
            System.out.println("Error al escribir en el archivo.");
        } finally {
            try{
                if( null != escritor ){
                   escritor.close();
                }
            }catch (IOException e2){
                System.out.println("Error :"+ e2);
            }
        }
    }
}
