package Model;

import Controller.ConnectMSSQL;
import Entity.Asset;

import java.sql.*;
import java.util.ArrayList;

public class AssetModel {

    private static ArrayList<Asset> assetArrayList = new ArrayList<>();
    private static Connection conn;
    private static Statement st;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static String sqlST;

    public AssetModel() throws Exception {
        try {
            conn = ConnectMSSQL.getConnection();
            st = conn.createStatement();
            assetArrayList = new ArrayList<>();
            pst = null;
            rs = null;
            sqlST = "";
            loadAsset();
        } catch (Exception e) {
            throw new Exception("Cannot load initialize Asset Model");
        }
    }

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

    public int getFreeAssetId() {
        for (int i = 0; i < assetArrayList.size(); i++) {
            if (i > assetArrayList.get(i).getAssetId()) {
                return i;
            }
        }
        return assetArrayList.size() + 1;
    }

    public String getAssetName(int contractId) {
        for (int i = 0; i < assetArrayList.size(); i++) {
            if (contractId == assetArrayList.get(i).getContractId()) {
                return assetArrayList.get(i).getAssetName();
            }
        }
        return null;
    }

    public void addAsset(int assetId, int contractId, String propertyType, String assetName, String startDate, String endDate, String licensePlate, String chassisId, String enginesId, String imei, String passcode) throws SQLException {
        try {
            sqlST = "INSERT INTO Asset(AssetId, ContractId, PropertyType, AssetName, StartDate, EndDate, LicensePlate, ChassisId, EnginesId, Imei, Passcode) VALUES ("
                    + +assetId + ", " + contractId + ", N'" + propertyType + "', N'" + assetName + "', '" + startDate + "', '" + endDate + "', '" + licensePlate + "', '" + chassisId + "', '" + enginesId + "' , '" + imei + "', '" + passcode + "')";
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            assetArrayList.add(new Asset(assetId, contractId, propertyType, assetName, startDate, endDate, licensePlate, chassisId, enginesId, imei, passcode));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int searchAsset(int keyword) {
        for (int i = 0; i < assetArrayList.size(); i++) {
            if (assetArrayList.get(i).getAssetId() == keyword) {
                return i;
            }
        }
        return -1;
    }

    public void printListAsset() {
        for (Asset asset : assetArrayList) {
            System.out.println(asset.getAssetId() + " | "
                    + asset.getContractId() + " | "
                    + asset.getPropertyType() + " | "
                    + asset.getAssetName() + " | "
                    + asset.getStartDate() + " | "
                    + asset.getEndDate() + " | "
                    + asset.getLicensePlate() + " | "
                    + asset.getChassisId() + " | "
                    + asset.getEnginesId() + " | "
                    + asset.getImei() + " | "
                    + asset.getPasscode());
        }
    }

    public ArrayList<Asset> getList() {
        return assetArrayList;
    }
}
