package com.example.demo.controller;

import com.example.demo.model.Curso;
import com.example.demo.repository.CursoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoRepository cursoRepository;

    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @PostMapping
    public ResponseEntity<String> addCurso(@RequestBody Curso curso) {
        if (cursoRepository.findById(curso.getId()).isPresent()) {
            return ResponseEntity.badRequest().body("Erro: ID do curso já existe!");
        }
        cursoRepository.save(curso);
        return ResponseEntity.ok("Curso adicionado com sucesso!");
    }

    @GetMapping
    public List<Curso> getAll() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(@PathVariable int id) {
        return cursoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCurso(@PathVariable int id, @RequestBody Curso novoCurso) {
        if (cursoRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        novoCurso.setId(id);
        cursoRepository.update(novoCurso);
        return ResponseEntity.ok("Curso atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCurso(@PathVariable int id) {
        if (cursoRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cursoRepository.delete(id);
        return ResponseEntity.ok("Curso removido com sucesso!");
    }
}