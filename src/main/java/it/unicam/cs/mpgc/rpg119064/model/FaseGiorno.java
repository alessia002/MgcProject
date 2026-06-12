package it.unicam.cs.mpgc.rpg119064.model;

import it.unicam.cs.mpgc.rpg119064.model.interfaces.Fase;
import java.util.HashMap;

public class FaseGiorno implements Fase {

    private Partita partita;
    private String log;

    public FaseGiorno(Partita partita) {
        this.partita = partita;
        this.log = "";
    }

    @Override
    public void elabora() {
        Giocatore[] giocatori = partita.getGiocatori();

        // HashMap per contare i voti: chiave = giocatore votato, valore = numero voti
        HashMap<Giocatore, Integer> conteggioVoti = new HashMap<>();

        for (int i = 0; i < giocatori.length; i++) {
            if (!giocatori[i].isVivo()) {
                continue;
            }
            if (giocatori[i].getVoto() != null) {
                Giocatore bersaglio = giocatori[i].getVoto();
                if (conteggioVoti.containsKey(bersaglio)) {
                    conteggioVoti.put(bersaglio, conteggioVoti.get(bersaglio) + 1);
                } else {
                    conteggioVoti.put(bersaglio, 1);
                }
            }
        }

        // Trova il piu' votato
        Giocatore piuVotato = null;
        int massimo = 0;
        boolean pareggio = false;

        for (Giocatore g : conteggioVoti.keySet()) {
            int voti = conteggioVoti.get(g);
            if (voti > massimo) {
                massimo = voti;
                piuVotato = g;
                pareggio = false;
            } else if (voti == massimo) {
                pareggio = true;
            }
        }

        // In caso di pareggio il Leader decide
        if (pareggio) {
            log = log + "Pareggio! Il Leader decide...\n";
            for (int i = 0; i < giocatori.length; i++) {
                if (!giocatori[i].isVivo()) {
                    continue;
                }
                if (giocatori[i].getRuolo() instanceof Contadino) {
                    Contadino c = (Contadino) giocatori[i].getRuolo();
                    int peso = c.getPesoVoto(giocatori[i].getLivello());
                    if (peso == 2) {
                        if (giocatori[i].getVoto() != null) {
                            piuVotato = giocatori[i].getVoto();
                            log = log + giocatori[i].getNome() + " (Leader) ha deciso: " + piuVotato.getNome() + " al rogo!\n";
                        }
                    }
                }
            }
        }

        if (piuVotato != null) {
            piuVotato.setVivo(false);
            log = log + piuVotato.getNome() + " e' stato mandato al rogo!\n";
        }

        for (int i = 0; i < giocatori.length; i++) {
            giocatori[i].resetVoto();
        }

        partita.setStato(StatoPartita.NOTTE);
    }


    public String getLog() {
        return log;
    }
}