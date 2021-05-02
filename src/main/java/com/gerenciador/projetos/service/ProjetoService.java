package com.gerenciador.projetos.service;

import com.gerenciador.projetos.enums.Status;
import com.gerenciador.projetos.model.Pessoa;
import com.gerenciador.projetos.model.Projeto;
import com.gerenciador.projetos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProjetoService {

    @Autowired
    private final ProjetoRepository projetoRepository;

    @Autowired
    private final PessoaService pessoaService;

    public ProjetoService(ProjetoRepository projetoRepository, PessoaService pessoaService) {
        this.projetoRepository = projetoRepository;
        this.pessoaService = pessoaService;
    }

    public List<Projeto> listarProjetos(){
        return projetoRepository.findAll();

    }


    public Projeto buscarProjetoPorId(Long id){
        Optional<Projeto> optional = projetoRepository.findById(id);
        Projeto projeto = null;
        if(optional.isPresent()){
            projeto = optional.get();
        }
        return projeto;
    }

    public Projeto salvarProjeto(Projeto projeto){
        return projetoRepository.save(projeto);
    }


    public void deletarProjeto(Long id){
        Projeto projeto = buscarProjetoPorId(id);
        if(projeto.getStatus().equals(Status.ENCERRADO) || projeto.getStatus().equals(Status.EM_ANDAMENTO)
        || projeto.getStatus().equals(Status.INICIADO) ){
            throw new RuntimeException("Esse projeto não pode ser deletado.");
        }
        projetoRepository.delete(projeto);
    }

    public Projeto finalizarProjeto(Long id){
        Projeto projeto = buscarProjetoPorId(id);
        projeto.setDataFim(LocalDate.now());
        projeto.setStatus(Status.ENCERRADO);
        return projeto;
    }

    public Pessoa addMembro(Long idProjeto, Pessoa pessoa){
        Pessoa pessoa1 = pessoaService.salvar(pessoa);
        Projeto projeto = buscarProjetoPorId(idProjeto);

        projeto.setMembro(Collections.singleton(pessoa1));

        return pessoa1;
    }

}
