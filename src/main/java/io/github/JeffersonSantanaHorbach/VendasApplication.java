package io.github.JeffersonSantanaHorbach;

import io.github.JeffersonSantanaHorbach.domain.entity.Cliente;
import io.github.JeffersonSantanaHorbach.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            System.out.println("Salvando clientes...");
            clientes.salvar(new Cliente("Dougllas"));
            clientes.salvar(new Cliente("Outro Cliente"));

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando Clientes");
            todosClientes.forEach(cliente -> {
                cliente.setNome(cliente.getNome() + " atualizado");
                clientes.atualizar(cliente);
            });

            System.out.println("Buscando Clientes");
            clientes.buscarPorNome("Dou").forEach(System.out::println);

            System.out.println("deletando Clientes");
            clientes.obterTodos().forEach(cliente -> {
                clientes.deletar(cliente.getId());
            });

            todosClientes = clientes.obterTodos();
            if(todosClientes.isEmpty()){
                System.out.print("Nenhum cliente encontrado");
            }else{
                todosClientes.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class,args);
    }
}
