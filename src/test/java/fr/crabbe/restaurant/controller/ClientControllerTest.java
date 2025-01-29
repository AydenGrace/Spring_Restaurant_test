package fr.crabbe.restaurant.controller;

import fr.crabbe.restaurant.domain.dto.ClientDto;
import fr.crabbe.restaurant.domain.entity.Client;
import fr.crabbe.restaurant.exception.ClientNotFoundException;
import fr.crabbe.restaurant.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClientControllerTest {
    private static UUID uuidTest;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        uuidTest = UUID.randomUUID();

    }

    @Test
    void create_shouldReturnStatusCreated() {
        ClientDto dto = new ClientDto(null, "TEST Test");
        Client client = new Client(159889L, uuidTest, "TEST Test", new ArrayList<>());

        when(clientService.create(dto)).thenReturn(client);

        ResponseEntity<ClientDto> response = clientController.create(dto);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getUuid());
        Assertions.assertEquals(client.getName(), response.getBody().getName());
    }

    @Test
    void getByUuid_shouldReturnClientDto() {
        ClientDto clientDto = new ClientDto(uuidTest, "TEST Test");

        when(clientService.getByUuid(Mockito.any(UUID.class))).thenReturn(clientDto);

        final ResponseEntity<ClientDto> response = clientController.getByUuid(uuidTest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(uuidTest, response.getBody().getUuid());
    }

    @Test
    void getByUuid_shouldReturnNotFound() {
        when(clientService.getByUuid(Mockito.any(UUID.class))).thenThrow(new ClientNotFoundException());

        Assertions.assertThrows(ClientNotFoundException.class,()->clientController.getByUuid(uuidTest));
    }

    @Test
    void getAll_shouldReturnAll(){
        List<ClientDto> clients = new ArrayList<>();
        clients.add(new ClientDto(UUID.randomUUID(),"TEST1"));
        clients.add(new ClientDto(UUID.randomUUID(),"TEST2"));
        clients.add(new ClientDto(UUID.randomUUID(),"TEST3"));

        when(clientService.getAll()).thenReturn(clients);

        ResponseEntity<List<ClientDto>> response = clientController.getAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(3,response.getBody().size());
    }

    @Test
    void patch_shouldReturnStatusOK(){
        ClientDto dto = new ClientDto(null,"TEST");
        ClientDto dtoResponse = new ClientDto(uuidTest,"TEST");

        when(clientService.update(Mockito.any(UUID.class),Mockito.any(ClientDto.class))).thenReturn(dtoResponse);

        ResponseEntity<ClientDto> response = clientController.patch(uuidTest,dto);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(dtoResponse.getUuid(),response.getBody().getUuid());
    }

    @Test
    void patch_shouldReturnStatusNotFound(){

        when(clientService.update(Mockito.any(UUID.class),Mockito.any(ClientDto.class))).thenThrow(new ClientNotFoundException());

        Assertions.assertThrows(ClientNotFoundException.class,()->clientController.patch(UUID.randomUUID(),new ClientDto(null,"TEST")));
    }

    @Test
    void patch_shouldReturnStatusNotModified(){

        when(clientService.update(Mockito.any(UUID.class),Mockito.any(ClientDto.class))).thenThrow(new ClientNotFoundException());

        Assertions.assertThrows(ClientNotFoundException.class,()->clientController.patch(UUID.randomUUID(),new ClientDto(null,"TEST")));
    }
}