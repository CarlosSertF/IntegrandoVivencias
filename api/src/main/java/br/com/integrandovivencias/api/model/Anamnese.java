package br.com.integrandovivencias.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "anamnese") // Garanta que o nome bate com o da sua tabela no pgAdmin
public class Anamnese {

    // --- Campos de Texto e Booleanos ---

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "queixa_atual", columnDefinition = "TEXT")
    private String queixaAtual;

    @Column(name = "tem_laudo")
    private boolean temLaudo;

    @Column(name = "laudo_descricao", columnDefinition = "TEXT")
    private String laudoDescricao;

    @Column(name = "toma_medicacao")
    private boolean tomaMedicacao;

    @Column(name = "medicacao_descricao", columnDefinition = "TEXT")
    private String medicacaoDescricao;

    @Column(name = "faz_acompanhamento")
    private boolean fazAcompanhamento;

    @Column(name = "acompanhamento_descricao", columnDefinition = "TEXT")
    private String acompanhamentoDescricao;

    @Column(name = "tem_dificuldade_fala")
    private boolean temDificuldadeFala;

    @Column(name = "preferencias_brincadeiras", columnDefinition = "TEXT")
    private String preferenciasBrincadeiras;

    // Campo de data (se você quiser ter no banco para ordenar depois)
    // Se no seu print não tem data, pode comentar ou remover, mas é bom ter!
    @Column(name = "data_realizacao")
    private LocalDateTime dataRealizacao = LocalDateTime.now();


    // --- RELACIONAMENTOS (As Foreign Keys do seu print: paciente_id e usuario_id) ---

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    // --- CONSTRUTORES ---

    // 1. Construtor Vazio (Obrigatório)
    public Anamnese() {
    }

    // 2. Construtor Cheio (Opcional, mas útil)
    // Dica: Não coloque o ID nem a Data no construtor, pois são automáticos.
    public Anamnese(String queixaAtual, boolean temLaudo, String laudoDescricao, 
                    boolean tomaMedicacao, String medicacaoDescricao, 
                    boolean fazAcompanhamento, String acompanhamentoDescricao,
                    boolean temDificuldadeFala, String preferenciasBrincadeiras,
                    Paciente paciente, Usuario usuario) {
        this.queixaAtual = queixaAtual;
        this.temLaudo = temLaudo;
        this.laudoDescricao = laudoDescricao;
        this.tomaMedicacao = tomaMedicacao;
        this.medicacaoDescricao = medicacaoDescricao;
        this.fazAcompanhamento = fazAcompanhamento;
        this.acompanhamentoDescricao = acompanhamentoDescricao;
        this.temDificuldadeFala = temDificuldadeFala;
        this.preferenciasBrincadeiras = preferenciasBrincadeiras;
        this.paciente = paciente;
        this.usuario = usuario;
    }

    // --- GETTERS E SETTERS ---
    // (Gere automaticamente no seu IDE para economizar tempo!)
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    

    public String getQueixaAtual() { return queixaAtual; }
    public void setQueixaAtual(String queixaAtual) { this.queixaAtual = queixaAtual; }

    public boolean isTemLaudo() { return temLaudo; }
    public void setTemLaudo(boolean temLaudo) { this.temLaudo = temLaudo; }

    public String getLaudoDescricao() { return laudoDescricao; }
    public void setLaudoDescricao(String laudoDescricao) { this.laudoDescricao = laudoDescricao; }

    public boolean isTomaMedicacao() { return tomaMedicacao; }
    public void setTomaMedicacao(boolean tomaMedicacao) { this.tomaMedicacao = tomaMedicacao; }

    public String getMedicacaoDescricao() { return medicacaoDescricao; }
    public void setMedicacaoDescricao(String medicacaoDescricao) { this.medicacaoDescricao = medicacaoDescricao; }

    public boolean isFazAcompanhamento() { return fazAcompanhamento; }
    public void setFazAcompanhamento(boolean fazAcompanhamento) { this.fazAcompanhamento = fazAcompanhamento; }

    public String getAcompanhamentoDescricao() { return acompanhamentoDescricao; }
    public void setAcompanhamentoDescricao(String acompanhamentoDescricao) { this.acompanhamentoDescricao = acompanhamentoDescricao; }

    public boolean isTemDificuldadeFala() { return temDificuldadeFala; }
    public void setTemDificuldadeFala(boolean temDificuldadeFala) { this.temDificuldadeFala = temDificuldadeFala; }

    public String getPreferenciasBrincadeiras() { return preferenciasBrincadeiras; }
    public void setPreferenciasBrincadeiras(String preferenciasBrincadeiras) { this.preferenciasBrincadeiras = preferenciasBrincadeiras; }

    public LocalDateTime getDataRealizacao() { return dataRealizacao; }
    public void setDataRealizacao(LocalDateTime dataRealizacao) { this.dataRealizacao = dataRealizacao; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}