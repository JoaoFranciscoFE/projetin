package com.example.demo.repository;

import com.example.demo.model.Curso;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CursoRepository {

    private final JdbcTemplate jdbcTemplate;

    public CursoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Curso> rowMapper = (rs, rowNum) -> {
        Curso curso = new Curso();
        curso.setId(rs.getInt("id"));
        curso.setNome(rs.getString("nome"));
        curso.setArea(rs.getString("area"));
        return curso;
    };

    public List<Curso> findAll() {
        return jdbcTemplate.query("SELECT * FROM CURSO", rowMapper);
    }

    public Optional<Curso> findById(int id) {
        return jdbcTemplate.query("SELECT * FROM CURSO WHERE id = ?", rowMapper, id)
                .stream()
                .findFirst();
    }

    public int save(Curso curso) {
        return jdbcTemplate.update("INSERT INTO CURSO (id, nome, area) VALUES (?, ?, ?)",
                curso.getId(), curso.getNome(), curso.getArea());
    }

    public int update(Curso curso) {
        return jdbcTemplate.update("UPDATE CURSO SET nome = ?, area = ? WHERE id = ?",
                curso.getNome(), curso.getArea(), curso.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM CURSO WHERE id = ?", id);
    }
}