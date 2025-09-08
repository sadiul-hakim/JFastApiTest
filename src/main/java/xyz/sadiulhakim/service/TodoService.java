package xyz.sadiulhakim.service;

import com.jFastApi.AppContext;
import com.jFastApi.annotation.Bean;
import com.jFastApi.db.HibernateRepository;
import xyz.sadiulhakim.model.Todo;

import java.util.List;

@Bean
public class TodoService {
    private final HibernateRepository<Todo, Long> todoRepository;

    public TodoService() {
        todoRepository = new HibernateRepository<>(AppContext.getDefaultSessionFactory(), Todo.class);
    }

    public void save(Todo todo) {
        todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo findById(long id) {
        return todoRepository.findById(id);
    }

    public boolean delete(long id) {
        Todo todo = findById(id);
        todoRepository.delete(todo);
        return true;
    }
}
