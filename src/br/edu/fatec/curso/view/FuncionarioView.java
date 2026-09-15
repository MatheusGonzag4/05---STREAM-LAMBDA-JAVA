package br.edu.fatec.curso.view;

import br.edu.fatec.curso.model.Funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FuncionarioView {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Ana Souza", "Tecnologia", 5200.00, 12));
        funcionarios.add(new Funcionario("Bruno Lima", "Financeiro", 3800.00, 6));
        funcionarios.add(new Funcionario("Carla Mendes", "Recursos Humanos", 4100.00, 11));
        funcionarios.add(new Funcionario("Diego Alves", "Tecnologia", 6700.00, 15));
        funcionarios.add(new Funcionario("Elaine Costa", "Financeiro", 4600.00, 9));
        funcionarios.add(new Funcionario("Felipe Rocha", "Recursos Humanos", 3300.00, 4));
        funcionarios.add(new Funcionario("Gabriela Martins", "Tecnologia", 5900.00, 8));
        funcionarios.add(new Funcionario("Henrique Oliveira", "Financeiro", 7200.00, 18));

        List<Funcionario> salariosAcimaDoLimite = funcionarios.stream()
                .filter(funcionario -> funcionario.getSalario() > 3000)
                .sorted((primeiro, segundo) -> primeiro.getNome().compareTo(segundo.getNome()))
                .collect(Collectors.toList());

        List<Funcionario> salariosComAumento = funcionarios.stream()
                .map(funcionario -> funcionario.getAnosDeServico() > 10
                        ? new Funcionario(
                                funcionario.getNome(),
                                funcionario.getDepartamento(),
                                funcionario.getSalario() * 1.05,
                                funcionario.getAnosDeServico())
                        : funcionario)
                .collect(Collectors.toList());

        double totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(0.0, Double::sum);

        Map<String, Double> mediasPorDepartamento = funcionarios.stream()
                .collect(Collectors.groupingBy(
                        Funcionario::getDepartamento,
                        Collectors.averagingDouble(Funcionario::getSalario)));

        System.out.println("FUNCIONARIOS COM SALARIO ACIMA DE R$ 3.000,00");
        salariosAcimaDoLimite.forEach(System.out::println);

        System.out.println("\nSALARIOS APOS AUMENTO DE 5% PARA MAIS DE 10 ANOS DE SERVICO");
        salariosComAumento.forEach(System.out::println);

        System.out.printf("\nTOTAL GASTO COM SALARIOS: R$ %.2f%n", totalSalarios);

        System.out.println("\nMEDIA SALARIAL POR DEPARTAMENTO");
        mediasPorDepartamento.forEach((departamento, media) ->
                System.out.printf("%s: R$ %.2f%n", departamento, media));
    }
}
