package br.com.fiap.resource;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.EnderecoDAO;
import br.com.fiap.to.EnderecoTO;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.sql.Connection;
import java.util.List;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    private EnderecoDAO enderecoDAO;

    public EnderecoResource() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            this.enderecoDAO = new EnderecoDAO(connection);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar o recurso.", e);
        }
    }

    @GET
    @Path("/findAll")
    public Response findAll() {
        try {
            List<EnderecoTO> enderecos = enderecoDAO.findAll();
            return Response.ok(enderecos).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao listar todos os endereços.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            EnderecoTO endereco = enderecoDAO.buscarPorId(id);
            if (endereco == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Endereço não encontrado.").build();
            }
            return Response.ok(endereco).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao buscar endereço.").build();
        }
    }

    @POST
    public Response inserir(EnderecoTO endereco) {
        try {
            if (enderecoDAO.inserirEndereco(endereco)) {
                return Response.status(Response.Status.CREATED).entity("Endereço criado com sucesso.").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao criar endereço.").build();
            }
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao criar endereço.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, EnderecoTO endereco) {
        try {
            endereco.setIdEndereco(id);
            if (enderecoDAO.atualizarEndereco(endereco)) {
                return Response.ok("Endereço atualizado com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Endereço não encontrado para atualização.").build();
            }
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao atualizar endereço.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        try {
            if (enderecoDAO.deletarEndereco(id)) {
                return Response.ok("Endereço removido com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Endereço não encontrado para exclusão.").build();
            }
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao remover endereço.").build();
        }
    }
}
