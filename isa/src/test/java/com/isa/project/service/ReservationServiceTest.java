package com.isa.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import com.isa.project.model.Reservation;
import com.isa.project.repository.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.isa.project.constants.ReservationConstants.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepositoryMock;

    @Mock
    private Reservation reservationMock;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    public void testFindById() {
        when(reservationRepositoryMock.findById(DB_ID)).thenReturn(Optional.of(reservationMock));

        Reservation dbReservation = reservationService.findById(DB_ID);

        assertEquals(reservationMock, dbReservation);

        verify(reservationRepositoryMock, times(1)).findById(DB_ID);
        verifyNoMoreInteractions(reservationRepositoryMock);
    }

    @Test
    public void testFindByClient() {

        when(reservationRepositoryMock.findByClient(DB_CLIENT)).thenReturn(Arrays.asList(new Reservation(DB_ID, DB_START_DATE, DB_DURATION, DB_NUMBER_OF_PEOPLE, new HashSet<>(), DB_PRICE, DB_COTTAGE, DB_CLIENT)));

        List<Reservation> reservations = reservationService.findByClient(DB_CLIENT);

        assertThat(reservations).hasSize(1);
        assertEquals(reservations.get(0).getClient().getId(), DB_CLIENT.getId());

        verify(reservationRepositoryMock, times(1)).findByClient(DB_CLIENT);
        verifyNoMoreInteractions(reservationRepositoryMock);
    }

    @Test
    public void testFindByService() {
        when(reservationRepositoryMock.findByService(DB_COTTAGE)).thenReturn(Arrays.asList(new Reservation(DB_ID, DB_START_DATE, DB_DURATION, DB_NUMBER_OF_PEOPLE, new HashSet<>(), DB_PRICE, DB_COTTAGE, DB_CLIENT)));

        List<Reservation> reservations = reservationService.findByService(DB_COTTAGE);

        assertThat(reservations).hasSize(1);
        assertEquals(reservations.get(0).getService().getId(), DB_COTTAGE.getId());

        verify(reservationRepositoryMock, times(1)).findByService(DB_COTTAGE);
        verifyNoMoreInteractions(reservationRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSave() {
        Reservation reservation = new Reservation();
        reservation.setService(DB_COTTAGE);
        reservation.setClient(DB_CLIENT);
        reservation.setPrice(NEW_PRICE);
        reservation.setReservationStartDateAndTime(NEW_START_DATE);
        reservation.setNumberOfPeople(NEW_NUMBER_OF_PEOPLE);
        reservation.setDurationInDays(NEW_DURATION);

        when(reservationRepositoryMock.findAll()).thenReturn(Arrays.asList(new Reservation(DB_ID, DB_START_DATE, DB_DURATION, DB_NUMBER_OF_PEOPLE, new HashSet<>(), DB_PRICE, DB_COTTAGE, DB_CLIENT)));
        when(reservationRepositoryMock.save(reservation)).thenReturn(reservation);

        int dbSizeBeforeAdd = reservationService.findAll().size();

        Reservation dbReservation = reservationService.save(reservation);

        when(reservationRepositoryMock.findAll()).thenReturn(Arrays.asList(new Reservation(DB_ID, DB_START_DATE, DB_DURATION, DB_NUMBER_OF_PEOPLE, new HashSet<>(), DB_PRICE, DB_COTTAGE, DB_CLIENT), reservation));

        assertThat(dbReservation).isNotNull();

        List<Reservation> reservations = reservationService.findAll();
        assertThat(reservations).hasSize(dbSizeBeforeAdd + 1);

        dbReservation = reservations.get(reservations.size() - 1);

        assertThat(dbReservation.getPrice()).isEqualTo(NEW_PRICE);
        assertThat(dbReservation.getDurationInDays()).isEqualTo(NEW_DURATION);
        assertThat(dbReservation.getNumberOfPeople()).isEqualTo(NEW_NUMBER_OF_PEOPLE);
        assertThat(dbReservation.getReservationStartDateAndTime()).isEqualTo(NEW_START_DATE);

        verify(reservationRepositoryMock, times(2)).findAll();
        verify(reservationRepositoryMock, times(1)).save(reservation);
        verifyNoMoreInteractions(reservationRepositoryMock);
    }

    @Test
    @Transactional
    public void testSavev2() {
        when(reservationRepositoryMock.save(reservationMock)).thenReturn(reservationMock);

        Reservation savedReservation = reservationService.save(reservationMock);

        assertThat(savedReservation).isEqualTo(reservationMock);
    }

    @Test
    public void testFindAll() {

        when(reservationRepositoryMock.findAll()).thenReturn(Arrays.asList(new Reservation(DB_ID, DB_START_DATE, DB_DURATION, DB_NUMBER_OF_PEOPLE, new HashSet<>(), DB_PRICE, DB_COTTAGE, DB_CLIENT)));

        List<Reservation> reservations = reservationService.findAll();

        assertThat(reservations).hasSize(1);
        assertEquals(reservations.get(0).getId(), 1L);

        verify(reservationRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(reservationRepositoryMock);
    }
}
