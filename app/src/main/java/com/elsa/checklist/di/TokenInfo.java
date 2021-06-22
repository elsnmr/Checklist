package com.elsa.checklist.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/*
 * Qualifier name untuk parameter ApiInfo
 * */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenInfo {
}
