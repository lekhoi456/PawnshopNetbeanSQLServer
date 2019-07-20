
import Model.ContractModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author KhoiLeQuoc
 */
public class TestAddContract {

    public static void main(String[] args) throws Exception {
        ContractModel contractModel = new ContractModel();
        int contractId = 2;
        int customerId = 5;
        String propertyType = "Mobile";
        String assetName = "Asus Zenfone";
        long totalLoanAmount = 4000000;
        long interestRate = 100000;
        String startDate = "2019-07-21";
        String endDate = "2019-07-22";
        long creditPeriod = 1;
        String note = "Xin lỗi anh Khanh";
        String cashier = "lekhoi456";
        System.out.println(contractModel.getFreeContractId());
        //contractModel.addContract(contractId, customerId, propertyType, assetName, totalLoanAmount, interestRate, startDate, endDate, creditPeriod, note, cashier, 1, "0", totalLoanAmount + interestRate, "0", 1);
    }
}
