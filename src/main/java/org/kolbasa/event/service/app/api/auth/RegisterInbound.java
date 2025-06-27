package org.kolbasa.event.service.app.api.auth;

import org.kolbasa.event.service.adapter.http.dto.RegisterRequestDto;
import org.kolbasa.event.service.app.api.event.dto.TokenPair;

public interface RegisterInbound {
    TokenPair execute(RegisterRequestDto requestDto);
}
