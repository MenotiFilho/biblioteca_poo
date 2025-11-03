package br.com.fatec.biblioteca.biblioteca_poo.config;

import br.com.fatec.biblioteca.biblioteca_poo.model.entity.Bibliotecario;
import br.com.fatec.biblioteca.biblioteca_poo.model.entity.LivroFisico; // Importar
import br.com.fatec.biblioteca.biblioteca_poo.model.repository.BibliotecarioRepository;
import br.com.fatec.biblioteca.biblioteca_poo.model.repository.ItemAcervoRepository; // Importar
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List; // Importar

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BibliotecarioRepository bibliotecarioRepository;

    @Autowired
    private ItemAcervoRepository itemAcervoRepository;

    @Override
    public void run(String... args) throws Exception {

        if (bibliotecarioRepository.findByLogin("admin").isEmpty()) {
            Bibliotecario admin = new Bibliotecario();
            admin.setNome("Bibliotecário Admin");
            admin.setLogin("admin");
            admin.setSenha("123");
            bibliotecarioRepository.save(admin);
        }

        if (itemAcervoRepository.count() == 0) {

            LivroFisico l1 = new LivroFisico();
            l1.setTitulo("1984");
            l1.setAutor("George Orwell");
            l1.setEdicao(1);
            l1.setIsbn("978-8535914849");
            l1.setUrlCapa("https://m.media-amazon.com/images/I/819js3EQwbL._AC_UF1000,1000_QL80_.jpg");
            l1.setEstaDisponivel(true);

            LivroFisico l2 = new LivroFisico();
            l2.setTitulo("O Senhor dos Anéis: A Sociedade do Anel");
            l2.setAutor("J.R.R. Tolkien");
            l2.setEdicao(3);
            l2.setIsbn("978-8595084759");
            l2.setUrlCapa("https://m.media-amazon.com/images/I/51w7gK+iOdL.jpg");
            l2.setEstaDisponivel(true);

            LivroFisico l3 = new LivroFisico();
            l3.setTitulo("Admirável Mundo Novo");
            l3.setAutor("Aldous Huxley");
            l3.setEdicao(1);
            l3.setIsbn("978-8525056000");
            l3.setUrlCapa("https://m.media-amazon.com/images/I/81r-3VItnNS._AC_UF1000,1000_QL80_.jpg");
            l3.setEstaDisponivel(true);

            LivroFisico l4 = new LivroFisico();
            l4.setTitulo("Fahrenheit 451");
            l4.setAutor("Ray Bradbury");
            l4.setEdicao(2);
            l4.setIsbn("978-8525055393");
            l4.setUrlCapa("https://m.media-amazon.com/images/I/71zxdJYk0eL._AC_UF1000,1000_QL80_.jpg");
            l4.setEstaDisponivel(false);

            LivroFisico l5 = new LivroFisico();
            l5.setTitulo("O Sol é para Todos");
            l5.setAutor("Harper Lee");
            l5.setEdicao(1);
            l5.setIsbn("978-8501068031");
            l5.setUrlCapa("https://m.media-amazon.com/images/I/81y+KN1nLNL._AC_UF1000,1000_QL80_.jpg");
            l5.setEstaDisponivel(true);

            LivroFisico l6 = new LivroFisico();
            l6.setTitulo("Dom Quixote");
            l6.setAutor("Miguel de Cervantes");
            l6.setEdicao(1);
            l6.setIsbn("978-8520931289");
            l6.setUrlCapa("https://m.media-amazon.com/images/I/81xL2GLehbL._AC_UF1000,1000_QL80_.jpg");
            l6.setEstaDisponivel(true);

            LivroFisico l7 = new LivroFisico();
            l7.setTitulo("A Revolução dos Bichos");
            l7.setAutor("George Orwell");
            l7.setEdicao(1);
            l7.setIsbn("978-8535909555");
            l7.setUrlCapa("https://m.media-amazon.com/images/I/91BsZhxCRjL._AC_UF1000,1000_QL80_.jpg");
            l7.setEstaDisponivel(true);

            LivroFisico l8 = new LivroFisico();
            l8.setTitulo("Duna");
            l8.setAutor("Frank Herbert");
            l8.setEdicao(1);
            l8.setIsbn("978-8576574849");
            l8.setUrlCapa("https://m.media-amazon.com/images/I/81d+xg+27xL._AC_UF1000,1000_QL80_.jpg");
            l8.setEstaDisponivel(true);

            LivroFisico l9 = new LivroFisico();
            l9.setTitulo("Cem Anos de Solidão");
            l9.setAutor("Gabriel García Márquez");
            l9.setEdicao(1);
            l9.setIsbn("978-8501012355");
            l9.setUrlCapa("https://m.media-amazon.com/images/I/81L-i8i3n9L._AC_UF1000,1000_QL80_.jpg");
            l9.setEstaDisponivel(true);

            LivroFisico l10 = new LivroFisico();
            l10.setTitulo("Neuromancer");
            l10.setAutor("William Gibson");
            l10.setEdicao(1);
            l10.setIsbn("978-8576570919");
            l10.setUrlCapa("https://m.media-amazon.com/images/I/91N-EDLw6kL._AC_UF1000,1000_QL80_.jpg");
            l10.setEstaDisponivel(true);

            itemAcervoRepository.saveAll(List.of(l1, l2, l3, l4, l5, l6, l7, l8, l9, l10));
        }
    }
}