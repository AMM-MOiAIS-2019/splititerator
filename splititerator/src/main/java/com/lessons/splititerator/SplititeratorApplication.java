package com.lessons.splititerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.StreamSupport;

@SpringBootApplication
public class SplititeratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplititeratorApplication.class, args);
		String[] array = {"one", "two", "three", "four", "five"};
		MyString ms = new MyString(array);
		System.out.println(ms.stream().count());
		System.out.println(ms.parallelStream().count());

		String[] array2 = {"one", "two", "three", "four", "five", "six"};
		ms = new MyString(array2);
		System.out.println(ms.stream().count());
		System.out.println(ms.parallelStream().count());

	}

}
