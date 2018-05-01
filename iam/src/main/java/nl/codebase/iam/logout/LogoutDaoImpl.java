package nl.codebase.iam.logout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDate;

@Repository
public class LogoutDaoImpl extends JdbcDaoSupport implements LogoutDao {

    private final DataSource dataSource;

    private static final String SQL_INSERT_TOKEN = "INSERT INTO f_logged_out_token (token, logout_time) VALUES (?,?)";

    @Autowired
    public LogoutDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void insertLoggedOutToken(String token) {
        getJdbcTemplate().update(SQL_INSERT_TOKEN, preparedStatement -> {
            preparedStatement.setString(0, token);
            preparedStatement.setTimestamp(1, new Timestamp(LocalDate.now().toEpochDay()));
        });
    }
}
