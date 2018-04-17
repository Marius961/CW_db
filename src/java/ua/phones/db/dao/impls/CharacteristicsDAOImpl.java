package ua.phones.db.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    public Characteristics getCharacteristics(Characteristics characteristics) {
        String sql = "SELECT * FROM characteristics WHERE battery_volume=:btVol AND processor_id=:processorId AND camera_id=:cameraId AND display_id=:displayId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("btVol", characteristics.getBatteryVolume());
        params.addValue("processorId", characteristics.getProcessorId());
        params.addValue("cameraId", characteristics.getCameraId());
        params.addValue("displayId", characteristics.getDisplayId());
        try {
            return jdbcTemplate.queryForObject(sql, params, new CharacteristicsMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int insertCharacteristics(Characteristics characteristics) {
        String sql = "INSERT INTO characteristics (battery_volume, processor_id, camera_id, display_id) VALUES (:btVol, :processorId, :cameraId, :displayId)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("btVol", characteristics.getBatteryVolume());
        params.addValue("processorId", characteristics.getProcessorId());
        params.addValue("cameraId", characteristics.getCameraId());
        params.addValue("displayId", characteristics.getDisplayId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(sql, params, keyHolder);
            return Integer.parseInt(keyHolder.getKey().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void deleteCharacteristics(int id) {
        String sql = "DELETE FROM characteristics WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void updateCharacteristics(Characteristics characteristics) {
        String sql = "UPDATE characteristics SET battery_volume=:btVol, processor_id=:processorId, camera_id=:cameraId, display_id=:displayId WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", characteristics.getId());
        params.addValue("btVol", characteristics.getBatteryVolume());
        params.addValue("processorId", characteristics.getProcessorId());
        params.addValue("cameraId", characteristics.getCameraId());
        params.addValue("displayId", characteristics.getDisplayId());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public int getCameraCount(int cameraId) {
        String sql = "SELECT count(*) FROM characteristics WHERE camera_id=id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", cameraId);
        return jdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    @Override
    public int getProcessorCount(int processorId) {
        String sql = "SELECT count(*) FROM characteristics WHERE processor_id=id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", processorId);
        return jdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    @Override
    public int displayCount(int displayId) {
        String sql = "SELECT count(*) FROM characteristics WHERE display_id=id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", displayId);
        return jdbcTemplate.queryForObject(sql, params, Integer.class);
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

