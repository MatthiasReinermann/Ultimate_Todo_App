package com.todo.todoapp.pages;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;


public class Error {

    @Persist(value = PersistenceConstants.FLASH)
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
