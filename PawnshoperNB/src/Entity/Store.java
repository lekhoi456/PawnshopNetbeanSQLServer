package Entity;

/**
 * Store entity
 *
 * @author KhoiLeQuoc
 */
public class Store {

    private int storeId;
    private String storeName;
    private String sAddress;
    private String representative;
    private long investmentCapital;
    private long cashFund;
    private long interestCollected;

    /**
     * constructor
     *
     * @param storeId
     * @param storeName
     * @param sAddress
     * @param representative
     * @param investmentCapital
     * @param cashFund
     * @param interestCollected
     */
    public Store(int storeId, String storeName, String sAddress, String representative, long investmentCapital, long cashFund, long interestCollected) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.sAddress = sAddress;
        this.representative = representative;
        this.investmentCapital = investmentCapital;
        this.cashFund = cashFund;
        this.interestCollected = interestCollected;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public long getInvestmentCapital() {
        return investmentCapital;
    }

    public void setInvestmentCapital(long investmentCapital) {
        this.investmentCapital = investmentCapital;
    }

    public long getCashFund() {
        return cashFund;
    }

    public void setCashFund(long cashFund) {
        this.cashFund = cashFund;
    }

    public long getInterestCollected() {
        return interestCollected;
    }

    public void setInterestCollected(long interestCollected) {
        this.interestCollected = interestCollected;
    }
}
