package it.unicam.cs.mpgc.rpg119064.model;


public class Partita {
    private StatoPartita stato;
    private int numeroGiocatori;
    private Giocatore[] giocatori;
    private int prossimoSlot = 0;

    public Partita(int numeroGiocatori) {
        if (numeroGiocatori < 10) {
            throw new IllegalArgumentException("Servono almeno 10 giocatori.");
        }

        this.numeroGiocatori = numeroGiocatori;
        this.giocatori = new Giocatore[numeroGiocatori];
    }

    public void aggiungiGiocatore(String nome) {
        if (prossimoSlot >= giocatori.length) {
            throw new IllegalStateException("Partita già piena.");
        }
        giocatori[prossimoSlot] = new Giocatore(nome);
        prossimoSlot++;
    }



    public void setStato(StatoPartita stato) { this.stato = stato; }
    public StatoPartita getStato() { return stato; }
    public Giocatore[] getGiocatori() { return giocatori; }
    public int getNumeroGiocatori() { return numeroGiocatori; }

    public void vittoria(Giocatore giocatore) { giocatore.aggiungiXp(50); }
    public void sconfitta(Giocatore giocatore) { giocatore.regressioneLivello(); }
}