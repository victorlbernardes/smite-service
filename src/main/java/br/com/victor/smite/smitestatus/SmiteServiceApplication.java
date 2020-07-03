package br.com.victor.smite.smitestatus;

import br.com.victor.smite.smitestatus.client.HiRezSmiteApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackageClasses = HiRezSmiteApi.class)
public class SmiteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmiteServiceApplication.class, args);
	}

}
