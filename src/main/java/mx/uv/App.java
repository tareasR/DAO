package mx.uv;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.UUID;
import com.google.gson.*;

/**
 * Hello world!
 *
 */
public class App 
{
    static Gson gson = new Gson();
    static HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
    
    public static void main( String[] args )
    {
        
        System.out.println( "Hello World!" );

        port(80);

        get("/usuarios", (request, response)->{
            response.type("application/json");
            // return gson.toJson(usuarios.values());
            return gson.toJson(DAO.dameUsuarios());
        });

        post("/usuarios", (request, response)->{
            String payload = request.body();
            Usuario usuario = gson.fromJson(payload, Usuario.class);
            System.out.println("payload "+payload);
            String id = UUID.randomUUID().toString();
            usuario.setId(id);
            //usuarios.put(id, usuario);
            DAO.crearUsuario(usuario);
            System.out.println("n "+usuario.getNombre());
            System.out.println("p "+usuario.getPassword());
            System.out.println("i "+usuario.getId());
            return usuario;
        });

    }
}
