package com.gerenciador.projetos.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;
    @NotNull
    private String nome;
    private String cargo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String cpf;
    private boolean funcionario;

    @OneToMany(mappedBy = "idProjeto")
    private Set<Projeto> projeto = new HashSet<>();;

    @ManyToMany
    @JoinTable(
            name = "membros",
            joinColumns = @JoinColumn(name = "idPessoa"),
            inverseJoinColumns = @JoinColumn(name = "idProjeto")
    )
    Set<Projeto> projetosParticipacao;

    public Pessoa(Long idPessoa, String nome, String cargo, LocalDate dataNascimento, String cpf, boolean funcionario) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.cargo = cargo;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.funcionario = funcionario;
    }

    public Pessoa(String nome, String cargo, LocalDate dataNascimento, String cpf, boolean funcionario) {
        this.nome = nome;
        this.cargo = cargo;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.funcionario = funcionario;
    }

    public Pessoa() {
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isFuncionario() {
        return funcionario;
    }

    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }

    public Set<Projeto> getProjeto() {
        return projeto;
    }

    public void setProjeto(Set<Projeto> projeto) {
        this.projeto = projeto;
    }

    public Set<Projeto> getProjetosParticipacao() {
        return projetosParticipacao;
    }

    public void setProjetosParticipacao(Set<Projeto> projetosParticipacao) {
        this.projetosParticipacao = projetosParticipacao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(idPessoa, pessoa.idPessoa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPessoa);
    }
}
