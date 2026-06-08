package it.unicam.cs.mpgc.rpg119064.model;

import it.unicam.cs.mpgc.rpg119064.model.interfaces.Fase;

public class FaseNotte implements Fase {

    private Partita partita;
    private String log;

    public FaseNotte(Partita partita) {
        this.partita = partita;
        this.log = "";
    }

    @Override
    public void elabora() {

        Giocatore giocatoreCroce   = trovaGiocatoreConRuolo(Crocerossina.class);
        Giocatore giocatoreGuardia = trovaGiocatoreConRuolo(GuardiadelCorpo.class);
        Giocatore giocatoreLupo    = trovaGiocatoreConRuolo(Lupo.class);
        Giocatore giocatoreMedium  = trovaGiocatoreConRuolo(Medium.class);

        Medium medium = null;
        if (giocatoreMedium != null && giocatoreMedium.isVivo()) {
            medium = (Medium) giocatoreMedium.getRuolo();
            medium.resetMorti();
        }

        Crocerossina croce = null;
        if (giocatoreCroce != null && giocatoreCroce.isVivo()) {
            croce = (Crocerossina) giocatoreCroce.getRuolo();
        }

        GuardiadelCorpo guardia = null;
        if (giocatoreGuardia != null && giocatoreGuardia.isVivo()) {
            guardia = (GuardiadelCorpo) giocatoreGuardia.getRuolo();
        }

        Lupo lupo = null;
        if (giocatoreLupo != null && giocatoreLupo.isVivo()) {
            lupo = (Lupo) giocatoreLupo.getRuolo();
        }

        Giocatore bersaglioLupi = null;
        if (lupo != null) {
            bersaglioLupi = lupo.getBersaglio();
        }

        if (croce != null && croce.getDestinazione() != null) {
            boolean sopravvive = croce.controllaVisitaLupo(giocatoreCroce.getLivello());
            if (!sopravvive) {
                giocatoreCroce.setVivo(false);
                log = log + giocatoreCroce.getNome() + " e' stata scoperta a casa di un Lupo e uccisa.\n";
                if (medium != null) {
                    medium.aggiungiMorto(giocatoreCroce);
                }
                croce = null;
            }
        }

        if (lupo != null && bersaglioLupi != null && bersaglioLupi.isVivo()) {

            if (croce != null && croce.eraFuoriCasa(giocatoreCroce, bersaglioLupi)) {
                log = log + "I Lupi hanno attaccato la casa di " + giocatoreCroce.getNome() + " ma era fuori. Nessuna vittima!\n";

            } else if (croce != null && croce.eNellaCasaAttaccata(bersaglioLupi)) {
                bersaglioLupi.setVivo(false);
                giocatoreCroce.setVivo(false);
                log = log + bersaglioLupi.getNome() + " e " + giocatoreCroce.getNome() + " sono stati uccisi insieme dai Lupi!\n";
                if (medium != null) {
                    medium.aggiungiMorto(bersaglioLupi);
                    medium.aggiungiMorto(giocatoreCroce);
                }

            } else if (guardia != null && guardia.proteggeDa(bersaglioLupi)) {
                log = log + "I Lupi hanno attaccato " + bersaglioLupi.getNome() + " ma la Guardia del Corpo li ha fermati!\n";
                guardia.notificaSalvataggio(giocatoreGuardia.getLivello(), giocatoreGuardia);

            } else {
                lupo.eseguiPotere();
                log = log + bersaglioLupi.getNome() + " e' stato sbranato dai Lupi.\n";
                if (medium != null) {
                    medium.aggiungiMorto(bersaglioLupi);
                }
            }
        }

        if (lupo != null) {
            lupo.avanzaTurno();
        }

        partita.setStato(StatoPartita.GIORNO);
        log = log + "L'alba e' arrivata.\n";
    }

    private Giocatore trovaGiocatoreConRuolo(Class<?> classe) {
        for (Giocatore g : partita.getGiocatori()) {
            if (g.isVivo() && g.getRuolo() != null && classe.isInstance(g.getRuolo())) {
                return g;
            }
        }
        return null;
    }

    public String getLog() {
        return log;
    }
}