package com.java.student_portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.student_portal.common.ResponseMessage;
import com.java.student_portal.dto.SubjectDto;
import com.java.student_portal.entities.Department;
import com.java.student_portal.entities.Subject;
import com.java.student_portal.repository.CourseRepo;
import com.java.student_portal.repository.DepartmentRepo;
import com.java.student_portal.repository.SubjectRepo;

import lombok.NonNull;

@Service
public class SubjectService {
	private SubjectRepo subjectRepo;
	private CourseRepo courseRepo;
	private DepartmentRepo departmentRepo;
	
	public SubjectService(@NonNull final SubjectRepo subjectRepo, @NonNull final CourseRepo courseRepo, @NonNull final DepartmentRepo departmentRepo) {
		this.subjectRepo = subjectRepo;
		this.courseRepo = courseRepo;
		this.departmentRepo = departmentRepo;
	}

	public String createSubject(List<SubjectDto> subjectDtoList) {
		List<Subject> subjectList = new ArrayList<Subject>();
		for(SubjectDto subjectDto : subjectDtoList) {
			Subject subject = new Subject();
			subject.setSubjectName(subjectDto.getSubjectName());
			subject.setSemester(subjectDto.getSemester());
			subject.setCourse(courseRepo.findOneByCode(subjectDto.getCourseCode()));
			subject.setDepartment(departmentRepo.findOneByCode(subjectDto.getDepartmentCode()));
			subjectList.add(subject);
		}
		for(Subject subject : subjectList) {
			subjectRepo.saveAndFlush(subject);
		}
		
		return "Subject Data Created Successfully";
	}
	public List<Subject> transformSubjectDtoList(List<SubjectDto> subjectDtoList) {
		List<Subject> subjectList = new ArrayList<Subject>();
		for(SubjectDto subjectDto : subjectDtoList) {
			Subject subject = new Subject();
		    subject.setSubjectName(subjectDto.getSubjectName());
			subject.setSemester(subjectDto.getSemester());
			subject.setCourse(courseRepo.findOneByCode(subjectDto.getCourseCode()));
			subject.setDepartment(departmentRepo.findOneByCode(subjectDto.getDepartmentCode()));
			subjectList.add(subject);
		}
		return subjectList;
		
	}
	
	public List<SubjectDto> transformSubjectEntity(List<Subject> subjectList) {
		List<SubjectDto> subjectDtoList = new ArrayList<SubjectDto>();
		for(Subject subject: subjectList ) {
			SubjectDto subjectDto = new SubjectDto();
			subjectDto.setSubjectName(subject.getSubjectName());
			subjectDto.setSemester(subject.getSemester());
			subjectDto.setCourseCode(subject.getCourse().getCode());
			subjectDto.setDepartmentCode(subject.getDepartment().getCode());
			subjectDtoList.add(subjectDto);
		}
		return subjectDtoList;
	}
}
