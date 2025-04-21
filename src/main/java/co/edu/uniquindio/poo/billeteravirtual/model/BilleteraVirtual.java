package co.edu.uniquindio.poo.billeteravirtual.model;

public class BilleteraVirtual {

    //Instancia del facade, el cúal maneja la lógica de negocio de la aplicación
    private SistemaBilleteraFacade facade;

    /**
     * Constructor público de la clase
     */
    public BilleteraVirtual() {
        this.facade = new SistemaBilleteraFacade();
    }

}
