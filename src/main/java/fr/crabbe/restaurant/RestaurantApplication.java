package fr.crabbe.restaurant;

import fr.crabbe.restaurant.domain.entity.Client;
import fr.crabbe.restaurant.domain.entity.Dish;
import fr.crabbe.restaurant.repository.IClientRepository;
import fr.crabbe.restaurant.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class RestaurantApplication implements CommandLineRunner {

	private final IClientRepository clientRepository;
	private final IDishRepository dishRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}


	@Override
	public void run(String... args) {
		if (clientRepository.count() == 0 && dishRepository.count() == 0) {
			loadInitialData();
		}
	}

	private void loadInitialData() {
		log.debug("🔹 Initialisation des données...");

		//Client creation
		List<Client> clients = new ArrayList<>();
		clients.add(new Client(null,null,"Pierre-Alex",new ArrayList<>()));
		clients.add(new Client(null,null,"Le",new ArrayList<>()));
		clients.add(new Client(null,null,"Lorry",new ArrayList<>()));
		clients.add(new Client(null,null,"Michael",new ArrayList<>()));

		//Dish creation
		List<Dish> dishes = new ArrayList<>();
		dishes.add(new Dish(null,null,"Tartiflette",new ArrayList<>()));
		dishes.add(new Dish(null,null,"Fondue",new ArrayList<>()));
		dishes.add(new Dish(null,null,"Raclette",new ArrayList<>()));
		dishes.add(new Dish(null,null,"Burger",new ArrayList<>()));

		//Save
		for (Client client : clients) {
			clientRepository.save(client);
		}

		for (Dish dish : dishes) {
			dishRepository.save(dish);
		}

		log.debug("✅ Données initiales insérées avec succès !");
	}


}
