package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.Board;
import com.ssafy.happyhouse.model.ClinicDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.villaDto;
import com.ssafy.happyhouse.model.service.HouseMapService;
import com.ssafy.happyhouse.model.service.HouseMapServiceImpl;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/map")
public class HouseMapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private HouseMapService houseMapService;

	
	
	@GetMapping
	public ResponseEntity<List<SidoGugunCodeDto>> getSido() throws Exception {
		logger.debug("getSido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun/{sidoCode}")
	public ResponseEntity<List<SidoGugunCodeDto>> getGugunInSido(@PathVariable String sidoCode) throws Exception {
		logger.debug("getGugunInSido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getGugunInSido(sidoCode), HttpStatus.OK);
	}
	
	@GetMapping("/dong/{gugunCode}")
	public ResponseEntity<List<HouseInfoDto>> getDongInGugun(@PathVariable String gugunCode) throws Exception {
		logger.debug("getDongInGugun - 호출");
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getDongInGugun(gugunCode), HttpStatus.OK);
	}
	
	@GetMapping("/apt/{dong}")
	public ResponseEntity<List<HouseInfoDto>> getAptInDong(@PathVariable String dong) throws Exception {
		logger.debug("getAptInDong - 호출");
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	
	@GetMapping("/priceApt/{dong}")  // 추가된 부분!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public ResponseEntity<List<HouseInfoDto>> getAptPriceByDong(@PathVariable String dong) throws Exception {
		logger.debug("getAptPriceByDong - 호출");
		System.out.println("가격 By " + dong + "!!!!!!!!!!!!!" );
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getAptPriceByDong(dong), HttpStatus.OK);
	}
	
	@GetMapping("/deal/{price}")
	public ResponseEntity<List<HouseInfoDto>> getAptPrice(@PathVariable int price) throws Exception {
		logger.debug("getAptPrice - 호출");
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getAptPrice(price), HttpStatus.OK);
	}
	
	@GetMapping("/rent/{price}")
	public ResponseEntity<List<HouseInfoDto>> getRentPrice(@PathVariable int price) throws Exception {
		logger.debug("getRentPrice - 호출");
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getRentPrice(price), HttpStatus.OK);
	}
	
	@GetMapping("/deal2/{price}")
	public ResponseEntity<List<villaDto>> getHousePrice(@PathVariable int price) throws Exception {
		logger.debug("getHousePrice - 호출");
		return new ResponseEntity<List<villaDto>>(houseMapService.getHousePrice(price), HttpStatus.OK);
	}
	
	@GetMapping("/rent2/{price}")
	public ResponseEntity<List<villaDto>> getHouseRentPrice(@PathVariable int price) throws Exception {
		logger.debug("getHouseRentPrice - 호출");
		return new ResponseEntity<List<villaDto>>(houseMapService.getHouseRentPrice(price), HttpStatus.OK);
	}
	/*
	 * protected void process(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * response.setCharacterEncoding("UTF-8");
	 */
	
//	@GetMapping
//	public void map(@RequestParam(value="act", required = true)String act,
//					@RequestParam(value="sido", required=false, defaultValue="") String sido,
//					@RequestParam(value="gugun", required = false, defaultValue="") String gugun,
//					@RequestParam(value="dong", required = false, defaultValue="") String dong,
//					@RequestParam(value="rent", required = false, defaultValue="") String rent,
//					@RequestParam(value="deal", required = false, defaultValue="") String deal,
//					@RequestParam(value="clinic", required = false, defaultValue="") String clinic,
//					HttpServletResponse response) throws IOException {
//		response.setCharacterEncoding("UTF-8");
//		if("sido".equals(act)) {
//			PrintWriter out = response.getWriter();
//			List<SidoGugunCodeDto> list = null;
//			JSONArray arr = new JSONArray();
//			try {
//				list = houseMapService.getSido();
//				for(SidoGugunCodeDto dto : list) {
//					JSONObject obj = new JSONObject();
//					obj.put("sido_code", dto.getSidoCode());  // {'sido_code':'11'}
//					obj.put("sido_name", dto.getSidoName());  // {'sido_code':'11', 'sido_name': '서울'}
//					arr.add(obj);  // [{'sido_code':'11', 'sido_name': '서울'}, {'sido_code':'11', 'sido_name': '서울'}, ...]
//				}
//			} catch (Exception e) {
//				arr = new JSONArray();
//				JSONObject obj = new JSONObject();
//				obj.put("message_code", "-1");
//				arr.add(obj);
//				e.printStackTrace();
//			} finally {
//				out.print(arr.toJSONString());
//				out.close();
//			}
//		}//sido
//		else if("gugun".equals(act)) {
//			PrintWriter out = response.getWriter();
//			List<SidoGugunCodeDto> list = null;
//			JSONArray arr = new JSONArray();
//			try {
//				list = houseMapService.getGugunInSido(sido);
//				for(SidoGugunCodeDto dto : list) {
//					JSONObject obj = new JSONObject();
//					obj.put("gugun_code", dto.getGugunCode());
//					obj.put("gugun_name", dto.getGugunName());
//					arr.add(obj);
//				}
//			} catch (Exception e) {
//				arr = new JSONArray();
//				JSONObject obj = new JSONObject();
//				obj.put("message_code", "-1");
//				arr.add(obj);
//				e.printStackTrace();
//			} finally {
//				out.print(arr.toJSONString());
//				out.close();
//			}
//		}//gugun
//		else if("dong".equals(act)) {
//			PrintWriter out = response.getWriter();
//			List<HouseInfoDto> list = null;
//			JSONArray arr = new JSONArray();
//			try {
//				list = houseMapService.getDongInGugun(gugun);
//				for(HouseInfoDto dto : list) {
//					JSONObject obj = new JSONObject();
//					obj.put("code", dto.getCode());
//					obj.put("dong", dto.getDong());
//					arr.add(obj);
//				}
//			} catch (Exception e) {
//				arr = new JSONArray();
//				JSONObject obj = new JSONObject();
//				obj.put("message_code", "-1");
//				arr.add(obj);
//				e.printStackTrace();
//			} finally {
//				out.print(arr.toJSONString());
//				out.close();
//			}
//		}//dong
//		else if("apt".equals(act)) {
//			String tmpStr = dong;
//			String[] tmpArr = null;
//			String dong1 = null;
//			String apt = null;
//			if(tmpStr.indexOf(",") != -1) {
//				tmpArr = dong.split(",");
//				dong = tmpArr[0].trim();
//				apt = tmpArr[1].trim();
////				System.out.println(tmpStr);
////				System.out.println(dong);
////				System.out.println(apt);
//				
//			}
//			else {
//				dong=tmpStr;
//			}
//			
//			PrintWriter out = response.getWriter();
//			List<HouseInfoDto> list = null;
//			JSONArray arr = new JSONArray();
//			try {
//				list = houseMapService.getAptInDong(dong);
//				for(HouseInfoDto dto : list) {
//					JSONObject obj = new JSONObject();
//					if(apt == null) {
//						obj.put("no", dto.getNo());
//						obj.put("dong", dto.getDong());
//						obj.put("aptName", dto.getAptName());
//						obj.put("code", dto.getCode());
//						obj.put("jibun", dto.getJibun());
//						arr.add(obj);
//					}
//					else {
//						if(apt .equals(dto.getAptName())) {
//							obj.put("no", dto.getNo());
//							obj.put("dong", dto.getDong());
//							obj.put("aptName", dto.getAptName());
//							obj.put("code", dto.getCode());
//							obj.put("jibun", dto.getJibun());
//							arr.add(obj);
//						}
//					}
//				}
//			} catch (Exception e) {
//				arr = new JSONArray();
//				JSONObject obj = new JSONObject();
//				obj.put("message_code", "-1");
//				arr.add(obj);
//				e.printStackTrace();
//			} finally {
//				out.print(arr.toJSONString());
//				out.close();
//			}
//		}//apt
//		else if("rent".equals(act)) {
//			int price = Integer.parseInt(rent);
//			PrintWriter out = response.getWriter();
//			List<HouseInfoDto> list = null;
//			JSONArray arr = new JSONArray();
//			System.out.println("rent   " + price);
//			try {
//				list = houseMapService.getRentPrice(price * 10000);
//				for(HouseInfoDto dto : list) {
//					JSONObject obj = new JSONObject();
//					obj.put("no", dto.getNo());
//					obj.put("dong", dto.getDong());
//					obj.put("aptName", dto.getAptName());
//					obj.put("dealAmount", dto.getPrice());
//					System.out.println(dto.getPrice() + "1111111111111dsd");
//					obj.put("code", dto.getCode());
//					obj.put("jibun", dto.getJibun());
//					arr.add(obj);
//				}
//			} catch (Exception e) {
//				arr = new JSONArray();
//				JSONObject obj = new JSONObject();
//				obj.put("message_code", "-1");
//				arr.add(obj);
//				e.printStackTrace();
//			} finally {
//				out.print(arr.toJSONString());
//				out.close();
//			}
//			
//		}
//		else if("rent2".equals(act)) {
//			int price = Integer.parseInt(rent);
//			PrintWriter out = response.getWriter();
//			List<villaDto> list = null;
//			JSONArray arr = new JSONArray();
//			System.out.println("rent   " + price);
//			try {
//				list = houseMapService.getHouseRentPrice(price * 1000);
//				for(villaDto dto : list) {
//					JSONObject obj = new JSONObject();
//					obj.put("no", dto.getNo());
//					obj.put("dong", dto.getDong());
//					obj.put("houseName", dto.getAptName());
//					//System.out.println(dto.getName() + "????");
//					obj.put("dealAmount", dto.getDealAmount());
//					obj.put("address", dto.getAddress());
//					obj.put("rent", dto.getRentMoney());
//					arr.add(obj);
//				}
//			} catch (Exception e) {
//				arr = new JSONArray();
//				JSONObject obj = new JSONObject();
//				obj.put("message_code", "-1");
//				arr.add(obj);
//				e.printStackTrace();
//			} finally {
//				out.print(arr.toJSONString());
//				out.close();
//			}
//			
//		}
//		else if("deal".equals(act)) {
//			int price = Integer.parseInt(deal);
//			PrintWriter out = response.getWriter();
//			List<HouseInfoDto> list = null;
//			JSONArray arr = new JSONArray();
//			//System.out.println(price + "1111111111111111111");
//			try {
//				list = houseMapService.getAptPrice(price);
//				for(HouseInfoDto dto : list) {
//					JSONObject obj = new JSONObject();
//					
//					obj.put("no", dto.getNo());
//					obj.put("dong", dto.getDong());
//					obj.put("aptName", dto.getAptName());
//					obj.put("dealAmount", dto.getPrice());
//					obj.put("code", dto.getCode());
//					obj.put("jibun", dto.getJibun());
//					arr.add(obj);
//				}
//			} catch (Exception e) {
//				arr = new JSONArray();
//				JSONObject obj = new JSONObject();
//				obj.put("message_code", "-1");
//				arr.add(obj);
//				e.printStackTrace();
//			} finally {
//				out.print(arr.toJSONString());
//				out.close();
//			}
//		}//deal
//		else if("deal2".equals(act)) {
//			int price = Integer.parseInt(deal);
//			PrintWriter out = response.getWriter();
//			List<villaDto> list = null;
//			JSONArray arr = new JSONArray();
//			try {
//				list = houseMapService.getHousePrice(price);
//				for(villaDto dto : list) {
//					JSONObject obj = new JSONObject();
//					
//					obj.put("no", dto.getNo());
//					obj.put("dong", dto.getDong());
//					obj.put("houseName", dto.getAptName());
//					//System.out.println(dto.getName() + "????");
//					obj.put("dealAmount", dto.getDealAmount());
//					obj.put("address", dto.getAddress());
//					obj.put("rent", dto.getRentMoney());
//					arr.add(obj);
//				}
//			} catch (Exception e) {
//				arr = new JSONArray();
//				JSONObject obj = new JSONObject();
//				obj.put("message_code", "-1");
//				arr.add(obj);
//				e.printStackTrace();
//			} finally {
//				out.print(arr.toJSONString());
//				out.close();
//			}
//		}//deal
//		else if("clinic".equals(act)) {
//			PrintWriter out = response.getWriter();
//			List<ClinicDto> list = null;
//			String sido1 = clinic;
//			JSONArray arr = new JSONArray();
//			try {
//				list = houseMapService.getClinic(sido1);
//				for(ClinicDto dto : list) {
//					JSONObject obj = new JSONObject();
//					obj.put("possibility", dto.getPossibility());
//					obj.put("sido", dto.getSido_name());
//					obj.put("gugun", dto.getGugun_name());
//					obj.put("medical", dto.getMedical_name());
//					obj.put("addr", dto.getAddr());
//					obj.put("week", dto.getWeekday());
//					obj.put("phone", dto.getPhone());
//					arr.add(obj);
//				}
//			} catch (Exception e) {
//				arr = new JSONArray();
//				JSONObject obj = new JSONObject();
//				obj.put("message_code", "-1");
//				arr.add(obj);
//				e.printStackTrace();
//			} finally {
//				out.print(arr.toJSONString());
//				out.close();
//			}
//		}
//	}//process

}