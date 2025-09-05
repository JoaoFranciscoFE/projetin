package com.example.demo.repository;

import com.example.demo.model.Aluno;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlunoRepository {

    private final JdbcTemplate jdbcTemplate;

    public AlunoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Aluno> rowMapper = (rs, rowNum) -> {
        Aluno aluno = new Aluno();
        aluno.setId(rs.getInt("id"));
        aluno.setNome(rs.getString("nome"));
        aluno.setMatricula(rs.getString("matricula"));
        aluno.setCursoId(rs.getInt("curso_id"));
        aluno.setIdade(rs.getInt("idade"));
        aluno.setEmail(rs.getString("email"));
        aluno.setTelefone(rs.getString("telefone"));
        return aluno;
    };

    public List<Aluno> findAll() {
        return jdbcTemplate.query("SELECT * FROM ALUNO", rowMapper);
    }

    public Optional<Aluno> findById(int id) {
        return jdbcTemplate.query("SELECT * FROM ALUNO WHERE id = ?", rowMapper, id)
                .stream()
                .findFirst();
    }

    public List<Aluno> findByName(String nome) {
        return jdbcTemplate.query("SELECT * FROM ALUNO WHERE LOWER(nome) LIKE ?", rowMapper, "%" + nome.toLowerCase() + "%");
    }

    public int save(Aluno aluno) {
        return jdbcTemplate.update("INSERT INTO ALUNO (id, nome, matricula, curso_id, idade, email, telefone) VALUES (?, ?, ?, ?, ?, ?, ?)",
                aluno.getId(), aluno.getNome(), aluno.getMatricula(), aluno.getCursoId(), aluno.getIdade(), aluno.getEmail(), aluno.getTelefone());
    }

    public int update(Aluno aluno) {
        return jdbcTemplate.update("UPDATE ALUNO SET nome = ?, matricula = ?, curso_id = ?, idade = ?, email = ?, telefone = ? WHERE id = ?",
                aluno.getNome(), aluno.getMatricula(), aluno.getCursoId(), aluno.getIdade(), aluno.getEmail(), aluno.getTelefone(), aluno.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM ALUNO WHERE id = ?", id);
    }
}