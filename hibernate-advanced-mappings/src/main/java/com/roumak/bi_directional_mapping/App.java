package com.roumak.bi_directional_mapping;

public class App {
	public static void main(String[] args) {
		Dao dao= new Dao();
		InstructorDetailsEntity theDetailsEntity= dao.fetchInstrDetailsEntityByID(1L);
		System.out.println(theDetailsEntity);
		
	}
}
