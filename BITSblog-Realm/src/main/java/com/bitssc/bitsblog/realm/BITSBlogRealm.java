/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.realm;

import com.bitssc.bitsblog.realm.entity.Applicationuser;
import com.bitssc.bitsblog.realm.entity.UserGroup;
import com.sun.appserv.security.AppservRealm;
import com.sun.enterprise.security.auth.realm.BadRealmException;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchRealmException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author RBroyles
 */
public class BITSBlogRealm extends AppservRealm {
    
    private static final String JAAS_CONTEXT="jaas-context";
    
    @Override
    public void init(Properties properties) throws BadRealmException, NoSuchRealmException{
        System.out.println("Init BITSBlogRealm");
 
       // Pass the properties declared in the console to the system
       String propJaasContext=properties.getProperty(JAAS_CONTEXT);
       if (propJaasContext!=null) {
          setProperty(JAAS_CONTEXT, propJaasContext);
       }
    }

    @Override
    public String getAuthType() {
        return "Custom";
    }

    @Override
    public Enumeration getGroupNames(String string) throws InvalidOperationException, NoSuchUserException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("loginRealmPU");
        EntityManager em = emf.createEntityManager();
        Vector<String> groupNames = new Vector<>();
        
        Query userQuery = em.createNamedQuery("Applicationuser.findByUserName");
        userQuery.setParameter("userName", string);
        Applicationuser usr = (Applicationuser)userQuery.getSingleResult();
        
        if(usr == null)
            throw new NoSuchUserException("User not found");
        
        for(UserGroup group : usr.getUserGroupCollection()){
            groupNames.add(group.getGroupName());
        }
        
        return groupNames.elements();
        
    }
    
    @Override
    public boolean supportsUserManagement(){
        return false;
    }
}
