package com.project.bookingya.unit;

import com.project.bookingya.dtos.ReservationDto;
import com.project.bookingya.entities.GuestEntity;
import com.project.bookingya.entities.RoomEntity;
import com.project.bookingya.repositories.IGuestRepository;
import com.project.bookingya.repositories.IReservationRepository;
import com.project.bookingya.repositories.IRoomRepository;
import com.project.bookingya.services.ReservationService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeAll;

public class ReservationServiceTest_Failed {

    @Test
    void shouldNotCreateReservationWhenRoomDoesNotExist() {
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
        dto.setRoomId(UUID.randomUUID());
        dto.setGuestId(UUID.randomUUID());
        dto.setGuestsCount(2);
        dto.setCheckIn(LocalDateTime.now().plusDays(1));
        dto.setCheckOut(LocalDateTime.now().plusDays(3));

        when(roomRepository.findById(dto.getRoomId()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.create(dto));

        verify(roomRepository, times(1)).findById(dto.getRoomId());
        verify(reservationRepository, never()).saveAndFlush(any());
    }

    @Test
    void shouldNotCreateReservationWhenGuestDoesNotExist() {
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

        UUID roomId = UUID.randomUUID();
        UUID guestId = UUID.randomUUID();

        ReservationDto dto = new ReservationDto();
        dto.setRoomId(roomId);
        dto.setGuestId(guestId);
        dto.setGuestsCount(2);
        dto.setCheckIn(LocalDateTime.now().plusDays(1));
        dto.setCheckOut(LocalDateTime.now().plusDays(3));

        RoomEntity room = new RoomEntity();
        room.setId(roomId);
        room.setAvailable(true);
        room.setMaxGuests(4);

        when(roomRepository.findById(roomId))
                .thenReturn(Optional.of(room));

        when(guestRepository.findById(guestId))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.create(dto));

        verify(roomRepository, times(1)).findById(roomId);
        verify(guestRepository, times(1)).findById(guestId);
        verify(reservationRepository, never()).saveAndFlush(any());
    }

    @Test
    void shouldNotCreateReservationWhenGuestsExceedRoomCapacity() {
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

        UUID roomId = UUID.randomUUID();
        UUID guestId = UUID.randomUUID();

        ReservationDto dto = new ReservationDto();
        dto.setRoomId(roomId);
        dto.setGuestId(guestId);
        dto.setGuestsCount(10);
        dto.setCheckIn(LocalDateTime.now().plusDays(1));
        dto.setCheckOut(LocalDateTime.now().plusDays(3));

        RoomEntity room = new RoomEntity();
        room.setId(roomId);
        room.setAvailable(true);
        room.setMaxGuests(2);

        GuestEntity guest = new GuestEntity();
        guest.setId(guestId);

        when(roomRepository.findById(roomId))
                .thenReturn(Optional.of(room));

        when(guestRepository.findById(guestId))
                .thenReturn(Optional.of(guest));

        assertThrows(RuntimeException.class, () -> service.create(dto));

        verify(reservationRepository, never()).saveAndFlush(any());
    }

    @Test
    void shouldNotGetReservationByIdWhenReservationDoesNotExist() {
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

        when(reservationRepository.findById(reservationId))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getById(reservationId));

        verify(reservationRepository, times(1)).findById(reservationId);
    }

    @BeforeAll
    static void negativeCasesInfo() {
        System.out.println("==============================================");
        System.out.println("EJECUCIÓN DE CASOS NEGATIVOS - ReservationService");
        System.out.println("Se validan las siguientes reglas de negocio:");
        System.out.println("- No crear reserva cuando la habitación no existe");
        System.out.println("- No crear reserva cuando el huésped no existe");
        System.out.println("- No crear reserva cuando se excede la capacidad de la habitación");
        System.out.println("- No consultar reserva por ID cuando la reserva no existe");
        System.out.println("==============================================");
    }
}