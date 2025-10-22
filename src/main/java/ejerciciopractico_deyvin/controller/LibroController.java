package ejerciciopractico_deyvin.controller;

import ejerciciopractico_deyvin.domain.Libro;
import ejerciciopractico_deyvin.services.CategoriaService;
import ejerciciopractico_deyvin.services.LibroService;
import jakarta.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/listado")
    public String listado(Model model) {
        var libros = libroService.getLibros(false);
        model.addAttribute("libros", libros);
        model.addAttribute("totalLibros", libros.size());
        model.addAttribute("libro", new Libro());
        return "/libro/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Libro libro, RedirectAttributes redirectAttributes) {
        libroService.save(libro);
        redirectAttributes.addFlashAttribute("todoOk",
            messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        return "redirect:/libro/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Long idLibro, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";

        try {
            libroService.delete(idLibro);
        } catch (IllegalArgumentException e) {
            titulo = "error";
            detalle = "libro.error01";
        } catch (IllegalStateException e) {
            titulo = "error";
            detalle = "libro.error02";
        } catch (Exception e) {
            titulo = "error";
            detalle = "libro.error03";
        }

        redirectAttributes.addFlashAttribute(titulo,
            messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/libro/listado";
    }

    @GetMapping("/modificar/{idLibro}")
    public String modificar(@PathVariable("idLibro") Long idLibro, Model model, RedirectAttributes redirectAttributes) {
        Optional<Libro> libroOpt = libroService.getLibro(idLibro);
        if (libroOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error",
                messageSource.getMessage("libro.error01", null, Locale.getDefault()));
            return "redirect:/libro/listado";
        }

        model.addAttribute("libro", libroOpt.get());
        var categorias = categoriaService.getCategorias();
        model.addAttribute("categorias", categorias);
        return "/libro/modifica";
    }
}