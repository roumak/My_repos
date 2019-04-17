package com.roumak.uni_directional_mapping;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		InstructorEntity theInstructor= new InstructorEntity( "ROumak", "Chakrbaorty", "RChak@email.com");
		InstructorDetailsEntity theInstructorDetailsEntity= new InstructorDetailsEntity( "yTube.com/rChak", "Motor cycleing");
		theInstructor.setInstructorDetails(theInstructorDetailsEntity);
		Dao dao= new Dao();
		System.out.println(dao.insertInstructor(theInstructor));
	}
}
