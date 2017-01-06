public enum EmployeeServiceEnum {

    ADD(1), UPDATE(2), DELETE(3), DISPLAY(4), DISPALYALL(5), INCREASE_SALAARY(6) , SORTBYNAME(7), SORTBYAGE(8), UNIQUEEMPLOYEENAMES(9),EMPLOYEEAGECOUNT(10);

    private int operationValue;

    EmployeeServiceEnum(int operationValue) {
        this.operationValue = operationValue;
    }

    public int getOperationValue() {
        return operationValue;
    }

    public static EmployeeServiceEnum resolveNameByOperationValue(int operationValue) {
        for (EmployeeServiceEnum employeeServiceEnm : EmployeeServiceEnum.values()) {
            if (employeeServiceEnm.getOperationValue() == operationValue) {
                return employeeServiceEnm;
            }
        }

        return null;
    }
}