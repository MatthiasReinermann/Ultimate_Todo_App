package com.todo.todoapp.data;


import com.todo.todoapp.entities.Todo;
import com.todo.todoapp.entities.Priority;
import com.todo.todoapp.entities.Status;
import java.util.List;
import java.util.Map;

public interface TodoServiceDAO {
	
	public Todo create(final Todo todo);
	
	public Todo update(Todo todo);
	
	public void delete(final Todo todo);
	
	public Todo getTodoById(long id);
	
	public Todo findUniqueWithNamedQuery(String queryName, Map<String, Object> params);
	
	public Todo findUniqueWithNamedQuery(String queryName);
	
	public List<Todo> findWithNamedQuery(String queryName, Map<String, Object> params);
}