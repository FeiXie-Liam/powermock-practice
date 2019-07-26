package com.xxx.xxx.service;

import com.xxx.xxx.domain.Person;
import com.xxx.xxx.domain.PersonRequest;
import com.xxx.xxx.utils.SalaryCalculator;
import com.xxx.xxx.utils.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TimeUnit.class, SalaryCalculator.class})
@SuppressStaticInitializationFor("com.xxx.xxx.utils.SalaryCalculator")
public class PersonServiceTest {
	private PersonService personService;

	@Before
	public void setUp() {
		personService = new PersonService();
	}

	@Test
	public void should_find_person() {
		//given
		PersonRequest request = new PersonRequest("name");
		//when
		Person result = personService.find(request);
		//then
		assertThat(result).isEqualToComparingFieldByField(new Person("None", "None", BigDecimal.ZERO));
	}

	@Test
	public void should_find_person_James() {
		//given
		PersonRequest request = new PersonRequest("James");
		PowerMockito.mockStatic(SalaryCalculator.class);
		PowerMockito.mockStatic(TimeUnit.class);
		PowerMockito.when(SalaryCalculator.calculate(BigDecimal.TEN))
				.thenReturn(new BigDecimal(20));
		//when
		Person result = personService.find(request);
		//then
		PowerMockito.verifyStatic(TimeUnit.class);
		TimeUnit.sleep(5000L);
		Person expected = new Person("Merson", "James", new BigDecimal(20));
		assertThat(result).isEqualToComparingFieldByField(expected);
	}
}