package nl.codebase.iam.logout;

public interface LogoutDao {

    void insertLoggedOutToken(String... token);
}
