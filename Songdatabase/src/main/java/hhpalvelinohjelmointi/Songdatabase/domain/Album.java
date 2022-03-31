package hhpalvelinohjelmointi.Songdatabase.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Album {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long albumid;
	private String title;
	private String artist;
	private String genre;
	private int releaseyear;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Song> songs;

	public Album(Long albumid, String title, String artist, String genre, int releaseyear, List<Song> songs) {
		super();
		this.albumid = albumid;
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.releaseyear = releaseyear;
		this.songs = songs;
	}

	public Album() {
		super();
	}

	public Long getAlbumid() {
		return albumid;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getGenre() {
		return genre;
	}

	public int getReleaseyear() {
		return releaseyear;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setAlbumid(Long albumid) {
		this.albumid = albumid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setReleaseyear(int releaseyear) {
		this.releaseyear = releaseyear;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "Album [albumid=" + albumid + ", title=" + title + ", artist=" + artist + ", genre=" + genre + ", releaseyear="
				+ releaseyear + ", songs=" + songs + "]";
	}
}
