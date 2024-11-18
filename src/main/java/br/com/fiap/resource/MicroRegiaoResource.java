package br.com.fiap.resource;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.MicroRegiaoDAO;
import br.com.fiap.to.MicroRegiaoTO;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.sql.Connection;
import java.util.List;

@Path("/microregioes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MicroRegiaoResource {

    private MicroRegiaoDAO microRegiaoDAO;

    public MicroRegiaoResource() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            this.microRegiaoDAO = new MicroRegiaoDAO(connection);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar o recurso.", e);
        }
    }

    @GET
    @Path("/findAll")
    public Response findAll() {
        try {
            List<MicroRegiaoTO> microRegioes = microRegiaoDAO.findAll();
            return Response.ok(microRegioes).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao listar todas as micro-regiões.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            MicroRegiaoTO microRegiao = microRegiaoDAO.buscarPorId(id);
            if (microRegiao == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Micro-região não encontrada.").build();
            }
            return Response.ok(microRegiao).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao buscar micro-região.").build();
        }
    }

    @POST
    public Response inserir(MicroRegiaoTO microRegiao) {
        try {
            microRegiaoDAO.inserir(microRegiao);
            return Response.status(Response.Status.CREATED).entity("Micro-região criada com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao criar micro-região.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, MicroRegiaoTO microRegiao) {
        try {
            microRegiao.setIdMicroRegiao(id);
            microRegiaoDAO.atualizar(microRegiao);
            return Response.ok("Micro-região atualizada com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao atualizar micro-região.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        try {
            microRegiaoDAO.deletar(id);
            return Response.ok("Micro-região removida com sucesso.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao remover micro-região.").build();
        }
    }
}
