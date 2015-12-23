package com.mottledog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mottledog.bo.DailyReport;

@Repository
@Transactional(readOnly = true)
public class DailyReportDao extends BaseDao<DailyReport, Long> {

	@SuppressWarnings("unchecked")
	public List<DailyReport> listByToday() {
		String sql = "select * from daily_report where to_days(work_date) = to_days(now()) order by name";
		List<DailyReport> drs = getSession().createSQLQuery(sql)
				.addEntity(DailyReport.class).list();
		return drs;
	}

	/**
	 * 按照日期查询
	 * 
	 * @param dateStr
	 *            2015-12-05
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DailyReport> listByDate(String dateStr) {
		String sql = "SELECT * FROM `daily_report` WHERE WORK_DATE BETWEEN '"
				+ dateStr + "' AND '" + dateStr + " 23:59:59' order by name asc, work_content asc";
		List<DailyReport> drs = getSession().createSQLQuery(sql)
				.addEntity(DailyReport.class).list();
		return drs;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DailyReport> list(){
		String sql = "select * from daily_report order by work_date desc, name asc";
		List<DailyReport> drs = getSession().createSQLQuery(sql)
				.addEntity(DailyReport.class).list();
		return drs;
	}
	
	@SuppressWarnings("unchecked")
	public List<DailyReport> list(String name, String start_date, String end_date){
		String sql = "select * from daily_report where name like '" + name + 
				"' and work_date between '" + start_date + "' and '" + end_date + "'" +
				"order by work_date asc, name asc";
		List<DailyReport> drs = getSession().createSQLQuery(sql)
				.addEntity(DailyReport.class).list();
		return drs;
	}
}
