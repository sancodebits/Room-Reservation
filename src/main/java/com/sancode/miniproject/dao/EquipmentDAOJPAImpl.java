

package com.sancode.miniproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sancode.miniproject.domain.Equipment;

@Repository
@Transactional
public class EquipmentDAOJPAImpl implements EquipmentDAO {

	@Autowired
	EntityManager em;
	
	@Override
	public Equipment save(Equipment tobeSaved) {
		em.persist(tobeSaved);
		return tobeSaved;
	}

	@Override
	public Equipment findById(int id) {
		return em.find(Equipment.class, id);
	}

	@Override
	public void deleteById(int id) {
		Query q = em.createQuery("delete from Equipment as e where e.id=:id");
		q.setParameter("id", id);
		q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipment> findAll() {
		Query q = em.createQuery("select e from Equipment as e");
		return q.getResultList();
	}

	@Override
	public Equipment updateById(Equipment toBeUpdated, int id) {
		String newName=toBeUpdated.getName();
        boolean newMultiUnitAvailability = toBeUpdated.isMultiUnitAvailability();
        String newCost = toBeUpdated.getCost();
        Query q=em.createQuery("update Equipment e set name=:newName , cost =:newCost, "
        		+ "multiUnitAvailability =:newMultiUnitAvailability "
        		+ "where e.id =:id ");
        q.setParameter("newName", newName);
        q.setParameter("newCost", newCost);
        q.setParameter("newMultiUnitAvailability", newMultiUnitAvailability);
        q.setParameter("id", id);
        q.executeUpdate();
        toBeUpdated.setId(id);
		return toBeUpdated;
	}

	@Override
	public long findByName(String name) {
        Query q=em.createQuery("select count(*) from Equipment as e where name=:name");
        q.setParameter("name", name);
		return (long) q.getSingleResult();
	}
	

}
