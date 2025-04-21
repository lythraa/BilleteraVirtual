package co.edu.uniquindio.poo.billeteravirtual.model;

public class SistemaBilleteraFacade {

    //Instancia única del gestor de datos, el cual
    //actúa como una simulación de base de datos
    private BaseDatos baseDatos;

    /**
     * Constructor público de la clase, permite su instanciación
     */
    public SistemaBilleteraFacade() {
        baseDatos = BaseDatos.getInstancia();
    }

    //=========================GETTERS============================//

    public BaseDatos getBaseDatos() {
        return baseDatos;
    }

}
