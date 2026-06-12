package it.unicam.cs.mpgc.rpg119064.model;

import it.unicam.cs.mpgc.rpg119064.model.interfaces.AssegnaRuoli;

import java.util.Arrays;

public class Narratore {

    private Partita partita;
    private String log;
    private AssegnaRuoli assegnaRuoli;
    private FaseNotte faseNotte;
    private FaseGiorno faseGiorno;

    public Narratore() {
        this.log = "";
        this.assegnaRuoli = new AssegnaRuoliCasuale();
    }

    public void nuovaPartita(String[] nomi) {
        partita = new Partita(nomi.length);
        for (int i = 0; i < nomi.length; i++) {
            partita.aggiungiGiocatore(nomi[i]);
        }
        assegnaRuoli.assegna(partita.getGiocatori());
        faseNotte = new FaseNotte(partita);
        faseGiorno = new FaseGiorno(partita);
        partita.setStato(StatoPartita.NOTTE);
        log = log + "La partita ha inizio. Cala la notte...\n";
    }

    public void elaboraNotte() {
        faseNotte.elabora();
        log = log + faseNotte.getLog();
        verificaVittoria();
    }

    public void risolviVoto() {
        faseGiorno.elabora();
        log = log + faseGiorno.getLog();
        verificaVittoria();
    }

    public void verificaVittoria() {
        Giocatore[] giocatori = partita.getGiocatori();

        // Stream per contare lupi e umani vivi
        long lupiVivi = Arrays.stream(giocatori)
                .filter(g -> g.isVivo())
                .filter(g -> g.getRuolo() instanceof Lupo)
                .count();

        long umaniVivi = Arrays.stream(giocatori)
                .filter(g -> g.isVivo())
                .filter(g -> !(g.getRuolo() instanceof Lupo))
                .count();

        if (lupiVivi == 0) {
            log = log + "Gli Umani hanno vinto!\n";
            distribuisciXp(false);
            partita.setStato(StatoPartita.TERMINATA);
        } else if (lupiVivi >= umaniVivi) {
            log = log + "I Lupi hanno vinto!\n";
            distribuisciXp(true);
            partita.setStato(StatoPartita.TERMINATA);
        }
    }

    private void distribuisciXp(boolean lupiVincono) {
        for (Giocatore g : partita.getGiocatori()) {
            boolean eLupo = g.getRuolo() instanceof Lupo;
            if ((lupiVincono && eLupo) || (!lupiVincono && !eLupo)) {
                g.aggiungiXp(50);
            } else {
                g.regressioneLivello();
            }
        }
    }

    public String getLog() { return log; }
    public Partita getPartita() { return partita; }
}