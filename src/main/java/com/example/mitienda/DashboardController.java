package com.example.mitienda;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStock;

    @FXML
    private ComboBox<String> cbCategoria;

    @FXML
    private ComboBox<String> cbEstado;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> colCodigo;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colCategoria;

    @FXML
    private TableColumn<Producto, Number> colPrecio;

    @FXML
    private TableColumn<Producto, Number> colStock;

    @FXML
    private TableColumn<Producto, String> colEstado;

    private final ObservableList<Producto> listaProductos =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        cbCategoria.getItems().addAll(
                "Electrónica",
                "Ropa",
                "Hogar",
                "Alimentos"
        );

        cbEstado.getItems().addAll(
                "Activo",
                "Inactivo"
        );

        colCodigo.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getCodigo()));

        colNombre.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getNombre()));

        colCategoria.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getCategoria()));

        colPrecio.setCellValueFactory(data ->
                new SimpleDoubleProperty(
                        data.getValue().getPrecio()));

        colStock.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getStock()));

        colEstado.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getEstado()));

        tablaProductos.setItems(listaProductos);
    }

    @FXML
    private void nuevo() {
        limpiar();
    }

    @FXML
    private void guardar() {

        try {

            Producto producto = new Producto(
                    txtCodigo.getText(),
                    txtNombre.getText(),
                    cbCategoria.getValue(),
                    Double.parseDouble(txtPrecio.getText()),
                    Integer.parseInt(txtStock.getText()),
                    cbEstado.getValue()
            );

            listaProductos.add(producto);

            limpiar();

        } catch (Exception e) {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Error");

            alert.setContentText(
                    "Ingrese datos válidos");

            alert.showAndWait();
        }
    }

    @FXML
    private void actualizar() {

        Producto producto =
                tablaProductos.getSelectionModel()
                        .getSelectedItem();

        if (producto != null) {

            producto.setCodigo(txtCodigo.getText());
            producto.setNombre(txtNombre.getText());
            producto.setCategoria(cbCategoria.getValue());
            producto.setPrecio(
                    Double.parseDouble(txtPrecio.getText()));
            producto.setStock(
                    Integer.parseInt(txtStock.getText()));
            producto.setEstado(cbEstado.getValue());

            tablaProductos.refresh();
        }
    }

    @FXML
    private void eliminar() {

        Producto producto =
                tablaProductos.getSelectionModel()
                        .getSelectedItem();

        if (producto != null) {

            listaProductos.remove(producto);

            limpiar();
        }
    }

    @FXML
    private void limpiar() {

        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtStock.clear();

        cbCategoria.setValue(null);
        cbEstado.setValue(null);
    }

    @FXML
    private void cerrarSesion() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource("login.fxml"));

            Scene scene =
                    new Scene(loader.load());

            scene.getStylesheets().add(
                    getClass()
                            .getResource("styles.css")
                            .toExternalForm());

            Stage login = new Stage();

            login.setTitle("Login");
            login.setScene(scene);
            login.show();

            Stage actual =
                    (Stage) tablaProductos
                            .getScene()
                            .getWindow();

            actual.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}