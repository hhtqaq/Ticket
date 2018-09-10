package com.ecjtu.rwx.admin.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecjtu.rwx.admin.service.FlightService;
import com.ecjtu.rwx.bean.Airport;
import com.ecjtu.rwx.bean.Airticket;
import com.ecjtu.rwx.bean.City;
import com.ecjtu.rwx.bean.Company;
import com.ecjtu.rwx.bean.Flight;
import com.ecjtu.rwx.dao.AirCompanyDao;
import com.ecjtu.rwx.dao.AirportDao;
import com.ecjtu.rwx.dao.BookingDao;
import com.ecjtu.rwx.dao.CityDao;
import com.ecjtu.rwx.dao.FlightDao;
import com.ecjtu.rwx.dao.impl.AirCompanyDaoImpl;
import com.ecjtu.rwx.dao.impl.AirportDaoImpl;
import com.ecjtu.rwx.dao.impl.BookingDaoImpl;
import com.ecjtu.rwx.dao.impl.CityDaoImpl;
import com.ecjtu.rwx.dao.impl.FlightDaoImpl;
import com.ecjtu.rwx.dbutils.PageBean;
import com.google.gson.Gson;

public class FlightServiceImpl implements FlightService{

	protected FlightDao flightDao=new FlightDaoImpl();
	protected AirportDao airportDao=new AirportDaoImpl();
	protected CityDao cityDao=new CityDaoImpl();
	protected AirCompanyDao airCompanyDao=new AirCompanyDaoImpl();
	private BookingDao bookingdao=new BookingDaoImpl();
	@Override
	public void listAllFlight(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String condition=req.getParameter("condition");
		String page=req.getParameter("currentPage");
		int currentPage=1;
		if(page!=null&&!"".equals(page)){
			currentPage=Integer.parseInt(page);
		}
		if(condition==null){
			condition="";
		}
		List<Flight> findAll=flightDao.findAllFlight(condition);
		if(findAll!=null){
			for (Flight flight : findAll) {
				int id = flight.getId();
				List<Airticket> tickets = bookingdao.findTicketById(id);
				if(tickets!=null){
				for (Airticket airticket : tickets) {
					if(("头等舱").equals(airticket.getSeattype())){
						flight.setFirstprice(airticket.getPrice());
						continue;
					}else if("商务舱".equals(airticket.getSeattype())){
						flight.setSecondprice(airticket.getPrice());
						continue;
					}else{
						flight.setThirdprice(airticket.getPrice());
						continue;
					}
				}
			}
				}
		}
		if (findAll.size()==0) {
			req.setAttribute("msg", "暂无符合条件的航班信息，请重新搜索");
		}else{
		//分页
		PageBean<Flight> bean=new PageBean<Flight>(currentPage,2,findAll.size());
		bean.setData(findAll);
		req.setAttribute("flights", bean);
		}
		req.setAttribute("condition", condition);
		req.getRequestDispatcher("/WEB-INF/adminpages/flight/listFlight.jsp").forward(req, resp);
	}
	
	
	@Override
	public void AddOrEditFlightUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String flightid=req.getParameter("id");
		if(flightid!=null&&!"".equals(flightid)){
			Flight flight=flightDao.findFlightById(Integer.parseInt(flightid));
			String starttime = flight.getStarttime();
			String endtime = flight.getEndtime();
			//日期格式回显 起飞时间 yyyy-MM-dd HH:mm  拆分成yyyy-MM-dd  HH mm
			flight.setStarttime(starttime.split(" ")[0]);
			String startHour=starttime.split(" ")[1].split(":")[0];
			String startMin=starttime.split(" ")[1].split(":")[1];
			//日期格式回显 到达时间 yyyy-MM-dd HH:mm  拆分成yyyy-MM-dd  HH mm
			flight.setEndtime(endtime.split(" ")[0]);
			String endHour=endtime.split(" ")[1].split(":")[0];
			String endMin=endtime.split(" ")[1].split(":")[1];
			
			//票价
			List<Airticket> tickets = bookingdao.findTicketById(Integer.parseInt(flightid));
			for (Airticket airticket : tickets) {
				if(("头等舱").equals(airticket.getSeattype())){
					flight.setFirstprice(airticket.getPrice());
					continue;
				}else if("商务舱".equals(airticket.getSeattype())){
					flight.setSecondprice(airticket.getPrice());
					continue;
				}else{
					flight.setThirdprice(airticket.getPrice());
					continue;
				}
			}
			req.setAttribute("flight", flight);
			req.setAttribute("startHour", startHour);
			req.setAttribute("startMin", startMin);
			req.setAttribute("endHour", endHour);
			req.setAttribute("endMin", endMin);
			
		}
		req.getRequestDispatcher("/WEB-INF/adminpages/flight/addOrEditFlight.jsp").forward(req, resp);
	}
	
	
	@Override
	public void AddOrEditFlight(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数id  如果有值那么就是修改   如果为空那么就是更新
		String id=req.getParameter("id");
		//获取其他参数
		TreeMap<String, String> tree=new TreeMap<String, String>();
		Enumeration<String> parameterNames = req.getParameterNames();
		while(parameterNames.hasMoreElements()){
			String name=parameterNames.nextElement();
			String value=req.getParameter(name);
			tree.put(name, value);
		}
		System.out.println(parameterNames);
		Flight flight=new Flight();
		flight.setAircompany((String) tree.get("aircompany"));
		if(tree.get("id")!=null&&!"".equals(tree.get("id"))){
		flight.setId(Integer.parseInt(tree.get("id")));
		}
		flight.setAirname(tree.get("airname"));
		flight.setAirtype(tree.get("airtype"));
		flight.setStartcity(tree.get("startcity"));
		flight.setStartairport(tree.get("startairport"));
		String starthour=tree.get("starthour");
		
		String satrtmin=tree.get("satrtmin");
		flight.setStarttime(tree.get("starttime")+" "+(starthour.length()<2?("0"+starthour):starthour)+":"+(satrtmin.length()<2?("0"+satrtmin):satrtmin));
		flight.setEndcity(tree.get("endcity"));
		flight.setEndairport(tree.get("endairport"));

		String endhour=tree.get("endhour");
		String endmin=tree.get("endmin");
		flight.setEndtime(tree.get("endtime")+" "+(endhour.length()<2?("0"+endhour):endhour)+":"+(endmin.length()<2?("0"+endmin):endmin));
		try{
		flight.setFirstclassnum(Integer.parseInt(tree.get("firstclassnum")));
		flight.setSecondclassnum(Integer.parseInt(tree.get("secondclassnum")));
		flight.setThirdclassnum(Integer.parseInt(tree.get("thirdclassnum")));
		flight.setFirstprice(Float.parseFloat(tree.get("firstprice")));
		flight.setSecondprice(Float.parseFloat(tree.get("secondprice")));
		flight.setThirdprice(Float.parseFloat(tree.get("thirdprice")));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		boolean flag=flightDao.saveOrupdate(flight);
		//跟新或者插入失败
		if(flag==false){
			req.setAttribute("msg", "更新或者插入失败");
			req.getRequestDispatcher("/admin/flight?method=addOrEditFlightUI&id="+id);
		}else{
		req.getRequestDispatcher("/admin/flight?method=listFlight").forward(req, resp);
		}
	}


	@Override
	public void FindAllCompany(HttpServletRequest req, HttpServletResponse resp) {
		String condition=req.getParameter("condition");
		if(condition==null||"".equals(condition))
		{
			condition="";
		}
		try {
			PrintWriter out=resp.getWriter();
			List<Company> companys=flightDao.findAllCompany(condition);
			Gson gson=new Gson();
			String json = gson.toJson(companys);
			out.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}


	@Override
	public void findAllAirport(HttpServletRequest req, HttpServletResponse resp) {
		try {
			PrintWriter out=resp.getWriter();
			List<Airport> airports=airportDao.findAllAirport();
			Gson gson=new Gson();
			String json = gson.toJson(airports);
			out.print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void findAllCity(HttpServletRequest req, HttpServletResponse resp) {
		String condition=req.getParameter("condition");
		if(condition==null||"".equals(condition))
		{
			condition="";
		}
		List<City> citys=cityDao.findAllCity(condition);
		req.setAttribute("citys", citys);
		try {
			req.getRequestDispatcher("/WEB-INF/adminpages/flight/listCity.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void ListAirCompany(HttpServletRequest req, HttpServletResponse resp) {
		String condition=req.getParameter("condition");
		if(condition==null||"".equals(condition))
		{
			condition="";
		}
		List<Company> companys = flightDao.findAllCompany(condition);
		req.setAttribute("companys", companys);
		try {
			req.getRequestDispatcher("/WEB-INF/adminpages/flight/listCompany.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void AddCity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out =resp.getWriter();
		String cityname=req.getParameter("name");
		cityDao.addCity(cityname);
		out.print("更新成功");
	}


	@Override
	public void AddCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		PrintWriter out =resp.getWriter();
		String companyname=req.getParameter("name");
		airCompanyDao.addCompany(companyname);
		out.print("更新成功");
	}

}
