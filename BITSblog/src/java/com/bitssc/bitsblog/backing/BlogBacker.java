/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.backing;

import com.bitssc.bitsblog.entity.Blog;
import com.bitssc.bitsblog.facade.BlogFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author RBroyles
 */
@Named(value = "blogBacker")
@SessionScoped
public class BlogBacker implements Serializable {
    @EJB
    private BlogFacade blogFacade;

    /**
     * Creates a new instance of BlogBacker
     */
    public BlogBacker() {
    }
    
    private Blog blog;
    public Blog getBlog() {
        if(blog == null)
            blog = blogFacade.findAll().get(0);
        return blog;
    }
    
}
