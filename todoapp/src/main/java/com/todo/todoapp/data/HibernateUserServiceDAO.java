package com.todo.todoapp.data;

import com.todo.todoapp.entities.User;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.util.Map.Entry;

public class HibernateUserServiceDAO implements UserServiceDAO {
	
	@Inject
	private Session session;
	
	private final Logger logger = LoggerFactory.getLogger(UserServiceDAO.class);	
	
	public User create(final User user){
			session.persist(user);
			session.flush();
			session.refresh(user);
		return user;
	}
	
	public User update(User user){
		session.merge(user);
		return user;
	}
	
	public void delete(final User user){
		session.delete(user);
	}
	
	public User getUserById(final long id){
		User user = (User) session.get(User.class, id);
		return user;
	}
	
	@SuppressWarnings("unchecked")
    public User findUniqueWithNamedQuery(String queryName)
    {
        return (User) session.getNamedQuery(queryName).uniqueResult();
    }
	
	@SuppressWarnings("unchecked")
    public User findUniqueWithNamedQuery(String queryName, Map<String, Object> params)
    {
        Set<Entry<String, Object>> rawParameters = params.entrySet();
        Query query = session.getNamedQuery(queryName);

        for (Entry<String, Object> entry : rawParameters)
        {
            query.setParameter(entry.getKey(), entry.getValue());

        }
        return (User) query.uniqueResult();
    }
	
	
}