package com.andrelucs.springbootmongodb.domain;

import com.andrelucs.springbootmongodb.dto.AuthorDTO;
import com.andrelucs.springbootmongodb.dto.CommentDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Document(collection = "post")
public class Post implements Serializable{
	@Id
	private String id;
	private Date date;
	private String title;
	private String body;
	private AuthorDTO author;

	private List<CommentDTO> comments = new ArrayList<>();

	public Post(){
	}

	public Post(Date date, String title, String body, User author) {
		this(date, title, body, new AuthorDTO(author));
	}
	public Post(Date date, String title, String body, AuthorDTO author) {
		super();
		this.date = date;
		this.title = title;
		this.body = body;
		this.author = author;
	}

	public Post(String id, Date date, String title, String body, AuthorDTO author) {
		this(date, title, body, author);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public List<CommentDTO> getComments(){
		return this.comments;
	}

	public void setComments(List<CommentDTO> newComments){
		this.comments = newComments;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Post post)) return false;
		return Objects.equals(getId(), post.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return "Post{" +
				"id='" + id + '\'' +
				", date=" + date +
				", title='" + title + '\'' +
				", body='" + body + '\'' +
				", author=" + author +
				'}';
	}
}