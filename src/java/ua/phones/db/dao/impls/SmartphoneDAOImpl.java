package ua.phones.db.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ua.phones.db.dao.interfaces.SmartphoneDAO;
import ua.phones.db.models.Smartphone;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SmartphoneDAOImpl implements SmartphoneDAO {


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Smartphone> getAllSmartphones() {
        String sql = "SELECT * FROM smartphones";
        try {
            return jdbcTemplate.query(sql, new SmartphoneMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Smartphone getSmartphone(int id) {
        String sql = "SELECT * FROM smartphones WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new SmartphoneMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Smartphone getSmartphone(Smartphone smartphone) {
        String sql =
                "SELECT * FROM smartphones " +
                "WHERE vendor_id=:vendorId " +
                "AND model=:model " +
                "AND characteristics_id=:characteristicsId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("vendorId", smartphone.getVendorId());
        params.addValue("model", smartphone.getModel());
        params.addValue("characteristicsId", smartphone.getCharacteristicsId());
        try {
            return jdbcTemplate.queryForObject(sql, params, new SmartphoneMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Smartphone> getSearchedPhones(String name) {
        String sql =
                "SELECT * FROM smartphones s, vendors v " +
                "WHERE s.vendor_id=v.id and (s.model LIKE :name " +
                "OR v.name LIKE :name)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", "%" + name + "%");
        try {
            return jdbcTemplate.query(sql, params, new SmartphoneMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Smartphone getSmartPhoneByModel(String model) {
        String sql = "SELECT * FROM smartphones WHERE model=:model";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("model", model);
        try {
            return jdbcTemplate.queryForObject(sql, params, new SmartphoneMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void insertSmartphone(Smartphone smartphone) {
        String sql =
                "INSERT INTO smartphones (vendor_id, model, characteristics_id) " +
                "VALUES (:vendorId, :model, :characteristicsId)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("vendorId", smartphone.getVendorId());
        params.addValue("model", smartphone.getModel());
        params.addValue("characteristicsId", smartphone.getCharacteristicsId());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteSmartphome(int id) {
        String sql = "DELETE FROM smartphones WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void updateSmartphone(Smartphone smartphone) {
        String sql = "UPDATE smartphones SET vendor_id=:vendorId, model=:model, characteristics_id=:characteristicsId WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", smartphone.getId());
        params.addValue("vendorId", smartphone.getVendorId());
        params.addValue("model", smartphone.getModel());
        params.addValue("characteristicsId", smartphone.getCharacteristicsId());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public Map<String, Integer> getPhonesFromVendorsCount() {
        String sql = "SELECT v.name, count(s.vendor_id) FROM vendors v, smartphones s WHERE v.id=s.vendor_id GROUP BY v.name";
        return jdbcTemplate.query(sql, (ResultSetExtractor<Map<String, Integer>>) rs -> {
            HashMap<String, Integer> mapRet = new HashMap<>();
            while (rs.next()) {
                mapRet.put(rs.getString("name"), rs.getInt("count(s.vendor_id)"));
            }
            return mapRet;
        });
    }

    @Override
    public int getVendorCount(int vendorId) {
        String sql = "SELECT count(*) FROM smartphones WHERE vendor_id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", vendorId);
        return jdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    private static final class SmartphoneMapper implements RowMapper<Smartphone> {

        @Override
        public Smartphone mapRow(ResultSet rs, int i) throws SQLException {
            Smartphone smartphone = new Smartphone();
            smartphone.setId(rs.getInt("id"));
            smartphone.setVendorId(rs.getInt("vendor_id"));
            smartphone.setModel(rs.getString("model"));
            smartphone.setCharacteristicsId(rs.getInt("characteristics_id"));
            return smartphone;
        }
    }
}
