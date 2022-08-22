

package com.sancode.miniproject.dao;

import java.util.List;

import com.sancode.miniproject.domain.Layout;

public interface LayoutDAO {

	
	List<Layout> getAllLayoutDetails();
    Layout saveLayout(Layout toBeAdded);
	Layout updateLayout(Layout toBeUpdated,int layoutId);
	Layout findById(int layoutId);
	void deleteById(int layoutId);
	long findByName(String name);
}
