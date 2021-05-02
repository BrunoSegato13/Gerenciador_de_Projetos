package com.gerenciador.projetos.controller;

import com.gerenciador.projetos.model.Pessoa;
import com.gerenciador.projetos.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public String viewPessoa(Model model){
        List<Pessoa> pessoaList = pessoaService.listarPessoas();
        model.addAttribute("pessoaList",pessoaList);
        return "pessoa/pessoas";
    }

    @GetMapping("/novaPessoaForm")
    public String addPessoa(Model model){
        Pessoa pessoa = new Pessoa();
        model.addAttribute("pessoa",pessoa);
        return "pessoa/novaPessoa";
    }
    @PostMapping("/salvarPessoa")
    public String savarPessoa(@ModelAttribute("pessoa") Pessoa pessoa){
        pessoaService.salvar(pessoa);
        return "redirect:/pessoa";
    }

    @GetMapping("/editarPessoaForm/{id}")
    public String editarPessoa(@PathVariable("id") Long id, Model model){

        Pessoa pessoa = pessoaService.buscarPessoaPorId(id);
        model.addAttribute("pessoa",pessoa);
        return "pessoa/editarPessoa";
    }

    @GetMapping("/deletarPessoa/{id}")
    public String deletarPessoa(@PathVariable("id") Long id){
        pessoaService.deletar(id);
        return "redirect:/pessoa";
    }
}
