package com.imagemnt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="images")
public class Image {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;
	
	@Column(name="name")
	protected String name;
	
	@Column(name="size")
	protected String size;
	
	@Column(name="image")
	protected String image;

	public Image(String name, String size, String image) {
		super();
		this.name = name;
		this.size = size ;
		this.image = image;
	}
	
	public Image(int id, String name, String size, String image) {
		super();
		this.id = id;
		this.name = name;
		this.size = size ;
		this.image = image;
	}
	
	public Image() {
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSize() {
		return size;
	}

	public String getImage() {
		return image;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
