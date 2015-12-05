package com.mottledog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mottledog.bo.Event;
import com.mottledog.dao.EventDao;

@Service
@Transactional
public class EventService {

	private EventDao eventDao;
	@Autowired
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	public int add(Event event){
		return eventDao.save(event);
	}
	
	public void delete(int id){
		eventDao.delete(id);
	}
	
	public void update(Event event){
		eventDao.update(event);
	}
	public List<Event> list(){
		return eventDao.list();
	}
}
