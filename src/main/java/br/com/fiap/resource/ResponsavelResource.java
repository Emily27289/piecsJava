package br.com.fiap.resource;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.ResponsavelDAO;
import br.com.fiap.to.ResponsavelTO;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.sql.Connection;
import java.util.List;

@Path("/responsaveis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResponsavelResource {

    private ResponsavelDAO responsavelDAO;

    public ResponsavelResource() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            this.responsavelDAO = new ResponsavelDAO(connection);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar o recurso.", e);
        }
    }

    @GET
    @Path("/findAll")
    public Response findAll() {
        try {
            List<ResponsavelTO> responsaveis = responsavelDAO.findAll();
            return Response.ok(responsaveis).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao listar todos os responsáveis.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            ResponsavelTO responsavel = responsavelDAO.buscarPorId(id);
            if (responsavel == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Responsável não encontrado.").build();
            }
            return Response.ok(responsavel).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao buscar responsável.").build();
        }
    }

    @POST
    public Response inserir(ResponsavelTO responsavel) {
        try {
            if (responsavelDAO.inserirResponsavel(responsavel)) {
                return Response.status(Response.Status.CREATED).entity("Responsável criado com sucesso.").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao criar responsável.").build();
            }
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao criar responsável.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, ResponsavelTO responsavel) {
        try {
            responsavel.setIdResponsavel(id);
            if (responsavelDAO.atualizarResponsavel(responsavel)) {
                return Response.ok("Responsável atualizado com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Responsável não encontrado para atualização.").build();
            }
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao atualizar responsável.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        try {
            if (responsavelDAO.deletarResponsavel(id)) {
                return Response.ok("Responsável removido com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Responsável não encontrado para exclusão.").build();
            }
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao remover responsável.").build();
        }
    }
}
