package ua.phones.db.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ua.phones.db.dao.interfaces.DisplayDAO;
import ua.phones.db.models.Display;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DisplayDAOImpl implements DisplayDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Display getDisplay(int id) {
        String sql = "SELECT * FROM displays WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new DisplayMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean insertDisplay(Display display) {
        String sql = "INSERT INTO displays (model, resolution, size, technology) VALUES (:model, :resolution, :size, :technology)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("model", display.getModel());
        params.addValue("resolution", display.getResolution());
        params.addValue("size", display.getSize());
        params.addValue("technology", display.getTechnology());
        try {
            jdbcTemplate.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean deleteDisplay(int id) {
        String sql = "DELETE FROM displays WHERE id=:id";
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
    public boolean updateDisplay(Display display) {
        String sql = "UPDATE displays SET model=:model, resolution=:resolution, size=:size, technology=:technology WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", display.getId());
        params.addValue("model", display.getModel());
        params.addValue("resolution", display.getResolution());
        params.addValue("size", display.getSize());
        params.addValue("technology", display.getTechnology());
        try {
            jdbcTemplate.update(sql, params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static final class DisplayMapper implements RowMapper<Display> {

        @Override
        public Display mapRow(ResultSet rs, int i) throws SQLException {
            Display display = new Display();
            display.setId(rs.getInt("id"));
            display.setModel(rs.getString("model"));
            display.setResolution(rs.getString("resolution"));
            display.setSize(rs.getDouble("size"));
            display.setTechnology(rs.getString("technology"));
            return display;
        }
    }
}
