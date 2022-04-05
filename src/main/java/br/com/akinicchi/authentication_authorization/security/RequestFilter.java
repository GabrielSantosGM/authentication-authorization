package br.com.akinicchi.authentication_authorization.security;

import br.com.akinicchi.authentication_authorization.entity.DataApplication;
import br.com.akinicchi.authentication_authorization.exception.HeaderParamsAuthenticationException;
import br.com.akinicchi.authentication_authorization.exception.MessageErrorEnum;
import br.com.akinicchi.authentication_authorization.exception.response.ResponseBody;
import br.com.akinicchi.authentication_authorization.exception.response.ResponseError;
import br.com.akinicchi.authentication_authorization.service.DataApplicationService;
import br.com.akinicchi.authentication_authorization.utils.ConstantUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class RequestFilter extends OncePerRequestFilter {

    private final DataApplicationService dataApplicationService;

    @Autowired
    public RequestFilter(DataApplicationService dataApplicationService) {
        this.dataApplicationService = dataApplicationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String pathURI = request.getServletPath();
        String clientId = request.getHeader(ConstantUtil.CLIENT_ID);
        String secretId = request.getHeader(ConstantUtil.SECRET_ID);

        if (!pathURI.contains(ConstantUtil.PUBLIC_KEY_URL)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (clientId != null && secretId != null) {
            try {
                DataApplication dataApplication = dataApplicationService.findByIdApplication(clientId, secretId);
                final UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(dataApplication, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } catch (HeaderParamsAuthenticationException e) {
                this.handlerRequestFilter(response, e);
            }
        } else {
            this.handlerRequestFilter(response, null);
        }
    }

    private void handlerRequestFilter(HttpServletResponse response,
                                      HeaderParamsAuthenticationException exception) throws IOException {
        ResponseBody<String> responseBody = new ResponseBody<>();
        ResponseError error = (exception == null)
                ? new ResponseError(MessageErrorEnum.THIRD_ROLE)
                : new ResponseError(exception.getMessageErrorEnum());
        response.setStatus(error.getMessageErrorEnum().getHttpStatus().value());
        response.setContentType(ConstantUtil.CONTENT_TYPE_JSON);
        responseBody.setResponseError(error);
        PrintWriter output = response.getWriter();
        output.print(new ObjectMapper().writeValueAsString(responseBody));
        output.flush();
    }
}
