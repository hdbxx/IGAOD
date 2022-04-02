package com.edu.iga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IgaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(IgaWebApplication.class, args);
		System.out.println("φ(゜▽゜*)♪  IGAOD Started successfully ");
		System.out.println("  _____ _____          ____  _____  \n" +
				" |_   _/ ____|   /\\   / __ \\|  __ \\ \n" +
				"   | || |  __   /  \\ | |  | | |  | |\n" +
				"   | || | |_ | / /\\ \\| |  | | |  | |\n" +
				"  _| || |__| |/ ____ \\ |__| | |__| |\n" +
				" |_____\\_____/_/    \\_\\____/|_____/ \n" +
				"                                    ");
	}

}
