package it.unicam.cs.mpgc.rpg119064.model;

import it.unicam.cs.mpgc.rpg119064.model.interfaces.AzioneNotte;
import it.unicam.cs.mpgc.rpg119064.model.interfaces.Ruolo;


public class GuardiadelCorpo implements Ruolo, AzioneNotte {

    private Giocatore bersaglioProtetto;
    private Giocatore secondoBersaglioProtetto; //livello2
    private boolean haProtetto; // true se ha salvato qualcuno

    public GuardiadelCorpo() {
        this.haProtetto = false;
    }


    public void setBersaglioProtetto(Giocatore bersaglio) {
        this.bersaglioProtetto = bersaglio;
    }


    public void setSecondoBersaglioProtetto(Giocatore secondo) {
        this.secondoBersaglioProtetto = secondo;
    }


    public boolean proteggeDa(Giocatore bersaglioAttacco) {
        if (bersaglioProtetto != null && bersaglioProtetto.equals(bersaglioAttacco)) {
            return true;
        }
        if (secondoBersaglioProtetto != null && secondoBersaglioProtetto.equals(bersaglioAttacco)) {
            return true;
        }
        return false;
    }


    public void notificaSalvataggio(int livelloGuardia, Giocatore guardia) {
        haProtetto = true;
        if (livelloGuardia >= 2) {
            guardia.aggiungiXp(20);
        } else {
            guardia.aggiungiXp(10);
        }
    }


    @Override
    public void eseguiPotere() {
        haProtetto = false;
    }

    public boolean haProtetto() {
        return haProtetto;
    }

    public Giocatore getBersaglioProtetto() {
        return bersaglioProtetto;
    }

    public Giocatore getSecondoBersaglioProtetto() {
        return secondoBersaglioProtetto;
    }
}