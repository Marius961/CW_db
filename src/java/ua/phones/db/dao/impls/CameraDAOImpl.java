package ua.phones.db.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.phones.db.dao.interfaces.CameraDAO;
import ua.phones.db.models.Camera;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CameraDAOImpl implements CameraDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Camera getCamera(int id) {
        String sql = "SELECT * FROM cameras WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new CameraMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Camera getCamera(Camera camera) {
        String sql = "SELECT * FROM cameras WHERE resolution=:resolution AND num_of_pixels=:numOfPixels";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("resolution", camera.getResolution());
        params.addValue("numOfPixels", camera.getNumOfPixels());
        try {
            return jdbcTemplate.queryForObject(sql, params, new CameraMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int insertCamera(Camera camera) {
        String sql = "INSERT INTO cameras (resolution, num_of_pixels) VALUES (:resolution, :numOfPixels)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("resolution", camera.getResolution());
        params.addValue("numOfPixels", camera.getNumOfPixels());
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(sql, params, keyHolder);
            return Integer.parseInt(keyHolder.getKey().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void deleteCamera(int id) {
        String sql = "DELETE FROM cameras WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void updateCamera(Camera camera) {
        String sql = "UPDATE cameras SET resolution=:resolution, num_of_pixels=:numOfPixels WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", camera.getId());
        params.addValue("resolution", camera.getResolution());
        params.addValue("numOfPixels", camera.getNumOfPixels());
        jdbcTemplate.update(sql, params);
    }

    private static final class CameraMapper implements RowMapper<Camera> {

        @Override
        public Camera mapRow(ResultSet rs, int i) throws SQLException {
            Camera camera = new Camera();
            camera.setId(rs.getInt("id"));
            camera.setResolution(rs.getString("resolution"));
            camera.setNumOfPixels(rs.getInt("num_of_pixels"));
            return camera;
        }
    }
}