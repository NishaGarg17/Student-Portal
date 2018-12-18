package com.java.student_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.java.student_portal.entities.AddressType;


@Repository
public interface AddressTypeRepo extends JpaRepository<AddressType, Long> {

	AddressType findOneByCode(String addressTypeCode);
	//List<AddressType> findAllByAddressTypeCode(String code);
}
