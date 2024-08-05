package com.example.taskmanagement.TaskManager;

import com.example.taskmanagement.TaskManager.Controller.UserController;
import com.example.taskmanagement.TaskManager.Entity.Task;
import com.example.taskmanagement.TaskManager.Entity.User;
import com.example.taskmanagement.TaskManager.Repository.TaskRepository;
import com.example.taskmanagement.TaskManager.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class TaskManagerApplicationTests {

//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private UserService userService;
//
//	private User user;
//
//	@BeforeEach
//	public void setUp() {
//		MockitoAnnotations.openMocks(this);
//
//		user = new User();
//		user.setId(1L);
//		user.setName("John Doe");
//		user.setEmail("john.doe@example.com");
//
//		when(userService.getAllUsers()).thenReturn(Arrays.asList(user));
//		when(userService.getUserById(anyLong())).thenReturn(user);
//		when(userService.createUser(any(User.class))).thenReturn(user);
//		when(userService.updateUser(anyLong(), any(User.class))).thenReturn(user);
//	}
//
//	@Test
//	public void testListUsers() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/users"))
//				.andExpect(status().isOk())
//				.andExpect(view().name("userList"))
//				.andExpect(model().attributeExists("users"));
//	}
//
//	@Test
//	public void testNewUser() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/users/new"))
//				.andExpect(status().isOk())
//				.andExpect(view().name("newUser"))
//				.andExpect(model().attributeExists("user"));
//	}
//
//	@Test
//	public void testCreateUser() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.post("/users")
//						.param("name", "John Doe")
//						.param("email", "john.doe@example.com"))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(redirectedUrl("/users"));
//	}
//
//	@Test
//	public void testEditUser() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/users/edit/1"))
//				.andExpect(status().isOk())
//				.andExpect(view().name("editUser"))
//				.andExpect(model().attributeExists("user"));
//	}
//
//	@Test
//	public void testUpdateUser() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.post("/users/1")
//						.param("name", "John Doe")
//						.param("email", "john.doe@example.com"))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(redirectedUrl("/users"));
//	}
//
//	@Test
//	public void testDeleteUser() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.post("/users/1/delete"))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(redirectedUrl("/users"));
//	}
}