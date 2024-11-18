package br.com.fiap.resource;

import br.com.fiap.dao.CompraDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.to.CompraTO;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.sql.Connection;
import java.util.List;

@Path("/compras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompraResource {

    private CompraDAO compraDAO;

    public CompraResource() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            this.compraDAO = new CompraDAO(connection);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar o recurso.", e);
        }
    }

    @GET
    @Path("/findAll")
    public Response findAll() {
        try {
            List<CompraTO> compras = compraDAO.findAll();
            return Response.ok(compras).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao listar todas as compras.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            CompraTO compra = compraDAO.buscarPorId(id);
            if (compra == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Compra não encontrada.").build();
            }
            return Response.ok(compra).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao buscar compra.").build();
        }
    }

    @POST
    public Response inserir(CompraTO compra) {
        try {
            compraDAO.inserir(compra);
            return Response.status(Response.Status.CREATED).entity("Compra criada com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao criar compra.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, CompraTO compra) {
        try {
            compra.setIdCompra(id);
            compraDAO.atualizar(compra);
            return Response.ok("Compra atualizada com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao atualizar compra.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        try {
            compraDAO.deletar(id);
            return Response.ok("Compra removida com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao remover compra.").build();
        }
    }
}

