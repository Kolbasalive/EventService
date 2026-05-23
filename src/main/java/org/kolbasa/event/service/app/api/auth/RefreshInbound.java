package org.kolbasa.event.service.app.api.auth;

import org.kolbasa.event.service.app.api.event.dto.TokenPair;

public interface RefreshInbound {
    TokenPair execute(String refreshToken);
}
