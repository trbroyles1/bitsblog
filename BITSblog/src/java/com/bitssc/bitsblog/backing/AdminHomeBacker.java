/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.backing;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author RBroyles
 */
@Named(value = "adminHomeBacker")
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "adminHome",pattern = "/admin/"  ,viewId = "/faces/admin/index.xhtml"),
    @URLMapping(id = "adminDashboard",pattern = "/admin/dashboard"  ,viewId = "/faces/admin/index.xhtml")
})
public class AdminHomeBacker {

    /**
     * Creates a new instance of AdminHomeBacker
     */
    public AdminHomeBacker() {
    }
}
