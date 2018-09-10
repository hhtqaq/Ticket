package com.ecjtu.rwx.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.ecjtu.rwx.bean.City;
import com.ecjtu.rwx.dao.BookingDao;
import com.ecjtu.rwx.dao.impl.BookingDaoImpl;

public class BookingDaoImplTest {

	private BookingDao bookdao=new BookingDaoImpl();
	@Test
	public void testFindCity() {
		List<City> findCity = bookdao.findCity();
		System.out.println(findCity);
	}
	@Test
	public void testdate() {
		String date="2018-05-12 16:20";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date parse = sdf.parse(date);
			System.out.println(parse);
			SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/MM/dd HH:mm");
			String format = sdf2.format(parse);
			System.out.println(format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
