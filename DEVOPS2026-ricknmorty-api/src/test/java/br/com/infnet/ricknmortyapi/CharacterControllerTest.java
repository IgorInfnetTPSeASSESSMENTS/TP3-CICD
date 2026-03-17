package br.com.infnet.ricknmortyapi;

import br.com.infnet.ricknmortyapi.model.Personagem;
import br.com.infnet.ricknmortyapi.repository.PersonagemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(CharacterController.class)
class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonagemRepository repository;

    @Test
    @DisplayName("Deve buscar personagens por nome")
    void shouldSearchCharactersByName() throws Exception {
        Personagem rick = Personagem.builder()
                .id(1L)
                .name("Rick Sanchez")
                .status("Alive")
                .species("Human")
                .type("")
                .gender("Male")
                .origin("Earth")
                .location("Citadel of Ricks")
                .image("https://example.com/rick.png")
                .episodeCount(51)
                .build();

        Mockito.when(repository.findByNameContainingIgnoreCase(eq("rick")))
                .thenReturn(List.of(rick));

        mockMvc.perform(get("/api/characters/search")
                        .param("name", "rick")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Rick Sanchez"))
                .andExpect(jsonPath("$[0].status").value("Alive"));
    }
}