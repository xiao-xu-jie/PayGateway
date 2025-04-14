package com.xujie.client.core.exceptio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class XOrderException extends RuntimeException {
    private String message;
}
