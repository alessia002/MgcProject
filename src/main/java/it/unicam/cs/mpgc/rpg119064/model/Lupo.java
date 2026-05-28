package it.unicam.cs.mpgc.rpg119064.model;

import it.unicam.cs.mpgc.rpg119064.model.interfaces.AzioneNotte;
import it.unicam.cs.mpgc.rpg119064.model.interfaces.Ruolo;


public class Lupo implements Ruolo, AzioneNotte {

    private Giocatore bersaglio;
    private Giocatore secondoBersaglio;  //livello 2
    private int turniDallUltimoAttaccoSpeciale;
    private boolean doppioAttaccoUsato;

    public Lupo() {
        this.turniDallUltimoAttaccoSpeciale = 0;
        this.doppioAttaccoUsato = false;
    }


    public void setBersaglio(Giocatore bersaglio) {
        this.bersaglio = bersaglio;
    }


    public void setSecondoBersaglio(Giocatore secondoBersaglio) {
        this.secondoBersaglio = secondoBersaglio;
    }


    public void avanzaTurno() {
        turniDallUltimoAttaccoSpeciale++;
    }


    public boolean isFameNeraDisponibile(int livelloGiocatore) {
        return livelloGiocatore >= 2 && turniDallUltimoAttaccoSpeciale >= 3;
    }


    @Override
    public void eseguiPotere() {

        if (bersaglio != null && bersaglio.isVivo()) {
            bersaglio.setVivo(false);
        }
    }


    public void eseguiFameNera() {
        eseguiPotere();
        if (secondoBersaglio != null && secondoBersaglio.isVivo()) {
            secondoBersaglio.setVivo(false);
        }
        turniDallUltimoAttaccoSpeciale = 0;
    }

    public Giocatore getBersaglio() {
        return bersaglio;
    }

    public Giocatore getSecondoBersaglio() {
        return secondoBersaglio;
    }
}