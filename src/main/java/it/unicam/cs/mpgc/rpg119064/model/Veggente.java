package it.unicam.cs.mpgc.rpg119064.model;


import it.unicam.cs.mpgc.rpg119064.model.interfaces.AzioneNotte;
import it.unicam.cs.mpgc.rpg119064.model.interfaces.Ruolo;

public class Veggente implements Ruolo, AzioneNotte {

    private Giocatore bersaglio;
    private Giocatore secondoBersaglio; // solo livello 2
    private String ultimaRivelazione = "";

    public void setBersaglio(Giocatore bersaglio) { this.bersaglio = bersaglio; }
    public void setSecondoBersaglio(Giocatore secondo) { this.secondoBersaglio = secondo; }

    @Override
    public void eseguiPotere() {
        if (bersaglio == null) { ultimaRivelazione = "Nessun bersaglio."; return; }
        boolean eLupo = bersaglio.getRuolo() instanceof Lupo;
        if (eLupo) bersaglio.setScoperto(true);
        ultimaRivelazione = bersaglio.getNome() + " è " + (eLupo ? "🐺 un Lupo" : "👤 un Umano");
    }

    public void eseguiChiaroveggenza() {
        eseguiPotere();
        if (secondoBersaglio == null){
            return;
        }
        boolean eLupo2 = secondoBersaglio.getRuolo() instanceof Lupo;
        if (eLupo2){
            secondoBersaglio.setScoperto(true);
        }
        ultimaRivelazione = ultimaRivelazione + "\n" +
                secondoBersaglio.getNome() + " è " + (eLupo2 ? "🐺 un Lupo" : "👤 un Umano");
    }

    public String getUltimaRivelazione() { return ultimaRivelazione; }
    public Giocatore getBersaglio() { return bersaglio; }
    public Giocatore getSecondoBersaglio() { return secondoBersaglio; }
}