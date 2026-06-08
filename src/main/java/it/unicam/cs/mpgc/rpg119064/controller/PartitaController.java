package it.unicam.cs.mpgc.rpg119064.controller;
import it.unicam.cs.mpgc.rpg119064.model.Giocatore;
import it.unicam.cs.mpgc.rpg119064.model.StatoPartita;
import it.unicam.cs.mpgc.rpg119064.service.PartitaService;

public class PartitaController {
    private PartitaService service;

    public PartitaController() {
        this.service = new PartitaService();
    }

    public void avviaPartita(String[] nomi) {
        service.avviaPartita(nomi);
    }

    public void elaboraNotte() {
        service.elaboraNotte();
    }

    public void risolviVoto() {
        service.risolviVoto();
    }

    public Giocatore[] getGiocatori() {
        return service.getGiocatori();
    }

    public String getLog() {
        return service.getLog();
    }

    public StatoPartita getStato() {
        return service.getStato();
    }

    public String getStatoGiocatore(Giocatore g) {
        return service.getStatoGiocatore(g);
    }

    public boolean isPartitaTerminata() {
        return service.getStato() == StatoPartita.TERMINATA;
    }
}
