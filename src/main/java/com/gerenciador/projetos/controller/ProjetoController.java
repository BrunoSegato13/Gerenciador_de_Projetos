package com.gerenciador.projetos.controller;


import com.gerenciador.projetos.model.Pessoa;
import com.gerenciador.projetos.model.Projeto;
import com.gerenciador.projetos.service.PessoaService;
import com.gerenciador.projetos.service.ProjetoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProjetoController {

    @Autowired
    private final ProjetoService projetoService;

    @Autowired
    private final PessoaService pessoaService;


    public ProjetoController(ProjetoService projetoService, PessoaService pessoaService) {
        this.projetoService = projetoService;
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public String listarProjetos(Model model){
        List<Projeto> projetoList = projetoService.listarProjetos();
        model.addAttribute("projetoList", projetoList);
        return "index";
    }

    @GetMapping("/detalhesProjeto/{id}")
    public String detalhesProjeto(@PathVariable("id") Long id, Model model){
        Projeto projeto = projetoService.buscarProjetoPorId(id);
        Pessoa gerente = pessoaService.buscarPessoa(projeto.getGerente());

        model.addAttribute("projeto",projeto);
        model.addAttribute("gerente",gerente);
        return "projeto/detalhesProjeto";
    }

    @GetMapping("/novoProjetoForm")
    public String addProjeto(Model model){
        Projeto projeto = new Projeto();

        List<Pessoa> pessoaList = pessoaService.listarPessoas();

        model.addAttribute("pessoaList",pessoaList);
        model.addAttribute("projeto",projeto);
        return "projeto/novoProjeto";
    }

    @PostMapping("/salvarProjeto")
    public String salvarProjeto(@ModelAttribute("projeto") Projeto projeto){
        projetoService.salvarProjeto(projeto);
        return "redirect:/";
    }

    @GetMapping("/editarProjetoForm/{id}")
    public String editarProjeto(@PathVariable("id") Long id, Model model){
        Projeto projeto = projetoService.buscarProjetoPorId(id);
        List<Pessoa> pessoaList = pessoaService.listarPessoas();

        model.addAttribute("pessoaList",pessoaList);
        model.addAttribute("projeto",projeto);
        return "projeto/editarProjeto";
    }

    @GetMapping("/deletarProjeto/{id}")
    public String deletarProjeto(@PathVariable("id") Long id){
        projetoService.deletarProjeto(id);
        return "redirect:/";
    }

    @GetMapping("/finalizarProjeto/{id}")
    public String finalizarProjeto(@PathVariable("id") Long id, Model model){
        Projeto projeto = projetoService.finalizarProjeto(id);

        model.addAttribute("projeto", projeto);
        return "redirect:/detalhesProjeto/"+id;
    }

    @PostMapping("/adicionarMembro/{id}")
    public ResponseEntity<Pessoa> adicionarMembro(@RequestBody Pessoa pessoa, @PathVariable("id") Long id){
        Pessoa pessoa1 = projetoService.addMembro(id,pessoa);
        return ResponseEntity.accepted().build();
    }
}
