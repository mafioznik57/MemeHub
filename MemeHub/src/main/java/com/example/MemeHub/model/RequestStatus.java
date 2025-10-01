package com.example.MemeHub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

public enum RequestStatus {
    PENDING,
    APPROVED,
    DECLINED
}

