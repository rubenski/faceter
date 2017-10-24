package nl.codebase.faceter.forms.definition;

import lombok.extern.slf4j.Slf4j;
import nl.codebasesoftware.faceter.generated.GeneratedFormDef;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by x077273 on 13-Jun-15.
 */
@Slf4j
public class XmlFormDefinitionReader {

    private JAXBContext jaxbContext;
    private Path path;

    public XmlFormDefinitionReader(final JAXBContext jaxbContext, final String path) {
        this.jaxbContext = jaxbContext;
        this.path = Paths.get(path);
    }

    Optional<GeneratedFormDef> getAsObject(String formId) throws XmlConfigException {

        List<GeneratedFormDef> formDefs = null;

        try {
            formDefs = readAll();
        } catch (XmlFileReaderException e) {
            throw new XmlConfigException(e);
        }

        for (GeneratedFormDef formDef : formDefs) {
            if (formDef.getFormId().equals(formId)) {
                return Optional.of(formDef);
            }
        }

        return Optional.empty();
    }

    List<GeneratedFormDef> getAsObjects() throws XmlConfigException {
        try {
            return readAll();
        } catch (XmlFileReaderException e) {
            throw new XmlConfigException(e);
        }
    }

    private List<GeneratedFormDef> readAll() throws XmlFileReaderException {
        List<Path> paths = getXmlFilePaths();
        List<GeneratedFormDef> objects = new ArrayList<>();
        for (Path path : paths) {
            try {
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                Object o = unmarshaller.unmarshal(path.toFile());
                objects.add((GeneratedFormDef) o);
            } catch (JAXBException e) {
                String message = String.format("Could not unmarshall XML for file %s", path.getFileName());
                throw new XmlFileReaderException(message, e);
            }
        }
        return objects;
    }

    private List<Path> getXmlFilePaths() throws XmlFileReaderException {
        List<Path> paths = new ArrayList<>();
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, "*.xml")) {
            for (Path d : ds) {
                paths.add(d);
            }
        } catch (IOException e) {
            throw new XmlFileReaderException("Path " + path + " does not esxisssst or is not accessible");
        }
        return paths;
    }
}
