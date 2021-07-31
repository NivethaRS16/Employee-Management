package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repo;
	@Autowired
	private TestEntityManager entitymanager;
	
	@Test
	public void testUser()
	{
		User user = new User();
		user.setEmail("admin@gmail.com");
		user.setFirstName("Admin");
		user.setLastName("Admin");
		user.setPassword("admin1");
		
		User savedUser = repo.save(user);
		User existUser = entitymanager.find(User.class,savedUser.getId());
		try
		{
		//existUser.setEmail("Dummy@gmail.com");	
		assertThat(savedUser.getEmail()).isEqualTo(existUser.getEmail());
		}
		catch(AssertionError e)
		{
			assertThat(e).hasMessage("There is a difference in the Email found and test case failed");
		}
	}

}
