package com.todo.todoapp.data;


import com.todo.todoapp.entities.Todo;
import com.todo.todoapp.entities.Priority;
import com.todo.todoapp.entities.Status;


import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.util.Map.Entry;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

public class HibernateTodoServiceDAO implements TodoServiceDAO {
	
	@Inject
	private Session session;

	private final Logger logger = LoggerFactory.getLogger(TodoServiceDAO.class);	
	
	public Todo create(final Todo todo){
		session.persist(todo);
		session.flush();
		session.refresh(todo);
		return todo;
	}
	
	public Todo update(Todo todo){
		session.merge(todo);
		return todo;
	}
	
	public void delete(final Todo todo){
	     session.delete(todo);
	}
	
	public Todo getTodoById(long id){
		Todo todo = (Todo) session.get(Todo.class,id);
		return todo;
	}
	
	@SuppressWarnings("unchecked")
    public List<Todo> findWithNamedQuery(String queryName, Map<String, Object> params)
    {
        Set<Entry<String, Object>> rawParameters = params.entrySet();
        Query query = session.getNamedQuery(queryName);

        for (Entry<String, Object> entry : rawParameters)
        {
            query.setParameter(entry.getKey(), entry.getValue());

        }
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public Todo findUniqueWithNamedQuery(String queryName)
    {
        return (Todo) session.getNamedQuery(queryName).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public Todo findUniqueWithNamedQuery(String queryName, Map<String, Object> params)
    {
        Set<Entry<String, Object>> rawParameters = params.entrySet();
        Query query = session.getNamedQuery(queryName);

        for (Entry<String, Object> entry : rawParameters)
        {
            query.setParameter(entry.getKey(), entry.getValue());

        }
        return (Todo) query.uniqueResult();
    }
}

