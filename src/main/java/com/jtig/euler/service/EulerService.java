package com.jtig.euler.service;

import com.jtig.euler.problem.Problem;
import com.jtig.euler.problem.ProblemRegistry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(tags = "euler problem")
@Path("/")
@Component
public class EulerService {

    @GET
    @ApiOperation("Ping the server")
    @ApiResponse(code = 200, message = "Successful Ping", response = PingResponse.class)
    @Path("ping")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping() {

        PingResponse response = PingResponse.builder()
                .message("Pong")
                .build();

        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @ApiOperation("Get Problem Information")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Problem info", response = ProblemInfoResponse.class),
            @ApiResponse(code = 404, message = "Problem undefined")
    })
    @Path("problem/{index}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfo(@PathParam("index") int index) {

        Problem problem = ProblemRegistry.instance.get(index);
        if (problem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ProblemInfoResponse response = ProblemInfoResponse.builder()
                .name(problem.getName())
                .description(problem.getDescription())
                .solved(problem.getSolved())
                .build();

        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @ApiOperation("Run Problem Solution")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Problem info", response = ProblemInfoResponse.class),
            @ApiResponse(code = 404, message = "Problem undefined")
    })
    @Path("problem/{index}/solve")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResult(@PathParam("index") int index) {

        Problem problem = ProblemRegistry.instance.get(index);
        if (problem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(problem).build();
    }

    @Builder
    private static class ProblemInfoResponse {
        @Getter
        private String name;

        @Getter
        private String description;

        @Getter
        private boolean solved;
    }


    @Builder
    private static class PingResponse {
        @Getter
        private String message;
    }
}