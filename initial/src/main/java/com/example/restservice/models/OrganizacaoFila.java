package com.example.restservice.models;

@Entity
@Table(name = "organizacao_fila")
public class OrganizacaoFila {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column(name = "posicao_fila")
    private int posicaoFila;

   
}

