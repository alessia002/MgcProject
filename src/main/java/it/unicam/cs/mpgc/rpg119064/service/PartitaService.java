package it.unicam.cs.mpgc.rpg119064.service;

import it.unicam.cs.mpgc.rpg119064.model.Giocatore;
import it.unicam.cs.mpgc.rpg119064.model.Narratore;
import it.unicam.cs.mpgc.rpg119064.model.StatoPartita;
import it.unicam.cs.mpgc.rpg119064.repository.GiocatoreRepository;

public class PartitaService {
    private Narratore narratore;
    private GiocatoreRepository repository;

    public PartitaService() {
        this.narratore = new Narratore();
        this.repository = new GiocatoreRepository();
    }

    public void avviaPartita(String[] nomi) {
        narratore.nuovaPartita(nomi);

        // Carica i profili esistenti dal DB
        Giocatore[] giocatori = narratore.getPartita().getGiocatori();
        for (int i = 0; i < giocatori.length; i++) {
            Giocatore profiloDB = repository.carica(giocatori[i].getNome());
            if (profiloDB != null) {
                // Il giocatore esiste nel DB, aggiorna livello e XP
                while (giocatori[i].getLivello() < profiloDB.getLivello()) {
                    giocatori[i].prossimoLivello();
                }
                giocatori[i].aggiungiXp(profiloDB.getXp());
            }
        }
    }

    public void elaboraNotte() {
        narratore.elaboraNotte();
    }

    public void risolviVoto() {
        narratore.risolviVoto();
        // Se la partita e' finita salva i profili nel DB
        if (narratore.getPartita().getStato() == StatoPartita.TERMINATA) {
            salvaGiocatori();
        }
    }


    private void salvaGiocatori() {
        Giocatore[] giocatori = narratore.getPartita().getGiocatori();
        for (int i = 0; i < giocatori.length; i++) {
            repository.salva(giocatori[i]);
        }
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
