package com.isa.project.service;

import com.isa.project.model.Action;
import com.isa.project.repository.ActionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static com.isa.project.constants.ActionConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActionServiceTest {

    @Mock
    private ActionRepository actionRepositoryMock;

    @Mock
    private Action actionMock;

    @InjectMocks
    private ActionService actionService;

    @Test
    public void testFindByService() {
        when(actionRepositoryMock.findByService(DB_BOAT)).thenReturn(Arrays.asList(new Action(DB_ID, DB_START_DATE, DB_DURATION, DB_NUMBER_OF_PEOPLE, new HashSet<>(), DB_PRICE, DB_BOAT)));

        List<Action> actions = actionService.findByService(DB_BOAT);

        assertThat(actions).hasSize(1);
        assertEquals(actions.get(0).getService().getId(), DB_BOAT.getId());

        verify(actionRepositoryMock, times(1)).findByService(DB_BOAT);
        verifyNoMoreInteractions(actionRepositoryMock);
    }

    @Test
    public void testFindById() {
        when(actionRepositoryMock.findById(DB_ID)).thenReturn(Optional.of(actionMock));

        Action dbAction = actionService.findById(DB_ID);

        assertEquals(actionMock, dbAction);

        verify(actionRepositoryMock, times(1)).findById(DB_ID);
        verifyNoMoreInteractions(actionRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteById() {
        when(actionRepositoryMock.findAll()).thenReturn(Arrays.asList(new Action(DB_ID, DB_START_DATE, DB_DURATION, DB_NUMBER_OF_PEOPLE, new HashSet<>(), DB_PRICE, DB_BOAT), new Action(DB_ID_TO_DELETE, NEW_START_DATE, NEW_DURATION, NEW_NUMBER_OF_PEOPLE, new HashSet<>(), NEW_PRICE, DB_BOAT)));
        doNothing().when(actionRepositoryMock).deleteById(DB_ID_TO_DELETE);
        when(actionRepositoryMock.findById(DB_ID_TO_DELETE)).thenReturn(Optional.empty());

        int dbSizeBeforeRemove = actionService.findAll().size();
        actionService.deleteById(DB_ID_TO_DELETE);

        when(actionRepositoryMock.findAll()).thenReturn(Arrays.asList(new Action(DB_ID, DB_START_DATE, DB_DURATION, DB_NUMBER_OF_PEOPLE, new HashSet<>(), DB_PRICE, DB_BOAT)));

        List<Action> actions = actionService.findAll();
        assertThat(actions).hasSize(dbSizeBeforeRemove - 1);

        Action dbAction = actionService.findById(DB_ID_TO_DELETE);
        assertThat(dbAction).isNull();

        verify(actionRepositoryMock, times(1)).deleteById(DB_ID_TO_DELETE);
        verify(actionRepositoryMock, times(2)).findAll();
        verify(actionRepositoryMock, times(1)).findById(DB_ID_TO_DELETE);
        verifyNoMoreInteractions(actionRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSave() {
        Action action = new Action();
        action.setService(DB_BOAT);
        action.setPrice(NEW_PRICE);
        action.setDurationInDays(NEW_DURATION);
        action.setStartTime(NEW_START_DATE);
        action.setMaxNumberOfPeople(NEW_NUMBER_OF_PEOPLE);

        when(actionRepositoryMock.findAll()).thenReturn(Arrays.asList(new Action(DB_ID, DB_START_DATE, DB_DURATION, DB_NUMBER_OF_PEOPLE, new HashSet<>(), DB_PRICE, DB_BOAT)));
        when(actionRepositoryMock.save(action)).thenReturn(action);

        int dbSizeBeforeAdd = actionService.findAll().size();

        Action dbAction = actionService.save(action);

        when(actionRepositoryMock.findAll()).thenReturn(Arrays.asList(new Action(DB_ID, DB_START_DATE, DB_DURATION, DB_NUMBER_OF_PEOPLE, new HashSet<>(), DB_PRICE, DB_BOAT), action));

        assertThat(dbAction).isNotNull();

        List<Action> actions = actionService.findAll();
        assertThat(actions).hasSize(dbSizeBeforeAdd + 1);

        dbAction = actions.get(actions.size() - 1);

        assertThat(dbAction.getPrice()).isEqualTo(NEW_PRICE);
        assertThat(dbAction.getDurationInDays()).isEqualTo(NEW_DURATION);
        assertThat(dbAction.getMaxNumberOfPeople()).isEqualTo(NEW_NUMBER_OF_PEOPLE);
        assertThat(dbAction.getStartTime()).isEqualTo(NEW_START_DATE);

        verify(actionRepositoryMock, times(2)).findAll();
        verify(actionRepositoryMock, times(1)).save(action);
        verifyNoMoreInteractions(actionRepositoryMock);
    }

    @Test
    @Transactional
    public void testSavev2() {
        when(actionRepositoryMock.save(actionMock)).thenReturn(actionMock);

        Action savedAction = actionService.save(actionMock);

        assertThat(savedAction).isEqualTo(actionMock);
    }

    @Test
    public void testFindAll() {

        when(actionRepositoryMock.findAll()).thenReturn(Arrays.asList(new Action(DB_ID, DB_START_DATE, DB_DURATION, DB_NUMBER_OF_PEOPLE, new HashSet<>(), DB_PRICE, DB_BOAT)));

        List<Action> actions = actionService.findAll();

        assertThat(actions).hasSize(1);
        assertEquals(actions.get(0).getId(), 2L);

        verify(actionRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(actionRepositoryMock);
    }
}
