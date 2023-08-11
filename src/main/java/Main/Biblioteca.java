/*
 * Rendon Estrada Jorge
 * Fecha: 24/07/2023
 * Descripcion: Clase main del programa
 */
package Main;

import MVC.Controlador;
import MVC.Modelo;
import com.formdev.flatlaf.FlatLightLaf;
import pantallas.Dashjboard;

/**
 *
 * @author Jorge
 */
public class Biblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Se usa este metdo para ponerle los estilos a la vista
        FlatLightLaf.setup();
        //Se crean los objetos
        Dashjboard db = new Dashjboard();//vista
        Modelo model = new Modelo();//modelo
        
        //Controlador
        Controlador controlador = new Controlador(model,db);
    }
    
}
