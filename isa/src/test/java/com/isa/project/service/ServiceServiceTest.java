package com.isa.project.service;

import com.isa.project.dto.ServiceCriteriaDTO;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.Service;
import com.isa.project.model.ServiceType;
import com.isa.project.repository.ServiceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static com.isa.project.constants.ServiceConstants.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceServiceTest {

    @Mock
    private ServiceRepository serviceRepositoryMock;

    @InjectMocks
    private ServiceService serviceService;

    @Test
    public void testFindIfMatchesCriteria() {
        when(serviceRepositoryMock.findAll()).thenReturn(Arrays.asList(new Cottage(DB_COTTAGE_ID, DB_COTTAGE_NAME, DB_COTTAGE_DESCRIPTION, DB_COTTAGE_DESCRIPTION, DB_COTTAGE_PRICE_PER_DAY, DB_COTTAGE_ADDRESS, DB_NUMBER_OF_ROOMS, new HashSet<>(), new CottageOwner(), DB_COTTAGE_FREE_PERIODS, DB_COTTAGE_NUMBER_OF_PEOPLE, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>())));
        ServiceCriteriaDTO criteria = new ServiceCriteriaDTO(ServiceType.COTTAGE, new GregorianCalendar(2022, 3, 5).getTime(), 3, "", 3, 0);
        List<Service> services = serviceService.getIfMatchesCriteria(criteria);
        assertThat(services).hasSize(1);
        assertEquals(services.get(0).getName(), DB_COTTAGE_NAME);
        verify(serviceRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(serviceRepositoryMock);
    }

    @Test
    public void testFindAll() {
        when(serviceRepositoryMock.findAll()).thenReturn(Arrays.asList(new Cottage(DB_COTTAGE_ID, DB_COTTAGE_NAME, DB_COTTAGE_DESCRIPTION, DB_COTTAGE_DESCRIPTION, DB_COTTAGE_PRICE_PER_DAY, DB_COTTAGE_ADDRESS, DB_NUMBER_OF_ROOMS, new HashSet<>(), new CottageOwner(), DB_COTTAGE_FREE_PERIODS, DB_COTTAGE_NUMBER_OF_PEOPLE, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>())));
        List<Service> services = serviceService.findAll();
        assertThat(services).hasSize(1);
        assertEquals(services.get(0).getName(), DB_COTTAGE_NAME);
        verify(serviceRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(serviceRepositoryMock);
    }
}
