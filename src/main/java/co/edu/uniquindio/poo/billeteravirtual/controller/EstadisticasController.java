package co.edu.uniquindio.poo.billeteravirtual.controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.billeteravirtual.model.SistemaBilleteraFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private BarChart<?, ?> barChartUsuarios;

    @FXML
    private VBox boxTopUsuarios;

    @FXML
    private VBox boxGastos;

    @FXML
    private PieChart chartGastos;

    @FXML
    private LineChart<String, Number> chartSaldoEvolucion;
    
}