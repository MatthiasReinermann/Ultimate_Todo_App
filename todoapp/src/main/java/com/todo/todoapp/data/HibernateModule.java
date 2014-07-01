package com.todo.todoapp.data;
import org.apache.tapestry5.hibernate.HibernateTransactionAdvisor;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Match;

/**
 * This class should contain contribution to data stuff (hibernate configuration, beanvalidators...)
 */
public class HibernateModule
{
    public static void bind(ServiceBinder binder)
    {
        binder.bind(UserServiceDAO.class, HibernateUserServiceDAO.class);
        binder.bind(TodoServiceDAO.class, HibernateTodoServiceDAO.class);
    }


    @Match("*DAO")
    public static void adviseTransactions(HibernateTransactionAdvisor advisor,
            MethodAdviceReceiver receiver)
    {
        advisor.addTransactionCommitAdvice(receiver);
    }
}
