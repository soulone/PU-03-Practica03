package com.tecsup.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OwnerServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);
	@Autowired
	private OwnerService OwnerService;

	/**
	 * 
	 */
//	@Test
//	public void testFindOwnerById() {
//
//		long ID = 1;
//		String NAME = "Leo";
//		Owner owner = null;
//		
//		try {
//			owner = OwnerService.findById(ID);
//		} catch (OwnerNotFoundException e) {
//			fail(e.getMessage());
//		}
//		logger.info("" + owner);
//
//		assertEquals(NAME, owner.getFirstName());
//
//	}
	
	@Test
	public void testFindOwnerByFirstName() {

		String FIND_NAME = "Jeff";
		int SIZE_EXPECTED = 1;

		List<Owner> owners = OwnerService.findByFirstName(FIND_NAME);

		assertEquals(SIZE_EXPECTED, owners.size());
	}
	
	@Test
	public void testFindOwnerByLastName() {

		String OWNER_LAST_NAME = "Escobito";
		Owner owner;
		List<Owner> owners = OwnerService.findByLastName(OWNER_LAST_NAME);
		owner = owners.get(0);
		
		assertEquals(OWNER_LAST_NAME, owner.getLastName());
		logger.info("Owner with lastname '" + OWNER_LAST_NAME + "' found.");
	}
	
	@Test
	public void testFindOwnerByCity() {

		String OWNER_CITY = "Monona";
		int SIZE_EXPECTED = 2;
		
		List<Owner> owners = OwnerService.findByCity(OWNER_CITY);
		
		assertEquals(SIZE_EXPECTED, owners.size());
		logger.info("Owner with city '" + OWNER_CITY + "' found.");
	}

	//Primero creo un usuario y luego elimino para no afectar los datos de la BD
	@Test
	public void testDeleteOwner() throws OwnerNotFoundException {
		
		String OWNER_FIRST_NAME = "leandro";
		String OWNER_LAST_NAME = "morocho";
		String OWNER_CITY = "lima";

		Owner nowOwner = new Owner(OWNER_FIRST_NAME, OWNER_LAST_NAME, OWNER_CITY);
		nowOwner = OwnerService.create(nowOwner);
		logger.info("Owner create: " + nowOwner);

		try {
			OwnerService.delete(nowOwner.getId());
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
			
		try {
			OwnerService.findById(nowOwner.getId());
			assertTrue(false);
		} catch (OwnerNotFoundException e) {
			assertTrue(true);
		} 
				

	}
	
	@Test
	public void testCreateAndCheckOwner() {
		
		String OWNER_FIRST_NAME = "leandro";
		String OWNER_LAST_NAME = "morocho";
		String OWNER_CITY = "lima";
		
		Owner nowOwner = new Owner(OWNER_FIRST_NAME, OWNER_LAST_NAME, OWNER_CITY);
		nowOwner = OwnerService.create(nowOwner);
		
		try {
			Owner ownerFound = OwnerService.findById(nowOwner.getId());
			logger.info("Existe Oner");
		}catch (OwnerNotFoundException e) {
			logger.info("Owner no ha sido creado");
		}
		
		Iterable<Owner> owners = OwnerService.findAll();
		
		while(owners.iterator().hasNext()) {
			try {
				Owner ownerFound = OwnerService.findById(nowOwner.getId());
				logger.info("Owner con el id: "+ ownerFound.getId() + " si existe");
				break;
			}catch (OwnerNotFoundException e) {
				logger.info("Owner no existe");
			}
		}
	}
}







