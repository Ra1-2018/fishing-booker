package com.isa.project.service;

import com.isa.project.model.TimeRange;
import com.isa.project.repository.TimeRangeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.isa.project.constants.TimRangeConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeRangeServiceTest {

    @Mock
    private TimeRangeRepository timeRangeRepositoryMock;

    @Mock
    private TimeRange timeRangeMock;

    @InjectMocks
    private TimeRangeService timeRangeService;

    @Test
    public void testFindByService() {
        when(timeRangeRepositoryMock.findByService(DB_BOAT)).thenReturn(Arrays.asList(new TimeRange(DB_ID, DB_START_DATE, DB_END_DATE, DB_BOAT)));

        List<TimeRange> freePeriods = timeRangeService.findByService(DB_BOAT);

        assertThat(freePeriods).hasSize(1);
        assertEquals(freePeriods.get(0).getService().getId(), DB_BOAT.getId());

        verify(timeRangeRepositoryMock, times(1)).findByService(DB_BOAT);
        verifyNoMoreInteractions(timeRangeRepositoryMock);
    }

    @Test
    public void testFindById() {
        when(timeRangeRepositoryMock.findById(DB_ID)).thenReturn(Optional.of(timeRangeMock));

        TimeRange dbTimeRange = timeRangeService.findById(DB_ID);

        assertEquals(timeRangeMock, dbTimeRange);

        verify(timeRangeRepositoryMock, times(1)).findById(DB_ID);
        verifyNoMoreInteractions(timeRangeRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteById() {
        when(timeRangeRepositoryMock.findAll()).thenReturn(Arrays.asList(new TimeRange(DB_ID, DB_START_DATE, DB_END_DATE, DB_BOAT), new TimeRange(DB_ID_TO_DELETE, NEW_START_DATE, NEW_END_DATE, DB_BOAT)));
        doNothing().when(timeRangeRepositoryMock).deleteById(DB_ID_TO_DELETE);
        when(timeRangeRepositoryMock.findById(DB_ID_TO_DELETE)).thenReturn(Optional.empty());

        int dbSizeBeforeRemove = timeRangeService.findAll().size();
        timeRangeService.deleteById(DB_ID_TO_DELETE);

        when(timeRangeRepositoryMock.findAll()).thenReturn(Arrays.asList(new TimeRange(DB_ID, DB_START_DATE, DB_END_DATE, DB_BOAT)));

        List<TimeRange> freePeriods = timeRangeService.findAll();
        assertThat(freePeriods).hasSize(dbSizeBeforeRemove - 1);

        TimeRange dbTimeRange = timeRangeService.findById(DB_ID_TO_DELETE);
        assertThat(dbTimeRange).isNull();

        verify(timeRangeRepositoryMock, times(1)).deleteById(DB_ID_TO_DELETE);
        verify(timeRangeRepositoryMock, times(2)).findAll();
        verify(timeRangeRepositoryMock, times(1)).findById(DB_ID_TO_DELETE);
        verifyNoMoreInteractions(timeRangeRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSave() {
        TimeRange timeRange = new TimeRange();
        timeRange.setService(DB_BOAT);
        timeRange.setStartDate(NEW_START_DATE);
        timeRange.setEndDate(NEW_END_DATE);

        when(timeRangeRepositoryMock.findAll()).thenReturn(Arrays.asList(new TimeRange(DB_ID, DB_START_DATE, DB_END_DATE, DB_BOAT)));
        when(timeRangeRepositoryMock.save(timeRange)).thenReturn(timeRange);

        int dbSizeBeforeAdd = timeRangeService.findAll().size();

        TimeRange dbTimeRange = timeRangeService.save(timeRange);

        when(timeRangeRepositoryMock.findAll()).thenReturn(Arrays.asList(new TimeRange(DB_ID, DB_START_DATE, DB_END_DATE, DB_BOAT), timeRange));

        assertThat(dbTimeRange).isNotNull();

        List<TimeRange> freePeriods = timeRangeService.findAll();
        assertThat(freePeriods).hasSize(dbSizeBeforeAdd + 1);

        dbTimeRange = freePeriods.get(freePeriods.size() - 1);

        assertThat(dbTimeRange.getEndDate()).isEqualTo(NEW_END_DATE);
        assertThat(dbTimeRange.getStartDate()).isEqualTo(NEW_START_DATE);

        verify(timeRangeRepositoryMock, times(2)).findAll();
        verify(timeRangeRepositoryMock, times(1)).save(timeRange);
        verifyNoMoreInteractions(timeRangeRepositoryMock);
    }

    @Test
    @Transactional
    public void testSavev2() {
        when(timeRangeRepositoryMock.save(timeRangeMock)).thenReturn(timeRangeMock);

        TimeRange timeRange = timeRangeService.save(timeRangeMock);

        assertThat(timeRange).isEqualTo(timeRangeMock);
    }

    @Test
    public void testFindAll() {

        when(timeRangeRepositoryMock.findAll()).thenReturn(Arrays.asList(new TimeRange(DB_ID, DB_START_DATE, DB_END_DATE, DB_BOAT)));

        List<TimeRange> freePeriods = timeRangeService.findAll();

        assertThat(freePeriods).hasSize(1);

        long id = 1L;
        if(freePeriods.get(0).getId()!= null)
            id = freePeriods.get(0).getId();

        assertEquals(id, 2L);

        verify(timeRangeRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(timeRangeRepositoryMock);
    }
}
