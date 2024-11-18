package br.com.fiap.resource;

import br.com.fiap.dao.CompradorDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.to.CompradorTO;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.sql.Connection;
import java.util.List;

@Path("/compradores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompradorResource {

    private CompradorDAO compradorDAO;

    public CompradorResource() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            this.compradorDAO = new CompradorDAO(connection);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar o recurso.", e);
        }
    }

    @GET
    @Path("/findAll")
    public Response findAll() {
        try {
            List<CompradorTO> compradores = compradorDAO.findAll();
            return Response.ok(compradores).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao listar todos os compradores.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            CompradorTO comprador = compradorDAO.buscarPorId(id);
            if (comprador == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Comprador não encontrado.").build();
            }
            return Response.ok(comprador).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao buscar comprador.").build();
        }
    }

    @POST
    public Response inserir(CompradorTO comprador) {
        try {
            compradorDAO.inserir(comprador);
            return Response.status(Response.Status.CREATED).entity("Comprador criado com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao criar comprador.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, CompradorTO comprador) {
        try {
            comprador.setIdComprador(id);
            compradorDAO.atualizar(comprador);
            return Response.ok("Comprador atualizado com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao atualizar comprador.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        try {
            compradorDAO.deletar(id);
            return Response.ok("Comprador removido com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao remover comprador.").build();
        }
    }
}
