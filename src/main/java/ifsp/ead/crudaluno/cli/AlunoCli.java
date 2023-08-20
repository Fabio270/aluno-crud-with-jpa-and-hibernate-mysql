package ifsp.ead.crudaluno.cli;

import ifsp.ead.crudaluno.controler.AlunoControler;
import ifsp.ead.crudaluno.model.Aluno;

import java.util.List;
import java.util.Scanner;

public class AlunoCli {
    public static void menu() {
        AlunoControler controler = new AlunoControler();
        Integer option = 2;
        Scanner scanner = new Scanner(System.in);
        while (option != 6){
            System.out.println("Escolha uma opção: 1.Cadastrar   2.Excluir   3.Alterar   4.Buscar aluno pelo nome   5.Listar alunos (com status aprovação)   6.SAIR");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1 -> {
                    System.out.println("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.println("Registro Acadêmico: ");
                    String ra = scanner.nextLine();
                    System.out.println("Email: ");
                    String email = scanner.nextLine();
                    System.out.println("Nota 1: ");
                    Double nota1 = scanner.nextDouble();
                    System.out.println("Nota 2: ");
                    Double nota2 = scanner.nextDouble();
                    System.out.println("Nota 3: ");
                    Double nota3 = scanner.nextDouble();
                    controler.createAluno(nome, ra, email, nota1, nota2, nota3);
                }
                case 2 -> {
                    System.out.println("Informe o Id do aluno: ");
                    Integer id = scanner.nextInt();
                    controler.deleteAluno(id);
                }
                case 3 -> {
                    System.out.println("Digite o ID do aluno a ser alterado: ");
                    Integer id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Digite os novos dados do aluno.");
                    System.out.println("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.println("Registro Acadêmico: ");
                    String ra = scanner.nextLine();
                    System.out.println("Email: ");
                    String email = scanner.nextLine();
                    System.out.println("Nota 1: ");
                    Double nota1 = scanner.nextDouble();
                    System.out.println("Nota 2: ");
                    Double nota2 = scanner.nextDouble();
                    System.out.println("Nota 3: ");
                    Double nota3 = scanner.nextDouble();
                    controler.updateAluno(id,nome, ra, email, nota1, nota2, nota3);
                }
                case 4 -> {
                    System.out.println("Digite o nome do aluno: ");
                    String nome = scanner.nextLine();
                    List<Aluno> alunoList= controler.findAlunoByName(nome);
                    System.out.println("Alunos encontrados: " + alunoList.size());
                    for (Aluno aluno: alunoList){
                        System.out.println(aluno.toString());
                    }
                }
                case 5 -> {
                    List<Aluno> alunoList = controler.findAllAluno();
                    for(Aluno aluno: alunoList){
                        double media = (aluno.getNota1()+ aluno.getNota2()+ aluno.getNota3())/3;
                        if (media>=6){
                            System.out.println("Aluno " +aluno.getNome()+ " aprovado! Média: " + media);
                        } else if (media>=4) {
                            System.out.println("Aluno " +aluno.getNome()+ " IFA! Média: " + media);
                        }
                        else {
                            System.out.println("Aluno " +aluno.getNome()+ " reprovado! Média:" + media);
                        }
                    }
                }
                case 6 -> System.out.println("Finalizando Programa!");
                default -> throw new IllegalStateException("Unexpected value: " + option);
            }


        }


    }
}
