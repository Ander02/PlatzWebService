package com.platz.service;

import com.platz.controller.ContaController;
import com.platz.controller.UsuarioController;
import com.platz.http.cadastro.UsuarioCadastro;
import com.platz.http.edicao.UsuarioEdicao;
import com.platz.http.leitura.UsuarioLeitura;
import com.platz.model.ContaModel;
import com.platz.model.Perfil;
import com.platz.model.UsuarioModel;
import com.platz.util.ImagemUtil;
import com.platz.util.PerfilAuth;
import java.io.InputStream;
import java.util.List;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author 15153770
 */
@Path("")
public class UsuarioService {

    private final UsuarioController usuarioController = new UsuarioController();

    @POST
    @Path(value = "/usuario")
    @PermitAll
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(UsuarioCadastro usuario) {
        try {
            //Instanciar uma nova model
            UsuarioModel model = new UsuarioModel(usuario);
            model.getConta().setPerfil(Perfil.USUARIO.ordinal());
            usuarioController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e a Mensagem de Leitura
            return Response.status(Response.Status.CREATED).entity(new UsuarioLeitura(model)).build();

        } catch (Exception e) {

            //Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar usuario").build();
        }
    }

    @GET
    @Path("/usuario/imagem/{id}")
    @PermitAll
    @Produces("image/*")
    public Response baixarImagem(@PathParam("id") String id) {

        try {

            UsuarioModel model = usuarioController.buscarPorId(id);

            if (model != null) {

                if (!model.getImagemPerfil().equals("") && model.getImagemPerfil() != null) {
                    InputStream input = new ImagemUtil().baixarImagem(model.getImagemPerfil());

                    if (input != null) {
                        return Response.ok(input).header("Content-Type", "image/png").build();
                    }
                }
                return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao baixar imagem, imagem inexistente").build();
            }
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao baixar imagem, usuário não encontrada").build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao baixar imagem").build();

        }
    }

    @GET
    @Path(value = "/usuarios")
    @PerfilAuth(Perfil.ADMINISTRADOR)    
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {

        try {
            //Lista com todas as Models cadastradas
            List<UsuarioModel> models = usuarioController.listarTodos();
            //Converter a lista de models para uma lista de leitura
            List<UsuarioLeitura> listaDeUsuarios = new UsuarioLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeUsuarios).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar usuarios").build();
        }
    }

    @GET
    @Path(value = "/usuarios/{nome}")
    @DenyAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloNome(@PathParam("nome") String nome) {

        try {
            //Lista com todas as Models cadastradas
            List<UsuarioModel> models = usuarioController.buscarPeloNome(nome);
            //Converter a lista de models para uma lista de leitura
            List<UsuarioLeitura> listaDeUsuarios = new UsuarioLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeUsuarios).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar usuarios").build();
        }
    }

    @GET
    @Path(value = "/usuario/{id}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        UsuarioModel model = usuarioController.buscarPorId(id);

        //Verifica se a model retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new UsuarioLeitura(model)).build();

        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrada").build();
    }

    @GET
    @Path(value = "/usuario/cpf/{cpf}")
    @DenyAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloCPF(@PathParam("cpf") String cpf) {
        UsuarioModel model = usuarioController.buscarPeloCPF(cpf);

        //Verifica se a model retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new UsuarioLeitura(model)).build();

        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrada").build();
    }

    @GET
    @Path(value = "/usuario/conta/{id}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPelaConta(@PathParam("id") String id) {

        ContaModel conta = new ContaController().buscarPorId(id);

        UsuarioModel model = usuarioController.buscarPelaConta(conta);

        //Verifica se a model retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new UsuarioLeitura(model)).build();

        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Conta não encontrada").build();
    }

    @PUT
    @Path(value = "/usuario/imagem/{id}")
    @PermitAll
    @Consumes(value = MediaType.MULTIPART_FORM_DATA)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response subirImagem(@FormDataParam("imgPerfil") InputStream iconeInputStream,
            @FormDataParam("imgPerfil") FormDataBodyPart fileMetaData,
            @PathParam("id") String id) {

        try {
            //Buscar Model pelo id
            UsuarioModel model = usuarioController.buscarPorId(id);

            if (model != null) {

                //Montando o caminho do upload
                String diretorio = "usuarios/";
                //Montando o nome do arquivo
                String nomeDoArquivo = model.getId() + "." + fileMetaData.getMediaType().getSubtype();

                //Verificar se já existe uma imagem cadastrado
                if (model.getImagemPerfil() != null && !model.getImagemPerfil().equals("")) {

                    boolean ok = new ImagemUtil().deletarArquivo(model.getImagemPerfil());

                    if (ok) {
                        System.out.println("Apagou arquivo antigo");
                    } else {
                        System.out.println("Não Apagou o arquivo antigo");
                        return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao subir imagem").build();
                    }
                }

                //Salvar Imagem
                boolean ok = new ImagemUtil().salvarArquivo(diretorio, nomeDoArquivo, iconeInputStream);

                //Se a imagem for salva sem nenhum erro atualiza a model
                if (ok) {
                    //Settar o caminho do icone na model
                    model.setImagemPerfil(ImagemUtil.URL_FTP + diretorio + nomeDoArquivo);
                    //Alterar
                    usuarioController.alterar(model);
                } else {
                    //Retorna uma BadRequest ao usuário
                    return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao subir imagem").build();
                }

                return Response.ok(new UsuarioLeitura(model)).build();
            }

            System.out.println("Id não existente");
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao subir imagem").build();

        } catch (Exception e) {
            // Envia erro pelo console
            System.out.println("Erro de upload: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao subir imagem").build();
        }
    }

    @PUT
    @Path(value = "/usuario/{id}")
    @PerfilAuth({Perfil.ADMINISTRADOR, Perfil.USUARIO})
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response alterar(@PathParam("id") String id, UsuarioEdicao usuario) {

        try {
            //Settar informações na model
            UsuarioModel model = usuarioController.buscarPorId(id);

            //Alterar registro
            usuarioController.alterar(model, usuario);

            //Retorna Status Code OK com a entity de leitura com a modificação
            return Response.status(Response.Status.OK).entity(new UsuarioLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar empresa").build();
        }
    }
}
