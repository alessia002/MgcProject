package it.unicam.cs.mpgc.rpg119064.model;

import it.unicam.cs.mpgc.rpg119064.model.interfaces.Ruolo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "giocatori")
public class Giocatore {

    @Id
    private String nome;

    private int livello;
    private int xp;

    @Transient
    private boolean vivo;

    @Transient
    private Ruolo ruolo;

    @Transient
    private boolean scoperto;

    @Transient
    private Giocatore voto;


    public Giocatore() {}

    public Giocatore(String nome) {
        this.nome = nome;
        this.ruolo = null;
        this.vivo = true;
        this.livello = 1;
        this.xp = 0;
        this.scoperto = false;
        this.voto = null;
    }

    public void vota(Giocatore bersaglio) {
        this.voto = bersaglio;
    }

    public Giocatore getVoto() { return voto; }
    public void resetVoto() { this.voto = null; }

    public void setRuolo(Ruolo ruolo) { this.ruolo = ruolo; }
    public Ruolo getRuolo() { return ruolo; }
    public String getNome() { return nome; }
    public boolean isVivo() { return vivo; }
    public void setVivo(boolean vivo) { this.vivo = vivo; }
    public int getLivello() { return livello; }
    public int getXp() { return xp; }
    public boolean isScoperto() { return scoperto; }
    public void setScoperto(boolean scoperto) { this.scoperto = scoperto; }

    public void aggiungiXp(int quantita) {
        this.xp += quantita;
        if (this.xp >= 100) {
            prossimoLivello();
            this.xp -= 100;
        }
    }

    public void prossimoLivello() { this.livello++; }

    public void regressioneLivello() {
        this.livello = 1;
        this.xp = 0;
    }

    @Override
    public String toString() {
        return nome + " [Livello " + livello + ", " + (vivo ? "vivo" : "morto") + "]";
    }
}