package it.unicam.cs.mpgc.rpg119064.model;

import it.unicam.cs.mpgc.rpg119064.model.interfaces.Fase;

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
        Giocatore piuVotato = null;
        int massimo = 0;
        boolean pareggio = false;

        for (Giocatore g : giocatori) {
            if (!g.isVivo()) continue;
            int voti = contaVoti(g);
            if (voti > massimo) {
                massimo = voti;
                piuVotato = g;
                pareggio = false;
            } else if (voti == massimo && massimo > 0) {
                pareggio = true;
            }
        }

        if (pareggio) {
            log = log + "Pareggio! Il Leader decide...\n";
            for (Giocatore g : giocatori) {
                if (!g.isVivo()) continue;
                if (g.getRuolo() instanceof Contadino) {
                    Contadino c = (Contadino) g.getRuolo();
                    if (c.getPesoVoto(g.getLivello()) == 2 && g.getVoto() != null) {
                        piuVotato = g.getVoto();
                        log = log + g.getNome() + " (Leader) ha deciso: " + piuVotato.getNome() + " al rogo!\n";
                    }
                }
            }
        }

        if (piuVotato != null) {
            piuVotato.setVivo(false);
            log = log + piuVotato.getNome() + " e' stato mandato al rogo!\n";
        }

        for (Giocatore g : giocatori) {
            g.resetVoto();
        }

        partita.setStato(StatoPartita.NOTTE);
    }

    private int contaVoti(Giocatore bersaglio) {
        int count = 0;
        for (Giocatore g : partita.getGiocatori()) {
            if (g.isVivo() && g.getVoto() != null && g.getVoto().equals(bersaglio)) {
                count++;
            }
        }
        return count;
    }

    public String getLog() {
        return log;
    }
}