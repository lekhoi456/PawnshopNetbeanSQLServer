package Entity;

/**
 * Asset entity
 *
 * @author KhoiLeQuoc
 */
public class Asset {

    private int assetId;
    private int contractId;
    private String propertyType;
    private String assetName;
    private String startDate;
    private String endDate;
    private String licensePlate;
    private String chassisId;
    private String enginesId;
    private String imei;
    private String passcode;

    /**
     * constructor
     *
     * @param assetId
     * @param contractId
     * @param propertyType
     * @param assetName
     * @param startDate
     * @param endDate
     * @param licensePlate
     * @param chassisId
     * @param enginesId
     * @param imei
     * @param passcode
     */
    public Asset(int assetId, int contractId, String propertyType, String assetName, String startDate, String endDate, String licensePlate, String chassisId, String enginesId, String imei, String passcode) {
        this.assetId = assetId;
        this.contractId = contractId;
        this.propertyType = propertyType;
        this.assetName = assetName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.licensePlate = licensePlate;
        this.chassisId = chassisId;
        this.enginesId = enginesId;
        this.imei = imei;
        this.passcode = passcode;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
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

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getChassisId() {
        return chassisId;
    }

    public void setChassisId(String chassisId) {
        this.chassisId = chassisId;
    }

    public String getEnginesId() {
        return enginesId;
    }

    public void setEnginesId(String enginesId) {
        this.enginesId = enginesId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
}
