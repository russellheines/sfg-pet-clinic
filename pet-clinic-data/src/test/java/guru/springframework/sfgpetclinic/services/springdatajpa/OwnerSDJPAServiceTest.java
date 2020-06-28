package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJPAServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJPAService service;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByLastName() {
        Owner returnOwner = Owner.builder().id(1L).lastName("Potter").build();

        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner potter = service.findByLastName("Potter");

        assertEquals("Potter", potter.getLastName());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnerSet = new HashSet<>();
        returnOwnerSet.add(Owner.builder().id(1L).build());
        returnOwnerSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

        assertNotNull(service.findAll());
        assertEquals(2, service.findAll().size());
    }

    @Test
    void findById() {
        Owner returnOwner = Owner.builder().id(1L).lastName("Potter").build();

        when(ownerRepository.findById(any())).thenReturn(Optional.of(returnOwner));

        assertNotNull(service.findById(1L));
    }

    @Test
    void findByIdNotFound() {
        Owner returnOwner = Owner.builder().id(1L).lastName("Potter").build();

        when(ownerRepository.findById(any())).thenReturn(Optional.empty());

        assertNull(service.findById(1L));
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).lastName("Potter").build();
        Owner returnOwner = Owner.builder().id(1L).lastName("Potter").build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        Owner returnOwner = Owner.builder().id(1L).lastName("Potter").build();

        service.delete(returnOwner);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        // default times 1
        verify(ownerRepository).deleteById(any());
    }
}