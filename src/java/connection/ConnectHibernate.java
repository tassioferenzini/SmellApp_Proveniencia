package connection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

public abstract class ConnectHibernate {

    private static SessionFactory sessionFactory;

    private static final void inicializar() throws Exception {
        try {
            Configuration cfg = new Configuration();
            cfg.configure();

            new SchemaUpdate(cfg).execute(true, true);

            sessionFactory = new AnnotationConfiguration().configure(
                    "hibernate.cfg.xml").buildSessionFactory();
            // cfg.buildSessionFactory();
        } catch (HibernateException e) {
            throw new Exception("Erro ao carregar Hibernate: " + e.getMessage());
        }
    }

    public static Session getSession() throws Exception {
        if (sessionFactory == null) {
            inicializar();
        }
        try {
            return sessionFactory.openSession();
        } catch (HibernateException e) {
            throw new Exception("Erro ao criar sessão Hibernate: "
                    + e.getMessage());
        }
    }

}
