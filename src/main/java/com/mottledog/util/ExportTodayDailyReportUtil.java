package com.mottledog.util;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mottledog.bo.DailyReport;

public class ExportTodayDailyReportUtil {

	public static HSSFWorkbook export(List<DailyReport> drs){
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("学生表一");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("工作日");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("工作内容（任务）");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("牵头人");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("工作进展（%）");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("是否测试");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("是否提交SVN");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("遇到的困难/问题");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("备注");
		cell.setCellStyle(style);
		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，

		for (int i = 0; i < drs.size(); i++) {
			row = sheet.createRow((int) i + 1);
			DailyReport dr = drs.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell(0).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(dr.getWork_date()));
			row.createCell(1).setCellValue(dr.getName());
			row.createCell(2).setCellValue(dr.getWork_content());
			row.createCell(3).setCellValue(dr.getInitiator());
			row.createCell(4).setCellValue(dr.getJob_progress());
			row.createCell(5).setCellValue(dr.isIs_test());
			row.createCell(6).setCellValue(dr.isIs_submit());
			row.createCell(7).setCellValue(dr.getDoubt());
			row.createCell(8).setCellValue(dr.getRemark());
			cell = row.createCell(9);
		}
		// 第六步，返回wb
		return wb;
	}
}
