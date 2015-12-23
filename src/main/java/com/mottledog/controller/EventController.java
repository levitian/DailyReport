package com.mottledog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mottledog.bo.Event;
import com.mottledog.bo.User;
import com.mottledog.model.EventModel;
import com.mottledog.service.EventService;
import com.mottledog.service.UserService;
import com.mottledog.util.DateUtil;

@Controller
@RequestMapping(value = "/event")
public class EventController {

	EventService eventService;
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	UserService userService;
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	@RequestMapping(value = { "/list"}, method = RequestMethod.GET)
	@ResponseBody
	public List<EventModel> list(HttpServletRequest request) {
		List<Event> events = eventService.list();
		List<EventModel> ems = new ArrayList<>();
		for(Event e : events){
			EventModel em = new EventModel();
			em.setId(e.getId());
			em.setUid(e.getUid());
			User u = userService.getUserById(e.getUid());
			if(u == null){
				em.setTitle("");
			}else{
				em.setTitle(u.getUsername());
			}
			em.setStart(DateUtil.DateToStr(e.getStart()));
			em.setEnd(DateUtil.DateToStr(e.getEnd()));
			ems.add(em);
		}
		
		return ems;
	}
	
	
	@RequestMapping(value = { "/add"}, method = RequestMethod.POST)
	@ResponseBody
	public EventModel add(HttpServletRequest request, @ModelAttribute EventModel em) {
		Event event = new Event();
		event.setUid(em.getUid());
		//格式："2015-07-02 8:0:0"
		event.setStart(DateUtil.StrToDate(em.getStart()));
		event.setEnd(DateUtil.StrToDate(em.getEnd()));
		int eventId = eventService.add(event);
		em.setId(eventId);
		return em;
	}
	
	@RequestMapping(value = { "/delete/{id}"}, method = RequestMethod.POST)
	@ResponseBody
	public boolean delete(HttpServletRequest request, @PathVariable(value="id") int id) {
		try{
			eventService.delete(id);;
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	@RequestMapping(value = { "/update"}, method = RequestMethod.POST)
	@ResponseBody
	public boolean update(HttpServletRequest request, @ModelAttribute EventModel em) {
		try{
			Event event = new Event();
			event.setId(em.getId());
			event.setUid(em.getUid());
			//格式："2015-07-02 8:0:0"
			event.setStart(DateUtil.StrToDate(em.getStart()));
			event.setEnd(DateUtil.StrToDate(em.getEnd()));
			eventService.update(event);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
