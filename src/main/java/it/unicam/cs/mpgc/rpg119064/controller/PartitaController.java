package it.unicam.cs.mpgc.rpg119064.controller;

import it.unicam.cs.mpgc.rpg119064.model.*;
import it.unicam.cs.mpgc.rpg119064.model.interfaces.Ruolo;
import it.unicam.cs.mpgc.rpg119064.service.PartitaService;

public class PartitaController {

    private PartitaService service;
    private int votiEspressi = 0;

    public PartitaController() {
        this.service = new PartitaService();
    }

    public void avviaPartita(String[] nomi) {
        service.avviaPartita(nomi);
        votiEspressi = 0;
    }

    public void elaboraNotte() {
        service.elaboraNotte();
    }

    public void risolviVoto() {
        service.risolviVoto();
        votiEspressi = 0;
    }

    /** Imposta il bersaglio per il ruolo specificato. */
    public void impostaBersaglio(String ruolo, Giocatore bersaglio) {
        Giocatore[] giocatori = service.getGiocatori();
        for (int i = 0; i < giocatori.length; i++) {
            Ruolo r = giocatori[i].getRuolo();
            if (ruolo.equals("Lupo") && r instanceof Lupo) {
                ((Lupo) r).setBersaglio(bersaglio);
            } else if (ruolo.equals("Veggente") && r instanceof Veggente) {
                ((Veggente) r).setBersaglio(bersaglio);
            } else if (ruolo.equals("Guardia") && r instanceof GuardiadelCorpo) {
                ((GuardiadelCorpo) r).setBersaglioProtetto(bersaglio);
            } else if (ruolo.equals("Crocerossina") && r instanceof Crocerossina) {
                ((Crocerossina) r).setDestinazione(bersaglio);
            }
        }
    }

    /** Registra il voto di un giocatore. */
    public void vota(Giocatore bersaglio) {
        Giocatore[] giocatori = service.getGiocatori();
        for (int i = 0; i < giocatori.length; i++) {
            if (giocatori[i].isVivo() && giocatori[i].getVoto() == null) {
                giocatori[i].vota(bersaglio);
                votiEspressi++;
                break;
            }
        }
    }

    /** Restituisce true se tutti i giocatori vivi hanno votato. */
    public boolean tuttiHannoVotato() {
        int viviTotali = 0;
        Giocatore[] giocatori = service.getGiocatori();
        for (int i = 0; i < giocatori.length; i++) {
            if (giocatori[i].isVivo()) {
                viviTotali++;
            }
        }
        return votiEspressi >= viviTotali;
    }

    /** Trova il giocatore con il ruolo specificato. */
    public Giocatore trovaGiocatorePerRuolo(String ruolo) {
        Giocatore[] giocatori = service.getGiocatori();
        for (int i = 0; i < giocatori.length; i++) {
            if (!giocatori[i].isVivo()) continue;
            Ruolo r = giocatori[i].getRuolo();
            if (ruolo.equals("Lupo") && r instanceof Lupo) return giocatori[i];
            if (ruolo.equals("Veggente") && r instanceof Veggente) return giocatori[i];
            if (ruolo.equals("Guardia") && r instanceof GuardiadelCorpo) return giocatori[i];
            if (ruolo.equals("Crocerossina") && r instanceof Crocerossina) return giocatori[i];
        }
        return null;
    }

    public Giocatore[] getGiocatori() { return service.getGiocatori(); }
    public String getLog() { return service.getLog(); }
    public StatoPartita getStato() { return service.getStato(); }
    public String getStatoGiocatore(Giocatore g) { return service.getStatoGiocatore(g); }
    public boolean isPartitaTerminata() { return service.getStato() == StatoPartita.TERMINATA; }
}