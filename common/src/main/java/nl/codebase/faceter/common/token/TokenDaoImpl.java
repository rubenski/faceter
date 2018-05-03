package nl.codebase.faceter.common.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Repository
public class TokenDaoImpl extends JdbcDaoSupport implements TokenDao {

    private final DataSource dataSource;

    private static final String SQL_INSERT_TOKEN = "INSERT INTO f_logged_out_token (token, logout_time, expiry_time) VALUES (?,?,?)";
    private static final String SQL_READ_TOKEN = "SELECT * FROM f_logged_out_token WHERE token = ?";

    @Autowired
    public TokenDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void insert(String token, int expires) {
        getJdbcTemplate().update(SQL_INSERT_TOKEN, preparedStatement -> {
            preparedStatement.setString(1, token);
            preparedStatement.setTimestamp(2, new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()));
            preparedStatement.setTimestamp(3, new Timestamp(LocalDateTime.ofEpochSecond(expires, 0, ZoneOffset.UTC).toInstant(ZoneOffset.UTC).toEpochMilli()));
        });
    }

    @Override
    public String read(String token) {
        return getJdbcTemplate().query(SQL_READ_TOKEN,
                preparedStatement -> preparedStatement.setString(1, token),
                resultSet -> {
            if(!resultSet.next()) {
                return null;
            }
            return resultSet.getString("token");
        });
    }
}
