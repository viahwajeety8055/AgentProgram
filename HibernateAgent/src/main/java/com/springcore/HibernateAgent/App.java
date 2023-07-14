package com.springcore.HibernateAgent;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App 
{
    public static void main( String[] args )
    {
    	Configuration configuration = new Configuration().configure().addAnnotatedClass(Agent.class);
        SessionFactory sFactory = configuration.buildSessionFactory();
        Session session = sFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        Agent agent1 = new Agent();
        agent1.setAgentId(1);
        agent1.setAgentName("Vishwajeet");
        
        Agent agent2 = new Agent();
        agent2.setAgentId(2);
        agent2.setAgentName("Osama");

        Agent agent3 = new Agent();
        agent3.setAgentId(3);
        agent3.setAgentName("Ramesh");
        
//        Adding to database
        session.save(agent1);
        session.save(agent2);
        session.save(agent3);

        transaction.commit();
        
//        Read Data from database
        
        readValue(session);
        
//        Update Data
        
        updateValue(session,1);
        
//        Delete Data
        
        deleteValue(session, 1);
        
        session.close();
        sFactory.close();
    }
    static void readValue(Session session) {
    	Query query = session.createQuery("FROM Agent");
        List<Agent> agents = query.getResultList();
        agents.forEach((value) -> System.out.println(value.getAgentId() +" " + value.getAgentName()));
    }
    static void updateValue(Session session,int value) {
    	Agent agent = session.load(Agent.class,value);
        agent.setAgentName("thakur");
        session.update(agent);
    }
    static void deleteValue(Session session,int value) {
    	Agent agent = session.load(Agent.class,value);
        session.delete(agent);
    }
}