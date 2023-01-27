package com.harulab.adapfit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest
class AdapfitApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	@ParameterizedTest
	void exception(int input) {
		assumeTrue(input == 10, () -> "입력값이 10이 아니므로 테스트를 종료합니다.");
		assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(100));
	}
}
