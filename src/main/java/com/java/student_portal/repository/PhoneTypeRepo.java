package com.java.student_portal.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.student_portal.entities.PhoneType;

@Repository
public interface PhoneTypeRepo extends JpaRepository<PhoneType, Serializable> {
	PhoneType findOneByCode(String phoneTypeCode);
}
