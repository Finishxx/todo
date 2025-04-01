package cz.orchitech.todobackend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.orchitech.todobackend.api.dto.TodoEntryRequestDto;
import cz.orchitech.todobackend.business.TodoEntryService;

import cz.orchitech.todobackend.model.TodoEntry;
import cz.orchitech.todobackend.model.TodoEntryStatus;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests if the controller follows the `openapi.yaml` specification
 */
@WebMvcTest(TodoEntryController.class)
class TodoEntryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TodoEntryService service;

    @InjectMocks
    private TodoEntryController controller;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    final private TodoEntry entryTodo1 = new TodoEntry(1L, "TODO1", TodoEntryStatus.TODO);
    final private TodoEntry entryTodo2 = new TodoEntry(2L, "TODO2", TodoEntryStatus.TODO);

    final private TodoEntryRequestDto requestDtoTodo1 = new TodoEntryRequestDto("TODO1", TodoEntryStatus.TODO);


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    final String entriesPath = "/entries";

    @Test
    void readAllEmpty() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(entriesPath).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    void readAllTwoEntries() throws Exception {
        when(service.readAll()).thenReturn(Arrays.asList(entryTodo1, entryTodo2));
        mockMvc.perform(MockMvcRequestBuilders.get(entriesPath).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("TODO1"))
                .andExpect(jsonPath("$[0].status").value("TODO"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("TODO2"))
                .andExpect(jsonPath("$[1].status").value("TODO"));
    }


    @Test
    void readOneNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(entriesPath + "/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


    }

    @Test
    void readOneExist() throws Exception {
        when(service.readById(1L)).thenReturn(Optional.of(entryTodo1));
        mockMvc.perform(MockMvcRequestBuilders.get(entriesPath + "/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TODO1"))
                .andExpect(jsonPath("$.status").value("TODO"));

    }

    @Test
    void createEntry() throws Exception {
        when(service.create(any())).thenReturn(entryTodo1);
        mockMvc.perform(post(entriesPath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDtoTodo1)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/entries/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TODO1"))
                .andExpect(jsonPath("$.status").value("TODO"));
    }

    @Test
    void createInvalidEntry() throws Exception {
        String invalid = "{\"name\":\"\", \"status\": \"DANZO\"}";

        mockMvc.perform(post(entriesPath).contentType(MediaType.APPLICATION_JSON).content(invalid)).andExpect(status().isBadRequest());
    }

    @Test
    void updateEntryNotExist() throws Exception {
        when(service.update(any())).thenThrow(new EntityNotFoundException());
        mockMvc.perform(patch(entriesPath + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDtoTodo1)))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateEntry() throws Exception {
        when(service.update(any())).thenReturn(entryTodo1);
        mockMvc.perform(patch(entriesPath + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDtoTodo1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TODO1"))
                .andExpect(jsonPath("$.status").value("TODO"));
    }

    @Test
    void updateInvalidEntry() throws Exception {
        String invalid = "{\"name\":\"\", \"status\": \"DANZO\"}";

        mockMvc.perform(patch(entriesPath + "/1").contentType(MediaType.APPLICATION_JSON).content(invalid)).andExpect(status().isBadRequest());
    }

    @Test
    void deleteEntity() throws Exception {
        mockMvc.perform(delete(entriesPath + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteEntityNotExist() throws Exception {
        doThrow(new EntityNotFoundException()).when(service).deleteById(1L);
        mockMvc.perform(delete(entriesPath + "/1"))
                .andExpect(status().isNotFound());
    }
}