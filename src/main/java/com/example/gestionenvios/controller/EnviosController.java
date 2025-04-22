package com.example.gestionenvios.controller;

import com.example.gestionenvios.model.Envios;
import com.example.gestionenvios.service.EnviosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RestController
@RequestMapping("/envios")
public class EnviosController {

    @Autowired
    private EnviosService enviosService;

    private static final Logger logger = Logger.getLogger(EnviosController.class.getName());

    // GET: Obtener todos los envíos
    @GetMapping
    public List<Envios> getAllEnvios() {
        logger.info("GET /envios - Obteniendo todos los envíos");
        return enviosService.getAllEnvios();
    }

    // GET: Obtener un envío por número de seguimiento
    @GetMapping("/{nSeguimiento}")
    public Optional<Envios> getEnvioById(@PathVariable Long nSeguimiento) {
        logger.info("GET /envios/" + nSeguimiento + " - Obteniendo envío por ID");
        return enviosService.getEnvioById(nSeguimiento);
    }

    // POST: Crear un nuevo envío
    @PostMapping
    public Envios createEnvio(@RequestBody Envios envios) {
        logger.info("POST /envios - Creando nuevo envío");
        return enviosService.createEnvio(envios);
    }

    // PUT: Actualizar un envío existente
    @PutMapping("/{nSeguimiento}")
    public Envios updateEnvio(@PathVariable Long nSeguimiento, @RequestBody Envios envios) {
        logger.info("PUT /envios/" + nSeguimiento + " - Actualizando envío");
        Envios actualizado = enviosService.updateEnvio(nSeguimiento, envios);
        if (actualizado == null) {
            logger.warning("No se pudo actualizar. Envío con ID " + nSeguimiento + " no encontrado.");
        }
        return actualizado;
    }

    // DELETE: Eliminar un envío por su número de seguimiento
    @DeleteMapping("/{nSeguimiento}")
    public void deleteEnvio(@PathVariable Long nSeguimiento) {
        logger.info("DELETE /envios/" + nSeguimiento + " - Eliminando envío");
        enviosService.deleteEnvio(nSeguimiento);
    }
}
