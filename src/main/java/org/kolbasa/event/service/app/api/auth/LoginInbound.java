package org.kolbasa.event.service.app.api.auth;

import org.kolbasa.event.service.app.api.event.dto.TokenPair;

public interface LoginInbound {
    TokenPair execute(String login, String password);
}