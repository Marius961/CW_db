package ua.phones.db.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ua.phones.db.dao.interfaces.VendorDAO;
import ua.phones.db.models.Processor;
import ua.phones.db.models.Vendor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class VendorDAOImpl implements VendorDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Vendor getVendor(int id) {
        String sql = "SELECT * FROM vendors WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new VendorMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean insertVendor(Vendor vendor) {
        String sql = "INSERT INTO vendors (name) VALUES (:name)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", vendor.getName());
        try {
            jdbcTemplate.update(sql, params);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }

    }

    @Override
    public boolean deleteVendor(int id) {
        String sql = "DELETE FROM vendors WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            jdbcTemplate.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateVendor(Vendor vendor) {
        String sql = "UPDATE vendors SET name:name WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", vendor.getId());
        params.addValue("name", vendor.getName());
        try {
            jdbcTemplate.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private static final class VendorMapper implements RowMapper<Vendor> {

        @Override
        public Vendor mapRow(ResultSet rs, int i) throws SQLException {
            Vendor vendor = new Vendor();
            vendor.setId(rs.getInt("id"));
            vendor.setName(rs.getNString("name"));
            return vendor;
        }
    }
}
