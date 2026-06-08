package it.unicam.cs.mpgc.rpg119064.view;
import it.unicam.cs.mpgc.rpg119064.controller.PartitaController;
import it.unicam.cs.mpgc.rpg119064.model.Giocatore;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainViewController {


    @FXML private VBox setupPane;
    @FXML private VBox giocoPane;
    @FXML private VBox azioniPane;
    @FXML private TextArea nomiTextArea;
    @FXML private TextArea logArea;
    @FXML private GridPane grigliaGiocatori;
    @FXML private Button notteButton;
    @FXML private Button giornoButton;

    private PartitaController controller;

    @FXML
    public void initialize() {
        controller = new PartitaController();
    }

    @FXML
    public void avviaPartita() {
        String testo = nomiTextArea.getText().trim();

        if (testo.isEmpty()) {
            return;
        }

        String[] nomi = testo.split("\n");

        if (nomi.length < 10) {
            logArea.setText("Servono almeno 10 giocatori!");
            return;
        }

        controller.avviaPartita(nomi);

        setupPane.setVisible(false);
        giocoPane.setVisible(true);
        azioniPane.setVisible(true);

        // Aggiorna griglia giocatori
        grigliaGiocatori.getChildren().clear();
        Giocatore[] giocatori = controller.getGiocatori();
        int col = 0;
        int row = 0;
        for (int i = 0; i < giocatori.length; i++) {
            Giocatore g = giocatori[i];
            Label label = new Label(controller.getStatoGiocatore(g));
            if (!g.isVivo()) {
                label.setStyle("-fx-text-fill: grey; -fx-strikethrough: true;");
            }
            grigliaGiocatori.add(label, col, row);
            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
        }

        logArea.setText(controller.getLog());
        notteButton.setDisable(false);
        giornoButton.setDisable(true);
    }

    @FXML
    public void elaboraNotte() {
        controller.elaboraNotte();

        // Aggiorna griglia giocatori
        grigliaGiocatori.getChildren().clear();
        Giocatore[] giocatori = controller.getGiocatori();
        int col = 0;
        int row = 0;
        for (int i = 0; i < giocatori.length; i++) {
            Giocatore g = giocatori[i];
            Label label = new Label(controller.getStatoGiocatore(g));
            if (!g.isVivo()) {
                label.setStyle("-fx-text-fill: grey; -fx-strikethrough: true;");
            }
            grigliaGiocatori.add(label, col, row);
            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
        }

        logArea.setText(controller.getLog());

        if (controller.isPartitaTerminata()) {
            notteButton.setDisable(true);
            giornoButton.setDisable(true);
        } else {
            notteButton.setDisable(true);
            giornoButton.setDisable(false);
        }
    }

    @FXML
    public void risolviVoto() {
        controller.risolviVoto();

        // Aggiorna griglia giocatori
        grigliaGiocatori.getChildren().clear();
        Giocatore[] giocatori = controller.getGiocatori();
        int col = 0;
        int row = 0;
        for (int i = 0; i < giocatori.length; i++) {
            Giocatore g = giocatori[i];
            Label label = new Label(controller.getStatoGiocatore(g));
            if (!g.isVivo()) {
                label.setStyle("-fx-text-fill: grey; -fx-strikethrough: true;");
            }
            grigliaGiocatori.add(label, col, row);
            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
        }

        logArea.setText(controller.getLog());

        if (controller.isPartitaTerminata()) {
            notteButton.setDisable(true);
            giornoButton.setDisable(true);
        } else {
            notteButton.setDisable(false);
            giornoButton.setDisable(true);
        }
    }
}
