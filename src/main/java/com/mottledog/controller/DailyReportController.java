package com.mottledog.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mottledog.bo.DailyReport;
import com.mottledog.service.DailyReportService;
import com.mottledog.util.DateEditor;
import com.mottledog.util.ExportTodayDailyReportUtil;


@Controller
@RequestMapping(value = "/dr")
public class DailyReportController {
	private static final Logger logger = LoggerFactory
			.getLogger(DailyReportController.class);

	DailyReportService dailyReportService;

	@Autowired
	public void setDailyReportService(DailyReportService dailyReportService) {
		this.dailyReportService = dailyReportService;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String dailyReportPage(Model model) {
		logger.info("into Daily Report requested");
		return "/dailyReport/list";
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	@ResponseBody
	public List<DailyReport> list(Model model) {
		List<DailyReport> drs = dailyReportService.list();
		return drs;
	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	@ResponseBody
	public boolean add(@ModelAttribute DailyReport dailyReport) {
		dailyReport.setCreate_date(new Date());
		dailyReportService.add(dailyReport);
		return true;
	}

	@RequestMapping(value = { "/exportTodayDailyReport" }, method = RequestMethod.GET)
	@ResponseBody
	public void exportTodayDailyReport(HttpServletResponse response) {
		List<DailyReport> drs = dailyReportService.listByToday();
		HSSFWorkbook wb = ExportTodayDailyReportUtil.export(drs);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			wb.write(os);
			byte[] bytes = os.toByteArray();

			response.reset();
			response.setContentType("application/msexcel;charset=utf-8");
			response.setHeader("Content-disposition", "attachment;filename=dailyReport.xls");
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			logger.error("export error:",e);
		}
	}

}
