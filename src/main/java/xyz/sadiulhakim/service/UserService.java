package xyz.sadiulhakim.service;

import com.jFastApi.AppContext;
import com.jFastApi.annotation.Bean;
import com.jFastApi.db.HibernateRepository;
import com.jFastApi.security.SecurityContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.SelectionQuery;
import xyz.sadiulhakim.model.User;

import java.util.List;

@Bean
public class UserService {

    private final HibernateRepository<User, Long> userRepository;

    public UserService() {
        userRepository = new HibernateRepository<>(AppContext.getDefaultSessionFactory(), User.class);
    }

    public void save(User user) {
        String newPassword = SecurityContext.getPasswordEncoder().encode(user.getPassword());
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    public User findById(long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        SessionFactory sessionFactory = AppContext.getDefaultSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            SelectionQuery<User> query = session.createSelectionQuery("from User where username = :username", User.class)
                    .setParameter("username", username);

            return query.getSingleResult();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            return null;
        }
    }

    public void delete(long id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}
