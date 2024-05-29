package ui;

import java.util.Arrays;

public class User {

    private int id;
    private String name;
    private String eMail;
    private char[] password;
    private String contactInfo;
    private String userType;
    

    public User(String userType) {
        this.userType = userType;
    }

    public User(int id, String name, String eMail, char[] password, String contactInfo, String userType) {
        this.id = id;
        this.name = name;
        this.eMail = eMail;
        this.password = password;
        this.contactInfo = contactInfo;
        this.userType = userType;
    }

    public boolean loginInfoChecker(String mail, char[] password) {
        return this.eMail.equals(mail) && Arrays.equals(this.password, password);
    }    

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

	public String getName() {

		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	public String getEmail() {
		return this.eMail;
	}

}
