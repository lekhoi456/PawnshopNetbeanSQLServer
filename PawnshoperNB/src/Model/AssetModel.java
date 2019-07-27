package Model;

import Utills.ConnectMSSQL;
import Entity.Asset;
import Exception.AssetException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Asset Model
 *
 * @author KhoiLeQuoc
 */
public class AssetModel {

    private static ArrayList<Asset> assetArrayList = new ArrayList<>();
    private static Connection conn;
    private static Statement st;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static String sqlST;

    /**
     * constructor
     *
     * @throws java.sql.SQLException
     */
    public AssetModel() throws SQLException {
        conn = ConnectMSSQL.getConnection();
        st = conn.createStatement();
        assetArrayList = new ArrayList<>();
        pst = null;
        rs = null;
        sqlST = "";
        loadAsset();
    }

    /**
     * load Asset from DB
     *
     * @throws SQLException
     */
    private void loadAsset() throws SQLException {
        sqlST = "SELECT * FROM Asset";
        rs = st.executeQuery(sqlST);
        if (rs.isBeforeFirst()) {
            assetArrayList.clear();
            while (rs.next()) {
                assetArrayList.add(
                        new Asset(rs.getInt("AssetId"),
                                rs.getInt("ContractId"),
                                rs.getString("PropertyType"),
                                rs.getString("AssetName"),
                                rs.getString("StartDate"),
                                rs.getString("EndDate"),
                                rs.getString("LicensePlate"),
                                rs.getString("ChassisId"),
                                rs.getString("EnginesId"),
                                rs.getString("Imei"),
                                rs.getString("Passcode")));
            }
        }
    }

    /**
     * get Free Asset ID
     *
     * @return assetArrayList.size() + 1
     */
    public int getFreeAssetId() {
        return assetArrayList.size() + 1;
    }

    /**
     * get Asset name
     *
     * @param contractId
     * @return assetArrayList.get(i).getAssetName()
     */
    public String getAssetName(int contractId) {
        for (int i = 0; i < assetArrayList.size(); i++) {
            if (contractId == assetArrayList.get(i).getContractId()) {
                return assetArrayList.get(i).getAssetName();
            }
        }
        return null;
    }

    /**
     * add new Asset to DB
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
     * @throws SQLException
     * @throws Exception.AssetException
     */
    public void addAsset(int assetId, int contractId, String propertyType, String assetName, String startDate, String endDate, String licensePlate, String chassisId, String enginesId, String imei, String passcode) throws SQLException, AssetException {
        try {
            sqlST = "INSERT INTO Asset(AssetId, ContractId, PropertyType, AssetName, StartDate, EndDate, LicensePlate, ChassisId, EnginesId, Imei, Passcode) VALUES ("
                    + +assetId + ", " + contractId + ", N'" + propertyType + "', N'" + assetName + "', '" + startDate + "', '" + endDate + "', '" + licensePlate + "', '" + chassisId + "', '" + enginesId + "' , '" + imei + "', '" + passcode + "')";
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        try {
            assetArrayList.add(new Asset(assetId, contractId, propertyType, assetName, startDate, endDate, licensePlate, chassisId, enginesId, imei, passcode));
        } catch (Exception e) {
            throw new AssetException("Can't add a new asset to AssetArrayList");
        }
    }

    /**
     * get AssetArrayList
     *
     * @return assetArrayList<>
     */
    public ArrayList<Asset> getList() {
        return assetArrayList;
    }
}
