package com.gerenciador.projetos.model;

import com.gerenciador.projetos.enums.Risco;
import com.gerenciador.projetos.enums.Status;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "projeto")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProjeto;
    @NotNull
    private String nome;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPrevisaoFim;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFim;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private Status status;
    private Float orcamento;

    @Enumerated(EnumType.STRING)
    private Risco risco;
    @ManyToOne
    @JoinColumn(name = "idPessoa", nullable = false)
    private Pessoa gerente;

    @ManyToMany(mappedBy = "projetosParticipacao")
    Set<Pessoa> membro = new HashSet<>();;

    public Projeto(Long idProjeto, String nome, LocalDate dataInicio, LocalDate dataPrevisaoFim,
                   LocalDate dataFim, String descricao, Status status, Float orcamento, Risco risco, Pessoa gerente) {
        this.idProjeto = idProjeto;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataPrevisaoFim = dataPrevisaoFim;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.status = status;
        this.orcamento = orcamento;
        this.risco = risco;
        this.gerente = gerente;
    }

    public Projeto(String nome, LocalDate dataInicio, LocalDate dataPrevisaoFim, LocalDate dataFim,
                   String descricao, Status status, Float orcamento, Risco risco, Pessoa gerente) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataPrevisaoFim = dataPrevisaoFim;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.status = status;
        this.orcamento = orcamento;
        this.risco = risco;
        this.gerente = gerente;
    }

    public Projeto() {
    }

    public Long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Long idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataPrevisaoFim() {
        return dataPrevisaoFim;
    }

    public void setDataPrevisaoFim(LocalDate dataPrevisaoFim) {
        this.dataPrevisaoFim = dataPrevisaoFim;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Float getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Float orcamento) {
        this.orcamento = orcamento;
    }

    public Risco getRisco() {
        return risco;
    }

    public void setRisco(Risco risco) {
        this.risco = risco;
    }

    public Pessoa getGerente() {
        return gerente;
    }

    public void setGerente(Pessoa gerente) {
        this.gerente = gerente;
    }

    public Set<Pessoa> getMembro() {
        return membro;
    }

    public void setMembro(Set<Pessoa> membro) {
        this.membro = membro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projeto projeto = (Projeto) o;
        return idProjeto.equals(projeto.idProjeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProjeto);
    }

}
