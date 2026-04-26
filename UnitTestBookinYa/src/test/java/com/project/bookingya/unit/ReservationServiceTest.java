package com.project.bookingya.unit;

import com.project.bookingya.dtos.ReservationDto;
import com.project.bookingya.entities.ReservationEntity;
import com.project.bookingya.entities.GuestEntity;
import com.project.bookingya.repositories.IReservationRepository;
import com.project.bookingya.repositories.IRoomRepository;
import com.project.bookingya.repositories.IGuestRepository;
import com.project.bookingya.services.ReservationService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;
import com.project.bookingya.entities.RoomEntity;
import java.util.Optional;
import java.util.UUID;
import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeAll;

public class ReservationServiceTest {

    @Test
    void shouldCreateReservationSuccessfully() {

        // Arrange
        IReservationRepository reservationRepository = mock(IReservationRepository.class);
        IRoomRepository roomRepository = mock(IRoomRepository.class);
        IGuestRepository guestRepository = mock(IGuestRepository.class);
        ModelMapper mapper = mock(ModelMapper.class);

        ReservationService service = new ReservationService(
                reservationRepository,
                roomRepository,
                guestRepository,
                mapper
        );

        ReservationDto dto = new ReservationDto();
        UUID roomId = UUID.randomUUID();
        UUID guestId = UUID.randomUUID();
        dto.setRoomId(roomId);
        dto.setGuestId(guestId);
        dto.setGuestsCount(2);
        dto.setCheckIn(LocalDateTime.now().plusDays(1));
        dto.setCheckOut(LocalDateTime.now().plusDays(3));
        RoomEntity room = new RoomEntity();
        room.setId(roomId);
        room.setAvailable(true);
        room.setMaxGuests(4);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        GuestEntity guest = new GuestEntity();
        guest.setId(guestId);

        when(guestRepository.findById(guestId)).thenReturn(Optional.of(guest));

        ReservationEntity entity = new ReservationEntity();
        ReservationEntity saved = new ReservationEntity();

        when(mapper.map(dto, ReservationEntity.class)).thenReturn(entity);
        when(reservationRepository.saveAndFlush(entity)).thenReturn(saved);
        when(mapper.map(saved, com.project.bookingya.models.Reservation.class))
                .thenReturn(new com.project.bookingya.models.Reservation());

        // Act
        var result = service.create(dto);

        // Assert
        assertNotNull(result);
        verify(reservationRepository, times(1)).saveAndFlush(any());
    }


    @Test
    void shouldGetAllReservationsSuccessfully() {

        // Arrange
        IReservationRepository reservationRepository = mock(IReservationRepository.class);
        IRoomRepository roomRepository = mock(IRoomRepository.class);
        IGuestRepository guestRepository = mock(IGuestRepository.class);
        ModelMapper mapper = mock(ModelMapper.class);

        ReservationService service = new ReservationService(
                reservationRepository,
                roomRepository,
                guestRepository,
                mapper
        );

        ReservationEntity reservationEntity = new ReservationEntity();

        when(reservationRepository.findAll())
                .thenReturn(List.of(reservationEntity));

        when(mapper.map(any(), any(Type.class)))
                .thenReturn(List.of(new com.project.bookingya.models.Reservation()));

        // Act
        var result = service.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdateReservationSuccessfully() {

        // Arrange
        IReservationRepository reservationRepository = mock(IReservationRepository.class);
        IRoomRepository roomRepository = mock(IRoomRepository.class);
        IGuestRepository guestRepository = mock(IGuestRepository.class);
        ModelMapper mapper = mock(ModelMapper.class);

        ReservationService service = new ReservationService(
                reservationRepository,
                roomRepository,
                guestRepository,
                mapper
        );

        UUID reservationId = UUID.randomUUID();
        UUID roomId = UUID.randomUUID();
        UUID guestId = UUID.randomUUID();

        ReservationDto dto = new ReservationDto();
        dto.setRoomId(roomId);
        dto.setGuestId(guestId);
        dto.setGuestsCount(2);
        dto.setCheckIn(LocalDateTime.now().plusDays(1));
        dto.setCheckOut(LocalDateTime.now().plusDays(3));

        ReservationEntity existingReservation = new ReservationEntity();
        existingReservation.setId(reservationId);

        RoomEntity room = new RoomEntity();
        room.setId(roomId);
        room.setAvailable(true);
        room.setMaxGuests(4);

        GuestEntity guest = new GuestEntity();
        guest.setId(guestId);

        ReservationEntity updatedReservation = new ReservationEntity();
        updatedReservation.setId(reservationId);

        when(reservationRepository.findById(reservationId))
                .thenReturn(Optional.of(existingReservation));

        when(roomRepository.findById(roomId))
                .thenReturn(Optional.of(room));

        when(guestRepository.findById(guestId))
                .thenReturn(Optional.of(guest));

        when(reservationRepository.saveAndFlush(existingReservation))
                .thenReturn(updatedReservation);

        when(mapper.map(updatedReservation, com.project.bookingya.models.Reservation.class))
                .thenReturn(new com.project.bookingya.models.Reservation());

        // Act
        var result = service.update(dto, reservationId);

        // Assert
        assertNotNull(result);
        verify(reservationRepository, times(1)).findById(reservationId);
        verify(reservationRepository, times(1)).saveAndFlush(existingReservation);
    }

    @Test
    void shouldDeleteReservationSuccessfully() {

        // Arrange
        IReservationRepository reservationRepository = mock(IReservationRepository.class);
        IRoomRepository roomRepository = mock(IRoomRepository.class);
        IGuestRepository guestRepository = mock(IGuestRepository.class);
        ModelMapper mapper = mock(ModelMapper.class);

        ReservationService service = new ReservationService(
                reservationRepository,
                roomRepository,
                guestRepository,
                mapper
        );

        UUID reservationId = UUID.randomUUID();

        ReservationEntity reservation = new ReservationEntity();
        reservation.setId(reservationId);

        when(reservationRepository.findById(reservationId))
                .thenReturn(Optional.of(reservation));

        doNothing().when(reservationRepository).delete(reservation);

        // Act
        service.delete(reservationId);

        // Assert
        verify(reservationRepository, times(1)).findById(reservationId);
        verify(reservationRepository, times(1)).delete(reservation);
    }

    @Test
    void shouldGetReservationByIdSuccessfully() {

        // Arrange
        IReservationRepository reservationRepository = mock(IReservationRepository.class);
        IRoomRepository roomRepository = mock(IRoomRepository.class);
        IGuestRepository guestRepository = mock(IGuestRepository.class);
        ModelMapper mapper = mock(ModelMapper.class);

        ReservationService service = new ReservationService(
                reservationRepository,
                roomRepository,
                guestRepository,
                mapper
        );

        UUID reservationId = UUID.randomUUID();

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(reservationId);

        when(reservationRepository.findById(reservationId))
                .thenReturn(Optional.of(reservationEntity));

        when(mapper.map(reservationEntity, com.project.bookingya.models.Reservation.class))
                .thenReturn(new com.project.bookingya.models.Reservation());

        // Act
        var result = service.getById(reservationId);

        // Assert
        assertNotNull(result);
        verify(reservationRepository, times(1)).findById(reservationId);
    }

    @BeforeAll
    static void positiveCasesInfo() {
        System.out.println("==============================================");
        System.out.println("EJECUCIÓN DE CASOS POSITIVOS - ReservationService");
        System.out.println("Se validan los siguientes escenarios:");
        System.out.println("- Creación exitosa de una reserva");
        System.out.println("- Consulta general de reservas");
        System.out.println("- Consulta de una reserva por ID");
        System.out.println("- Actualización exitosa de una reserva");
        System.out.println("- Eliminación exitosa de una reserva");
        System.out.println("==============================================");
    }

}