package nl.codebase.faceter.forms;

import nl.codebase.faceter.forms.definition.XmlFormDefinitionReader;
import nl.codebasesoftware.faceter.generated.GeneratedFormDef;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@SpringBootApplication
public class FormsApplication {

	@Value("${definition.dir}")
	private String formDefDir;

	public static void main(String[] args) {
		SpringApplication.run(FormsApplication.class, args);
	}

	@Bean
	public XmlFormDefinitionReader xmlFormDefinitionReader() throws JAXBException {
		return new XmlFormDefinitionReader(JAXBContext.newInstance(GeneratedFormDef.class), formDefDir);
	}
}
