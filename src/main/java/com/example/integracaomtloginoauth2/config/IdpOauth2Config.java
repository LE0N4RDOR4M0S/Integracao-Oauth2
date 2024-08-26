package com.example.integracaomtloginoauth2.config;

import com.example.integracaomtloginoauth2.exception.OAuthAuthenticationException;
import com.example.integracaomtloginoauth2.data.Message;
import com.example.integracaomtloginoauth2.model.UserPrincipals;
import com.example.integracaomtloginoauth2.repository.UsuarioRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class IdpOauth2Config extends DefaultOAuth2UserService {
    private final UsuarioRepository usuarioRepository;

    public IdpOauth2Config(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest){
        var oauthUser = super.loadUser(userRequest);
        return UserPrincipals.idpOf(oauthUser, usuarioRepository.findByEmail(oauthUser.getAttribute("email"))
                .orElseThrow(() -> new OAuthAuthenticationException(Message.toLocale("auth.idp.error.notfound"))));
    }
}
