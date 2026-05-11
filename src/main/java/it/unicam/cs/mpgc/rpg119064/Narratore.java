package it.unicam.cs.mpgc.rpg119064;

public class Narratore {
    private int numeroGiocatori;
    private Giocatore [] giocatori;

    public void aggiungiNumeroGiocatori(int numeroGiocatori){
        this.numeroGiocatori = numeroGiocatori;
    }

    public void aggiungiGiocatore(String nome){

        for(int i=0; i<numeroGiocatori; i++){

            if (giocatori[i] == null){

                giocatori[i] = new Giocatore(nome);

            }
        }
    }

    public void assegnaRuoli(){

        giocatori[0].setRuolo(new Lupo());
        giocatori[1].setRuolo(new Contadino());
        giocatori[2].setRuolo(new Veggente());
        giocatori[3].setRuolo(new GuardiadelCorpo());
        giocatori[4].setRuolo(new Crocerossina());

    }

    //trovare soluzione per renderli automatico in base al numero di giocatore scelto senno ale si arrabbia
    // non lamentarsi!!!

}
