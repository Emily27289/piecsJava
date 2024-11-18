package br.com.fiap.resource;

import br.com.fiap.dao.BateriaDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.to.BateriaTO;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.sql.Connection;
import java.util.List;

@Path("/baterias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BateriaResource {

    private BateriaDAO bateriaDAO;

    public BateriaResource() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            this.bateriaDAO = new BateriaDAO(connection);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar o recurso.", e);
        }
    }

    @GET
    @Path("/findAll")
    public Response findAll() {
        try {
            List<BateriaTO> baterias = bateriaDAO.findAll();
            return Response.ok(baterias).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao listar todas as baterias.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            BateriaTO bateria = bateriaDAO.buscarPorId(id);
            if (bateria == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Bateria não encontrada.").build();
            }
            return Response.ok(bateria).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao buscar bateria.").build();
        }
    }

    @POST
    public Response inserir(BateriaTO bateria) {
        try {
            bateriaDAO.inserir(bateria);
            return Response.status(Response.Status.CREATED).entity("Bateria criada com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao criar bateria.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, BateriaTO bateria) {
        try {
            bateria.setIdBateria(id);
            bateriaDAO.atualizar(bateria);
            return Response.ok("Bateria atualizada com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao atualizar bateria.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        try {
            bateriaDAO.deletar(id);
            return Response.ok("Bateria removida com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao remover bateria.").build();
        }
    }
}
