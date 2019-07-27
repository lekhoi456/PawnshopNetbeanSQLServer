package Entity;

/**
 * Employee entity
 *
 * @author KhoiLeQuoc
 */
public class Employee {

    private int empId;
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String eAddress;
    private String eRole;
    private int isActive;
    private int storeId;

    /**
     * constructor
     *
     * @param empId
     * @param username
     * @param password
     * @param fullName
     * @param phoneNumber
     * @param email
     * @param eAddress
     * @param eRole
     * @param isActive
     * @param storeId
     */
    public Employee(int empId, String username, String password, String fullName, String phoneNumber, String email, String eAddress, String eRole, int isActive, int storeId) {
        this.empId = empId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.eAddress = eAddress;
        this.eRole = eRole;
        this.isActive = isActive;
        this.storeId = storeId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String geteAddress() {
        return eAddress;
    }

    public void seteAddress(String eAddress) {
        this.eAddress = eAddress;
    }

    public String geteRole() {
        return eRole;
    }

    public void seteRole(String eRole) {
        this.eRole = eRole;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
