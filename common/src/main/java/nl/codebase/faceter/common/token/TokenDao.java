package nl.codebase.faceter.common.token;

public interface TokenDao {

    void insertLoggedOutToken(String token, int expires);
}
