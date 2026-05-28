package it.unicam.cs.mpgc.rpg119064.model;

import it.unicam.cs.mpgc.rpg119064.model.interfaces.AzioneGiorno;
import it.unicam.cs.mpgc.rpg119064.model.interfaces.Ruolo;


public class Contadino implements Ruolo, AzioneGiorno {

    private Giocatore voto;

    public Contadino() {
    }


    @Override
    public void eseguiPotere(Giocatore voto) {
        this.voto = voto;
    }


    public Giocatore getVoto() {
        return voto;
    }


    public int getPesoVoto(int livelloContadino) {
        return (livelloContadino >= 2) ? 2 : 1;
    }
}