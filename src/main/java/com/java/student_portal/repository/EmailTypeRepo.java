package com.java.student_portal.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.student_portal.entities.EmailType;

@Repository
public interface EmailTypeRepo extends JpaRepository<EmailType, Serializable>{
	EmailType findOneByCode(String emailTypeCode);
}
