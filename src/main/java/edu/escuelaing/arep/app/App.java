package edu.escuelaing.arep.app;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import static spark.Spark.*;
/**
 * Clase principal encargada de llevar a cabo la ejecucion de todo el programa.
 * @author  Alejandro Toro Daza
 * @version 1.0.  (24 de Marzo del 2021) 
 */
public class App {
    private static Calculadora calcular = new Calculadora(); 
    /**
     * Metodo principal main que se encarga del funcionamiento de toda la clase App.
     * @param args Parametro que indica la lista de los elementos a evaluar.
     */
    public static void main(String[] args) {
        port(getPort());
        get("/atan", (req, res) -> inputDataPage(req, res,"atan"));
        get("/log", (req, res) -> inputDataPage(req, res,"log"));
    }
   /**
    * @param req Parametro que se encarga de almacenar la informacion de la peticion.
    * @param res Parametro que se encarga de almacenar la informacion de la respuesta del servidor.
    * @param operation Parametro que se encarga de realizar la respectiva operacion.
    * @return obj Retorna en formato JSON los resultados tal como se describen en el enunciado del examen.
    */
    private static JSONObject inputDataPage(Request req, Response res,String operation) {
        double number = Double.parseDouble(req.queryParams("value"));
        JSONObject obj = new JSONObject();
        obj.put("operation",operation);
        obj.put("input",number);
        obj.put("output",(operation.equals("atan"))? calcular.arcTangent(number) : calcular.logarithm(number));
        return obj;
    }
   /**
    * Este metodo lee el puerto predeterminado segun lo especificado por la variable PORT en el entorno.
    * @return returns Retorna el puerto predeterminado si el heroku-port no esta configurado (es decir, en localhost).
    */
   static int getPort() {
       if (System.getenv("PORT") != null) {
           return Integer.parseInt(System.getenv("PORT"));
       }
       return 3456;
   }
}