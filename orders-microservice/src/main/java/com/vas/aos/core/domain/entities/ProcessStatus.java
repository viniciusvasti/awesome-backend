package com.vas.aos.core.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ProcessStatus {
    PENDING, SUCCEEDED, FAILED
}
