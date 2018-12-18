package com.java.student_portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.student_portal.dto.EmailDto;
import com.java.student_portal.dto.EmailTypeDto;
import com.java.student_portal.dto.StudentDto;
import com.java.student_portal.entities.Email;
import com.java.student_portal.entities.EmailType;
import com.java.student_portal.entities.Student;
import com.java.student_portal.entities.Teacher;
import com.java.student_portal.repository.EmailTypeRepo;
import com.java.student_portal.repository.StudentRepo;
import com.java.student_portal.repository.TeacherRepo;

import lombok.NonNull;

@Service
public class EmailService {
	private StudentRepo studentRepo;
	private EmailTypeRepo emailTypeRepo;
	private TeacherRepo teacherRepo;
	
	public EmailService(@NonNull final StudentRepo studentRepo, @NonNull final EmailTypeRepo emailTypeRepo,@NonNull final TeacherRepo teacherRepo) {
		this.studentRepo = studentRepo;
		this.emailTypeRepo = emailTypeRepo;
		this.teacherRepo = teacherRepo;
	}
	
	 List<EmailDto> transformEmailEntity(List<Email> emailEntityList) {
		List<EmailDto> edtoList = new ArrayList<>();
		for(Email emailEntity: emailEntityList) {
			EmailDto edto = new EmailDto();
			edto.setEmail(emailEntity.getEmail());
			edto.setIsPrimary(emailEntity.getIsPrimary());
			edto.setEmailTypeCode(emailEntity.getEmailType().getCode());
			edtoList.add(edto);
		}
		return edtoList;
	}
	 List<Email> transformEmail(List<EmailDto> edtoList,String uniqueKey) {
		List emailList = new ArrayList();
		if(uniqueKey.matches("[0-9]")) {
			emailList =  transformStudentEmail(edtoList, uniqueKey);
		} else if(uniqueKey.matches("[0-9a-zA-Z]")) {
			 emailList =  transformTeacherEmail(edtoList, uniqueKey);
		} 
		return emailList;
			

		}
	 	List<Email> transformStudentEmail(List<EmailDto> edtoList,String stuRollNo) {
			List<Email> emailList = new ArrayList<>();
			edtoList.forEach (edto-> {
				Email email = new Email();
				email.setEmail(edto.getEmail());
				
				EmailType emailType = emailTypeRepo.findOneByCode(edto.getEmailTypeCode());

				email.setEmailType(emailType);
				System.out.println("Email Primary Or Not: " + edto.getIsPrimary());
				Student student = studentRepo.findStudentByRollNo(stuRollNo);
				List<Email> stuEmailList = new ArrayList<Email>();
				if(student != null) {
					stuEmailList = student.getEmails();
				}
					if(stuEmailList.size() == 0 && emailList.size() == 0) {
					email.setIsPrimary(edto.getIsPrimary());
				} else if(stuEmailList.size() == 0 && emailList.size() > 0) {
					email.setIsPrimary(edto.getIsPrimary() == true ? expiresPreviousAssociateNew(emailList) : false);
				}
				else if(stuEmailList.size() > 0 && emailList.size() == 0) {
					email.setIsPrimary(edto.getIsPrimary() == true ? expiresPreviousAssociateNew(stuEmailList) : false);
				} else if(stuEmailList.size() > 0  && emailList.size() > 0) {
					List<Email> tempList = new ArrayList<>();
					tempList.addAll(stuEmailList);
					tempList.addAll(emailList);
					email.setIsPrimary(edto.getIsPrimary() == true ? expiresPreviousAssociateNew(tempList) : false);
				}
					emailList.add(email);
			});
			return emailList;
		 
	 	}
		List<Email> transformTeacherEmail(List<EmailDto> edtoList,String teacherCode) {
			List<Email> emailList = new ArrayList<>();
			edtoList.forEach (edto-> {
				Email email = new Email();
				email.setEmail(edto.getEmail());
				
				EmailType emailType = emailTypeRepo.findOneByCode(edto.getEmailTypeCode());

				email.setEmailType(emailType);
				System.out.println("Teacher Email Primary Or Not: " + edto.getIsPrimary());
				Teacher teacher = teacherRepo.findOneByTeacherCode(teacherCode);
				List<Email> teacherEmailList = new ArrayList<Email>();
				if(teacher != null) {
					teacherEmailList = teacher.getEmailList();
				}
					if(teacherEmailList.size() == 0 && emailList.size() == 0) {
					email.setIsPrimary(edto.getIsPrimary());
				} else if(teacherEmailList.size() == 0 && emailList.size() > 0) {
					email.setIsPrimary(edto.getIsPrimary() == true ? expiresPreviousAssociateNew(emailList) : false);
				}
				else if(teacherEmailList.size() > 0 && emailList.size() == 0) {
					email.setIsPrimary(edto.getIsPrimary() == true ? expiresPreviousAssociateNew(teacherEmailList) : false);
				} else if(teacherEmailList.size() > 0  && emailList.size() > 0) {
					List<Email> tempList = new ArrayList<>();
					tempList.addAll(teacherEmailList);
					tempList.addAll(emailList);
					email.setIsPrimary(edto.getIsPrimary() == true ? expiresPreviousAssociateNew(tempList) : false);
				}
					emailList.add(email);
			});
			return emailList;
		 
	 	}
		
	    boolean expiresPreviousAssociateNew(List<Email> emailList) {
			System.out.println("inside expiresPreviousAssociateNew Email");
			int i = 1;

			for(Email email: emailList) {
					if(email.getIsPrimary() == true) {
						email.setIsPrimary(false);
						System.out.println("i  is : " + i++);
					}
				}
				return true;
			}

		public String createEmailType(@NonNull final EmailTypeDto emailTypeDto) {
			EmailType emailType = new EmailType();
			emailType.setCode(emailTypeDto.getCode());
			emailType.setDisplayName(emailTypeDto.getDisplayName());
			emailType.setIsActive(emailTypeDto.getIsActive());
			emailTypeRepo.saveAndFlush(emailType);
			emailTypeRepo.saveAndFlush(emailType);
			return "EmailType Created Successfully";
		}

		public List<Email> updateStudentEmail(StudentDto studentDto) {
			List<Email> emailList = new ArrayList<Email>();
			if(studentDto.getEmail() != null) {
				emailList = transformEmail(studentDto.getEmail(), studentDto.getRollNo());
			}
			return emailList;
		}	
		
		
}
