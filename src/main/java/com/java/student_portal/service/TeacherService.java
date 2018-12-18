package com.java.student_portal.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.student_portal.common.ResponseMessage;
import com.java.student_portal.dto.TeacherDto;
import com.java.student_portal.entities.Teacher;
import com.java.student_portal.repository.EmailTypeRepo;
import com.java.student_portal.repository.TeacherRepo;

@Service
public class TeacherService {
	private EmailService emailService;
	private PhoneService phoneService;
	private AddressService addressservice;
	private TeacherRepo teacherRepo;
	
	@Autowired
	public TeacherService(EmailService emailService, PhoneService phoneService, AddressService addressservice, EmailTypeRepo emailTypeRepo,TeacherRepo teacherRepo) {

		this.emailService = emailService;
		this.phoneService = phoneService;
		this.addressservice = addressservice;
		this.teacherRepo = teacherRepo;
	}
	public ResponseMessage addTeacher(List<TeacherDto> teacherDtoList) {
		for(TeacherDto teacherDto : teacherDtoList) {
			Teacher teacher = new Teacher();
			teacher.setName(teacherDto.getName());
			teacher.setDob(teacherDto.getDob());
			teacher.setEmailList(emailService.transformEmail(teacherDto.getEmailList(), teacherDto.getTeacherCode()));
			teacher.setPhoneList(phoneService.transformPhone(teacherDto.getPhoneList(), teacherDto.getTeacherCode()));
			teacher.setAddressList(addressservice.transformAddress(teacherDto.getAddressList(), teacherDto.getTeacherCode()));
			teacherRepo.saveAndFlush(teacher);
		}
		return ResponseMessage.SUCCESS;
	}

}
