package com.mottledog.util;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import com.mottledog.bo.DailyReport;

public class ExportTodayDailyReportUtil {

	public static HSSFWorkbook export(List<DailyReport> drs) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("日报");
		sheet.setDefaultColumnWidth(10);
		sheet.setColumnWidth(2, 40 * 256);
		sheet.setColumnWidth(4, 20 * 256);
		sheet.setColumnWidth(5, 20 * 256);
		sheet.setColumnWidth(6, 22 * 256);
		sheet.setColumnWidth(7, 25 * 256);

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		row.setHeightInPoints(30);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式
		style.setWrapText(true);// 设置为自动换行
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		// 设置字体
		HSSFFont font = wb.createFont();// 创建字体对象
		font.setFontHeightInPoints((short) 14);// 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体
		font.setFontName("微软雅黑");// 设置为黑体字
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);// 将字体加入到样式对象
		
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

		HSSFCellStyle style1 = wb.createCellStyle();
		style1.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式
		style1.setWrapText(true);// 设置为自动换行
		style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 设置字体
		HSSFFont font1 = wb.createFont();// 创建字体对象
		font1.setFontHeightInPoints((short) 11);// 设置字体大小
		font1.setFontName("宋体");// 设置为黑体字
		style1.setFont(font1);// 将字体加入到样式对象
		for (int i = 0; i < drs.size(); i++) {
			row = sheet.createRow((int) i + 1);
			row.setHeightInPoints(30);
			DailyReport dr = drs.get(i);
			// 第四步，创建单元格，并设置值
			HSSFCell cell0 = row.createCell(0);
			cell0.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(dr.getWork_date()));
			cell0.setCellStyle(style1);
			
			HSSFCell cell1 = row.createCell(1);
			cell1.setCellValue(dr.getName());
			cell1.setCellStyle(style1);
			
			HSSFCell cell2 = row.createCell(2);
			cell2.setCellValue(dr.getWork_content());
			cell2.setCellStyle(style1);
			
			HSSFCell cell3 = row.createCell(3);
			cell3.setCellValue(dr.getInitiator());
			cell3.setCellStyle(style1);
			
			HSSFCell cell4 = row.createCell(4);
			cell4.setCellValue(dr.getJob_progress());
			cell4.setCellStyle(style1);
			
			HSSFCell cell5 = row.createCell(5);
			cell5.setCellValue(dr.isIs_test()?"是":"否");
			cell5.setCellStyle(style1);
			
			HSSFCell cell6 = row.createCell(6);
			cell6.setCellValue(dr.isIs_submit()?"是":"否");
			cell6.setCellStyle(style1);
			
			HSSFCell cell7 = row.createCell(7);
			cell7.setCellValue(dr.getDoubt());
			cell7.setCellStyle(style1);
			
			HSSFCell cell8 = row.createCell(8);
			cell8.setCellValue(dr.getRemark());
			cell8.setCellStyle(style1);
/*			row.createCell(1).setCellValue(dr.getName());
			row.createCell(2).setCellValue(dr.getWork_content());
			row.createCell(3).setCellValue(dr.getInitiator());
			row.createCell(4).setCellValue(dr.getJob_progress());
			row.createCell(5).setCellValue(dr.isIs_test());
			row.createCell(6).setCellValue(dr.isIs_submit());
			row.createCell(7).setCellValue(dr.getDoubt());
			row.createCell(8).setCellValue(dr.getRemark());*/

			cell = row.createCell(9);
		}
		// 第六步，返回wb
		return wb;
	}
}
