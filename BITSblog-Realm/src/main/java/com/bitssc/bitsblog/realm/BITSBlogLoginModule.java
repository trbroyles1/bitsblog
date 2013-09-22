/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.realm;

import com.sun.appserv.security.AppservPasswordLoginModule;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.security.auth.login.LoginException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author RBroyles
 */
public class BITSBlogLoginModule extends AppservPasswordLoginModule {

    @Override
    protected void authenticateUser() throws LoginException {
        if (!(_currentRealm instanceof BITSBlogRealm)) {
            throw new LoginException("Realm not MyRealm. Check 'login.conf'.");
        }
        BITSBlogRealm realm = (BITSBlogRealm) _currentRealm;

        // Authenticate User
        if (!doAuthentication(_username, _password)) {
            //Login failed
            throw new LoginException("BITSBlogRealm LoginModule: Login Failed for user " + _username);
        }

        // Get group names for the authenticated user from the Realm class
        Enumeration enumeration = null;
        try {
            enumeration = realm.getGroupNames(_username);
        } catch (InvalidOperationException e) {
            throw new LoginException("InvalidOperationException was thrown for getGroupNames() on BITSBlogRealm");
        } catch (NoSuchUserException e) {
            throw new LoginException("NoSuchUserException was thrown for getGroupNames() on BITSBlogRealm");
        }

        // Convert the Enumeration to String[]
        List<String> g = new ArrayList<>();
        while (enumeration != null && enumeration.hasMoreElements()) {
            g.add((String) enumeration.nextElement());
        }

        String[] authenticatedGroups = g.toArray(new String[g.size()]);

        // Call commitUserAuthentication with the group names the user belongs to.
        // Note that this method is called after the authentication has succeeded.
        // If authentication failed do not call this method. Global instance field
        // succeeded is set to true by this method.
        commitUserAuthentication(authenticatedGroups);
    }

    private boolean doAuthentication(String user, String password) throws LoginException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("loginRealmPU");
        EntityManager em = emf.createEntityManager();
        MessageDigest digester;
        try {
            digester = MessageDigest.getInstance("SHA-1");
            StringBuilder result = new StringBuilder();
            for (byte b : digester.digest(password.getBytes())) {
                result.append(String.format("%02X", b));
            }
            Query userQuery = em.createNamedQuery("Applicationuser.findByUserNameAndSecret");
            userQuery.setParameter("userName", user);
            userQuery.setParameter("userSecret", result.toString());
            return userQuery.getResultList().size() > 0;
        } catch (Exception ex) {
            throw new LoginException(ex.toString());
        }
    }
}
