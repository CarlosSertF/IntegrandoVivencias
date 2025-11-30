package br.com.integrandovivencias.api.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate data_nascimento;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate data_cadastro;

    // LÓGICA DE RELACIONAMENTO
    // Um Paciente tem VÁRIAS Anamneses.
    // 'mappedBy': Avisa que o dono da relação é o campo 'paciente' na outra tabela.
    // 'cascade = ALL': Se eu deletar o paciente, deleto todas as fichas dele (limpeza automática).
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Anamnese> anamneses = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id") 
    private Usuario usuario;

    // Construtores, Getters e Setters

    public Paciente(){
    }

    public Paciente(LocalDate data_cadastro, LocalDate data_nascimento, String nome, Usuario usuario) {
        this.data_cadastro = data_cadastro;
        this.data_nascimento = data_nascimento;
        this.nome = nome;
        this.usuario = usuario;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public LocalDate getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(LocalDate data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}