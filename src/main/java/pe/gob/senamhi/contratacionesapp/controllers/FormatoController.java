package pe.gob.senamhi.contratacionesapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.gob.senamhi.contratacionesapp.entities.Formato;
import pe.gob.senamhi.contratacionesapp.services.FileService;
import pe.gob.senamhi.contratacionesapp.services.FormatoService;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/formatos")
@CrossOrigin(origins = "*")
public class FormatoController {
    @Autowired
    private FormatoService formatoService;
    @Autowired
    private FileService fileService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<Formato>> findAll() {
        return ResponseEntity.ok(formatoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formato> findById(@PathVariable("id")  Long id) {
        return ResponseEntity.ok(formatoService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id")  Long id) {
        formatoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Formato> save(@RequestBody Formato formato) {
        return ResponseEntity.ok(formatoService.save(formato));
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, String>> updateFile(@RequestParam(value = "file", required = false) MultipartFile file,
                                                          @RequestParam("descripcion") String descripcion,
                                                          @RequestParam("id") String id) {
        Formato formato = formatoService.findById(Long.parseLong(id));
        if (formato == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Formato no encontrado"));
        }
        formato.setDescripcion(descripcion);
        String url = formato.getUrl();
        if(file != null) {
            url = saveFile(file);
            formato.setNombre(file.getOriginalFilename());
            formato.setUrl(url);
            formato.setFechaEdicion(LocalDate.now());
        }
        formatoService.save(formato);
        return ResponseEntity.ok(Map.of("url", url));
    }
    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file,
                                                          @RequestParam("descripcion") String descripcion) {
        String url = saveFile(file);
        Formato formato = new Formato();
        formato.setNombre(file.getOriginalFilename());
        formato.setUrl(url);
        formato.setDescripcion(descripcion);
        formato.setFechaCreacion(LocalDate.now());
        formato.setFechaEdicion(LocalDate.now());
        formato.setEstado("Activo");
        formatoService.save(formato);
        return ResponseEntity.ok(Map.of("url", url));
    }

    @GetMapping("/files/{filename:.*}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        Resource file = fileService.loadFile(filename);
        String contentType = determineContentType(filename);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));

        // Configurar el encabezado Content-Disposition para establecer el nombre del archivo
        ContentDisposition contentDisposition = ContentDisposition
                .builder("attachment")
                .filename(filename)
                .build();
        headers.setContentDisposition(contentDisposition);

        return ResponseEntity.ok().headers(headers).body(file);
    }

    private String determineContentType(String filename) {
        // Obtiene la extensión del archivo
        String extension = FilenameUtils.getExtension(filename);

        // Determina el tipo de contenido basado en la extensión
        switch (extension.toLowerCase()) {
            case "pdf":
                return "application/pdf";
            case "doc":
            case "docx":
                return "application/msword";
            case "xls":
            case "xlsx":
                return "application/vnd.ms-excel";
            // Agrega más casos según los tipos de archivo que necesites manejar
            default:
                return "application/octet-stream"; // Tipo de contenido por defecto
        }
    }

    public String saveFile(MultipartFile file){
        String path = fileService.store(file);
        String host = request.getRequestURL().toString()
                .replace(request.getRequestURI(), "");
        return ServletUriComponentsBuilder.fromHttpUrl(host).
                path("/formatos/files/").
                path(path).
                toUriString();
    }
}
