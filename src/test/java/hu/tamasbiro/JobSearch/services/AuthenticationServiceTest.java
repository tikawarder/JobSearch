package hu.tamasbiro.JobSearch.services;

import hu.tamasbiro.JobSearch.domains.Client;
import hu.tamasbiro.JobSearch.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationServiceTest {
    private static final String TEST_UUID = "test_UUID";
    private static final String SAME_UUID = "test_UUID";
    private static final String DIFFERENT_UUID = "different_UUID";
    private static final String NAME = "Janos";
    private static final String EMAIL = "valami@barmi.hu";
    @Mock
    private ClientRepository repository;
    @InjectMocks
    private AuthenticationService underTest;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsUUIDIncorrectShouldReturnTrueWhenLoggedInClientIsNull(){
        //when
        boolean result = underTest.isUUIDIncorrect(TEST_UUID);
        //then
        assertTrue(result);
    }

    @Test
    public void testIsUUIDIncorrectShouldReturnTrueWhenLoggedInClientExistsButUUIDisNotDifferent(){
        //given
        Client client = Client.builder()
                .uuid(TEST_UUID)
                .build();
        underTest.setLoggedInClient(client);
        //when
        boolean result = underTest.isUUIDIncorrect(DIFFERENT_UUID);
        //then
        assertTrue(result);
    }

    @Test
    public void testIsUUIDIncorrectShouldReturnFalseWhenLoggedInClientExistsAndUUIDisSame(){
        //given
        Client client = Client.builder()
                .uuid(TEST_UUID)
                .build();
        underTest.setLoggedInClient(client);
        //when
        boolean result = underTest.isUUIDIncorrect(SAME_UUID);
        //then
        assertFalse(result);
    }

    @Test
    public void testLoginShouldReturnClientUUIDWhenExistInRepository(){
        //given
        Client client = Client.builder()
                .uuid(TEST_UUID)
                .name(NAME)
                .email(EMAIL)
                .build();
        Mockito.when(repository.findByNameAndEmail(NAME, EMAIL)).thenReturn(Optional.of(client));
        //when
        String result = underTest.login(client);
        //then
        assertEquals(client, underTest.getLoggedInClient());
        assertEquals(TEST_UUID, result);
        Mockito.verify(repository).findByNameAndEmail(NAME, EMAIL);
    }

    @Test
    public void testLoginShouldCreateNewClientUUIDWhenNotExistInRepository(){
        //given
        Client client = Client.builder()
                .uuid(TEST_UUID)
                .name(NAME)
                .email(EMAIL)
                .build();
        //when
        Mockito.when(repository.findByNameAndEmail(NAME, EMAIL)).thenReturn(Optional.ofNullable(null));
        Mockito.when(repository.save(client)).thenReturn(client);
        String result = underTest.login(client);
        //then
        Mockito.verify(repository).findByNameAndEmail(NAME, EMAIL);
    }
}
