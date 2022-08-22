

package com.sancode.miniproject.service;

import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sancode.miniproject.dao.LayoutDAO;
import com.sancode.miniproject.domain.Layout;
@Service
public class LayoutServiceImpl implements LayoutService {

	LayoutDAO layoutDao;
	 @Autowired
	 public void setLayoutDao(LayoutDAO layoutDao) {
		this.layoutDao = layoutDao;
	}
	
	@Override
	public List<Layout> getAllLayoutDetails() {
		List<Layout>allLayout= layoutDao.getAllLayoutDetails();
		for(Layout l : allLayout) {
			if(l.getImageData() == null) {
				continue;
			}
			String image=new String(Base64.decodeBase64(l.getImageData()));
			l.setImage(image);
		}
				 
		return allLayout;		 
	}

	@Override
	public int addNewLayout(Layout toBeAdded) {
		System.out.println(toBeAdded);
		long count=layoutDao.findByName(toBeAdded.getName());
		if(count!=0) {
			throw new IllegalArgumentException("This layout name is already in use, please use a different name");
		}
        if(toBeAdded.getImage()!=null) {
			String image=toBeAdded.getImage();
			toBeAdded.setImageData(Base64.encodeBase64(image.getBytes()));
        }
		
		return layoutDao.saveLayout(toBeAdded).getLayoutId();
	}


	@Override
	public Layout updateLayout(Layout toBeUpdated,int layoutId) {
		String image=toBeUpdated.getImage();
		Layout layout = layoutDao.findById(layoutId);
		if(layout == null) {
			throw new IllegalArgumentException("there is no layout with given id");
		}
		if(image != null) {
			layout.setImageData(Base64.encodeBase64(image.getBytes()));
			layout.setImage(image);
		}
		else {
			
			image = new String(Base64.decodeBase64(layout.getImageData()));
			layout.setImage(image);
		}
		
		String newName=toBeUpdated.getName();
		
		if(layout.getName().compareTo(newName) != 0) {
		
			long count=layoutDao.findByName(toBeUpdated.getName());
			if(count!=0) {
				throw new IllegalArgumentException("This layout name is already in use, please use a different name");
			}
			layout.setName(newName);
		}
		
		
		return layoutDao.saveLayout(layout);
	}

	@Override
	public Layout findById(int layoutId) {
		return layoutDao.findById(layoutId);
	}

	@Override
	public String removeLayout(int layoutId) {
//        layoutDao.deleteById(layoutId);
		if(layoutDao.findById(layoutId) != null) {
			layoutDao.deleteById(layoutId);
			return "Success";
		}
		else {
			throw new IllegalArgumentException("There is no layout with the given name");
		}
	}

}
