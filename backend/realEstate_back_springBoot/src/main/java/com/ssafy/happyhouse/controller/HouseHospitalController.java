package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.service.HospitalService;
import com.ssafy.happyhouse.model.service.HospitalServiceImpl;

@Controller
@RequestMapping("/hospital")
public class HouseHospitalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private HospitalService hospitalService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<HospitalDto> list;
		try {
			list = hospitalService.listHospital();
			model.addAttribute("hospitals",list);
			return "safeHospital";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg","병원목록을 얻어오는 중 문제가 발생했습니다.");
			return "Error";
		}
	}
	
}
