package PracticaXML;



import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;



public class EjercicioXML {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		
		DOMImplementation implementacion = builder.getDOMImplementation();
		Document registroEmpleados = implementacion.createDocument(null, "empleados", null);
		
		registroEmpleados.setXmlVersion("1.0");
		

		crearEmpleadoXML(registroEmpleados);

		Document registro = builder.parse(new File("Empleados.xml"));
		leerXml(registro);
	}

	public static void leerXml(Document registro) {
		registro.getDocumentElement().normalize();
		System.out.println("El elemento raiz es " + registro.getDocumentElement().getNodeName());
		NodeList empleados = registro.getElementsByTagName("empleado");
		System.out.println("Se han econtrado " + empleados.getLength() + " empleado");
		
	}
	
	public static void crearEmpleadoXML(Document registro) {
		try {
			Element empleado = registro.createElement("empleado");
			registro.getDocumentElement().appendChild(empleado);

			Element id = registro.createElement("id");
			Text texto = registro.createTextNode("01");
			id.appendChild(texto);
			empleado.appendChild(id);

			Element nombre = registro.createElement("nombre");
			texto = registro.createTextNode("Antonio");
			nombre.appendChild(texto);
			empleado.appendChild(nombre);

			Element apellido = registro.createElement("apellido");
			texto = registro.createTextNode("Morales");
			apellido.appendChild(texto);
			empleado.appendChild(apellido);

			Source origen = new DOMSource(registro);
			Result resultado = new StreamResult(new File ("Empleados.xml"));
			Transformer transformador = TransformerFactory.newInstance().newTransformer();
			transformador.transform(origen,  resultado);
			
			Result salidaEstandar = new StreamResult(System.out);
			transformador.transform(origen,salidaEstandar);
			
			
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
