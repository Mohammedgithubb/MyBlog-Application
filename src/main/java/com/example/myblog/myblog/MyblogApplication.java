package com.example.myblog.myblog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyblogApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	//  ^
	//  |
	//We know that Spring IOC will create the objects but in some conditions,
	//For example, This ModelMapper is used instead of writing lengthy codes
	//where After importing this dependency(ModelMapper()) in pom.xml we have to create
	//one method in main and give @beam as a annotation so that Spring IOC will understand
	//and we can use that by creating referenc variable in model layer i.e(ServiceImpl) layer.

}
