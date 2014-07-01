package com.todo.todoapp.data;

import java.util.Map;

import com.todo.todoapp.entities.User;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;


public interface UserServiceDAO {
	
	 /**
     * Creates a new User for the given type. After a call to this method the entity will be
     * persisted into database and then refreshed. Also current persistent Session will be flushed.
     * 
     * @param User
     * @param user
     * @return persisted User
     */
	@CommitAfter
	public User create(final User user);
	
	@CommitAfter
	public User update(User user);
	
	@CommitAfter
	public void delete(final User user);
	
	public User getUserById(final long id);
	
	public User findUniqueWithNamedQuery(final String queryString);
	
	public User findUniqueWithNamedQuery(String queryName, Map<String, Object> params);
	
}