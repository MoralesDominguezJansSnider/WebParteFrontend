package pe.ucv.ucvbackend.web.controller;

import pe.ucv.ucvbackend.domain.Incident;
import pe.ucv.ucvbackend.domain.service.IncidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ucv")
public class IncidentController {

    private static final Logger logger = LoggerFactory.getLogger(IncidentController.class);
    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/incidentList")
    public ResponseEntity<List<Incident>> getAllIncidents() {
        logger.info("******************************************");
        logger.info("Incident list request accepted successfully.");
        logger.info("******************************************");
        List<Incident> incidents = incidentService.getAllIncidents();
        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/incident/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Long id) {
        logger.info("******************************************");
        logger.info("Incident by ID request accepted successfully for ID: {}", id);
        logger.info("******************************************");
        return incidentService.getIncidentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/incidentSave")
    public ResponseEntity<Incident> saveIncident(@RequestBody Incident incident) {
        logger.info("******************************************");
        logger.info("Incident save request accepted successfully.");
        logger.info("******************************************");
        Incident savedIncident = incidentService.createIncident(incident);
        return ResponseEntity.ok(savedIncident);
    }

    @PutMapping("/incidentUpdate/{id}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Long id, @RequestBody Incident incident) {
        logger.info("******************************************");
        logger.info("Incident update request accepted successfully for ID: {}", id);
        logger.info("******************************************");
        Incident updatedIncident = incidentService.updateIncident(id, incident);
        return ResponseEntity.ok(updatedIncident);
    }

    @DeleteMapping("/incidentDelete/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        logger.info("******************************************");
        logger.info("Incident delete request accepted successfully for ID: {}", id);
        logger.info("******************************************");
        incidentService.deleteIncident(id);
        return ResponseEntity.ok().build();
    }

    // Endpoints adicionales para filtros
    @GetMapping("/incidentsByArea")
    public ResponseEntity<List<Incident>> getIncidentsByArea(@RequestParam String area) {
        logger.info("Getting incidents by area: {}", area);
        List<Incident> incidents = incidentService.getIncidentsByArea(area);
        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/incidentsByPriority")
    public ResponseEntity<List<Incident>> getIncidentsByPriority(@RequestParam String priorityLevel) {
        logger.info("Getting incidents by priority level: {}", priorityLevel);
        List<Incident> incidents = incidentService.getIncidentsByPriorityLevel(priorityLevel);
        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/incidentsByUser/{userId}")
    public ResponseEntity<List<Incident>> getIncidentsByUser(@PathVariable Long userId) {
        logger.info("Getting incidents by user ID: {}", userId);
        List<Incident> incidents = incidentService.getIncidentsByUserId(userId);
        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/incidentsByCategory/{categoryId}")
    public ResponseEntity<List<Incident>> getIncidentsByCategory(@PathVariable Long categoryId) {
        logger.info("Getting incidents by category ID: {}", categoryId);
        List<Incident> incidents = incidentService.getIncidentsByCategoryId(categoryId);
        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/incidentsByDepartment/{departmentId}")
    public ResponseEntity<List<Incident>> getIncidentsByDepartment(@PathVariable Long departmentId) {
        logger.info("Getting incidents by department ID: {}", departmentId);
        List<Incident> incidents = incidentService.getIncidentsByDepartmentId(departmentId);
        return ResponseEntity.ok(incidents);
    }
}