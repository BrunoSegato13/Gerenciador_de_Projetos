package com.gerenciador.projetos.service;

import com.gerenciador.projetos.model.Pessoa;
import com.gerenciador.projetos.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PessoaService {

    @Autowired
    private final PessoaRepository pessoaRepository;


    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa buscarPessoa(Pessoa pessoa){
        Optional<Pessoa> optional = pessoaRepository.findById(pessoa.getIdPessoa());
        Pessoa pessoaEncontrada = null;
        if(optional.isPresent()){
            pessoaEncontrada = optional.get();
        } else {
            throw new RuntimeException("Pessoa não encontrada");
        }
        return pessoaEncontrada;
    }
    public Pessoa buscarPessoaPorId(Long id){
        Optional<Pessoa> optional = pessoaRepository.findById(id);
        Pessoa pessoa = null;
        if(optional.isPresent()){
            pessoa = optional.get();
        } else {
            throw new RuntimeException("Pessoa não encontrada");
        }
        return pessoa;
    }
    public List<Pessoa> listarPessoas(){
        return pessoaRepository.findAll();
    }

    public Pessoa salvar(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public void deletar(Long id){
        Pessoa pessoa = buscarPessoaPorId(id);
        pessoaRepository.delete(pessoa);

    }

}
