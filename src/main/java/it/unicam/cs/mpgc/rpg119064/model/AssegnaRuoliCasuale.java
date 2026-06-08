package it.unicam.cs.mpgc.rpg119064.model;

import it.unicam.cs.mpgc.rpg119064.model.interfaces.AssegnaRuoli;
import it.unicam.cs.mpgc.rpg119064.model.interfaces.Ruolo;
import java.util.Random;

public class AssegnaRuoliCasuale implements AssegnaRuoli {

    @Override
    public void assegna(Giocatore[] giocatori) {

        int numeroGiocatori = giocatori.length;
        int rimanenti = numeroGiocatori - 4; // 4 ruoli speciali fissi
        int numeroLupi = rimanenti / 2;
        int numeroVillici = rimanenti - numeroLupi;

        Ruolo[] ruoli = new Ruolo[numeroGiocatori];
        int indice = 0;

        for (int i = 0; i < numeroVillici; i++) ruoli[indice++] = new Contadino();
        for (int i = 0; i < numeroLupi; i++) ruoli[indice++] = new Lupo();
        ruoli[indice++] = new Crocerossina();
        ruoli[indice++] = new GuardiadelCorpo();
        ruoli[indice++] = new Veggente();
        ruoli[indice]   = new Medium();

        Random random = new Random();
        for (int i = ruoli.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Ruolo temp = ruoli[i];
            ruoli[i] = ruoli[j];
            ruoli[j] = temp;
        }

        for (int i = 0; i < giocatori.length; i++) {
            giocatori[i].setRuolo(ruoli[i]);
        }
    }
}