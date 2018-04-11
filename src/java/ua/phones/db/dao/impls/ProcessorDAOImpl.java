package ua.phones.db.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.phones.db.dao.interfaces.ProcessorDAO;
import ua.phones.db.models.Processor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProcessorDAOImpl implements ProcessorDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Processor getProcessor(int id) {
        String sql = "SELECT * FROM processors WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new ProcessorMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Processor getProcessor(Processor processor) {
        String sql = "SELECT * FROM processors WHERE model=:model AND cores=:cores AND frequency=:frequency";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("model", processor.getModel());
        params.addValue("cores", processor.getCores());
        params.addValue("frequency", processor.getFrequency());
        try {
            return jdbcTemplate.queryForObject(sql, params, new ProcessorMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int insertProcessor(Processor processor) {
        String sql = "INSERT INTO processors (model, cores, frequency) VALUES (:model, :cores, :frequency)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("model", processor.getModel());
        params.addValue("cores", processor.getCores());
        params.addValue("frequency", processor.getFrequency());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(sql, params, keyHolder);
            return Integer.parseInt(keyHolder.getKey().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void deleteProcessor(int id) {
        String sql = "DELETE FROM processors WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void updateProcessor(Processor processor) {
        String sql = "UPDATE processors SET model=:model, cores=:cores, frequency=:frequency WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", processor.getId());
        params.addValue("model", processor.getModel());
        params.addValue("cores", processor.getCores());
        params.addValue("frequency", processor.getFrequency());
        jdbcTemplate.update(sql, params);
    }

    private static final class ProcessorMapper implements RowMapper<Processor> {

        @Override
        public Processor mapRow(ResultSet rs, int i) throws SQLException {
            Processor processor = new Processor();
            processor.setId(rs.getInt("id"));
            processor.setModel(rs.getString("model"));
            processor.setCores(rs.getInt("cores"));
            processor.setFrequency(rs.getDouble("frequency"));
            return processor;
        }
    }
}
