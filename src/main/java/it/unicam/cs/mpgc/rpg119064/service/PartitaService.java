package it.unicam.cs.mpgc.rpg119064.service;

import it.unicam.cs.mpgc.rpg119064.model.Giocatore;
import it.unicam.cs.mpgc.rpg119064.model.Narratore;
import it.unicam.cs.mpgc.rpg119064.model.StatoPartita;

public class PartitaService {
    private Narratore narratore;

    public PartitaService() {
        this.narratore = new Narratore();
    }

    public void avviaPartita(String[] nomi) {
        narratore.nuovaPartita(nomi);
    }

    public void elaboraNotte() {
        narratore.elaboraNotte();
    }

    public void risolviVoto() {
        narratore.risolviVoto();
    }

    public String getLog() {
        return narratore.getLog();
    }

    public Giocatore[] getGiocatori() {
        return narratore.getPartita().getGiocatori();
    }

    public StatoPartita getStato() {
        return narratore.getPartita().getStato();
    }

    public String getStatoGiocatore(Giocatore g) {
        return g.getNome() + " - Livello " + g.getLivello() + " - " + g.getXp() + " XP";
    }
}
