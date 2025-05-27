package co.edu.uniquindio.poo.billeteravirtual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class EstadisticasController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox boxEvolucionSaldo;

    @FXML
    private ImageView iconoVolver;

    @FXML
    private VBox boxSaldoPromedio;

    @FXML
    private Label labelSaldoPromedio;

    @FXML
    private BarChart<?, ?> chartUsuarios;

    @FXML
    private VBox boxTopUsuarios;

    @FXML
    private VBox boxGastos;

    @FXML
    private PieChart chartGastos;

    @FXML
    private LineChart<?, ?> chartSaldoEvolucion;

    @FXML
    void onVolver(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxEvolucionSaldo != null : "fx:id=\"boxEvolucionSaldo\" was not injected: check your FXML file 'EstadisticasView.fxml'.";
        assert iconoVolver != null : "fx:id=\"iconoVolver\" was not injected: check your FXML file 'EstadisticasView.fxml'.";
        assert boxSaldoPromedio != null : "fx:id=\"boxSaldoPromedio\" was not injected: check your FXML file 'EstadisticasView.fxml'.";
        assert labelSaldoPromedio != null : "fx:id=\"labelSaldoPromedio\" was not injected: check your FXML file 'EstadisticasView.fxml'.";
        assert chartUsuarios != null : "fx:id=\"chartUsuarios\" was not injected: check your FXML file 'EstadisticasView.fxml'.";
        assert boxTopUsuarios != null : "fx:id=\"boxTopUsuarios\" was not injected: check your FXML file 'EstadisticasView.fxml'.";
        assert boxGastos != null : "fx:id=\"boxGastos\" was not injected: check your FXML file 'EstadisticasView.fxml'.";
        assert chartGastos != null : "fx:id=\"chartGastos\" was not injected: check your FXML file 'EstadisticasView.fxml'.";
        assert chartSaldoEvolucion != null : "fx:id=\"chartSaldoEvolucion\" was not injected: check your FXML file 'EstadisticasView.fxml'.";

    }
}