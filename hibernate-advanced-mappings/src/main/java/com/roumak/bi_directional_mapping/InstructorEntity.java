package com.roumak.bi_directional_mapping;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Class InstructorEntity.
 * 
 * @author Roumak_Chakraborty
 */
@Entity
@Table(name = "instructor_table")
public class InstructorEntity {

	/** The id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The first name. */
	@Column(name = "first_name")
	private String firstName;

	/** The last name. */
	@Column(name = "last_name")
	private String lastName;

	/** The email. */
	@Column(name = "email")
	private String email;

	/** The instructor details. */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "instructor_details" )
	private InstructorDetailsEntity instructorDetails;

	/**
	 * Instantiates a new instructor entity.
	 */
	public InstructorEntity() {
	}

	/**
	 * Instantiates a new instructor entity.
	 *
	 * @param id        the id
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @param email     the email
	 */
	public InstructorEntity( String firstName, String lastName, String email) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	/**
	 * Gets the instructor details.
	 *
	 * @return the instructor details
	 */
	public InstructorDetailsEntity getInstructorDetails() {
		return instructorDetails;
	}

	/**
	 * Sets the instructor details.
	 *
	 * @param instructorDetails the new instructor details
	 */
	public void setInstructorDetails(InstructorDetailsEntity instructorDetails) {
		this.instructorDetails = instructorDetails;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "InstructorEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", instructorDetails=" + instructorDetails + "]";
	}

}
