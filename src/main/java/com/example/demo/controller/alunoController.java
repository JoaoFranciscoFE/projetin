package com.example.demo.controller;

import com.example.demo.model.Aluno;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class alunoController {
    private List<Aluno> alunos = new ArrayList<>();

    @PostMapping
    public String addAluno(@RequestBody Aluno aluno) {
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                return "Erro: ID já existe!";
            }
        }
        alunos.add(aluno);
        return "Aluno adicionado com sucesso!";
    }

    @GetMapping
    public List<Aluno> getAll() {
        return alunos;
    }

    @GetMapping("/{id}")
    public Aluno getById(@PathVariable int id) {
        return alunos.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/busca")
    public List<Aluno> buscarPorNome(@RequestParam String nome) {
        List<Aluno> resultado = new ArrayList<>();
        for (Aluno a : alunos) {
            if (a.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    @PutMapping("/{id}")
    public String updateAluno(@PathVariable int id, @RequestBody Aluno novoAluno) {
        for (Aluno a : alunos) {
            if (a.getId() == id) {
                a.setNome(novoAluno.getNome());
                a.setMatricula(novoAluno.getMatricula());
                a.setCurso(novoAluno.getCurso());
                a.setIdade(novoAluno.getIdade());
                a.setEmail(novoAluno.getEmail());
                a.setTelefone(novoAluno.getTelefone());
                return "Aluno atualizado com sucesso!";
            }
        }
        return "Aluno não encontrado!";
    }

    @DeleteMapping("/{id}")
    public String deleteAluno(@PathVariable int id) {
        Aluno aluno = alunos.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (aluno != null) {
            alunos.remove(aluno);
            return "Aluno removido com sucesso!";
        }
        return "Aluno não encontrado!";
    }
}
