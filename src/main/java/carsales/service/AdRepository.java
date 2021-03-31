package carsales.service;

import carsales.model.Announcement;
import carsales.model.Brand;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

public class AdRepository implements Store {
    private final SessionFactory sf;

    private AdRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private static final class Lazy {
        private static final Store INST = new AdRepository();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    private <T> T tx(final Function<Session, T> command) {
        T rsl;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = command.apply(session);
            session.getTransaction().commit();
        }
        return rsl;
    }

    @Override
    public List<Announcement> getAnnouncementsByLastDay() {
        return tx(session -> session.createQuery("from Announcement where date >= : date")
                .setParameter("date", LocalDate.now().getDayOfMonth())
                .list());
    }

    @Override
    public List<Announcement> getAnnouncementsWithPhoto() {
        return tx(session -> session.createQuery("from Announcement where photo != : isPhoto ")
        .setParameter("isPhoto", null)
        .list());
    }

    @Override
    public List<Announcement> getAnnouncementsByBrand(Brand brand) {
        return tx(session -> session.createQuery("from Announcement where brand.name = : brand")
        .setParameter("brand", brand.getName())
        .list());
    }
}
