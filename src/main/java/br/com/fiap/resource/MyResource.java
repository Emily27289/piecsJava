package br.com.fiap.resource;

import javax.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.*;

@Path("/PIECS")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MyResource {

    private static final Map<Integer, String> DADOS = new HashMap<>();
    private static int contador = 1;

    @GET
    public Response listarTodos() {
        return Response.ok(DADOS).build();
    }

    @GET
    @Path("/PIECS")
    public Response buscarPorId(@PathParam("id") int id) {
        if (!DADOS.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Item não encontrado").build();
        }
        return Response.ok(DADOS.get(id)).build();
    }

    @POST
    public Response adicionar(String novoItem) {
        int id = contador++;
        DADOS.put(id, novoItem);
        return Response.status(Response.Status.CREATED)
                .entity("Item criado com ID: " + id).build();
    }

    // PUT - Atualiza um item existente
    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, String novoItem) {
        if (!DADOS.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Item não encontrado").build();
        }
        DADOS.put(id, novoItem);
        return Response.ok("Item atualizado com sucesso").build();
    }

    // DELETE - Remove um item
    @DELETE
    @Path("/PIECS")
    public Response deletar(@PathParam("id") int id) {
        if (!DADOS.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Item não encontrado").build();
        }
        DADOS.remove(id);
        return Response.ok("Item removido com sucesso").build();
    }

}

