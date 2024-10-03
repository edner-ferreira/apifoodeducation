package br.com.apifoodeducation.model;

import jakarta.persistence.*;

import java.util.Set;


public enum Role {
    ROLE_ADMIN,
    ROLE_MEDICO,
    ROLE_USER
}
