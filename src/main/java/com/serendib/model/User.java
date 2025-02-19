class User {
    private String userId;
    private String userName;
    private String password;
    private IdentityDocument identityDocument;
    private String email;
    private String mobile;
    private Account account;
    private UserState state; // ACTIVE, LOCKED

    public User(String userId, String userName, String password, IdentityDocument identityDocument, String email, String mobile, Account account) {
        // TODO: Implement validation
        // if(!isValidUserId(userId)) {
        //     throw new IllegalArgumentException("Invalid user ID.");
        // }
        // if(!isValidUserName(userName)) {
        //     throw new IllegalArgumentException("Invalid user name.");
        // }
        // if(!isValidPassword(password)) {
        //     throw new IllegalArgumentException("Invalid password.");
        // }
        // if(!isValidEmail(email)) {
        //     throw new IllegalArgumentException("Invalid email.");
        // }
        // if(!isValidMobile(mobile)) {
        //     throw new IllegalArgumentException("Invalid mobile number.");
        // }

        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.identityDocument = identityDocument;
        this.email = email;
        this.mobile = mobile;
        this.account = account;
        this.state = UserState.ACTIVE;
    }

    // TODO: Adjust getters and setters
    public String getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getPassword() { return password; }

    public String getEmail() { return email; }
    public String getMobile() { return mobile; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    public UserState getState() { return state; }
    public void setState(UserState state) { this.state = state; }

    public IdentityDocument getIdentityDocument() { return identityDocument; }
}