package Entity;

/**
 * Customer entity
 *
 * @author KhoiLeQuoc
 */
public class Customer {

    private int customerId;
    private String customerName;
    private String phoneNumber;
    private String socialId;
    private String dateRange;
    private String registeredPlace;
    private String address;
    private int isActive;
    private String customerImage;

    /**
     * constructor
     *
     * @param customerId
     * @param customerName
     * @param phoneNumber
     * @param socialId
     * @param dateRange
     * @param registeredPlace
     * @param address
     * @param isActive
     * @param customerImage
     */
    public Customer(int customerId, String customerName, String phoneNumber, String socialId, String dateRange, String registeredPlace, String address, int isActive, String customerImage) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.socialId = socialId;
        this.dateRange = dateRange;
        this.registeredPlace = registeredPlace;
        this.address = address;
        this.isActive = isActive;
        this.customerImage = customerImage;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public String getRegisteredPlace() {
        return registeredPlace;
    }

    public void setRegisteredPlace(String registeredPlace) {
        this.registeredPlace = registeredPlace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(String customerImage) {
        this.customerImage = customerImage;
    }
}
