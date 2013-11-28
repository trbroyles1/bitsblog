/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.backing;


import com.bitssc.bitsblog.entity.ApplicationUser;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author RBroyles
 */

@Named(value = "userAdminBacker")
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(
            id = "usersAdministration",
            pattern = "/admin/users",
            viewId = "/faces/admin/users.xhtml"
        ),
    @URLMapping(
            id = "newUser",
            pattern = "/admin/users/newUser",
            viewId = "/faces/admin/users.xhtml"
        ),
})
public class UserBacker {
    /*
    @EJB
    private ListsProvider listsProvider;
    @EJB
    private UserManager userManager;
    @EJB
    private UserFacade userFacade;
    
    
    

    private ApplicationUser currentUser;
    private Boolean userListVisible = true;
    private Boolean userDetailsVisible;
    private Boolean userEditVisible;
    //private String editedUserStatus;
    //private String editedUserTags;

    public UserBacker() {
    }
    
    @URLAction(mappingId = "newUser")
    public void initNewUser(){
        currentUser = new ApplicationUser();
        userListVisible = false;
        userDetailsVisible = false;
        userEditVisible = true;
    }
    
    private FacesContext getCurrentFacesContext(){
        return FacesContext.getCurrentInstance();
    }
    
    public ApplicationUser getCurrentUser() {
        return currentUser;
    }

    private void setCurrentUser(ApplicationUser value) {
        currentUser = value;
    }

    public Boolean getUserListVisible() {
        return userListVisible;
    }

    public Boolean getUserDetailsVisible() {
        return userDetailsVisible;
    }

    public Boolean getUserEditVisible() {
        return userEditVisible;
    }
    
    public Collection<UserStatus> getUserStatuses(){
        return listsProvider.getUserStatuses().values();
    }

    public String getEditedUserStatus() {
        return editedUserStatus;
    }

    public void setEditedUserStatus(String editedUserStatus) {
        this.editedUserStatus = editedUserStatus;
    }
    
    public String getUserTags(){
        return String.format("'%s'", StringUtils.join(listsProvider.getUserTags().keySet().toArray(), "','"));
    }

    public String getEditedUserTags() {
        return editedUserTags;
    }

    public void setEditedUserTags(String value) {
        editedUserTags = value;
    }

    public void showUserList() {
        userListVisible = true;
        userDetailsVisible = false;
        userEditVisible = false;
    }

    public void showUserDetails(int userId) {
        currentUser = userFacade.find(userId);
        userDetailsVisible = true;
        userEditVisible = false;
        userListVisible = false;
    }

    public void showUserEdit(int userId) {
        if (currentUser == null || currentUser.getUserId() != userId) {
            currentUser = userFacade.find(userId);
        }

        editedUserStatus = currentUser.getUserStatus().getName();
        List<String> tags = new ArrayList<>();
        for (Tag t : currentUser.getTagList()) {
            tags.add(t.getName());
        }
        editedUserTags = StringUtils.join(tags, ",");

        userListVisible = false;
        userDetailsVisible = false;
        userEditVisible = true;
    }

    public void saveUser() {
        currentUser.setDateLastModified(new Date());
        currentUser.setUserStatus(listsProvider.getUserStatuses().get(editedUserStatus));

        if (currentUser.getUserId() == null) {
            userManager.createUser(currentUser, getCurrentFacesContext().getExternalContext().getUserPrincipal().getName(), editedUserTags);
            getCurrentFacesContext().addMessage(null, new FacesMessage("Success", String.format("%s was created", currentUser.getTitle())));
        } else {
            userManager.editUser(currentUser, editedUserTags);
            getCurrentFacesContext().addMessage(null, new FacesMessage("Success", String.format("%s was updated", currentUser.getTitle())));
        }
        showUserDetails(currentUser.getUserId());
    }

    public void deleteUser(int userId) {
        userFacade.remove(userFacade.find(userId));
        showUserList();
    }

    public void cancelEdit() {
        if (currentUser.getUserId() != null) {
            showUserDetails(currentUser.getUserId());
        } else {
            showUserList();
        }
    }
    * */
}
