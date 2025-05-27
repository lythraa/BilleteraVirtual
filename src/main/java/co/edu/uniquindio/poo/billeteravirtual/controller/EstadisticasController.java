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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EstadisticasController {

    @FXML
    private VBox boxEvolucionSaldo;

    @FXML
    private ImageView iconoVolver;

    @FXML
    private VBox boxSaldoPromedio;

    @FXML
    private LineChart<?, ?> chartEvolucionSaldoPromedio;

    @FXML
    private PieChart chartMovimientosPorCategoria;

    @FXML
    private Label labelSaldoPromedio;

    @FXML
    private VBox boxTopUsuarios;

    @FXML
    private BarChart<String, Number> chartUsuariosConMasMovimientos;

    @FXML
    private VBox boxGastos;

    private SistemaBilleteraFacade fachada = SistemaBilleteraFacade.getInstancia();

    @FXML
    public void initialize() {
        cargarChartMovimientosPorCategoria();
        cargarChartUsuariosConMasMovimientos();
        mostrarSaldoPromedioGeneral();
    }

    @FXML
    public void onVolver(){
        Stage stage = (Stage) labelSaldoPromedio.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "AdministradorView.fxml", "Vista Administrador");
    }

    private void cargarChartMovimientosPorCategoria() {
        chartMovimientosPorCategoria.getData().clear();
        Map<String, Double> datos = fachada.obtenerMovimientosPorCategoria();

        for (Map.Entry<String, Double> entry : datos.entrySet()) {
            PieChart.Data slice = new PieChart.Data(entry.getKey() + " (" + String.format("%.1f", entry.getValue()) + "%)", entry.getValue());
            chartMovimientosPorCategoria.getData().add(slice);
        }
    }

    private void cargarChartUsuariosConMasMovimientos() {
        chartUsuariosConMasMovimientos.getData().clear();
        Map<String, Integer> datos = fachada.obtenerUsuariosConMasMovimientos(5); // top 5

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Usuarios");

        for (Map.Entry<String, Integer> entry : datos.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        chartUsuariosConMasMovimientos.getData().add(series);
    }

    private void mostrarSaldoPromedioGeneral() {
        double saldo = fachada.calcularSaldoPromedioUsuarios();
        labelSaldoPromedio.setText("$ " + String.format("%,.2f", saldo));
    }



}