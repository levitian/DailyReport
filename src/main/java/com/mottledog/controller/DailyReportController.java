package com.mottledog.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mottledog.bo.DailyReport;
import com.mottledog.service.DailyReportService;
import com.mottledog.util.DateEditor;
import com.mottledog.util.DateUtil;
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
	public String dailyReportPage(HttpServletRequest request) {
		logger.info("into Daily Report requested");
		String dateTime = DateUtil.DateToStr(new Date());
		request.setAttribute("dateTime", dateTime);
		
		try {
			request.setAttribute("nextDate", DateUtil.getNextDate(dateTime));
			request.setAttribute("formerDate", DateUtil.getFormerDate(dateTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/dailyReport/list";
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	@ResponseBody
	public List<DailyReport> list(Model model) {
		List<DailyReport> drs = dailyReportService.listByToday();
		return drs;
	}

	@RequestMapping(value = { "/list/{dateTime}" }, method = RequestMethod.GET)
	public String list(@PathVariable("dateTime") String dateTime, HttpServletRequest request) {
		try {
			request.setAttribute("dateTime", dateTime);
			request.setAttribute("nextDate", DateUtil.getNextDate(dateTime));
			request.setAttribute("formerDate", DateUtil.getFormerDate(dateTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/dailyReport/list";
	}
	
	@RequestMapping(value = { "/list/{dateTime}" }, method = RequestMethod.POST)
	@ResponseBody
	public List<DailyReport> listByDate(@PathVariable("dateTime") String dateTime) {
		List<DailyReport> drs = dailyReportService.listByDate(dateTime);
		return drs;
	}
	
	@RequestMapping(value = { "/search" }, method = RequestMethod.POST)
	public String listByName(HttpServletRequest request) {
		String name = ServletRequestUtils.getStringParameter(request, "query_name", "");
		String start_work_date = ServletRequestUtils.getStringParameter(request, "start_work_date", "");
		String end_work_date = ServletRequestUtils.getStringParameter(request, "end_work_date", "");
		List<DailyReport> drs = dailyReportService.list(name,start_work_date,end_work_date);
		request.setAttribute("drs", drs);
		return "/dailyReport/search";
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dataStr = sdf.format(new Date());
		HSSFWorkbook wb = ExportTodayDailyReportUtil.export(drs);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			wb.write(os);
			byte[] bytes = os.toByteArray();

			response.reset();
			response.setContentType("application/msexcel;charset=utf-8");
			response.setHeader("Content-disposition", "attachment;filename=DailyReport" + dataStr + 
					".xls");
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			logger.error("export error:",e);
		}
	}
	
	public static void main(String[] args){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datastr = sdf.format(new Date());
		System.out.println(datastr);
	}

}
