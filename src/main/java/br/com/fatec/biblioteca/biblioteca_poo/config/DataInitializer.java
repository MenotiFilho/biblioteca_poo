package br.com.fatec.biblioteca.biblioteca_poo.config;

import br.com.fatec.biblioteca.biblioteca_poo.model.entity.Bibliotecario;
import br.com.fatec.biblioteca.biblioteca_poo.model.entity.Cliente;
import br.com.fatec.biblioteca.biblioteca_poo.model.entity.Emprestimo;
import br.com.fatec.biblioteca.biblioteca_poo.model.entity.LivroFisico;
import br.com.fatec.biblioteca.biblioteca_poo.model.repository.BibliotecarioRepository;
import br.com.fatec.biblioteca.biblioteca_poo.model.repository.ClienteRepository;
import br.com.fatec.biblioteca.biblioteca_poo.model.repository.EmprestimoRepository;
import br.com.fatec.biblioteca.biblioteca_poo.model.repository.ItemAcervoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer { // Não precisa mais do 'implements CommandLineRunner'

    @Autowired
    private BibliotecarioRepository bibliotecarioRepository;

    @Autowired
    private ItemAcervoRepository itemAcervoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @EventListener(ContextRefreshedEvent.class) // Isso dispara DEPOIS que o banco está pronto
    public void run() { // Renomeado para 'run' sem argumentos

        // Se a aplicação tiver um erro de permissão (AccessDeniedException),
        // ele vai aparecer aqui, mas isso só deve ocorrer se o Render não tiver a pasta /data

        // --- 1. ADMIN ---
        if (bibliotecarioRepository.findByLogin("admin").isEmpty()) {
            Bibliotecario admin = new Bibliotecario();
            admin.setNome("Bibliotecário Admin");
            admin.setLogin("admin");
            admin.setSenha("123");
            bibliotecarioRepository.save(admin);
        }

        // As lógicas abaixo usam os objetos recém-criados, por isso devem estar aqui dentro
        // do bloco @EventListener.

        // --- 2. CLIENTES ---
        List<Cliente> clientes = List.of();
        if (clienteRepository.count() == 0) {
            Cliente c1 = new Cliente();
            c1.setNome("João da Silva");
            c1.setEmail("joao.silva@email.com");
            c1.setTelefone("(11) 98765-4321");

            Cliente c2 = new Cliente();
            c2.setNome("Maria Oliveira");
            c2.setEmail("maria.oliveira@email.com");
            c2.setTelefone("(19) 91234-5678");

            Cliente c3 = new Cliente();
            c3.setNome("Carlos Pereira");
            c3.setEmail("carlos.pereira@email.com");
            c3.setTelefone("(11) 95555-4444");

            Cliente c4 = new Cliente();
            c4.setNome("Ana Souza");
            c4.setEmail("ana.souza@email.com");
            c4.setTelefone("(19) 94321-8765");

            Cliente c5 = new Cliente();
            c5.setNome("Paulo Ricardo");
            c5.setEmail("paulo.ricardo@email.com");
            c5.setTelefone("(11) 91111-2222");

            clientes = clienteRepository.saveAll(List.of(c1, c2, c3, c4, c5));
        } else {
            clientes = clienteRepository.findAll();
        }

        // --- 3. LIVROS ---
        List<LivroFisico> livros = List.of();
        if (itemAcervoRepository.count() == 0) {

            LivroFisico l1 = new LivroFisico();
            l1.setTitulo("1984");
            l1.setAutor("George Orwell");
            l1.setEdicao(1);
            l1.setIsbn("978-8535914849");
            l1.setUrlCapa("https://m.media-amazon.com/images/I/819js3EQwbL._AC_UF1000,1000_QL80_.jpg");
            l1.setQuantidadeTotal(5);

            LivroFisico l2 = new LivroFisico();
            l2.setTitulo("O Senhor dos Anéis: A Sociedade do Anel");
            l2.setAutor("J.R.R. Tolkien");
            l2.setEdicao(3);
            l2.setIsbn("978-8595084759");
            l2.setQuantidadeTotal(3);

            LivroFisico l3 = new LivroFisico();
            l3.setTitulo("Admirável Mundo Novo");
            l3.setAutor("Aldous Huxley");
            l3.setEdicao(1);
            l3.setIsbn("978-8525056000");
            l3.setQuantidadeTotal(2);

            LivroFisico l4 = new LivroFisico();
            l4.setTitulo("Fahrenheit 451");
            l4.setAutor("Ray Bradbury");
            l4.setEdicao(2);
            l4.setIsbn("978-8525055393");
            l4.setQuantidadeTotal(4);

            LivroFisico l5 = new LivroFisico();
            l5.setTitulo("O Sol é para Todos");
            l5.setAutor("Harper Lee");
            l5.setEdicao(1);
            l5.setIsbn("978-8501068031");
            l5.setQuantidadeTotal(1);

            LivroFisico l6 = new LivroFisico();
            l6.setTitulo("Dom Quixote");
            l6.setAutor("Miguel de Cervantes");
            l6.setEdicao(1);
            l6.setIsbn("978-8520931289");
            l6.setQuantidadeTotal(1);

            LivroFisico l7 = new LivroFisico();
            l7.setTitulo("A Revolução dos Bichos");
            l7.setAutor("George Orwell");
            l7.setEdicao(1);
            l7.setIsbn("978-8535909555");
            l7.setUrlCapa("https://m.media-amazon.com/images/I/91BsZhxCRjL._AC_UF1000,1000_QL80_.jpg");
            l7.setQuantidadeTotal(2);

            LivroFisico l8 = new LivroFisico();
            l8.setTitulo("Duna");
            l8.setAutor("Frank Herbert");
            l8.setEdicao(1);
            l8.setIsbn("978-8576574849");
            l8.setQuantidadeTotal(3);

            LivroFisico l9 = new LivroFisico();
            l9.setTitulo("Cem Anos de Solidão");
            l9.setAutor("Gabriel García Márquez");
            l9.setEdicao(1);
            l9.setIsbn("978-8501012355");
            l9.setQuantidadeTotal(1);

            LivroFisico l10 = new LivroFisico();
            l10.setTitulo("Neuromancer");
            l10.setAutor("William Gibson");
            l10.setEdicao(1);
            l10.setIsbn("978-8576570919");
            l10.setQuantidadeTotal(1);

            livros = itemAcervoRepository.saveAll(List.of(l1, l2, l3, l4, l5, l6, l7, l8, l9, l10));
        } else {
            livros = itemAcervoRepository.findAll().stream()
                    .filter(l -> l instanceof LivroFisico)
                    .map(l -> (LivroFisico) l)
                    .toList();
        }

        // --- 4. EMPRÉSTIMOS ---
        if (emprestimoRepository.count() == 0 && !livros.isEmpty() && !clientes.isEmpty()) {

            LivroFisico livro1984 = livros.get(0);
            LivroFisico livroMundoNovo = livros.get(2);

            // Empréstimo ATIVO
            Emprestimo empAtivo = new Emprestimo();
            empAtivo.setCliente(clientes.get(0));
            empAtivo.setItemAcervo(livro1984);
            empAtivo.setDataEmprestimo(LocalDate.now().minusDays(3));
            empAtivo.setDataPrevistaDevolucao(LocalDate.now().plusDays(11));

            // Empréstimo DEVOLVIDO
            Emprestimo empDevolvido = new Emprestimo();
            empDevolvido.setCliente(clientes.get(1));
            empDevolvido.setItemAcervo(livros.get(1));
            empDevolvido.setDataEmprestimo(LocalDate.now().minusDays(30));
            empDevolvido.setDataPrevistaDevolucao(LocalDate.now().minusDays(16));
            empDevolvido.setDataDevolucao(LocalDate.now().minusDays(10));

            // Empréstimo ATRASADO (para teste de multa)
            Emprestimo empAtrasado = new Emprestimo();
            empAtrasado.setCliente(clientes.get(2));
            empAtrasado.setItemAcervo(livroMundoNovo);
            empAtrasado.setDataEmprestimo(LocalDate.now().minusDays(20));
            empAtrasado.setDataPrevistaDevolucao(LocalDate.now().minusDays(6));

            // Simular a redução de estoque para os livros ativos (manual, pois o save não está no service)
            // Se o livro ainda não foi devolvido, reduzimos a quantidade disponível.
            livro1984.setQuantidadeDisponivel(livro1984.getQuantidadeDisponivel() - 1);
            livroMundoNovo.setQuantidadeDisponivel(livroMundoNovo.getQuantidadeDisponivel() - 1);

            // Salva os empréstimos e atualiza os livros com o novo estoque
            emprestimoRepository.saveAll(List.of(empAtivo, empDevolvido, empAtrasado));
            itemAcervoRepository.saveAll(List.of(livro1984, livroMundoNovo));
        }
    }
}