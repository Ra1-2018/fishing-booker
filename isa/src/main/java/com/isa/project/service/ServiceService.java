package com.isa.project.service;

import com.isa.project.dto.ServiceCriteriaDTO;
import com.isa.project.dto.ServiceDTO;
import com.isa.project.model.TimeRange;
import com.isa.project.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<com.isa.project.model.Service> findAll() { return serviceRepository.findAll(); }

    public com.isa.project.model.Service findById(Long id) { return serviceRepository.findById(id).orElse(null); }

    public Collection<com.isa.project.model.Service> getIfMatchesCriteria(ServiceCriteriaDTO serviceCriteria) {
        Collection<com.isa.project.model.Service> services = findAll();
        Collection<com.isa.project.model.Service> matchingServices = new ArrayList<>();
        Date startDate = serviceCriteria.getStartDate();
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, serviceCriteria.getDurationInDays());
        Date endDate = c.getTime();
        for (com.isa.project.model.Service service : services) {
            if(!(service.getServiceType() == serviceCriteria.getServiceType() && service.getAddress().toLowerCase().contains(serviceCriteria.getAddress().toLowerCase()) && service.getMaxNumberOfPeople() >= serviceCriteria.getNumberOfPeople())) {
                continue;
            }
            for(TimeRange timeRange : service.getFreePeriods()) {
                if(startDate.after(timeRange.getStartDate()) && endDate.before(timeRange.getEndDate())) {
                    matchingServices.add(service);
                    break;
                }
            }
        }
        return matchingServices;
    }
}
