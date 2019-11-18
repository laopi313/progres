package com.peter.postgres.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peter.postgres.model.MyEntity;
import com.peter.postgres.model.MyJson;

public class TestJsonbSupport {

	Logger log = Logger.getLogger(this.getClass().getName());

	private EntityManagerFactory emf;

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
	}

	@After
	public void close() {
		emf.close();
	}

	@Test
	public void testJsonMapping() throws JsonProcessingException {
		log.info("... testJsonMapping ...");

		MyJson j = new MyJson();
		j.setLongProp(123L);
		j.setStringProp("abc");
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(j));
	}
	
	@Test
	public void testCreateJsonbEntity() {
		log.info("... testCreateJsonbEntity ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		MyEntity e = new MyEntity();

		MyJson j = new MyJson();
		j.setLongProp(123L);
		j.setStringProp("abc");
				
		e.setJsonProperty(j);

		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		e.setIntList(list);
		
		List<String> sList = new ArrayList<>();
		sList.add("s1");
		sList.add("s2");
		e.setStringList(sList);		

		em.persist(e);
		
		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void testUpdateJsonbEntity() {
		log.info("... testUpdateJsonbEntity ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		MyEntity e = em.find(MyEntity.class, 22);
			
		e.getJsonProperty().setStringProp("changed");
		e.getJsonProperty().setLongProp(789L);
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void testSelectJsonbEntity() {
		log.info("... testSelectJsonbEntity ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		MyEntity e = em.find(MyEntity.class, 21);

		//MyEntity e = (MyEntity) em.createNativeQuery("SELECT * FROM myentity e WHERE CAST(e.jsonproperty->>'longProp' AS BIGINT) = 456", MyEntity.class).getSingleResult();
		
		//Assert.assertNotNull(e.getJsonProperty());
		//System.out.println("JSON: stringProp = "+e.getJsonProperty().getStringProp()+"    longProp = "+e.getJsonProperty().getLongProp());
		
		em.getTransaction().commit();
		em.close();
	}
}
