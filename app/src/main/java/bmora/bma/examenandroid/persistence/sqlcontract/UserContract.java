package bmora.bma.examenandroid.persistence.sqlcontract;

public final class UserContract {
    private UserContract(){}

    public static class UserEntry {
        public static final String TABLE_NAME="usuario";
        public static final String COLUMN_TOKEN = "token";
        public static final String COLUMN_EMAIL = "email";
    }
}
