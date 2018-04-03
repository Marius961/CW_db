package ua.phones.db.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ua.phones.db.dao.interfaces.CharacteristicsDAO;
import ua.phones.db.models.Characteristics;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CharacteristicsDAOImpl implements CharacteristicsDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Characteristics getCharacteristics(int id) {
        String sql = "SELECT * FROM characteristics WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new CharacteristicsMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean insertCharacteristics(Characteristics characteristics) {
        String sql = "INSERT INTO characteristics (battery_volume, processor_id, camera_id, display_id) VALUES (:btVol, :processorId, :cameraId, :displayId)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("btVol", characteristics.getBatteryVolume());
        params.addValue("processorId", characteristics.getProcessorId());
        params.addValue("cameraId", characteristics.getCameraId());
        params.addValue("displayId", characteristics.getDisplayId());
        try {
            jdbcTemplate.update(sql, params);
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteCharacteristics(int id) {
        String sql = "DELETE FROM characteristics WHERE id=:id";
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
    public boolean updateCharacteristics(Characteristics characteristics) {
        String sql = "UPDATE characteristics SET battery_volume=:btVol, processor_id=:processorId, camera_id=:cameraId, display_id=:displayId WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", characteristics.getId());
        params.addValue("btVol", characteristics.getBatteryVolume());
        params.addValue("processorId", characteristics.getProcessorId());
        params.addValue("cameraId", characteristics.getCameraId());
        params.addValue("displayId", characteristics.getDisplayId());
        try {
            jdbcTemplate.update(sql, params);
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static final class CharacteristicsMapper implements RowMapper<Characteristics> {

        @Override
        public Characteristics mapRow(ResultSet rs, int i) throws SQLException {
            Characteristics characteristics = new Characteristics();
            characteristics.setId(rs.getInt("id"));
            characteristics.setBatteryVolume(rs.getInt("battery_volume"));
            characteristics.setProcessorId(rs.getInt("processor_id"));
            characteristics.setCameraId(rs.getInt("camera_id"));
            characteristics.setDisplayId(rs.getInt("display_id"));
            return characteristics;
        }
    }
}

