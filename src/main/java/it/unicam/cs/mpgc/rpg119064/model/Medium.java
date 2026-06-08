package it.unicam.cs.mpgc.rpg119064.model;

import it.unicam.cs.mpgc.rpg119064.model.interfaces.AzioneNotte;
import it.unicam.cs.mpgc.rpg119064.model.interfaces.Ruolo;

import java.util.ArrayList;
import java.util.List;


public class Medium implements Ruolo, AzioneNotte {

    private List<Giocatore> mortiNottePrecedente = new ArrayList<>();
    private String ultimaVisione = "";

    public void aggiungiMorto(Giocatore morto) {
        mortiNottePrecedente.add(morto);
    }

    public void resetMorti() {
        mortiNottePrecedente.clear();
    }

    @Override
    public void eseguiPotere() {
        if (mortiNottePrecedente.isEmpty()) {
            ultimaVisione = "Nessuna morte la scorsa notte.";
            return;
        }
        String risultato = "";
        for (Giocatore morto : mortiNottePrecedente) {
            boolean eLupo = morto.getRuolo() instanceof Lupo;
            risultato = risultato + morto.getNome() + " era " + (eLupo ? "🐺 un Lupo" : "👤 un Umano") + "\n";
        }
        ultimaVisione = risultato;
    }

    public void eseguiContatto() {
        if (mortiNottePrecedente.isEmpty()) {
            ultimaVisione = "Nessuna morte la scorsa notte.";
            return;
        }
        String risultato = "";
        for (Giocatore morto : mortiNottePrecedente) {
            boolean eLupo = morto.getRuolo() instanceof Lupo;
            if (eLupo && !morto.isScoperto()) {
                risultato = risultato + "⚠️ " + morto.getNome() + " era un Lupo nascosto!\n";
            } else {
                risultato = risultato + morto.getNome() + " era " + (eLupo ? "🐺 un Lupo" : "👤 un Umano") + "\n";
            }
        }
        ultimaVisione = risultato;
    }

    public String getUltimaVisione() { return ultimaVisione; }
    public List<Giocatore> getMortiNottePrecedente() { return mortiNottePrecedente; }
}