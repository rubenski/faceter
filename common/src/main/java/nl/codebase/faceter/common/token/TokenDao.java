package nl.codebase.faceter.common.token;

public interface TokenDao {

    void insert(String token, int expires);

    String read(String token);
}
