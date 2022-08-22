

package com.sancode.miniproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sancode.miniproject.domain.Layout;
@Repository
@Transactional
public class LayoutDAOImpl implements LayoutDAO {
    @Autowired
	EntityManager em;
	
    @SuppressWarnings("unchecked")
	@Override
	public List<Layout> getAllLayoutDetails() {
        Query q=em.createQuery("select l from Layout as l");
		return q.getResultList();
	}

	@Override
	public Layout saveLayout(Layout toBeAdded) {
		em.persist(toBeAdded);
		return toBeAdded;
	}

	@Override
	public Layout updateLayout(Layout toBeUpdated,int layoutId) {
         String newName=toBeUpdated.getName();
         Query q=em.createQuery("update Layout l set name=:newName where l.layoutId=:layoutId");
         q.setParameter("newName", newName);
         q.setParameter("layoutId", layoutId);
         q.executeUpdate();
         
         return findById(layoutId);
	}

	@Override
	public Layout findById(int layoutId) {
		return em.find(Layout.class,layoutId);
	}

	@Override
	public void deleteById(int layoutId) {
       Query q=em.createQuery("delete from Layout as l where l.layoutId=:layoutId");
       q.setParameter("layoutId", layoutId);
       q.executeUpdate();
	}

	@Override
	public long findByName(String name) {
        Query q=em.createQuery("select count(*) from Layout as l where name=:name");
        q.setParameter("name", name);
        return (long) q.getSingleResult();
	}

}
