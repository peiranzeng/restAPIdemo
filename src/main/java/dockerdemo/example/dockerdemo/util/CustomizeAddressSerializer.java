package dockerdemo.example.dockerdemo.util;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import dockerdemo.example.dockerdemo.model.Address;

@JsonComponent
public class CustomizeAddressSerializer extends StdSerializer<Address> {

	public CustomizeAddressSerializer() {
		this(null);
	}

	public CustomizeAddressSerializer(Class<Address> t) {
		super(t);
	}

	@Override
	public void serialize(Address value, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException{
		
		String str = String.valueOf(value.getHomeNumber()) + ", " + value.getCity() + ", " + value.getState();
		
		gen.writeString(str);
	}
    
	
	
}
