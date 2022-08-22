

package com.sancode.miniproject.service;

import java.util.List;

import com.sancode.miniproject.domain.Layout;

public interface LayoutService {

	
	List<Layout> getAllLayoutDetails();
    int  addNewLayout(Layout toBeAdded);
	Layout updateLayout(Layout toBeUpdated,int layoutId);
	Layout findById(int layoutId);
	String removeLayout(int layoutId);
}
