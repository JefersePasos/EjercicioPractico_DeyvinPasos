package ejerciciopractico_deyvin.controller;

import ejerciciopractico_deyvin.services.CategoriaService;
import ejerciciopractico_deyvin.services.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    private final LibroService libroService;
    private final CategoriaService categoriaService;

    public IndexController(LibroService libroService, CategoriaService categoriaService) {
        this.libroService = libroService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/")
    public String cargarPaginaInicio(Model model) {
        var libros = libroService.getLibros(true);
        model.addAttribute("libros", libros);

        var categorias = categoriaService.getCategorias();
        model.addAttribute("categorias", categorias);

        return "/index";
    }

}