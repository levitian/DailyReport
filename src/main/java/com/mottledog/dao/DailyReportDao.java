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
		String sql = "select * from daily_report where to_days(work_date) = to_days(now())";
		List<DailyReport> drs = getSession().createSQLQuery(sql)
				.addEntity(DailyReport.class).list();
		return drs;
	}
}
