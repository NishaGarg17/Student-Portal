package com.java.student_portal.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.student_portal.entities.Subject;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Serializable> {
	Subject findOneBySubjectName(String subjectName);
}
