package com.peter.postgres.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;

@Entity
public class MyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;

	@Column
	@Type(type = "com.peter.postgres.types.JsonType")
	private MyJson jsonProperty;

	@Column(columnDefinition="smallint[]")
	@Type(type = "com.peter.postgres.types.ShortArrayType")
	private List<Integer> intList;


	@Column(columnDefinition="text[]")
	@Type(type = "com.peter.postgres.types.StringArrayType")
	private List<String> stringList;	
	
	
//	@Column
//	@Type(type="com.peter.postgres.types.NewStringArrayType")
//	private String[] strings;
		

	public List<String> getStringList() {
		return stringList;
	}

	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}

	public MyEntity() {
	}

//	public String[] getStrings() {
//		return strings;
//	}
//
//	public void setStrings(String[] strings) {
//		this.strings = strings;
//	}


	public List<Integer> getIntList() {
		return intList;
	}

	public void setIntList(List<Integer> intList) {
		this.intList = intList;
	}

	public MyJson getJsonProperty() {
		return jsonProperty;
	}


	public void setJsonProperty(MyJson jsonProperty) {
		this.jsonProperty = jsonProperty;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}
}