

package com.sancode.miniproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sancode.miniproject.domain.FoodAndDrink;

@Repository
@Transactional
public class FoodAndDrinkDAOImpl implements FoodAndDrinkDAO {

	@Autowired
	EntityManager em;
	@Override
	public List<FoodAndDrink> getAllFoodAndDrinkDetails() {
       Query q=em.createQuery("select f from FoodAndDrink as f");

		return q.getResultList();
	}

	@Override
	public FoodAndDrink saveNewFoodAndDrink(FoodAndDrink toBeAdded) {

		em.persist(toBeAdded);
		return toBeAdded;
	}

	@Override
	public FoodAndDrink updateFoodAndDrink(FoodAndDrink toBeUpdated, int fid) {
           float newCost=toBeUpdated.getCost();
           String newName = toBeUpdated.getName();
           Query q=em.createQuery("update FoodAndDrink f set name=:newName , cost =:newCost"
           		+ " where id =:fid ");
           q.setParameter("newCost", newCost);
           q.setParameter("newName", newName);
           q.setParameter("fid", fid);
           q.executeUpdate();
		return em.find(FoodAndDrink.class,fid);
	}
 


	@Override
	public FoodAndDrink findById(int foodAndDrinkId) {
		return em.find(FoodAndDrink.class,foodAndDrinkId);
	}

	@Override
	public void deleteById(int foodAndDrinkId) {
         Query q=em.createQuery("delete from FoodAndDrink where id=:id ");
         q.setParameter("id", foodAndDrinkId);
         q.executeUpdate();
		
	}

}
