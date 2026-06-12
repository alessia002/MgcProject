package it.unicam.cs.mpgc.rpg119064.view;

import it.unicam.cs.mpgc.rpg119064.controller.PartitaController;
import it.unicam.cs.mpgc.rpg119064.model.Giocatore;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainViewController {

    @FXML private VBox setupPane;
    @FXML private VBox giocoPane;
    @FXML private HBox azioniPane;
    @FXML private VBox logPane;
    @FXML private VBox passaDispositivoPane;
    @FXML private VBox mostraRuoloPane;
    @FXML private TextArea nomiTextArea;
    @FXML private TextArea logArea;
    @FXML private GridPane grigliaGiocatori;
    @FXML private Button notteButton;
    @FXML private Button giornoButton;
    @FXML private Label passaLabel;
    @FXML private Label ruoloLabel;
    @FXML private Label turnoLabel;

    private PartitaController controller;
    private int turnoAssegnaRuoli = 0;
    private int turnoCorrente = 0;
    private String[] ordineRuoli = {"Lupo", "Veggente", "Guardia", "Crocerossina"};
    private boolean faseVoto = false;
    private boolean faseAssegnaRuoli = false;

    @FXML
    public void initialize() {
        controller = new PartitaController();
    }

    @FXML
    public void avviaPartita() {
        String testo = nomiTextArea.getText().trim();
        if (testo.isEmpty()) return;
        String[] nomi = testo.split("\n");
        if (nomi.length < 10) {
            logArea.setText("Servono almeno 10 giocatori!");
            return;
        }
        controller.avviaPartita(nomi);
        setupPane.setVisible(false);
        faseAssegnaRuoli = true;
        turnoAssegnaRuoli = 0;
        mostraPassaPerRuolo();
    }

    private void mostraPassaPerRuolo() {
        Giocatore[] giocatori = controller.getGiocatori();
        if (turnoAssegnaRuoli >= giocatori.length) {
            faseAssegnaRuoli = false;
            giocoPane.setVisible(true);
            azioniPane.setVisible(true);
            logPane.setVisible(true);
            aggiornaGriglia(false);
            logArea.setText(controller.getLog());
            notteButton.setDisable(false);
            giornoButton.setDisable(true);
            return;
        }
        Giocatore g = giocatori[turnoAssegnaRuoli];
        passaDispositivoPane.setVisible(true);
        mostraRuoloPane.setVisible(false);
        giocoPane.setVisible(false);
        azioniPane.setVisible(false);
        logPane.setVisible(false);
        passaLabel.setText("Passa il dispositivo a: " + g.getNome());
        turnoLabel.setText("Premi quando sei pronto");
    }

    @FXML
    public void confermaTurno() {
        if (faseAssegnaRuoli) {
            Giocatore g = controller.getGiocatori()[turnoAssegnaRuoli];
            passaDispositivoPane.setVisible(false);
            mostraRuoloPane.setVisible(true);
            ruoloLabel.setText("Il tuo ruolo e': " + g.getRuolo().getClass().getSimpleName());
        } else {
            passaDispositivoPane.setVisible(false);
            giocoPane.setVisible(true);
            azioniPane.setVisible(true);
            logPane.setVisible(true);
            turnoLabel.setText(ordineRuoli[turnoCorrente] + ": scegli il bersaglio");
            aggiornaGriglia(true);
        }
    }

    @FXML
    public void hoVisto() {
        mostraRuoloPane.setVisible(false);
        turnoAssegnaRuoli++;
        mostraPassaPerRuolo();
    }

    @FXML
    public void elaboraNotte() {
        faseVoto = false;
        turnoCorrente = 0;
        mostraPassaDispositivoNotte();
    }

    private void mostraPassaDispositivoNotte() {
        if (turnoCorrente >= ordineRuoli.length) {
            eseguiElaboraNotte();
            return;
        }
        String ruoloCorrente = ordineRuoli[turnoCorrente];
        Giocatore giocatore = controller.trovaGiocatorePerRuolo(ruoloCorrente);
        if (giocatore == null || !giocatore.isVivo()) {
            turnoCorrente++;
            mostraPassaDispositivoNotte();
            return;
        }
        giocoPane.setVisible(false);
        azioniPane.setVisible(false);
        logPane.setVisible(false);
        passaDispositivoPane.setVisible(true);
        passaLabel.setText("Passa il dispositivo a: " + giocatore.getNome());
        turnoLabel.setText("Premi quando sei pronto");
    }

    private void selezionaBersaglio(Giocatore bersaglio) {
        controller.impostaBersaglio(ordineRuoli[turnoCorrente], bersaglio);
        turnoCorrente++;
        mostraPassaDispositivoNotte();
    }

    private void eseguiElaboraNotte() {
        controller.elaboraNotte();
        aggiornaGriglia(false);
        logArea.setText(controller.getLog());
        turnoLabel.setText("");
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
        faseVoto = true;
        aggiornaGriglia(true);
        turnoLabel.setText("Vota il sospettato!");
        notteButton.setDisable(true);
        giornoButton.setDisable(true);
    }

    private void eseguiRisolviVoto(Giocatore bersaglio) {
        controller.vota(bersaglio);
        if (controller.tuttiHannoVotato()) {
            controller.risolviVoto();
            aggiornaGriglia(false);
            logArea.setText(controller.getLog());
            turnoLabel.setText("");
            if (controller.isPartitaTerminata()) {
                notteButton.setDisable(true);
                giornoButton.setDisable(true);
            } else {
                notteButton.setDisable(false);
                giornoButton.setDisable(true);
            }
        } else {
            turnoLabel.setText("Prossimo giocatore vota!");
        }
    }

    private void aggiornaGriglia(boolean cliccabile) {
        grigliaGiocatori.getChildren().clear();
        Giocatore[] giocatori = controller.getGiocatori();
        int col = 0;
        int row = 0;
        for (int i = 0; i < giocatori.length; i++) {
            Giocatore g = giocatori[i];
            Button btn = new Button(controller.getStatoGiocatore(g));
            if (!g.isVivo()) {
                btn.setStyle("-fx-text-fill: grey;");
                btn.setDisable(true);
            } else if (cliccabile) {
                final Giocatore bersaglio = g;
                if (faseVoto) {
                    btn.setOnAction(e -> eseguiRisolviVoto(bersaglio));
                } else {
                    btn.setOnAction(e -> selezionaBersaglio(bersaglio));
                }
            }
            grigliaGiocatori.add(btn, col, row);
            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
        }
    }
}