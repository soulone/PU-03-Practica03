package com.tecsup.petclinic.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
	
	// Fetch owners by firstName
		List<Owner> findByFirstName(String first_name);

		// Fetch owners by lastName
		List<Owner> findByLastName(String last_name);

		// Fetch owners by city
		List<Owner> findByCity(String city);
}
