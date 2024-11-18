package br.com.fiap.resource;

import br.com.fiap.dao.BeneficiarioDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.to.BeneficiarioTO;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.sql.Connection;
import java.util.List;

@Path("/beneficiarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BeneficiarioResource {

    private BeneficiarioDAO beneficiarioDAO;

    public BeneficiarioResource() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            this.beneficiarioDAO = new BeneficiarioDAO(connection);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar o recurso.", e);
        }
    }

    @GET
    @Path("/findAll")
    public Response findAll() {
        try {
            List<BeneficiarioTO> beneficiarios = beneficiarioDAO.findAll();
            return Response.ok(beneficiarios).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao listar todos os beneficiários.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            BeneficiarioTO beneficiario = beneficiarioDAO.buscarPorId(id);
            if (beneficiario == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Beneficiário não encontrado.").build();
            }
            return Response.ok(beneficiario).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao buscar beneficiário.").build();
        }
    }

    @POST
    public Response inserir(BeneficiarioTO beneficiario) {
        try {
            beneficiarioDAO.inserir(beneficiario);
            return Response.status(Response.Status.CREATED).entity("Beneficiário criado com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao criar beneficiário.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, BeneficiarioTO beneficiario) {
        try {
            beneficiario.setIdBeneficiario(id);
            beneficiarioDAO.atualizar(beneficiario);
            return Response.ok("Beneficiário atualizado com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao atualizar beneficiário.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        try {
            beneficiarioDAO.deletar(id);
            return Response.ok("Beneficiário removido com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao remover beneficiário.").build();
        }
    }
}
