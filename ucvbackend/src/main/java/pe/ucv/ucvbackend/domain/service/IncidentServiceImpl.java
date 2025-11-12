package pe.ucv.ucvbackend.domain.service;

import pe.ucv.ucvbackend.domain.Incident;
import pe.ucv.ucvbackend.domain.repository.IncidentRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentServiceImpl implements IncidentService {

    private static final Logger logger = LoggerFactory.getLogger(IncidentServiceImpl.class);
    private final IncidentRepository incidentRepository;

    public IncidentServiceImpl(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @Override
    public List<Incident> getAllIncidents() {
        logger.info("Getting all incidents");
        return incidentRepository.findAll();
    }

    @Override
    public Optional<Incident> getIncidentById(Long id) {
        logger.info("Getting incident by ID: {}", id);
        return incidentRepository.findById(id);
    }

    @Override
    public Incident createIncident(Incident incident) {
        logger.info("Creating new incident in area: {}", incident.getArea());

        // Validaciones
        if (incident.getCategoryId() == null) {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }
        if (incident.getDepartmentId() == null) {
            throw new IllegalArgumentException("El departamento es obligatorio");
        }
        if (incident.getUserId() == null) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }

        // Establecer fecha de registro si no viene
        if (incident.getRegisteredDate() == null) {
            incident.setRegisteredDate(java.time.LocalDateTime.now());
        }

        // Establecer fecha de incidencia si no viene
        if (incident.getIncidentDate() == null) {
            incident.setIncidentDate(java.time.LocalDate.now());
        }

        return incidentRepository.save(incident);
    }

    @Override
    public Incident updateIncident(Long id, Incident incident) {
        logger.info("Updating incident with ID: {}", id);

        // Verificar que la incidencia exista
        Incident existingIncident = incidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con ID: " + id));

        // Actualizar campos permitidos
        existingIncident.setArea(incident.getArea());
        existingIncident.setDescription(incident.getDescription());
        existingIncident.setIncidentDate(incident.getIncidentDate());
        existingIncident.setPriorityLevel(incident.getPriorityLevel());
        existingIncident.setRegisteredUser(incident.getRegisteredUser());
        existingIncident.setCategoryId(incident.getCategoryId());
        existingIncident.setDepartmentId(incident.getDepartmentId());
        existingIncident.setUserId(incident.getUserId());

        return incidentRepository.save(existingIncident);
    }

    @Override
    public void deleteIncident(Long id) {
        logger.info("Deleting incident with ID: {}", id);

        // Verificar que la incidencia exista
        if (!incidentRepository.findById(id).isPresent()) {
            throw new RuntimeException("Incidencia no encontrada con ID: " + id);
        }

        incidentRepository.deleteById(id);
    }

    @Override
    public List<Incident> getIncidentsByArea(String area) {
        logger.info("Getting incidents by area: {}", area);
        return incidentRepository.findByAreaContainingIgnoreCase(area);
    }

    @Override
    public List<Incident> getIncidentsByPriorityLevel(String priorityLevel) {
        logger.info("Getting incidents by priority level: {}", priorityLevel);
        return incidentRepository.findByPriorityLevel(priorityLevel);
    }

    @Override
    public List<Incident> getIncidentsByUserId(Long userId) {
        logger.info("Getting incidents by user ID: {}", userId);
        return incidentRepository.findByUserId(userId);
    }

    @Override
    public List<Incident> getIncidentsByCategoryId(Long categoryId) {
        logger.info("Getting incidents by category ID: {}", categoryId);
        return incidentRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Incident> getIncidentsByDepartmentId(Long departmentId) {
        logger.info("Getting incidents by department ID: {}", departmentId);
        return incidentRepository.findByDepartmentId(departmentId);
    }
}