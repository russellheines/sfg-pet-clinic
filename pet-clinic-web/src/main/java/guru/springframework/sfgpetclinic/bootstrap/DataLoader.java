package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.finalAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty spells = new Specialty();
        spells.setDescription("Spells");
        spells = specialtyService.save(spells);

        Specialty charms = new Specialty();
        charms.setDescription("Charms");
        charms = specialtyService.save(charms);

        Specialty transfiguration = new Specialty();
        transfiguration.setDescription("Transfiguration");
        transfiguration = specialtyService.save(transfiguration);

        Owner owner1 = new Owner();
        owner1.setFirstName("Harry");
        owner1.setLastName("Potter");
        owner1.setAddress("234 Cedar Street");
        owner1.setCity("Somerville");
        owner1.setTelephone("1234567");

        Pet pet1 = new Pet();
        pet1.setPetType(savedDogPetType);
        pet1.setOwner(owner1);
        pet1.setBirthDate(LocalDate.now());
        pet1.setName("Nimbus 2000");
        owner1.getPets().add(pet1);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Hermoine");
        owner2.setLastName("Granger");
        owner2.setAddress("234 Cedar Street");
        owner2.setCity("Somerville");
        owner2.setTelephone("1234567");

        Pet pet2 = new Pet();
        pet2.setPetType(savedCatPetType);
        pet2.setOwner(owner2);
        pet2.setBirthDate(LocalDate.now());
        pet2.setName("Nimbus 2001");
        owner2.getPets().add(pet2);

        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Albus");
        vet1.setLastName("Dumbledore");
        vet1.getSpecialties().add(spells);
        vet1.getSpecialties().add(charms);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Minerva");
        vet2.setLastName("McGonagall");
        vet2.getSpecialties().add(transfiguration);

        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
