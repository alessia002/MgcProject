package it.unicam.cs.mpgc.rpg119064.repository;

import it.unicam.cs.mpgc.rpg119064.model.Giocatore;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class GiocatoreRepository {

    private SessionFactory sessionFactory;

    public GiocatoreRepository() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    /** Salva un giocatore nel DB. Se esiste già lo aggiorna. */
    public void salva(Giocatore giocatore) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(giocatore);
        transaction.commit();
        session.close();
    }

    /** Carica un giocatore dal DB tramite il nome. Restituisce null se non esiste. */
    public Giocatore carica(String nome) {
        Session session = sessionFactory.openSession();
        Giocatore giocatore = session.get(Giocatore.class, nome);
        session.close();
        return giocatore;
    }

    /** Chiude la connessione al DB. */
    public void chiudi() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}