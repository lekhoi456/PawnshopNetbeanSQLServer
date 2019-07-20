/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.EmployeeModel;

/**
 *
 * @author KhoiLeQuoc
 */
public class CheckAccount {

    public boolean isTrue(String username, String password) throws Exception {
        HashPW hashPW = new HashPW();
        EmployeeModel employeeModel = new EmployeeModel();
        boolean isCorrect = false;
        for (int i = 0; i < employeeModel.getList().size(); i++) {
            if (employeeModel.getList().get(i).getUsername().equalsIgnoreCase(username) && (hashPW.decode(password, employeeModel.getList().get(i).getPassword()) == true)) {
                isCorrect = true;
            }
        }
        return isCorrect;
    }
}