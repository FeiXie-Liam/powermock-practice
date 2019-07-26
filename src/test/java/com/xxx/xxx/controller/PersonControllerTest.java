package com.xxx.xxx.controller;

import com.xxx.xxx.domain.Person;
import com.xxx.xxx.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {
	@InjectMocks
	private PersonController personController;
	@Mock
	private PersonService personService;

	@Test
	public void should_get_full_name() {
		//given
		String name = "name";
		String firstName = "firstName";
		String lastName = "lastName";
		BigDecimal salary = new BigDecimal(0);
		given(personService.find(argThat(p -> p.getName().equals(name))))
				.willReturn(new Person(firstName, lastName, salary));
		//when
		String fullName = personController.getName(name);
		//then
		assertThat(fullName).isEqualTo(firstName + "," + lastName);
	}

}