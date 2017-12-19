package com.crmax.persistence.dao;

import com.crmax.persistence.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<User> getUsers() {

        Session session = sessionFactory.getCurrentSession();

        Query<User> query =
                session.createQuery("from User", User.class);

        List<User> users = query.getResultList();

        return users;
    }

}
