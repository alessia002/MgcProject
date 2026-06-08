package it.unicam.cs.mpgc.rpg119064.model;

import it.unicam.cs.mpgc.rpg119064.model.interfaces.AzioneNotte;
import it.unicam.cs.mpgc.rpg119064.model.interfaces.Ruolo;

import java.util.Random;


public class Crocerossina implements Ruolo, AzioneNotte {

    private Giocatore destinazione;
    private static final double PROBABILITA_FUGA = 0.30;
    private final Random random = new Random();

    public void setDestinazione(Giocatore destinazione) {
        this.destinazione = destinazione;
    }

    public Giocatore getDestinazione() { return destinazione; }


    public boolean controllaVisitaLupo(int livello) {
        if (destinazione == null) return true;
        if (!(destinazione.getRuolo() instanceof Lupo)) return true;

        if (livello >= 2) {
            return random.nextDouble() < PROBABILITA_FUGA;
        }
        return false;
    }


    public boolean eNellaCasaAttaccata(Giocatore bersaglioLupi) {
        return destinazione != null && destinazione.equals(bersaglioLupi);
    }


    public boolean eraFuoriCasa(Giocatore crocerossina, Giocatore bersaglioLupi) {
        return bersaglioLupi != null && bersaglioLupi.equals(crocerossina);
    }

    @Override
    public void eseguiPotere() {
    }
}