package com.todo.todoapp.pages;

import java.util.Date;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;
import com.todo.todoapp.entities.User;

// import .model.user
// property logged User exists
/**
 * Start page of application todoapp.
 */
public class Index
{
	@SessionState
	private User loggedUser; 
	
	@Property
	private boolean loggedUserExists;
}
