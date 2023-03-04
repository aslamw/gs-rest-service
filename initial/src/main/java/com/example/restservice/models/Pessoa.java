package com.example.restservice.models;

@Entity
@Table(name = "pessoas")

public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;

    @Column(name = "posicao_fila")
    private int posicaoFila;

    @OneToMany(mappedBy = "pessoa")
    private List<OrganizacaoFila> organizacaoFila = new ArrayList<>();


}
