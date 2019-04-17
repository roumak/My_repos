package com.roumak.bi_directional_mapping;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "instructor_details")
public class InstructorDetailsEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "youtube_channel")
	private String youtubeChannel;

	@Column(name = "hobby")
	private String hobby;
	
	// addition for bi directional mapping 
	
	@OneToOne(mappedBy="instructor_details", cascade=CascadeType.ALL)  // new {Mapped by}
	private InstructorEntity theInstructorEntity;

	@Override
	public String toString() {
		return "InstructorDetailsEntity [id=" + id + ", youtubeChannel=" + youtubeChannel + ", hobby=" + hobby
				+ ", theInstructorEntity=" + theInstructorEntity + "]";
	}

	public InstructorEntity getTheInstructorEntity() {
		return theInstructorEntity;
	}

	public void setTheInstructorEntity(InstructorEntity theInstructorEntity) {
		this.theInstructorEntity = theInstructorEntity;
	}

	public InstructorDetailsEntity() {
	}

	public InstructorDetailsEntity(String youtubeChannel, String hobby) {
		super();

		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getYoutubeChannel() {
		return youtubeChannel;
	}

	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

}
