package com.example.tripplanner.HotelDataBase;

public class Room {

    private Integer roomNum;
    private Integer hotelId;
    private Integer price;
    private Integer type;
    private Integer numBeds;
    private Integer capacity;


    public Room(Integer roomNum, Integer hotelId, Integer price, Integer type, Integer numBeds, Integer capacity) {
        this.roomNum = roomNum;
        this.hotelId = hotelId;
        this.price = price;
        this.type = type;
        this.numBeds = numBeds;
        this.capacity = capacity;
    }

    public String toString() {
        String allInfo = "";
        allInfo += "Room Number: " + this.roomNum + "\n";
        allInfo += "Hotel ID: " + this.hotelId + "\n";
        allInfo += "Price: " + this.price + "\n";
        allInfo += "Room Type: " + this.type + "\n";
        allInfo += "Number of beds: " + this.numBeds + "\n";
        allInfo += "Capacity: " + this.capacity + "\n";
        return allInfo;
    }

    public Integer getRoomNum() {
		return this.roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

	public Integer getHotelId() {
		return this.hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getNumBeds() {
		return this.numBeds;
	}

	public void setNumBeds(Integer numBeds) {
		this.numBeds = numBeds;
	}

	public Integer getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}


    /**
     * Checks whether the room is available for the given date range.
     * @params: Long dateFrom, Long dateTo
     * @return: boolean, true if the room is available then, false if not
     */
    // public Boolean isAvailable(Long dateFrom, Long dateTo) {
    //     for (Reservation reservation : reservations) {

    //     }
    // }

}
