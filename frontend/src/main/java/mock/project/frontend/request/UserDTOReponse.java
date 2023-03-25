package mock.project.frontend.request;

import java.util.Date;

import mock.project.frontend.entities.Roles;

public class UserDTOReponse {
	private Integer userId;
	private String userName;
	private String fullName;
	private String email;
	private String address;
	private String phone;
	private Date dateofBirth;
	private String image;
	private Roles role;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDateofBirth() {
		return dateofBirth;
	}
	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserDTOReponse [userId=" + userId + ", userName=" + userName + ", fullName=" + fullName + ", email="
				+ email + ", address=" + address + ", phone=" + phone + ", dateofBirth=" + dateofBirth + ", image="
				+ image + ", role=" + role + "]";
	}
	
}
