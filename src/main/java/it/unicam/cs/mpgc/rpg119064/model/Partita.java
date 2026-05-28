package it.unicam.cs.mpgc.rpg119064.model;

public class Partita {
    private StatoPartita stato; //giorno, notte e finita
    private int numeroGiocatori;
    private int numeroLupi;
    private int numeroContadini;
    private Giocatore [] giocatori;

    public Partita(int numeroGiocatori, int numeroContadini, int numeroLupi) {

        if( numeroGiocatori < 10){
            throw new IllegalArgumentException("il valore inserito non e' accettabile");
        }

        if( (numeroLupi + numeroContadini) != numeroGiocatori - 4 || (numeroLupi <3) || (numeroContadini <3)){
            throw new IllegalArgumentException("il valore inserito dei personaggi non e' corretto");
        }

        this.numeroGiocatori = numeroGiocatori;
        this.numeroLupi = numeroLupi;
        this.numeroContadini = numeroContadini;
        giocatori = new Giocatore[numeroGiocatori];

    }

    public void aggiungiGiocatore(String nome){

        for(int i=0; i<giocatori.length; i++){

            if (giocatori[i] == null){
                giocatori[i] = new Giocatore(nome);
            }
        }
    }

    public void assegnaRuoli(){

        for(int i=0; i<numeroContadini; i++){

            if(giocatori[i] != null){

                giocatori[i].setRuolo(new Contadino());
            }
        }

        for(int i=numeroContadini; i<numeroLupi; i++){
            if(giocatori[i] != null){
                giocatori[i].setRuolo(new Lupo());
            }
        }

        giocatori[numeroLupi].setRuolo(new Crocerossina());
        giocatori[numeroLupi+1].setRuolo(new GuardiadelCorpo());
        giocatori[numeroLupi+2].setRuolo(new Veggente());
        giocatori[numeroGiocatori].setRuolo(new Medium());

    }

    public void setStato(StatoPartita stato){
        this.stato = stato;
    }

    public void vittoria(Giocatore giocatore){
        giocatore.prossimoLivello();
    }

    public void sconfitta(Giocatore giocatore){
        giocatore.regressioneLivello();
    }






}
