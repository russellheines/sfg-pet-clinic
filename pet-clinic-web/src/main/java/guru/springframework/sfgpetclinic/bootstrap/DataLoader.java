package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Harry");
        owner1.setLastName("Potter");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Hermoine");
        owner2.setLastName("Granger");

        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Albus");
        vet1.setLastName("Dumbledore");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Minerva");
        vet2.setLastName("McGonagall");

        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
