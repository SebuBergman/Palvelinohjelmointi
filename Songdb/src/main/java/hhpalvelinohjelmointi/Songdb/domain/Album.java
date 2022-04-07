package hhpalvelinohjelmointi.Songdb.domain;

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
	private String name;
	private String artist;
	private String genre;
	private int releaseyear;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
	private List<Song> songs;

	public Album() {}
	
	public Album(String name, String artist, String genre, int releaseyear) {
		super();
		this.name = name;
		this.artist = artist;
		this.genre = genre;
		this.releaseyear = releaseyear;
	}

	public Album(Long albumid, String name, String artist, String genre, int releaseyear) {
		super();
		this.albumid = albumid;
		this.name = name;
		this.artist = artist;
		this.genre = genre;
		this.releaseyear = releaseyear;
	}

	public Album(Long albumid, String name, String artist, String genre, int releaseyear, List<Song> songs) {
		super();
		this.albumid = albumid;
		this.name = name;
		this.artist = artist;
		this.genre = genre;
		this.releaseyear = releaseyear;
		this.songs = songs;
	}

	public Long getAlbumid() {
		return albumid;
	}

	public void setAlbumid(Long albumid) {
		this.albumid = albumid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getReleaseyear() {
		return releaseyear;
	}

	public void setReleaseyear(int releaseyear) {
		this.releaseyear = releaseyear;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "Album [albumid=" + albumid + ", name=" + name + ", artist=" + artist + ", genre=" + genre
				+ ", releaseyear=" + releaseyear + "]";
	}
}