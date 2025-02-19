class IdentityDocument {
    private IdType idType;
    private String idNumber;

    public IdentityDocument(IdType idType, String idNumber) {
        if(!isValidId(idType, idNumber)) {
            throw new IllegalArgumentException("Invalid NIC or Passport number.");
        }
        this.idType = idType;
        this.idNumber = idNumber;
    }

    public IdType getIdType() { return idType; }
    public String getIdNumber() { return idNumber; }

    private boolean isValidId(IdType idType, String idNumber) {
        if(idType == IdType.NIC) {
            // NIC number should be 9 digits followed by V or X or 12 digits
            return idNumber.matches("\\d{9}[Vv]?|\\d{12}");
        } else if(idType == IdType.PASSPORT) {
            // Passport number should be a letter followed by 7 digits
            return idNumber.matches("[A-Z]{1}[0-9]{7}");
        }
        return false;
    }
}
