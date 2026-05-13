package it.unicam.cs.mpgc.rpg119064;

public class Giocatore {
    private String nome;
    private boolean vivo;
    private Posizione posizione;
    private Ruolo ruolo;
    private int livello;

    public Giocatore(String nome) {
        this.nome = nome;
        ruolo = null;
        vivo=true;
        posizione = new Posizione(0,0);
        livello=1;

    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;

    }


}
