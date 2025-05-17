import java.util.Random;
import java.util.Scanner;

public class trabalho {

    public static void main(String[] args) {
        Random rnd = new Random();
        Scanner scn = new Scanner(System.in);
        Aluno[] alunos = new Aluno[10];
        Disciplina[] disciplinas = new Disciplina[10];

        init(alunos, disciplinas, rnd);

        int resposta = 0;
        while (resposta != 9) {
            System.out.println("- - - - - SISTEMA DE MATRÍCULAS - - - - -");
            System.out.println("1. Cadastrar disciplinas");
            System.out.println("2. Remover disciplinas");
            System.out.println("3. Listar as disciplinas");
            System.out.println("4. Cadastrar alunos");
            System.out.println("5. Alterar os dados de aluno");
            System.out.println("6. Listar alunos");
            System.out.println("7. Matricular alunos em disciplinas");
            System.out.println("8. Listar as matrículas");
            System.out.println("9. Sair");
            resposta = scn.nextInt();
            if (resposta == 1) {
                cadastrarDisciplinas(disciplinas, scn, rnd);
            }
            if (resposta == 2) {
                removerDisciplinas(disciplinas, alunos, scn);
            }
            if (resposta == 3) {
                listarDisciplinas(disciplinas);
            }
            if (resposta == 4) {
                cadastrarAlunos(alunos, disciplinas, scn, rnd);
            }
            if (resposta == 5) {
                alterarDadosAlunos(alunos, scn);
            }
            if (resposta == 6) {
                listarAlunos(alunos, scn);
            }
            if (resposta == 7) {
                matricularAlunosDisciplinas(alunos, disciplinas, scn);
            }
            if (resposta == 8) {
                listarMatriculas(disciplinas, alunos);
            }
            if (resposta == 9) {
                System.out.println("Fim do sistema!");
            }
        }

    }

    static void init(Aluno[] alunos, Disciplina[] disciplinas, Random rnd) {
        String[] nomesDisciplinas = {"Fundamentos da Programação",
                "Programação Orientada a Objetos", "Banco de Dados"};
        String[] nomesSiglas = {"FP", "POO", "BD"};
        String[] nomesProfessores = {"Maurilio", "Luciano", "Marcelo"};
        String[] nomesAlunos = {"Pedro", "Tiago", "Maria", "Lucas", "Victor"};
        String[] endereco = {"Vila dos Técnicos", "Alvorada", "Bromélias",
                "Quitandinha", "Novo Horizonte"};

        // ALEATORIZAR AS DISCIPLINAS
        for (int i = 0; i < 3; i++) {
            Disciplina d = new Disciplina();

            d.ano = rnd.nextInt(3) + 1;
            d.nomeDisciplina = nomesDisciplinas[i];
            d.sigla = nomesSiglas[i];
            d.nomeProfessor = nomesProfessores[i];

            d.code = gerarCodigoDisciplinas(disciplinas, rnd);

            inserirDisciplinas(d, disciplinas);
        }

        // ALEATORIZAR OS ALUNOS
        for (int i = 0; i < 5; i++) {
            Aluno a = new Aluno();

            a.nome = nomesAlunos[i];
            a.endereco = endereco[i];
            a.quantDisciplinas = rnd.nextInt(3) + 1;

            for (int j = 0; j < a.quantDisciplinas; j++) {
                double aux = rnd.nextInt(100) + 1;
                a.notas[j] = aux / 10;
                a.media += a.notas[j];
                int auxRandom = rnd.nextInt(3);
                a.NomeDisciplinasMat[j] = disciplinas[auxRandom].sigla;
                a.CodeDisciplinasMat[j] = disciplinas[auxRandom].code;

                int k = 0;
                while (k < a.quantDisciplinas) {
                    while (a.CodeDisciplinasMat[k] == a.CodeDisciplinasMat[j] && j != k) {
                        k = 0;
                        auxRandom = rnd.nextInt(3);
                        a.NomeDisciplinasMat[j] = disciplinas[auxRandom].sigla;
                        a.CodeDisciplinasMat[j] = disciplinas[auxRandom].code;
                    }
                    k++;
                }
            }
            a.media /= a.quantDisciplinas;
            a.media = Math.round(a.media * 10) / 10.0;


            a.code = gerarCodigoAlunos(alunos, rnd);

            inserirAlunos(a, alunos);
        }
    }

    static void inserirAlunos(Aluno a, Aluno[] alunos) {
        int contador = 0;
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null) {
                contador++;
            }
        }
        if (a != null) {
            alunos[contador] = a;
        }
    }

    static void inserirDisciplinas(Disciplina d, Disciplina[] disciplinas) {
        int contador = 0;
        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] != null) {
                contador++;
            }
        }
        if (d != null) {
            disciplinas[contador] = d;
        }
    }

    static void cadastrarDisciplinas(Disciplina[] disciplinas, Scanner scn, Random rnd) {
        int contador = 0;
        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] != null) {
                contador++;
            }
        }
        if (contador == disciplinas.length) {
            System.out.println("LIMITE MÁXIMO DE DISCIPLINAS ATINGIDO!");
        } else {
            Disciplina d = new Disciplina();

            d = new Disciplina();
            System.out.println("Digite o ano da disciplina: ");
            d.ano = scn.nextInt();
            System.out.println("Digite o nome da disciplina: ");
            d.nomeDisciplina = scn.nextLine(); //repetida pra funcionar o scn.nextLine
            d.nomeDisciplina = scn.nextLine();
            System.out.println("Digite a sigla da disciplina: ");
            d.sigla = scn.nextLine();
            System.out.println("Digite o nome do professor: ");
            d.nomeProfessor = scn.nextLine();

            d.code = gerarCodigoDisciplinas(disciplinas, rnd);
            System.out.println("------------------------------------------------");
            System.out.printf("%-6s %-5s %-8s %-4s %s\n", "ID", "Ano", "Nome", "Sigla", "Professor");
            System.out.printf("%-6d %-5s %-8s %-4s %s\n", d.code,
                    d.ano,
                    d.nomeDisciplina,
                    d.sigla,
                    d.nomeProfessor);
            System.out.println("------------------------------------------------");
            System.out.println("Disciplina cadastrada com sucesso!");

            inserirDisciplinas(d, disciplinas);
        }
    }

    static int gerarCodigoDisciplinas(Disciplina[] disciplinas, Random rnd) {
        int code = rnd.nextInt(9000) + 1000;
        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] != null) {
                while (disciplinas[i].code == code) {
                    code = rnd.nextInt(9000) + 1000;
                }
            }
        }
        return code;
    }

    static void removerDisciplinas(Disciplina[] disciplinas, Aluno[] alunos, Scanner scn) {
        System.out.println("Informe o código da disciplina que deseja remover" +
                " (digite -1 caso queira sair pra olhar o código): ");
        int resposta = scn.nextInt();
        while ((resposta < 1000 || resposta > 9999) && resposta != -1) {
            System.out.print("Código não encontrado, informe novamente (-1 para sair): ");
            resposta = scn.nextInt();
        }

        if (resposta != -1) {
            int aux = 0;
            for (int i = 0; i < disciplinas.length; i++) {
                if (disciplinas[i] != null && disciplinas[i].code == resposta) {

                    //REMOVENDO DO VETOR DISCIPLINAS
                    disciplinas[i] = null;
                    for (int j = i; j < disciplinas.length - 1; j++) {
                        disciplinas[j] = disciplinas[j + 1];
                    }

                    //REMOVENDO DOS ALUNOS QUE TINHAM
                    removerDisciplinaAlunos(alunos, i, resposta);

                    System.out.println("Disciplina removida com sucesso!");
                    aux = 1;
                }
            }
            if (aux == 0) {
                System.out.println("Código não encontrado.");
            }
        }

    }

    static void removerDisciplinaAlunos(Aluno[] alunos, int i, int resposta) {
        for (int j = 0; j < alunos.length; j++) {
            if (alunos[j] != null) {
                for (int k = 0; k < alunos[j].quantDisciplinas; k++) {
                    if (alunos[j].CodeDisciplinasMat[k] == resposta) {
                        alunos[j].CodeDisciplinasMat[k] = 0;
                        alunos[j].NomeDisciplinasMat[k] = "";
                        for (int l = k; l < alunos[j].quantDisciplinas - 1; l++) {
                            alunos[j].CodeDisciplinasMat[l] = alunos[j].CodeDisciplinasMat[l + 1];
                            alunos[j].CodeDisciplinasMat[l + 1] = 0;
                            alunos[j].NomeDisciplinasMat[l] = alunos[j].NomeDisciplinasMat[l + 1];
                            alunos[j].NomeDisciplinasMat[l + 1] = "";
                            alunos[j].notas[l] = alunos[j].notas[l + 1];
                            alunos[j].notas[l + 1] = 0;
                        }
                        alunos[j].quantDisciplinas--;
                        if (alunos[j].quantDisciplinas == 0) {
                            alunos[j].notas[0] = 0.0;
                        }
                    }
                }
                alunos[j].media = 0;
                for (int k = 0; k < alunos[j].quantDisciplinas; k++) {
                    alunos[j].media += alunos[j].notas[k];
                }
                alunos[j].media /= alunos[j].quantDisciplinas;
                alunos[j].media = Math.round(alunos[j].media * 10) / 10.0;
            }
        }
    }

    static void listarDisciplinas(Disciplina[] disciplinas) {
        System.out.printf("%-6s %-32s %-6s %-20s\n", "ID", "Nome", "Sigla", "Professor");
        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] != null) {
                System.out.printf("%-6d %-32s %-6s %-20s\n", disciplinas[i].code, disciplinas[i].nomeDisciplina, disciplinas[i].sigla, disciplinas[i].nomeProfessor);
            }
        }
    }

    static void cadastrarAlunos(Aluno[] alunos, Disciplina[] disciplinas, Scanner scn, Random rnd) {
        Aluno a = new Aluno();

        int contador = 0;
        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] != null) {
                contador++;
            }
        }
        if (contador == alunos.length) {
            System.out.println("LIMITE MÁXIMO DE ALUNOS ATINGIDO!");
        } else {
            a = new Aluno();
            System.out.println("Digite o nome do aluno: ");
            a.nome = scn.nextLine(); //duplicado pro scn.nextLine(); funcionar
            a.nome = scn.nextLine();
            System.out.println("Digite o endereço do aluno: ");
            a.endereco = scn.nextLine();
            a.quantDisciplinas = 0;
            a.media = 0;
            a.code = gerarCodigoAlunos(alunos, rnd);

            System.out.printf("%-6s %-10s %-4s %-20s %s\n", "ID", "Nome", "Qtd", "Disciplinas", "Média");
            String disciplinasAux = "";
            for (int i = 0; i < a.quantDisciplinas; i++) {
                if (i != a.quantDisciplinas - 1) {
                    disciplinasAux = disciplinasAux + a.NomeDisciplinasMat[i] + ", ";
                } else {
                    disciplinasAux = disciplinasAux + a.NomeDisciplinasMat[i];
                }
            }

            System.out.printf("%-6d %-10s %-4d %-20s %.1f\n",
                    a.code,
                    a.nome,
                    a.quantDisciplinas,
                    disciplinasAux,
                    a.media);
            System.out.println("------------------------------------------------");
            System.out.println("Aluno cadastrado com sucesso!");

            inserirAlunos(a, alunos);
        }
    }

    static int gerarCodigoAlunos(Aluno[] alunos, Random rnd) {
        int code = rnd.nextInt(9000) + 1000;
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null) {
                while (alunos[i].code == code) {
                    code = rnd.nextInt(9000) + 1000;
                }
            }
        }
        return code;
    }

    static void alterarDadosAlunos(Aluno[] alunos, Scanner scn) {
        int contador = 0;
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null) {
                contador++;
            }
        }

        System.out.print("Digite o código do aluno que deseja alterar os dados " +
                "(digite -1 caso queira sair pra olhar o código): ");
        int resposta = scn.nextInt();
        while (resposta != -1 && resposta < 1000 || resposta > 9999) {
            System.out.print("Código inválido, informe novamente (-1 para sair): ");
            resposta = scn.nextInt();
        }

        if (resposta != -1) {
            int aux = 0;
            for (int i = 0; i < alunos.length; i++) {
                if (alunos[i] != null && alunos[i].code == resposta) {
                    System.out.println("Informe o novo nome do aluno: ");
                    alunos[i].nome = scn.nextLine(); //repetido pra funcionar o scn.nextLine();
                    alunos[i].nome = scn.nextLine();
                    System.out.println("Informe o novo endereço do aluno: ");
                    alunos[i].endereco = scn.nextLine();
                    alunos[i].media = 0;
                    for (int j = 0; j < alunos[i].quantDisciplinas; j++) {
                        System.out.println("Informe a nova nota da disciplina "
                                + alunos[i].NomeDisciplinasMat[j] + "(0,0 - 10,0): ");
                        alunos[i].notas[j] = scn.nextDouble();
                        while (alunos[i].notas[j] < 0 || alunos[i].notas[j] > 10) {
                            System.out.print("Inválido. Insira novamente (0,0 - 10,0): ");
                            alunos[i].notas[j] = scn.nextDouble();
                        }
                        alunos[i].media += alunos[i].notas[j];
                    }
                    alunos[i].media /= alunos[i].quantDisciplinas;
                    alunos[i].media = Math.round(alunos[i].media * 10) / 10.0;
                    System.out.println("------------------------------------------------");
                    aux = 1;
                }
            }
            if (aux == 0) {
                System.out.println("Código não encontrado.");
            }
            if (aux == 1) {
                System.out.println("Dados alterados com sucesso!");
            }
        }
    }

    static void listarAlunos(Aluno[] alunos, Scanner scn) {
        int resposta = 0;

        while (resposta < 1 || resposta > 2) {

            System.out.println("- - - - - LISTAR - - - - -");
            System.out.println("1. Pela ordem de cadastro");
            System.out.println("2. Pela ordem decrescente da média de nota");
            resposta = scn.nextInt();

            if (resposta == 1) {

                System.out.printf("%-6s %-10s %-4s %-20s %s\n", "ID", "Nome", "Qtd", "Disciplinas", "Média");
                for (int i = 0; i < alunos.length; i++) {
                    if (alunos[i] != null) {
                        String disciplinasAux = "";
                        for (int j = 0; j < alunos[i].quantDisciplinas; j++) {
                            if (j != alunos[i].quantDisciplinas - 1) {
                                disciplinasAux = disciplinasAux + alunos[i].NomeDisciplinasMat[j] + ", ";
                            } else {
                                disciplinasAux = disciplinasAux + alunos[i].NomeDisciplinasMat[j];
                            }
                        }

                        System.out.printf("%-6d %-10s %-4d %-20s %.1f\n", alunos[i].code, alunos[i].nome, alunos[i].quantDisciplinas, disciplinasAux, alunos[i].media);
                    }
                }

            }

            if (resposta == 2) {

                System.out.printf("%-6s %-10s %-4s %-20s %s\n", "ID", "Nome", "Qtd", "Disciplinas", "Nota");
                Aluno[] alunosDecrescente = new Aluno[alunos.length];
                alunosDecrescente = alunos;
                for (int i = 0; i < alunosDecrescente.length; i++) {
                    for (int j = 0; j < alunosDecrescente.length - 1; j++) {
                        if (alunosDecrescente[i] != null && alunosDecrescente[j] != null) {
                            if (alunosDecrescente[i].media > alunosDecrescente[j].media) {
                                Aluno aux = alunosDecrescente[j];
                                alunosDecrescente[j] = alunosDecrescente[i];
                                alunosDecrescente[i] = aux;
                            }
                        }
                    }
                }

                for (int i = 0; i < alunosDecrescente.length; i++) {

                    if (alunosDecrescente[i] != null) {
                        String disciplinasAux = "";
                        for (int j = 0; j < alunosDecrescente[i].quantDisciplinas; j++) {
                            if (alunosDecrescente[j] != null) {
                                if (j != alunosDecrescente[i].quantDisciplinas - 1) {
                                    disciplinasAux = disciplinasAux + alunosDecrescente[i].NomeDisciplinasMat[j] + ", ";
                                } else {
                                    disciplinasAux = disciplinasAux + alunosDecrescente[i].NomeDisciplinasMat[j];
                                }
                            }
                        }

                        System.out.printf("%-6d %-10s %-4d %-20s %.1f\n", alunosDecrescente[i].code, alunosDecrescente[i].nome, alunosDecrescente[i].quantDisciplinas, disciplinasAux, alunosDecrescente[i].media);
                    }

                }

            }

        }

    }

    static void matricularAlunosDisciplinas(Aluno[] alunos, Disciplina[] disciplinas, Scanner scn) {

        System.out.print("Digite o código do aluno que deseja matricular " +
                "(digite -1 caso queira sair pra olhar o código): ");
        int resposta = scn.nextInt();
        while (resposta != -1 && resposta < 1000 || resposta > 9999) {
            System.out.print("Código inválido, informe novamente (-1 para sair): ");
            resposta = scn.nextInt();
        }

        if (resposta != -1) {
            int aux = 0;
            for (int i = 0; i < disciplinas.length; i++) {
                if (alunos[i] != null && alunos[i].code == resposta) {
                    listarDisciplinas(disciplinas);
                    boolean flag = false;
                    int aux2 = 0;
                    while (!flag) {
                        System.out.println("Informe o código da disciplina que deseja matricular o aluno: ");
                        int codigo = scn.nextInt();
                        for (int j = 0; j < disciplinas.length; j++) {
                            if (disciplinas[j] != null) {
                                if (codigo == disciplinas[j].code) {
                                    flag = true;
                                    aux2 = j;
                                }
                            }
                        }
                        if (!flag) {
                            System.out.println("Código inválido ou não encontrado.");
                        }
                    }
                    assert disciplinas[i] != null;
                    alunos[i].CodeDisciplinasMat[alunos[i].quantDisciplinas] = disciplinas[aux2].code;
                    alunos[i].NomeDisciplinasMat[alunos[i].quantDisciplinas] = disciplinas[aux2].sigla;
                    alunos[i].quantDisciplinas++;
                    System.out.println("Digite a nota do aluno na disciplina (0,0 - 10,0):");
                    alunos[i].notas[alunos[i].quantDisciplinas - 1] = scn.nextDouble();
                    while (alunos[i].notas[alunos[i].quantDisciplinas - 1] < 0.0 || alunos[i].notas[alunos[i].quantDisciplinas - 1] > 10.0) {
                        System.out.println("Inválido. Digite a nota do aluno na disciplina (0,0 - 10,0):");
                        alunos[i].notas[alunos[i].quantDisciplinas - 1] = scn.nextDouble();
                    }
                    alunos[i].media = 0;
                    for (int j = 0; j < alunos[i].quantDisciplinas; j++) {
                        alunos[i].media += alunos[i].notas[j];
                    }
                    alunos[i].media /= alunos[i].quantDisciplinas;
                    alunos[i].media = Math.round(alunos[i].media * 10) / 10.0;
                    System.out.println("Aluno matriculado com sucesso!");
                    aux = 1;
                }
            }
            if (aux == 0) {
                System.out.println("Código não encontrado.");
            }
        }
    }

    static void listarMatriculas(Disciplina[] disciplinas, Aluno[] alunos) {
        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] != null) {
                System.out.print("Alunos matriculados na disciplina " + disciplinas[i].sigla + ": ");
                for (int j = 0; j < alunos.length; j++) {
                    if (alunos[j] != null) {
                        for (int k = 0; k < alunos[j].quantDisciplinas; k++) {
                            if (alunos[j].CodeDisciplinasMat[k] == disciplinas[i].code) {
                                System.out.print(alunos[j].nome + " ");
                            }
                        }
                    }
                }
                System.out.println();
            }
        }
    }
}