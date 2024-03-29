package Utills;

import Model.EmployeeModel;

/**
 * Check Account
 *
 * @author KhoiLeQuoc
 */
public class CheckAccount {

    /**
     * check account is valid in db
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public boolean isTrue(String username, String password) throws Exception {
        HashPW hashPW = new HashPW();
        EmployeeModel employeeModel = new EmployeeModel();
        boolean isCorrect = false;
        for (int i = 0; i < employeeModel.getList().size(); i++) {
            if (employeeModel.getList().get(i).getUsername().equalsIgnoreCase(username)
                    && (hashPW.decode(password, employeeModel.getList().get(i).getPassword()) == true)
                    && (employeeModel.getList().get(i).getIsActive() == 0)) {
                isCorrect = true;
            }
        }
        return isCorrect;
    }
}
