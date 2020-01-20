package dockerdemo.example.dockerdemo.util;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import dockerdemo.example.dockerdemo.model.Address;

@JsonComponent
public class CustomizeAddressDeserializer extends StdDeserializer<Address>{

	protected CustomizeAddressDeserializer(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}
	
	public CustomizeAddressDeserializer() { 
        this(null); 
    }
	
	

	@Override
	public Address deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		String[] list = p.getText().split(",");
		
		Address address = new Address(Integer.parseInt(list[0]), list[1], list[2]);
		
		return address;
	}

	
	

}
