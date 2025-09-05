package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class alunoController {

    private final AlunoRepository alunoRepository;

    public alunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @PostMapping
    public ResponseEntity<String> addAluno(@RequestBody Aluno aluno) {
        if (alunoRepository.findById(aluno.getId()).isPresent()) {
            return ResponseEntity.badRequest().body("Erro: ID do aluno já existe!");
        }
        alunoRepository.save(aluno);
        return ResponseEntity.ok("Aluno adicionado com sucesso!");
    }

    @GetMapping
    public List<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getById(@PathVariable int id) {
        return alunoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/busca")
    public List<Aluno> buscarPorNome(@RequestParam String nome) {
        return alunoRepository.findByName(nome);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAluno(@PathVariable int id, @RequestBody Aluno novoAluno) {
        if (alunoRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        novoAluno.setId(id);
        alunoRepository.update(novoAluno);
        return ResponseEntity.ok("Aluno atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAluno(@PathVariable int id) {
        if (alunoRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        alunoRepository.delete(id);
        return ResponseEntity.ok("Aluno removido com sucesso!");
    }
}