package ru.vasilev;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.vasilev.model.Author;

@Component
public class TestDataLoader implements CommandLineRunner {

    private final SessionFactory sessionFactory;

    public TestDataLoader(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            
            // Создадим тестового автора
            Author author = new Author("Тестовый автор", null);
            session.persist(author);
            
            session.getTransaction().commit();
            System.out.println("Автор сохранён: " + author.getId());
        }
    }
}