package com.example;

import java.util.TreeMap;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.example.application.proxies")
@OpenAPIDefinition(info = @Info(title = "Microservicio: Demos", version = "1.0", description = "**Demos** de Microservicios.", license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html"), contact = @Contact(name = "Javier Martín", url = "https://github.com/jmagit", email = "support@example.com")), externalDocs = @ExternalDocumentation(description = "Documentación del proyecto", url = "https://github.com/jmagit/curso"))
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	OpenApiCustomizer sortSchemasAlphabetically() {
		return openApi -> {
			var schemas = openApi.getComponents().getSchemas();
			openApi.getComponents().setSchemas(new TreeMap<>(schemas));
		};
	}

	@Bean
	@Primary
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplateLB(RestTemplateBuilder builder) {
		return builder.build();
	}

//	@Autowired
//	ActorService srv;
//	
	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicación arrancada...");
//		srv.getByProjection(ActorDTO.class).forEach(System.out::println);
	}
//	
//	@Bean
//	CommandLineRunner lookup(CalculatorProxy client) {
//		return args -> { System.err.println("CalculatorProxy --> " + client.add(2, 3)); };
//	}
//	
////	@Bean
//	CommandLineRunner lookup(Jaxb2Marshaller marshaller) {
//		return args -> {		
//			WebServiceTemplate ws = new WebServiceTemplate(marshaller);
//			var request = new AddRequest();
//			request.setOp1(2);
//			request.setOp2(3);
//			var response = (AddResponse) ws.marshalSendAndReceive("http://localhost:8090/ws/calculator", 
//					 request, new SoapActionCallback("http://example.com/webservices/schemas/calculator"));
//			System.err.println("WebServiceTemplate --> " + response.getAddResult());
//		};
//	}

	/*
	 * @Autowired ActorRepository dao;
	 * 
	 * @Override
	 * 
	 * @Transactional public void run(String... args) throws Exception {
	 * System.err.println("Aplicación arrancada..."); //
	 * dao.findAll().forEach(System.out::println); // var item = dao.findById(301);
	 * // if(item.isEmpty()) { // System.err.println("No encontrado"); // } else {
	 * // System.out.println(item.get()); // } // var actor = new Actor(0, "Pepito",
	 * "Grillo"); // System.out.println(dao.save(actor)); // var item =
	 * dao.findById(201); // if(item.isEmpty()) { //
	 * System.err.println("No encontrado"); // } else { // var actor = item.get();
	 * // actor.setFirstName(actor.getFirstName().toUpperCase()); //
	 * dao.save(actor); // } // dao.deleteById(201); //
	 * dao.findAll().forEach(System.out::println); //
	 * dao.findTop5ByLastNameStartingWithOrderByFirstNameDesc("P").forEach(System.
	 * out::println); // dao.findTop5ByLastNameStartingWith("P",
	 * Sort.by("LastName").ascending()).forEach(System.out::println); //
	 * dao.findByActorIdGreaterThanEqual(200).forEach(System.out::println); //
	 * dao.findByJPQL(200).forEach(System.out::println); //
	 * dao.findBySQL(200).forEach(System.out::println); // dao.findAll((root, query,
	 * builder) -> builder.greaterThanOrEqualTo(root.get("actorId"),
	 * 200)).forEach(System.out::println); // dao.findAll((root, query, builder) ->
	 * builder.lessThan(root.get("actorId"), 10)).forEach(System.out::println); //
	 * var item = dao.findById(1); // if(item.isEmpty()) { //
	 * System.err.println("No encontrado"); // } else { // var actor = item.get();
	 * // System.out.println(actor); // actor.getFilmActors().forEach(f ->
	 * System.out.println(f.getFilm().getTitle())); // } // var actor = new Actor(0,
	 * null, "12345678Z"); // if(actor.isValid()) { //
	 * System.out.println(dao.save(actor)); // } else { //
	 * actor.getErrors().forEach(System.out::println); // } // var actor = new
	 * ActorDTO(0, "FROM", "DTO"); // dao.save(ActorDTO.from(actor)); //
	 * dao.findAll().forEach(item -> System.out.println(ActorDTO.from(item))); //
	 * dao.readByActorIdGreaterThanEqual(200).forEach(System.out::println); //
	 * dao.queryByActorIdGreaterThanEqual(200).forEach(item ->
	 * System.out.println(item.getId() + " " + item.getNombre())); //
	 * dao.findByActorIdGreaterThanEqual(200,
	 * ActorDTO.class).forEach(System.out::println); //
	 * dao.findByActorIdGreaterThanEqual(200, ActorShort.class).forEach(item ->
	 * System.out.println(item.getId() + " " + item.getNombre())); //
	 * dao.findAll(PageRequest.of(3, 10,
	 * Sort.by("ActorId"))).forEach(System.out::println); // var serializa = new
	 * ObjectMapper(); // dao.findByActorIdGreaterThanEqual(200,
	 * ActorDTO.class).forEach(item -> { // try { //
	 * System.out.println(serializa.writeValueAsString(item)); // } catch
	 * (JsonProcessingException e) { // // TODO Auto-generated catch block //
	 * e.printStackTrace(); // } // }); var serializa = new XmlMapper();;
	 * dao.findAll(PageRequest.of(3, 10, Sort.by("ActorId"))).forEach(item -> { try
	 * { System.out.println(serializa.writeValueAsString(item)); } catch
	 * (JsonProcessingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } });
	 * 
	 * }
	 */
	/*
	 * @Autowired // @Qualifier("es") Saluda saluda;
	 * 
	 * @Autowired // @Qualifier("en") Saluda saluda2;
	 * 
	 * @Autowired Entorno entorno;
	 * 
	 * @Autowired private Rango rango;
	 * 
	 * // @Autowired(required = false) // SaludaEnImpl kk;
	 * 
	 * @Override public void run(String... args) throws Exception {
	 * System.err.println("Aplicación arrancada..."); // var saluda = new Saluda();
	 * System.out.println(saluda.getContador()); saluda.saluda("Mundo"); //
	 * saluda.saluda(null); saluda2.saluda("Mundo");
	 * System.out.println(saluda.getContador());
	 * System.out.println(saluda2.getContador());
	 * System.out.println(entorno.getContador()); System.out.println(rango.getMin()
	 * + " -> " + rango.getMax()); }
	 */
}
