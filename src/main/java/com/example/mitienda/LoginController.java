package com.example.mitienda;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    public TextField txtUsuario;
    public PasswordField txtPassword;
    public ComboBox<String> cbRol;

    public void initialize() {

        cbRol.getItems().addAll(
                "Administrador",
                "Empleado"
        );
    }

    public void ingresar(ActionEvent event) {

        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();
        String rol = cbRol.getValue();

        if (usuario.equals("admin")
                && password.equals("admin")
                && rol != null
                && rol.equals("Administrador")) {

            try {

                FXMLLoader loader =
                        new FXMLLoader(
                                getClass().getResource("dashboard.fxml"));

                Scene scene =
                        new Scene(loader.load());

                scene.getStylesheets().add(
                        getClass().getResource("styles.css")
                                .toExternalForm());

                Stage stage = new Stage();

                stage.setTitle("MiTienda");
                stage.setScene(scene);
                stage.show();

                Stage actual =
                        (Stage) txtUsuario
                                .getScene()
                                .getWindow();

                actual.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText(null);

            alert.setContentText(
                    "Usuario o contraseña incorrectos");

            alert.showAndWait();
        }
    }
}