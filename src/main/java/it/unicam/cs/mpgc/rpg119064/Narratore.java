package it.unicam.cs.mpgc.rpg119064;

public class Narratore implements AzioneGiorno, AzioneNotte{

    public Narratore() {}

    public void nuovaPartita(){
      Partita partita1 = new Partita(10,3,3);
      partita1.setStato(StatoPartita.GIORNO);
    }



    @Override
    public void eseguiPotere() {

    }



}
