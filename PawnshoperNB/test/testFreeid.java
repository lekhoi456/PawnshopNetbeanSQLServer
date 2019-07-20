
import Model.CustomerModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author KhoiLeQuoc
 */
public class testFreeid {

    public static void main(String[] args) throws Exception {
        CustomerModel customerModel = new CustomerModel();
        int freeId = customerModel.getFreeId();
        System.out.println(freeId);
    }
}
