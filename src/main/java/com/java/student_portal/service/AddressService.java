package com.java.student_portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.student_portal.dto.AddressDto;
import com.java.student_portal.dto.AddressTypeDto;
import com.java.student_portal.dto.StudentDto;
import com.java.student_portal.entities.Address;
import com.java.student_portal.entities.AddressType;
import com.java.student_portal.entities.Student;
import com.java.student_portal.entities.Teacher;
import com.java.student_portal.repository.AddressTypeRepo;
import com.java.student_portal.repository.StudentRepo;
import com.java.student_portal.repository.TeacherRepo;

import lombok.NonNull;

@Service
public class AddressService {
	private StudentRepo studentRepo;
	private AddressTypeRepo addTypeRepo;
	private TeacherRepo teacherRepo;
	
	public AddressService(@NonNull final StudentRepo studentRepo, @NonNull final AddressTypeRepo addTypeRepo, @NonNull TeacherRepo teacherRepo) {
		this.studentRepo = studentRepo;
		this.addTypeRepo = addTypeRepo;
		this.teacherRepo = teacherRepo;
	}
	
	 List<AddressDto> transformAddressEntity(List<Address> addressEntityList) {
		List<AddressDto> adtoList = new ArrayList<>();
		for(Address addressEntity: addressEntityList) {
			AddressDto adto = new AddressDto();
			adto.setAddressLine1(addressEntity.getAddressLine1());
			adto.setAddressLine2(addressEntity.getAddressLine2());
			adto.setCountry(addressEntity.getCountry());
			adto.setState(addressEntity.getState());
			adto.setZipCode(addressEntity.getZipCode());
			adto.setCity(addressEntity.getCity());
			adto.setAddressTypeCode(addressEntity.getAddressType().getCode());
			adto.setIsPrimary(addressEntity.getIsPrimary());
			adtoList.add(adto);
		}
		return adtoList;
	}
	 
	  List<Address> transformAddress(List<AddressDto> adtoList,String uniqueKey) {
		  List<Address> addressList = new ArrayList<>();
		  if(uniqueKey.matches("[0-9]")) {
			  addressList = transformStudentAddress(adtoList, uniqueKey);
			} else if(uniqueKey.matches("[A-Za-z0-9]")) {
				addressList = transformTeacherAddress(adtoList, uniqueKey);
			}
			 return addressList;
		}
	  List<Address> transformStudentAddress(List<AddressDto> adtoList,String stuRollNo) {
			
			List<Address> addressList = new ArrayList<>();
			adtoList.forEach (adto-> {
				Address address = new Address();
				address.setAddressLine1(adto.getAddressLine1());
				address.setAddressLine2(adto.getAddressLine2());
				address.setCountry(adto.getCountry());
				address.setState(adto.getState());
				address.setZipCode(adto.getZipCode());
				address.setCity(adto.getCity());
				
				AddressType addressType = addTypeRepo.findOneByCode(adto.getAddressTypeCode());

				address.setAddressType(addressType);
				System.out.println("Adress Primary Or Not: " + adto.getIsPrimary());
				Student student = studentRepo.findStudentByRollNo(stuRollNo);
				List<Address> stuAddressList = new ArrayList<Address>();
				if(student != null) {
					stuAddressList = student.getAddresses();
				}
					if(stuAddressList.size() == 0 && addressList.size() == 0) {
					address.setIsPrimary(adto.getIsPrimary());
				} else if(stuAddressList.size() == 0 && addressList.size() > 0) {
					address.setIsPrimary(adto.getIsPrimary() == true ? expiresPreviousAssociateNew(addressList) : false);
				}
				else if(stuAddressList.size() > 0 && addressList.size() == 0) {
					address.setIsPrimary(adto.getIsPrimary() == true ? expiresPreviousAssociateNew(stuAddressList) : false);
				} else if(stuAddressList.size() > 0  && addressList.size() > 0) {
					List<Address> tempList = new ArrayList<>();
					tempList.addAll(stuAddressList);
					tempList.addAll(addressList);
					address.setIsPrimary(adto.getIsPrimary() == true ? expiresPreviousAssociateNew(tempList) : false);
				}
				addressList.add(address);
			});
			return addressList;
	    }
	  List<Address> transformTeacherAddress(List<AddressDto> adtoList,String teacherCode) {
		  List<Address> addressList = new ArrayList<>();
			adtoList.forEach (adto-> {
				Address address = new Address();
				address.setAddressLine1(adto.getAddressLine1());
				address.setAddressLine2(adto.getAddressLine2());
				address.setCountry(adto.getCountry());
				address.setState(adto.getState());
				address.setZipCode(adto.getZipCode());
				address.setCity(adto.getCity());
				
				AddressType addressType = addTypeRepo.findOneByCode(adto.getAddressTypeCode());

				address.setAddressType(addressType);
				System.out.println("Adress Primary Or Not: " + adto.getIsPrimary());
				Teacher teacher = teacherRepo.findOneByTeacherCode(teacherCode);
				List<Address> teacherAddressList = new ArrayList<Address>();
				if(teacher != null) {
					teacherAddressList = teacher.getAddressList();
				}
					if(teacherAddressList.size() == 0 && addressList.size() == 0) {
					address.setIsPrimary(adto.getIsPrimary());
				} else if(teacherAddressList.size() == 0 && addressList.size() > 0) {
					address.setIsPrimary(adto.getIsPrimary() == true ? expiresPreviousAssociateNew(addressList) : false);
				}
				else if(teacherAddressList.size() > 0 && addressList.size() == 0) {
					address.setIsPrimary(adto.getIsPrimary() == true ? expiresPreviousAssociateNew(teacherAddressList) : false);
				} else if(teacherAddressList.size() > 0  && addressList.size() > 0) {
					List<Address> tempList = new ArrayList<>();
					tempList.addAll(teacherAddressList);
					tempList.addAll(addressList);
					address.setIsPrimary(adto.getIsPrimary() == true ? expiresPreviousAssociateNew(tempList) : false);
				}
				addressList.add(address);
			});
			return addressList;
	  }
	    boolean expiresPreviousAssociateNew(List<Address> addressList) {
			System.out.println("inside expiresPreviousAssociateNew");
			int i = 1;
		    for(Address address: addressList) {
					if(address.getIsPrimary() == true) {
						address.setIsPrimary(false);
						System.out.println("i  is : " + i++);
					}
				}
				return true;
			}

		 public String createAddressType(AddressTypeDto adTypeDto) {
			AddressType addressType = new AddressType();
			addressType.setCode(adTypeDto.getCode());
			addressType.setDisplayName(adTypeDto.getDisplayName());
			addressType.setIsActive(adTypeDto.getIsActive());
			addTypeRepo.saveAndFlush(addressType);
			return "AddressType Record Created";
		}

		public List<Address> updateStudentAddress(StudentDto studentDto) {
			List<Address> addressList = new ArrayList<Address>();
			if(studentDto.getAddress() != null) {
				addressList = transformAddress(studentDto.getAddress(),studentDto.getRollNo()); 
			}
			return addressList;
		}
		
	
}
