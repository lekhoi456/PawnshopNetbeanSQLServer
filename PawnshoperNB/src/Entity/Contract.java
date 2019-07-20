package Entity;

public class Contract {

    private int contractId;
    private int customerId;
    private String propertyType;
    private String assetName;
    private long totalLoanAmount;
    private long interestRate;
    private String startDate;
    private String endDate;
    private long creditPeriod;
    private String note;
    private String cashier;
    private int status;
    private String contractImage;
    private long totalMoney;
    private String redeemAtDay;
    private int storeId;

    public Contract(int contractId, int customerId, String propertyType, String assetName, long totalLoanAmount, long interestRate, String startDate, String endDate, long creditPeriod, String note, String cashier, int status, String contractImage, long totalMoney, String redeemAtDay, int storeId) {
        this.contractId = contractId;
        this.customerId = customerId;
        this.propertyType = propertyType;
        this.assetName = assetName;
        this.totalLoanAmount = totalLoanAmount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.creditPeriod = creditPeriod;
        this.note = note;
        this.cashier = cashier;
        this.status = status;
        this.contractImage = contractImage;
        this.totalMoney = totalMoney;
        this.redeemAtDay = redeemAtDay;
        this.storeId = storeId;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public long getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public void setTotalLoanAmount(long totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }

    public long getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(long interestRate) {
        this.interestRate = interestRate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getCreditPeriod() {
        return creditPeriod;
    }

    public void setCreditPeriod(long creditPeriod) {
        this.creditPeriod = creditPeriod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContractImage() {
        return contractImage;
    }

    public void setContractImage(String contractImage) {
        this.contractImage = contractImage;
    }

    public long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getRedeemAtDay() {
        return redeemAtDay;
    }

    public void setRedeemAtDay(String redeemAtDay) {
        this.redeemAtDay = redeemAtDay;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
