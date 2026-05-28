package it.unicam.cs.mpgc.rpg119064.model;

import it.unicam.cs.mpgc.rpg119064.model.interfaces.Ruolo;

public class Giocatore {
    private String nome;
    private boolean vivo;
    private Posizione posizione;
    private Ruolo ruolo;
    private int livello;
    private int xp;

    public Giocatore(String nome) {
        this.nome = nome;
        ruolo = null;
        vivo = true;
        posizione = new Posizione(0, 0);
        livello = 1;
        xp = 0;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public String getNome() {
        return nome;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public int getLivello() {
        return livello;
    }

    public int getXp() {
        return xp;
    }

    public void aggiungiXp(int quantita) {
        this.xp += quantita;
        if (this.xp >= 100) {
            prossimoLivello();
            this.xp -= 100;
        }
    }

    public void prossimoLivello() {
        this.livello++;
    }

    public void regressioneLivello() {
        this.livello = 1;
        this.xp = 0;
    }

    @Override
    public String toString() {
        return nome + " [Livello " + livello + ", " + (vivo ? "vivo" : "morto") + "]";
    }
}