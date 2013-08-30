/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.facade;

import com.bitssc.bitsblog.entity.Post;
import com.bitssc.bitsblog.entity.Tag;
import com.bitssc.bitsblog.session.ListsProvider;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author RBroyles
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> {
    @EJB
    private ListsProvider statusProvider;
    
    @PersistenceContext(unitName = "BITSblogPU")
    private EntityManager em;
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }
    
    public List<Post> findTopNByViews(int maximumResultSize){
        Query postQuery = em.createNamedQuery("Post.findAllOrderedByViewsDescending");
        postQuery.setMaxResults(maximumResultSize);
        return postQuery.getResultList();
    }
    
    public List<Post> findTopNByComments(int maximumResultSize){
        Query postQuery = em.createNamedQuery("Post.findAll");
        List<Post> posts = postQuery.getResultList();
        Collections.sort(posts, new Comparator<Post>(){

            @Override
            public int compare(Post o1, Post o2) {
                //If this looks backwards, it's because it is! We want the list ordered most comments first
                //Sort is designed to order from smallest to largest so we just say it's "smaller" if it has more comments
                return o1.getCommentList().size() > o2.getCommentList().size() ? -1 : 1;
            }
            
        });
        
        return posts.subList(0, posts.size() < maximumResultSize ? posts.size() - 1 : maximumResultSize - 1);
    }
    
    public List<Post> findPinned(){
        Query postQuery = getEntityManager().createNamedQuery("Post.findByPinned");
        postQuery.setParameter("pinned", true);
        return postQuery.getResultList();
    }
    
    public List<Post> findNewest2Published(){
        Query postQuery = getEntityManager().createNamedQuery("Post.findByStatusDescending");
        postQuery.setParameter("statusName", Post.STATUS_PUBLISHED);
        postQuery.setMaxResults(2);
        
        return postQuery.getResultList();
    }
    
    public List<Post> findByTag(Tag tag){
        Query postQuery = em.createNamedQuery("Post.findByCategory");
        postQuery.setParameter("tag", tag);
     
        return postQuery.getResultList();
    }
    
    public Post[] findPostAndSiblingsPublished(Date date, String title){
        Post[] posts = new Post[3];
        List<Post> olderPosts, newerPosts;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.roll(Calendar.DATE, true);
        Date followingDate = new Date(cal.getTimeInMillis());
        
        
        Query olderPostQuery = getEntityManager().createNamedQuery("Post.findOlderThanByStatusDescending");
        Query centralPostQuery = getEntityManager().createNamedQuery("Post.findByYearMonthDayTitle");
        Query newerPostQuery = getEntityManager().createNamedQuery("Post.findNewerThanByStatusAscending");
        centralPostQuery.setParameter("targetDate",date);
        centralPostQuery.setParameter("followingDate",followingDate);
        centralPostQuery.setParameter("title", title);
        centralPostQuery.setMaxResults(1);
        posts[1] = (Post)centralPostQuery.getSingleResult();
        
        olderPostQuery.setParameter("postId", posts[1].getPostId());
        olderPostQuery.setParameter("statusName", Post.STATUS_PUBLISHED);
        newerPostQuery.setParameter("postId", posts[1].getPostId());
        newerPostQuery.setParameter("statusName", Post.STATUS_PUBLISHED);
        olderPostQuery.setMaxResults(1);
        newerPostQuery.setMaxResults(1);
        olderPosts = olderPostQuery.getResultList();
        newerPosts = newerPostQuery.getResultList();
        
        posts[0] = olderPosts.size() > 0 ? olderPosts.get(0) : null;    
        posts[2] = newerPosts.size() > 0 ? newerPosts.get(0) : null;
        
        return posts;
    }
    
}
