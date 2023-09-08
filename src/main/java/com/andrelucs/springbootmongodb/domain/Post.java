package com.andrelucs.springbootmongodb.domain;

import com.andrelucs.springbootmongodb.dto.AuthorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@Document
public class Post implements Serializable{
	@Id
	private String id;

	private Date date;
	private String title;
	private String body;
	private AuthorDTO author;

	public Post(){
	}

	public Post(Date date, String title, String body, User author) {
		super();
		this.date = date;
		this.title = title;
		this.body = body;
		this.author = new AuthorDTO(author);
	}
	public Post(Date date, String title, String body, AuthorDTO author) {
		super();
		this.date = date;
		this.title = title;
		this.body = body;
		this.author = author;
	}
}