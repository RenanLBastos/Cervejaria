package com.example.cervejaria.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Map;

@Getter
@Setter
public final class ResourceNotFoundException extends RuntimeException {

    private final int status;
    @Nullable
    private final String reason;

    public ResourceNotFoundException() {

        status = 0;
        reason = null;
    }
    public ResourceNotFoundException(HttpStatus status) {
        this(status, (String) null, (Throwable) null);
    }

    public ResourceNotFoundException(HttpStatus status, @Nullable String reason) {
        this(status, reason, (Throwable) null);
    }

    public ResourceNotFoundException(HttpStatus status, @Nullable String reason, @Nullable Throwable cause) {
        super((String) null, cause);
        Assert.notNull(status, "HttpStatus is required");
        this.status = status.value();
        this.reason = reason;
    }

    public ResourceNotFoundException(int rawStatusCode, @Nullable String reason, @Nullable Throwable cause) {
        super((String) null, cause);
        this.status = rawStatusCode;
        this.reason = reason;
    }

    public HttpStatus getStatus() {
        return HttpStatus.valueOf(this.status);
    }

    public int getRawStatusCode() {
        return this.status;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public Map<String, String> getHeaders() {
        return Collections.emptyMap();
    }

    public HttpHeaders getResponseHeaders() {
        Map<String, String> headers = this.getHeaders();
        if (headers.isEmpty()) {
            return HttpHeaders.EMPTY;
        } else {
            HttpHeaders result = new HttpHeaders();
            this.getHeaders().forEach(result::add);
            return result;
        }
    }

    @Nullable
    public String getReason() {
        return this.reason;
    }

    @Override
    public String getMessage() {
        HttpStatus code = HttpStatus.resolve(this.status);
        String msg = (code != null ? code : this.status) + (this.reason != null ? " \"" + this.reason + "\"" : "");
        return NestedExceptionUtils.buildMessage(msg, this.getCause());
    }
}
