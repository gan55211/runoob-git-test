package cn.itcast.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.domain.quyu;
import cn.itcast.service.uploadServcie;
@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/")
@Actions
public class uploadACtion2 extends ActionSupport{
	@Autowired
	private uploadServcie servcie;
	@Autowired
	private ComboPooledDataSource datasource;
	public String a="aaa";
	private File excel;

	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}
	
	/*@Action(value="upload")
	public void upload() throws FileNotFoundException, IOException{
		List<quyu> quyu=new ArrayList<>();
		HSSFWorkbook hssfworkbook=new HSSFWorkbook(new FileInputStream(excel));
		for(int i=0;i<3;i++){
		HSSFSheet sheetAt = hssfworkbook.getSheetAt(i);
		for (Row row : sheetAt) {
			
			if(row.getCell(1)==null||StringUtils.isBlank(row.getCell(1).getStringCellValue())){
				continue;
			}
			quyu quyu2 = new quyu();
			quyu2.setQuyu(row.getCell(1).getStringCellValue());
			quyu.add(quyu2);
		}
		}
		System.out.println(quyu.size());
		servcie.upload(quyu);
	}*/
	@Action(value="upload")
	public void upload() throws FileNotFoundException, IOException, Exception{
		HSSFWorkbook hssfworkbook=new HSSFWorkbook(new FileInputStream(excel));
		Connection connection=null;
		long start = System.currentTimeMillis();
		int ind=1000;
		
		for(int i=0;i<3;i++){
		HSSFSheet sheetAt = hssfworkbook.getSheetAt(i);
		for (Row row : sheetAt) {
			ind++;
			if(ind%100==1){
				connection = datasource.getConnection();
				connection.setAutoCommit(false);
			}
			if(row.getCell(1)==null||StringUtils.isBlank(row.getCell(1).getStringCellValue())){
				continue;
			}
			PreparedStatement statement = connection.prepareStatement("insert into T_QUYU2 (C_QUYU,C_ID) values (?,?)");
			statement.setString(1,row.getCell(1).getStringCellValue());
			statement.setInt(2,ind);
			statement.execute();
			if(ind%100==0){
				connection.commit();
				connection.close();
			}
		}
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		connection.commit();
		connection.close();
		
	}
}
