package com.websystique.springmvc.model;

public class Company {

	private long id;
	
	private String companyName;
        
        private String owner;
	
	private String address;
        
        private String city;
	
        private String country;
        
        private String phoneNum;
        
	private String email;
	
	public Company(){
		id=0;
	}
	
	public Company(long id, String companyname,String owner, String address,String city,String country,String phoneNum, String email){
		this.id = id;
		this.companyName = companyname;
                this.owner = owner;
		this.address = address;
                this.city = city;
                 this.country = country;
                  this.phoneNum = phoneNum;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getcompanyName() {
		return companyName;
	}

	public void setcompanyName(String companyname) {
		this.companyName = companyname;
	}
        
        public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
        
        public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
        
          public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
        
          public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Company))
			return false;
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", companyName=" + companyName + ", address=" + address+ ", city=" + city+ ", country=" + country+ ", phoneNum=" + phoneNum
				+ ", email=" + email + "]";
	}
	

	
}
