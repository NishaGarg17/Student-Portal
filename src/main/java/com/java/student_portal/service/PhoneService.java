package com.java.student_portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.student_portal.dto.PhoneDto;
import com.java.student_portal.dto.PhoneTypeDto;
import com.java.student_portal.dto.StudentDto;
import com.java.student_portal.entities.Phone;
import com.java.student_portal.entities.PhoneType;
import com.java.student_portal.entities.Student;
import com.java.student_portal.entities.Teacher;
import com.java.student_portal.repository.PhoneTypeRepo;
import com.java.student_portal.repository.StudentRepo;
import com.java.student_portal.repository.TeacherRepo;

import lombok.NonNull;

@Service
public class PhoneService {
	private StudentRepo studentRepo;
	private PhoneTypeRepo phoneTypeRepo;
	private TeacherRepo teacherRepo;
	
	public PhoneService(@NonNull final StudentRepo studentRepo, @NonNull final PhoneTypeRepo phoneTypeRepo, @NonNull final TeacherRepo teacherRepo) {
		this.studentRepo = studentRepo;
		this.phoneTypeRepo = phoneTypeRepo;
		this.teacherRepo = teacherRepo;
	}
	
	 List<PhoneDto> transformPhoneEntity(List<Phone> phoneEntityList) {
		List<PhoneDto> pdtoList = new ArrayList<>();
		for(Phone phoneEntity: phoneEntityList) {
			PhoneDto pdto = new PhoneDto();
			pdto.setPhoneNo(phoneEntity.getPhoneNumber());
			pdto.setPrimary(phoneEntity.getIsPrimary());
			pdto.setPhoneTypeCode(phoneEntity.getPhoneType().getCode());
			pdtoList.add(pdto);
		}
		return pdtoList;
	}
	 
	 List<Phone> transformPhone(List<PhoneDto> pdtoList,String uniqueKey) {
		 List<Phone> phoneList = new ArrayList();
		 if(uniqueKey.matches("[0-9]+")) {
			phoneList = transformStudentPhone(pdtoList, uniqueKey);
		} else if(uniqueKey.matches("[A-Za-z0-9]+")) {
			phoneList = transformTeacherPhone(pdtoList, uniqueKey);
		}
		 return phoneList;
	}
	List<Phone> transformStudentPhone(List<PhoneDto> pdtoList,String stuRollNo) {
	 		List<Phone> phoneList = new ArrayList<>();
			pdtoList.forEach (pdto-> {
				Phone phone = new Phone();
				phone.setPhoneNumber(pdto.getPhoneNo());
				phone.setIsPrimary(pdto.getIsPrimary());
				
				PhoneType phoneType = phoneTypeRepo.findOneByCode(pdto.getPhoneTypeCode());

				phone.setPhoneType(phoneType);
				System.out.println("Phone Primary Or Not: " + pdto.getIsPrimary());
				Student student = studentRepo.findStudentByRollNo(stuRollNo);
				List<Phone> stuPhoneList = new ArrayList<Phone>();
				if(student != null) {
					stuPhoneList = student.getPhones();
				}
					if(stuPhoneList.size() == 0 && phoneList.size() == 0) {
					phone.setIsPrimary(pdto.getIsPrimary());
				} else if(stuPhoneList.size() == 0 && phoneList.size() > 0) {
					phone.setIsPrimary(pdto.getIsPrimary() == true ? expiresPreviousAssociateNew(phoneList) : false);
				}
				else if(stuPhoneList.size() > 0 && phoneList.size() == 0) {
					phone.setIsPrimary(pdto.getIsPrimary() == true ? expiresPreviousAssociateNew(stuPhoneList) : false);
				} else if(stuPhoneList.size() > 0  && phoneList.size() > 0) {
					List<Phone> tempList = new ArrayList<>();
					tempList.addAll(stuPhoneList);
					tempList.addAll(phoneList);
					phone.setIsPrimary(pdto.getIsPrimary() == true ? expiresPreviousAssociateNew(tempList) : false);
				}
					phoneList.add(phone);
			});
			return phoneList;
	 	}
		List<Phone> transformTeacherPhone(List<PhoneDto> pdtoList,String teacherCode) {
			List<Phone> phoneList = new ArrayList<>();
			pdtoList.forEach (pdto-> {
				Phone phone = new Phone();
				phone.setPhoneNumber(pdto.getPhoneNo());
				phone.setIsPrimary(pdto.getIsPrimary());
				
				PhoneType phoneType = phoneTypeRepo.findOneByCode(pdto.getPhoneTypeCode());

				phone.setPhoneType(phoneType);
				System.out.println("Phone Primary Or Not: " + pdto.getIsPrimary());
				Teacher teacher = teacherRepo.findOneByTeacherCode(teacherCode);
				List<Phone> teacherPhoneList = new ArrayList<Phone>();
				if(teacher != null) {
					teacherPhoneList = teacher.getPhoneList();
				}
					if(teacherPhoneList.size() == 0 && phoneList.size() == 0) {
					phone.setIsPrimary(pdto.getIsPrimary());
				} else if(teacherPhoneList.size() == 0 && phoneList.size() > 0) {
					phone.setIsPrimary(pdto.getIsPrimary() == true ? expiresPreviousAssociateNew(phoneList) : false);
				}
				else if(teacherPhoneList.size() > 0 && phoneList.size() == 0) {
					phone.setIsPrimary(pdto.getIsPrimary() == true ? expiresPreviousAssociateNew(teacherPhoneList) : false);
				} else if(teacherPhoneList.size() > 0  && phoneList.size() > 0) {
					List<Phone> tempList = new ArrayList<>();
					tempList.addAll(teacherPhoneList);
					tempList.addAll(phoneList);
					phone.setIsPrimary(pdto.getIsPrimary() == true ? expiresPreviousAssociateNew(tempList) : false);
				}
					phoneList.add(phone);
			});
			return phoneList;
		}
	    boolean expiresPreviousAssociateNew(List<Phone> phoneList) {
			System.out.println("inside expiresPreviousAssociateNew Phone");
			int i = 1;
		    for(Phone phone: phoneList) {
					if(phone.getIsPrimary() == true) {
						phone.setIsPrimary(false);
						System.out.println("i  is : " + i++);
					}
				}
				return true;
			}

		public String createPhoneType(@NonNull final PhoneTypeDto phoneTypeDto) {
			PhoneType phoneType = new PhoneType();
			phoneType.setCode(phoneTypeDto.getCode());
			phoneType.setDisplayName(phoneTypeDto.getDisplayName());
			phoneType.setIsActive(phoneTypeDto.getIsActive());
			phoneTypeRepo.saveAndFlush(phoneType);
			return "PhoneType Created Successfully";
		}

		public List<Phone> updateStudentPhone(StudentDto studentDto){
			List<Phone> phoneList = new ArrayList<Phone>();
			if(studentDto.getPhone() != null) {
				phoneList = transformPhone(studentDto.getPhone(), studentDto.getRollNo());
			}
			return phoneList;
		}
}
