package com.java.student_portal.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Required;


@Entity
@Table(name = "S_Student")
public class Student implements Serializable {

	private static final long serialVersionUID = -4225308411818746611L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STUDENT_ID")
	private Long id;
	private String dob; 
	private String name;
	private String joiningDate;
	private int semester;
	
	@Column (unique = true, updatable = false)
	private String rollNo;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "S_STUDENT_EMAIL",
	joinColumns = {@JoinColumn(name = "STUDENT_ID")},
	inverseJoinColumns = {@JoinColumn(name = "EMAIL_ID")})
	private List<Email> emails;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "S_Student_Phone",
	joinColumns = {@JoinColumn(name = "STUDENT_ID")},
	inverseJoinColumns = {@JoinColumn(name = "PHONE_ID")})
	private List<Phone> phones;


	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
	        name = "S_Student_Address",
	        joinColumns = {@JoinColumn(name = "STUDENT_ID")},
	        inverseJoinColumns = {@JoinColumn(name = "ADDRESS_ID")})
	private List<Address> addresses;
	
	
	@ManyToOne
	@JoinColumn(name = "department", referencedColumnName = "code")
	private Department department;
	
	@ManyToOne
	@JoinColumn(name = "course", referencedColumnName = "code")
	private Course course;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRollNo() {
		return rollNo;
	}
	@Required
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
 
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public void setEmail(List<Email> emails) {
		this.emails = emails;
	}
	public List<Email> getEmails() {
		return emails;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	

}
