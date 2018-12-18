package com.java.student_portal.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.student_portal.common.ResponseMessage;
import com.java.student_portal.dto.StudentDto;
import com.java.student_portal.entities.Address;
import com.java.student_portal.entities.Course;
import com.java.student_portal.entities.Department;
import com.java.student_portal.entities.Email;
import com.java.student_portal.entities.Phone;
import com.java.student_portal.entities.Student;
import com.java.student_portal.repository.CourseRepo;
import com.java.student_portal.repository.DepartmentRepo;
import com.java.student_portal.repository.StudentRepo;

import lombok.NonNull;

@Service
public class StudentService {
	
	private StudentRepo studentRepo;
	private DepartmentRepo deptRepo;
	private CourseRepo courseRepo;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	PhoneService phoneService;
	
	@Autowired
	public StudentService(@NonNull final StudentRepo studentRepo, @NonNull final DepartmentRepo deptRepo, @NonNull final CourseRepo courseRepo) {
		this.studentRepo = studentRepo;
		this.deptRepo = deptRepo;
		this.courseRepo = courseRepo;
	}

	public List<StudentDto> findAllStudents(){
		List<Student> studentEntityList =  studentRepo.findAll();
		List<StudentDto> studentDtoList =  transformStudentEntityList(studentEntityList);
		return studentDtoList;
	}
	
	private List<StudentDto> transformStudentEntityList(List<Student> studentEntityList) {
		List<StudentDto> studentDtoList = new ArrayList<>();
		studentEntityList.forEach(studentEntity -> {
			studentDtoList.add(transformStudent(studentEntity));	
		}
		);
		return studentDtoList;
	}
	
	private StudentDto transformStudent(Student studentEntity) {
		StudentDto sdto = new StudentDto();
		sdto.setName(studentEntity.getName());
		sdto.setEmail(emailService.transformEmailEntity(studentEntity.getEmails()));
		sdto.setPhone(phoneService.transformPhoneEntity(studentEntity.getPhones()));
		sdto.setRollNo(studentEntity.getRollNo());
		sdto.setAddress(addressService.transformAddressEntity(studentEntity.getAddresses()));
		sdto.setDepartmentCode(studentEntity.getDepartment().getCode());
		sdto.setCourseCode(studentEntity.getCourse().getCode());
		sdto.setJoiningDate(studentEntity.getJoiningDate().toString());
		sdto.setSemester(studentEntity.getSemester());
		return sdto;
	}

	//code to create new student
	public ResponseMessage createStudent(@NonNull final StudentDto sdto) {
		Student student =  new Student();
		student.setName(sdto.getName());
		student.setDob(sdto.getDob());
		student.setEmail(emailService.transformEmail(sdto.getEmail(), sdto.getRollNo()));
		student.setPhones(phoneService.transformPhone(sdto.getPhone(), sdto.getRollNo()));
		student.setRollNo(sdto.getRollNo());
		student.setAddresses(addressService.transformAddress(sdto.getAddress(),sdto.getRollNo()));
		Course course = courseRepo.findOneByCode(sdto.getCourseCode());
		student.setCourse(course);
		Department dept = deptRepo.findOneByCode(sdto.getDepartmentCode());
		if(!dept.getCourse().contains(course)) {
			System.out.println("inside ERROR Validation");
			return ResponseMessage.VALIDATION_ERROR;
		}
		student.setDepartment(dept);
		student.setJoiningDate(sdto.getJoiningDate());
		if(sdto.getSemester() > 0 && sdto.getSemester() <= 8 && student.getCourse().getCode().equals("BTECH")) {
			student.setSemester(sdto.getSemester());
		} else if(sdto.getSemester() > 0 && sdto.getSemester() <= 4 && student.getCourse().getCode().equals("MTECH")) {
			student.setSemester(sdto.getSemester());
		} else {
			return ResponseMessage.VALIDATION_ERROR;
		}
		studentRepo.saveAndFlush(student);
		return ResponseMessage.SUCCESS;
	}
	//code to update existing student
	public ResponseMessage updateStudent(StudentDto studentDto) {
		
		Student student = studentRepo.findStudentByRollNo(studentDto.getRollNo());
		if(student == null) {
			return ResponseMessage.STUDENT_NOT_FOUND;
		}
		if(!studentDto.getDepartmentCode().equals(student.getDepartment().getCode())) {
			student.setDepartment(deptRepo.findOneByCode(studentDto.getDepartmentCode()));
		}
		List<Address> addressList = addressService.updateStudentAddress(studentDto);
		addressList.addAll(addressService.updateStudentAddress(studentDto));
		List<Phone> phoneList = student.getPhones();
	    phoneList.addAll(phoneService.updateStudentPhone(studentDto));
		List<Email> emailList = student.getEmails();
		emailList.addAll(emailService.updateStudentEmail(studentDto));
		student.setDob(studentDto.getDob());
		student.setAddresses(addressList);
		student.setPhones(phoneList);
		student.setEmail(emailList);
		student.setSemester(studentDto.getSemester());
		studentRepo.saveAndFlush(student);
		return ResponseMessage.SUCCESS;
	}
		
}
