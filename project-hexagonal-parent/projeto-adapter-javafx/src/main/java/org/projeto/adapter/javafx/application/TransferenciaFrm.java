package org.projeto.adapter.javafx.application;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import javax.inject.Inject;
import javax.inject.Named;

import org.projeto.caseuser.port.TransferenciaPort;
import org.projeto.domain.model.Conta;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

/**
 *  Responsável por desenhar a tela de transferencia com a tecnologia javafx.
 * @author anderson
 *
 */
@Named
public class TransferenciaFrm {

    private TextField tfDebito;
    private TextField tfNomeDebito;
    private TextField tfCredito;
    private TextField tfNomeCredito;
    private TextField tfValor;
    private TransferenciaPort port;

    @Inject
    public TransferenciaFrm(TransferenciaPort port) {
        this.port = port;
    }

    private void limpar() {
        tfDebito.setText("");
        tfNomeDebito.setText("");
        tfCredito.setText("");
        tfNomeCredito.setText("");
        tfValor.setText("");
    }

    private Integer get(String valor) {
        try {
            return Integer.valueOf(valor);
        } catch (Exception e) {
            return null;
        }
    }

    private void mensagem(String texto) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Transferência Bancária");
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    private void get(TextField tfEntrada, TextField tfSaida) {
        try {
            Conta conta = port.getConta(get(tfEntrada.getText()));
            if (isNull(conta)) {
                tfSaida.setText("");
            } else {
                tfSaida.setText(conta.getCorrentista() + " - Saldo R$ " + conta.getSaldo());
            }
        } catch (Exception e) {
            mensagem(e.getMessage());
        }
    }

    private BigDecimal get() {
        try {
            return new BigDecimal(tfValor.getText());
        } catch (Exception e) {
            return null;
        }
    }

    private FlowPane montarTela() {
    	FlowPane pn = new FlowPane();
        pn.setHgap(10);
        pn.setVgap(10);

        pn.getChildren().add(new Label(" Conta Débito:"));
        tfDebito = new TextField();
        tfDebito.setPrefWidth(50);
        tfDebito.focusedProperty().addListener((o, v, n) -> {
            if (!n) get(tfDebito, tfNomeDebito);
        });

        pn.getChildren().add(tfDebito);

        tfNomeDebito = new TextField();
        tfNomeDebito.setPrefWidth(300);
        tfNomeDebito.setEditable(false);
        pn.getChildren().add(tfNomeDebito);

        pn.getChildren().add(new Label(" Conta Crédito:"));
        tfCredito = new TextField();
        tfCredito.setPrefWidth(50);
        tfCredito.focusedProperty().addListener((o, v, n) -> {
            if (!n) get(tfCredito, tfNomeCredito);
        });
        pn.getChildren().add(tfCredito);

        tfNomeCredito = new TextField();
        tfNomeCredito.setEditable(false);
        tfNomeCredito.setPrefWidth(300);
        pn.getChildren().add(tfNomeCredito);

        pn.getChildren().add(new Label(" Valor R$:"));
        tfValor = new TextField();
        tfValor.setPrefWidth(200);
        pn.getChildren().add(tfValor);

        Button bt = new Button();
        bt.setOnAction((ev) -> {
            try {
                port.transferir(get(tfDebito.getText()), get(tfCredito.getText()), get());
                limpar();
                mensagem("Transferência feita com sucesso!");
            } catch (Exception e) {
                mensagem(e.getMessage());
            }
        });
        bt.setText("Transferir");
        pn.getChildren().add(bt);
        return pn;
    }

    public void mostrar(Stage stage) {
        stage.setTitle("Adaptador JavaFX");
        Scene scene = new Scene(montarTela(), 500, 100);
        stage.setScene(scene);
        stage.show();
    }
}
