package hhpalvelinohjelmointi.Songdatabase.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Song {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String genre;
	private double length;
	private String songwriter;
	
	@ManyToOne
	@JsonIgnoreProperties ("songs")
	@JoinColumn(name = "albumid")
	private Album album;
	
	public Song(Long id, String title, String genre, double length, String songwriter) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.length = length;
		this.songwriter = songwriter;
	}

	public Song() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public double getLength() {
		return length;
	}

	public String getSongwriter() {
		return songwriter;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setSongwriter(String songwriter) {
		this.songwriter = songwriter;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", title=" + title + ", genre=" + genre + ", length=" + length + ", songwriter="
				+ songwriter + "]";
	}
}
