package it.unicam.cs.mpgc.rpg119064.model;

import it.unicam.cs.mpgc.rpg119064.model.interfaces.AzioneNotte;
import it.unicam.cs.mpgc.rpg119064.model.interfaces.Ruolo;

public class Contadino implements Ruolo, AzioneNotte {

    @Override
    public void eseguiPotere() {
    }


    public int getPesoVoto(int livelloContadino) {
        if (livelloContadino >= 2) {
            return 2;
        } else {
            return 1;
        }
    }
}