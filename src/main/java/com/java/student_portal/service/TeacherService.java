package com.java.student_portal.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.student_portal.common.ResponseMessage;
import com.java.student_portal.dto.SubjectDto;
import com.java.student_portal.dto.TeacherDto;
import com.java.student_portal.entities.Subject;
import com.java.student_portal.entities.Teacher;
import com.java.student_portal.repository.EmailTypeRepo;
import com.java.student_portal.repository.SubjectRepo;
import com.java.student_portal.repository.TeacherRepo;

@Service
public class TeacherService {
	private EmailService emailService;
	private PhoneService phoneService;
	private AddressService addressservice;
	private TeacherRepo teacherRepo;
	private SubjectService subjectService;
	private SubjectRepo subjectRepo;
	
	@Autowired
	public TeacherService(EmailService emailService, PhoneService phoneService, AddressService addressservice,SubjectService subjectService, EmailTypeRepo emailTypeRepo,TeacherRepo teacherRepo, SubjectRepo subjectRepo) {

		this.emailService = emailService;
		this.phoneService = phoneService;
		this.addressservice = addressservice;
		this.subjectService = subjectService;
		this.teacherRepo = teacherRepo;
		this.subjectRepo = subjectRepo;
	}
	public ResponseMessage addTeacher(List<TeacherDto> teacherDtoList) {
		for(TeacherDto teacherDto : teacherDtoList) {
			Teacher teacher = new Teacher();
			teacher.setName(teacherDto.getName());
			teacher.setTeacherCode(teacherDto.getTeacherCode());
			teacher.setDob(teacherDto.getDob());
			teacher.setEmailList(emailService.transformEmail(teacherDto.getEmailList(), teacherDto.getTeacherCode()));
			teacher.setPhoneList(phoneService.transformPhone(teacherDto.getPhoneList(), teacherDto.getTeacherCode()));
			teacher.setAddressList(addressservice.transformAddress(teacherDto.getAddressList(), teacherDto.getTeacherCode()));
			for(SubjectDto subjectDto : teacherDto.getSubjectList()) {
				Subject sub = subjectRepo.findOneBySubjectName(subjectDto.getSubjectName());
				if(!sub.getSemester().equals(subjectDto.getSemester()) || !sub.getCourse().getCode().equals(subjectDto.getCourseCode())
						|| !sub.getDepartment().getCode().equals(subjectDto.getDepartmentCode())) {
					return ResponseMessage.INVALID_SUBJECT_DATA;
				}
			}
			teacher.setSubjectList(subjectService.transformSubjectDtoList(teacherDto.getSubjectList()));
			teacherRepo.saveAndFlush(teacher);
		}
		return ResponseMessage.SUCCESS;
	}
	public List<TeacherDto> findAllTeachers() {
		List<Teacher> teacherList = teacherRepo.findAll();
		List<TeacherDto> teacherDtoList = transformTeacherEntityList(teacherList);
		return teacherDtoList;
	}
	private List<TeacherDto> transformTeacherEntityList(List<Teacher> teacherList) {
		List<TeacherDto> teacherDtoList = new ArrayList<TeacherDto>();
		for(Teacher teacher : teacherList) {
			TeacherDto teacherDto = new TeacherDto();
			teacherDto.setName(teacher.getName());
			teacherDto.setDob(teacher.getDob());
			teacherDto.setTeacherCode(teacher.getTeacherCode());
			teacherDto.setAddressList(addressservice.transformAddressEntity(teacher.getAddressList()));
			teacherDto.setEmailList(emailService.transformEmailEntity(teacher.getEmailList()));
			teacherDto.setPhoneList(phoneService.transformPhoneEntity(teacher.getPhoneList()));
			teacherDto.setSubjectList(subjectService.transformSubjectEntity(teacher.getSubjectList()));
			teacherDtoList.add(teacherDto);
		}
		return teacherDtoList;
	}

}
