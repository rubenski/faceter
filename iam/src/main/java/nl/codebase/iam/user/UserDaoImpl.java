package nl.codebase.iam.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Collection;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM f_user WHERE email = ?";

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public User findUserById(String uuid) {
        return getJdbcTemplate().queryForObject(SQL_SELECT_BY_ID, new Object[]{uuid}, (rs, i) -> {
            User user = new User();
            user.setUuid(rs.getString("uuid"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setRoles(rs.getString("roles"));
            user.setLocked(rs.getBoolean("locked"));
            user.setEnabled(rs.getBoolean("enabled"));
            user.setAccountExpired(rs.getBoolean("account_expired"));
            user.setPassword(rs.getString("pw"));

            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            return user;
        });
    }
}
