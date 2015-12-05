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
		// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
		HSSFWorkbook wb = new HSSFWorkbook();
		// �ڶ�������webbook������һ��sheet,��ӦExcel�ļ��е�sheet
		HSSFSheet sheet = wb.createSheet("ѧ����һ");
		// ����������sheet�����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short
		HSSFRow row = sheet.createRow((int) 0);
		// ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����һ�����и�ʽ

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("������");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("����");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("�������ݣ�����");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("ǣͷ��");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("������չ��%��");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("�Ƿ����");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("�Ƿ��ύSVN");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("����������/����");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("��ע");
		cell.setCellStyle(style);
		// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���

		for (int i = 0; i < drs.size(); i++) {
			row = sheet.createRow((int) i + 1);
			DailyReport dr = drs.get(i);
			// ���Ĳ���������Ԫ�񣬲�����ֵ
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
		// ������������wb
		return wb;
	}
}