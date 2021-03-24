package edu.escuelaing.arep.app;
import java.lang.Math.*;
/**
 * Clase encargada de realizar las operaciones necesarias para calcular arcotangente y logaritmo.
 * @author  Alejandro Toro Daza
 * @version 1.0.  (24 de Marzo del 2021) 
 */
public class Calculadora {
    /**
     * Metodo encargado de realizar las operaciones necesarias para calcular el arcotangente del valor ingresado.
     * @param number Parametro que indica el numero ingresado con el que se van a realizar las operaciones.
     * @return Retorna el numero luego de realizar los respectivos calculos.
     */
    public double arcTangent(double number){
        return Math.atan(number);
    }
    /**
     * Metodo encargado de realizar las operaciones necesarias para calcular el logaritmo del valor ingresado.
     * @param number Parametro que indica el numero ingresado con el que se van a realizar las operaciones.
     * @return Retorna el numero luego de realizar los respectivos calculos.
     */
    public double logarithm(double number){
        return Math.log(number);
    }
}