package com.mottledog.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event implements Serializable { //Fullcalendar Event
	
	private static final long serialVersionUID = 5906528718554609306L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	//private String title;
	//private boolean allDay;
	@Column(name = "start", nullable = false)
	private Date start;
	@Column(name = "end", nullable = false)
	private Date end;
/*	private String url;
	private String className;
	private boolean editable;
	private boolean startEditable;
	private boolean durationEditable;
	private Object rendering;
	private boolean overlap;
	private int constraint;
	private Object source;
	private String color;
	private String backgroundColor;
	private String borderColor;
	private String textColor;*/
	@Column(name = "uid", nullable = false)
	private int uid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

	
}
