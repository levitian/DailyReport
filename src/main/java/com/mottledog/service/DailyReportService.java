package com.mottledog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mottledog.bo.DailyReport;
import com.mottledog.dao.DailyReportDao;

@Service
@Transactional
public class DailyReportService {
	
	private DailyReportDao dailyReportDao;
	@Autowired
	public void setDailyReportDao(DailyReportDao dailyReportDao) {
		this.dailyReportDao = dailyReportDao;
	}
	
	public long add(DailyReport dailyReport){
		return dailyReportDao.save(dailyReport);
	}
	
	public void delete(long id){
		dailyReportDao.delete(id);
	}
	
	public List<DailyReport> list(){
		return dailyReportDao.list();
	}
	
	public void update(DailyReport dailyReport){
		dailyReportDao.update(dailyReport);
	}
	
	public List<DailyReport> list(String propertyName, Object value){
		return dailyReportDao.list(propertyName, value);
	}
	
	public List<DailyReport> listByToday(){
		return dailyReportDao.listByToday();
	}
}
