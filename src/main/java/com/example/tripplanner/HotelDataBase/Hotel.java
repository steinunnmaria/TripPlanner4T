package com.example.tripplanner.HotelDataBase;

import java.util.ArrayList;

public class Hotel {

    private Integer id;
    private String name;
    private Integer region;
    private String town;
    private String image;
    private Info hotelInfo;
    private ArrayList<Room> rooms;

    public Hotel(Integer id, String name, Integer region, String town, String image, Info hotelInfo) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.town = town;
        this.image = image;
        this.hotelInfo = hotelInfo;
        this.rooms = null;
    }

	public String toString() {
        String allInfo = "";
        allInfo += "Hotel ID: " + this.id + "\n";
        allInfo += "Hotel name: " + this.name + "\n";
        allInfo += "Region: " + this.region + "\n";
        allInfo += "Town: " + this.town + "\n";
        allInfo += "image: " + this.image + "\n";
		allInfo += hotelInfo.toString();
        return allInfo;
    }

    public void addRoom(Room newRoom) {
        rooms.add(newRoom);
    }

    public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRegion() {
		return this.region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Info getHotelInfo() {
		return this.hotelInfo;
	}

	public void setHotelInfo(Info hotelInfo) {
		this.hotelInfo = hotelInfo;
	}

	public ArrayList<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
}
